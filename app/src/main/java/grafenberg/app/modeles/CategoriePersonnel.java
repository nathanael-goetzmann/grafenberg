package grafenberg.app.modeles;

public class CategoriePersonnel {
    private String cat_code;
    private int cat_cout_horaire;

    public CategoriePersonnel(String cat_code, int cat_cout_horaire) {
        this.cat_code = cat_code;
        this.cat_cout_horaire = cat_cout_horaire;
    }
    
    public String getCat_code() {
        return cat_code;
    }
    public void setCat_code(String cat_code) {
        this.cat_code = cat_code;
    }
    public int getCat_cout_horaire() {
        return cat_cout_horaire;
    }
    public void setCat_cout_horaire(int cat_cout_horaire) {
        this.cat_cout_horaire = cat_cout_horaire;
    }
    
}
