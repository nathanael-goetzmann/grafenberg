package grafenberg.app.modeles;


public class Personnel {

    private short per_num;
    private String per_nom;
    private String per_cat_code;

    public Personnel(short per_num, String per_nom, String per_cat_code) {
        this.per_num = per_num;
        this.per_nom = per_nom;
        this.per_cat_code = per_cat_code;
    }

    public Personnel(String per_nom, String per_cat_code) {
        this.per_nom = per_nom;
        this.per_cat_code = per_cat_code;
    }

    public short getPer_num() {
        return per_num;
    }

    public String getPer_nom() {
        return per_nom;
    }

    public String getPer_cat_code() {
        return per_cat_code;
    }

    public void setPer_num(short per_num) {
        this.per_num = per_num;
    }

    public void setPer_nom(String per_nom) {
        this.per_nom = per_nom;
    }

    public void setPer_cat_code(String per_cat_code) {
        this.per_cat_code = per_cat_code;
    }
}
