package grafenberg.app.modeles;

public final class TypeOperation {
    
    private int id;
    private String nom;
    private double charges;
    private Bloc bloc;
    
    public TypeOperation(int id, String nom, double charges, Bloc bloc) {
        this.id = id;
        this.nom = nom;
        this.charges = charges;
        this.bloc = bloc;
    }
    public TypeOperation() { }
    
    public int getId() { return id; }
    public String getNom() { return nom; }
    public double getCharges() { return charges; }
    public Bloc getBloc() { return bloc; }
    
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setCharges(double charges) { this.charges = charges; }
    public void setBloc(Bloc bloc) { this.bloc = bloc; }
}
