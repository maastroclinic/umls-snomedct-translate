package nl.maastro.nlp.umlssnomedcttranslate;

import javax.persistence.*;


@Entity
@Table(indexes = { @Index(name = "IDX_SCUI", columnList = "scui") })
public class MrconsoEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String cui;

    @Column(name="scui", unique = true)
    private Long scui;

    @Column(length=1024)
    private String line;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public Long getScui() {
        return scui;
    }

    public void setScui(Long scui) {
        this.scui = scui;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
