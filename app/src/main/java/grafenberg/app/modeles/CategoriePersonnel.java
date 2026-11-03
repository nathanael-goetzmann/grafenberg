package grafenberg.app.modeles;

public class CategoriePersonnel {
    private String cat_code;
    private int cat_couthoraire;

    public CategoriePersonnel(String cat_code, int cat_couthoraire) {
        this.cat_code = cat_code;
        this.cat_couthoraire = cat_couthoraire;
    }
    
    public String getCat_code() {
        return cat_code;
    }
    public void setCat_code(String cat_code) {
        this.cat_code = cat_code;
    }
    public int getcat_couthoraire() {
        return cat_couthoraire;
    }
    public void setcat_couthoraire(int cat_couthoraire) {
        this.cat_couthoraire = cat_couthoraire;
    }
    
}
