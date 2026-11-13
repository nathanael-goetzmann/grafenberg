package grafenbergapp.modeles;

/**
 * La classe décrit l'entité Bloc définie par la structure de la base de données Grafenberg.
 * @author Nathanaël Goetzmann
 * @version 1.0
 * @since 2026-11-13
 */
public final class Bloc {
    
    /** Représente l'identifiant de l'objet Bloc. */
    private int id;
    /** Représente les charges en euros de l'objet Bloc. */
    private double charges;
    
    /**
     * Instancie un objet Bloc en spécifiant toutes les propriétés.
     * @param id l'identifiant du bloc à construire
     * @param charges les charges en euros du bloc à construire
     */
    public Bloc(int id, double charges) {
        this.id = id;
        this.charges = charges;
    }
    /** Instancie un objet Bloc avec des valeurs nulles. */
    public Bloc() { }
    
    /**
     * Renvoie l'identifiant de l'objet bloc.
     * @return la propriété id
     */
    public int getId() { return id; }
    /**
     * Renvoie les charges en euros de l'objet bloc.
     * @return la propriété charges
     */
    public double getCharges() { return charges; }
    
    /**
     * Affecte une nouvelle valeur à l'identifiant de l'objet Bloc.
     * @param id le nouvel identifiant
     */
    public void setId(int id) { this.id = id; }
    /**
     * Affecte une nouvelle valeur aux charges en euros de l'objet Bloc.
     * @param charges les nouvelles charges en euros
     */
    public void setCharges(double charges) { this.charges = charges; }
}
