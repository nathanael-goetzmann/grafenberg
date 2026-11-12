package grafenbergapp.modeles;

public final class CategoriePersonnel {
    
    private String code;
    private double coutHoraire;
    
    public CategoriePersonnel(String code, double coutHoraire) {
        this.code = code;
        this.coutHoraire = coutHoraire;
    }
    public CategoriePersonnel() { }
    
    public String getCode() { return code; }
    public double getCoutHoraire() { return coutHoraire; }
    
    public void setCode(String code) { this.code = code; }
    public void setCoutHoraire(double coutHoraire) { this.coutHoraire = coutHoraire; }
}
