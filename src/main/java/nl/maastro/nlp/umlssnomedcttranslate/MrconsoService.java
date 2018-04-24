package nl.maastro.nlp.umlssnomedcttranslate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;

@Service
@EnableConfigurationProperties(LookupProperties.class)
public class MrconsoService {

    private MrconsoRepository mrconsoRepository;

    private LookupProperties lookupProperties;

    private Logger logger = LoggerFactory.getLogger(MrconsoService.class);

    public MrconsoService(MrconsoRepository mrconsoRepository, LookupProperties lookupProperties) {
        this.mrconsoRepository = mrconsoRepository;
        this.lookupProperties = lookupProperties;
    }

    public void indexMrconso(String fileName){
        try (Stream<String> stream = lines(Paths.get(fileName))) {
            stream.forEach(line -> {
                MrconsoEntity entity = getMrconsoEntity(line);
                if(mrconsoRepository.findByScui(entity.getScui())==null)
                    mrconsoRepository.save(entity);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMrconsoReplace(String snomedFile, String outputPath) throws IOException {

        long lineCount = lines(Paths.get(snomedFile)).count();
        logger.error(snomedFile+" lineCount=" + lineCount);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputPath))) {

            AtomicInteger counter = new AtomicInteger(0);

            try (Stream<String> stream = lines(Paths.get(snomedFile))) {
                stream.forEach(line -> {

                    int count = counter.incrementAndGet();
                    if((count % 1000) == 0)
                        logger.info("line "+ count);

                    String[] snomedLine = splitSnomedLine(line);
                    if(snomedLine[lookupProperties.getIndexSnomedLanguage()].equals(lookupProperties.getSnomedLanguage())) {

                        String conceptIdString = snomedLine[lookupProperties.getIndexSnomedConceptId()];
                        try{
                            Long conceptId = Long.parseLong(conceptIdString);
                            MrconsoEntity mrconsoEntity = mrconsoRepository.findByScui(conceptId);

                            if(mrconsoEntity!=null) {
                                String[] mrConsoLine = mrconsoEntity.getLine().split("\\|");
                                try {
                                    writer.write(joinMrconsoLine(getMrconsoLineReplaced(mrConsoLine, snomedLine)));
                                    writer.newLine();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            else{
                                logger.warn("ConceptId not present in index:" + conceptId);
                            }
                        }
                        catch(Exception e){
                            logger.error("Error parsing conceptId=" + conceptIdString);
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private MrconsoEntity getMrconsoEntity(String line){
        String[] splitLine = line.split("\\|");
        MrconsoEntity mrconsoEntity = new MrconsoEntity();
        mrconsoEntity.setCui(splitLine[lookupProperties.getIndexMrconsoCui()]);
        try {

            mrconsoEntity.setScui( Long.parseLong(splitLine[lookupProperties.getIndexMrconsoScui()]));
        }
        catch (NumberFormatException e){
            logger.error("Cannot set number for scui", e.getMessage() );
        }
        mrconsoEntity.setLine(line);
        return mrconsoEntity;
    }

    private String[] splitSnomedLine(String line){
        return line.split("\\t");
    }

    private String joinMrconsoLine(String[] mrConsoLine){
        return String.join("|", mrConsoLine);
    }


    private String[] getMrconsoLineReplaced(String[] mrConsoLine, String[] snomedLine){
        for (Map.Entry<Integer, Integer> entry : lookupProperties.getReplaceMap().entrySet()) {
            mrConsoLine[entry.getKey()] = snomedLine[entry.getValue()];
        }
        mrConsoLine[lookupProperties.getIndexMrconsoLanguage()] = lookupProperties.getMrconsoLanguage();
        mrConsoLine[lookupProperties.getIndexMrconsoSab()] = lookupProperties.getMrconsoSabPrefix()+lookupProperties.getMrconsoLanguage();
        return mrConsoLine;
    }

}
