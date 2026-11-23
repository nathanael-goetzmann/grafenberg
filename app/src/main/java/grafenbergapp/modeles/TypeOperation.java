package grafenbergapp.modeles;

/**
 * La classe décrit l'entité TypeOperation définie par la structure de la base de données Grafenberg.
 * @author Nathanaël Goetzmann
 * @version 1.0
 * @since 2026-11-13
 */
public final class TypeOperation {
    
    /** Représente l'identifiant de l'objet TypeOperation. */
    private int id;
    /** Représente le nom de l'objet TypeOperation. */
    private String nom;
    /** Représente les charges en euros de l'objet TypeOperation. */
    private double charges;
    /** Représente l'objet Bloc associé. */
    private Bloc bloc;
    
    /**
     * Instancie un objet TypeOperation en spécifiant toutes les propriétés.
     * @param id l'identifiant du type d'opération à construire
     * @param nom le nom du type d'opération à construire
     * @param charges les charges en euros du type d'opération à construire
     * @param bloc l'objet Bloc à affecter
     */
    public TypeOperation(int id, String nom, double charges, Bloc bloc) {
        this.id = id;
        this.nom = nom;
        this.charges = charges;
        this.bloc = bloc;
    }
    /** Instancie un objet TypeOperation avec des valeurs par défaut : 0 ou null. */
    public TypeOperation() { }
    
    /**
     * Renvoie l'identifiant de l'objet TypeOperation.
     * @return la propriété id
     */
    public int getId() { return id; }
    /**
     * Renvoie le nom de l'objet TypeOperation.
     * @return la propriété nom
     */
    public String getNom() { return nom; }
    /**
     * Renvoie les charges en euros de l'objet TypeOperation.
     * @return la propriété charges
     */
    public double getCharges() { return charges; }
    /**
     * Renvoie l'objet Bloc associé.
     * @return la propriété bloc
     */
    public Bloc getBloc() { return bloc; }
    
    /**
     * Affecte une nouvelle valeur à l'identifiant de l'objet TypeOperation.
     * @param id le nouvel identifiant
     */
    public void setId(int id) { this.id = id; }
    /**
     * Affecte une nouvelle valeur au nom de l'objet TypeOperation.
     * @param nom le nouveau nom
     */
    public void setNom(String nom) { this.nom = nom; }
    /**
     * Affecte une nouvelle valeur aux charges en euros de l'objet TypeOperation.
     * @param charges les nouvelles charges en euros
     */
    public void setCharges(double charges) { this.charges = charges; }
    /**
     * Affecte un nouveau bloc à l'objet TypeOperation.
     * @param bloc le nouveau bloc
     */
    public void setBloc(Bloc bloc) { this.bloc = bloc; }
}
