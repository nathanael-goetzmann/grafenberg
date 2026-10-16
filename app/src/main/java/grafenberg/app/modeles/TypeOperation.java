package grafenberg.app.modeles;

/**
 * Ce fichier propose une classe qui représente
 * l'entité « type_operation » du diagramme de classes.
 * @author Nathanaël Goetzmann
 * @version v1.0
 * @since 2026-10-16
 */

public final class TypeOperation {
    
    /**
     * En plus des propriétés « id » et « charges »,
     * chaque instance a connaissance d'un objet « Bloc ».
     */
    
    private int id;
    private double charges;
    private Bloc bloc;
    
    /**
     * Crée une instance à destination du package « controleurs ».
     * @param id représente l'identifiant du type d'opération
     * @param charges représente le coût de l'opération en euros
     * @param bloc représente le bloc opératoire associé
     */
    public TypeOperation(int id, double charges, Bloc bloc) {
        this.id = id;
        this.charges = charges;
        this.bloc = bloc;
    }
    
    /**
     * Crée une instance avec des valeurs par défaut : 0 ou null.
     */
    public TypeOperation() { this(0, 0, null); }
    
    /**
     * Renvoie la valeur de l'identifiant de l'opération.
     * @return int
     */
    public int getId() { return id; }
    
    /**
     * Renvoie la valeur du coût de l'opération en euros.
     * @return double
     */
    public double getCharges() { return charges; }
    
    /**
     * Renvoie la référence du bloc opératoire associé.
     * @return Bloc
     */
    public Bloc getBloc() { return bloc; }
    
    /**
     * Définit une nouvelle valeur pour l'identifiant.
     * @param id représente l'identifiant du type d'opération
     */
    public void setId(int id) { this.id = id; }

    /**
     * Définit une nouvelle valeur pour les charges en euros.
     * @param charges représente le coût de l'opération en euros
     */
    public void setCharges(double charges) { this.charges = charges; }

    /**
     * Définit une nouvelle valeur pour le bloc opératoire associé.
     * @param bloc représente le bloc opératoire associé
     */
    public void setBloc(Bloc bloc) { this.bloc = bloc; }
}
