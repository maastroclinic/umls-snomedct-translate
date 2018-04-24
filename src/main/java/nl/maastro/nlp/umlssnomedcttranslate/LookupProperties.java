package nl.maastro.nlp.umlssnomedcttranslate;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties("lookup")
public class LookupProperties {

    private Map<Integer, Integer> replaceMap;

    private int indexMrconsoCui = 0;

    private int indexMrconsoScui = 9;

    private int indexMrconsoLanguage = 1;

    private int indexMrconsoSab = 11;

    private String mrconsoSabPrefix = "SNOMEDCT_";

    private int indexSnomedConceptId = 4;

    private int indexSnomedLanguage = 5;

    private String snomedLanguage = "nl";

    private String mrconsoLanguage = "DUT";



    public int getIndexMrconsoCui() {
        return indexMrconsoCui;
    }

    public void setIndexMrconsoCui(int indexMrconsoCui) {
        this.indexMrconsoCui = indexMrconsoCui;
    }

    public int getIndexMrconsoScui() {
        return indexMrconsoScui;
    }

    public void setIndexMrconsoScui(int indexMrconsoScui) {
        this.indexMrconsoScui = indexMrconsoScui;
    }

    public int getIndexSnomedConceptId() {
        return indexSnomedConceptId;
    }

    public void setIndexSnomedConceptId(int indexSnomedConceptId) {
        this.indexSnomedConceptId = indexSnomedConceptId;
    }

    public Map<Integer, Integer> getReplaceMap() {
        return replaceMap;
    }

    public void setReplaceMap(Map<Integer, Integer> replaceMap) {
        this.replaceMap = replaceMap;
    }

    public int getIndexMrconsoLanguage() {
        return indexMrconsoLanguage;
    }

    public void setIndexMrconsoLanguage(int indexMrconsoLanguage) {
        this.indexMrconsoLanguage = indexMrconsoLanguage;
    }

    public int getIndexSnomedLanguage() {
        return indexSnomedLanguage;
    }

    public void setIndexSnomedLanguage(int indexSnomedLanguage) {
        this.indexSnomedLanguage = indexSnomedLanguage;
    }

    public String getSnomedLanguage() {
        return snomedLanguage;
    }

    public void setSnomedLanguage(String snomedLanguage) {
        this.snomedLanguage = snomedLanguage;
    }

    public String getMrconsoLanguage() {
        return mrconsoLanguage;
    }

    public void setMrconsoLanguage(String mrconsoLanguage) {
        this.mrconsoLanguage = mrconsoLanguage;
    }

    public int getIndexMrconsoSab() {
        return indexMrconsoSab;
    }

    public void setIndexMrconsoSab(int indexMrconsoSab) {
        this.indexMrconsoSab = indexMrconsoSab;
    }

    public String getMrconsoSabPrefix() {
        return mrconsoSabPrefix;
    }

    public void setMrconsoSabPrefix(String mrconsoSabPrefix) {
        this.mrconsoSabPrefix = mrconsoSabPrefix;
    }
}
