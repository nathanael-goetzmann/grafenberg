package grafenbergapp.modeles;

public final class Personnel {
    
    private int id;
    private String nom;
    private CategoriePersonnel categoriePersonnel;
    
    public Personnel(int id, String nom, CategoriePersonnel categoriePersonnel) {
        this.id = id;
        this.nom = nom;
        this.categoriePersonnel = categoriePersonnel;
    }
    public Personnel() { }
    
    public int getId() { return id; }
    public String getNom() { return nom; }
    public CategoriePersonnel getCategoriePersonnel() { return categoriePersonnel; }
    
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setCategoriePersonnel(CategoriePersonnel categoriePersonnel) {
        this.categoriePersonnel = categoriePersonnel;
    }
}
