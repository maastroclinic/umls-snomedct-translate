package nl.maastro.nlp.umlssnomedcttranslate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SnomedUmlsLookupApplicationTests {

	@Autowired
	private MrconsoService mrconsoService;

	private String mrconsoPath = "D:\\UMLS-SNOMED_US\\2017AB\\META\\MRCONSO.RRF";
	private String snomedPath = "D:\\nlp\\SNOMED CT - Netherlands Edition 31 March 2018\\SnomedCT_Netherlands_EditionRelease_20180331\\Full\\Terminology\\sct2_Description_Full_NL_20180331.txt";
	private String mrconsoReplacePath = "D:\\UMLS-SNOMED_US\\2017AB\\META\\MRCONSO_NL.RRF";

	@Test
	public void writeMrconsoReplaced() throws IOException {
		mrconsoService.indexMrconso(mrconsoPath);
		mrconsoService.writeMrconsoReplace(snomedPath, mrconsoReplacePath);
	}
}
