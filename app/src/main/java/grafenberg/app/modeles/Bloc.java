package grafenberg.app.modeles;

/**
 * Ce fichier propose une classe qui représente
 * l'entité « bloc » du diagramme de classes.
 * @author Nathanaël Goetzmann
 * @version v1.0
 * @since 2026-10-16
 */

public final class Bloc {
    
    /**
     * Les instances de la classe n'ont pas connaissance
     * des autres entités.
     */
    
    private int id;
    private double charges;
    
    /**
     * Crée une instance à destination du package « controleurs ».
     * @param id représente l'identifiant du bloc opératoire
     * @param charges représente le coût du bloc en euros
     */
    public Bloc(int id, double charges) {
        this.id = id;
        this.charges = charges;
    }
    
    /**
     * Crée une instance avec des valeurs nulles par défaut.
     */
    public Bloc() { this(0, 0); }
    
    /**
     * Renvoie la valeur de l'identifiant du bloc opératoire.
     * @return int
     */
    public int getId() { return id; }
    
    /**
     * Renvoie la valeur du coût du bloc en euros.
     * @return double
     */
    public double getCharges() { return charges; }
    
    /**
     * Définit une nouvelle valeur pour l'identifiant.
     * @param id représente l'identifiant du bloc opératoire
     */
    public void setId(int id) { this.id = id; }

    /**
     * Définit une nouvelle valeur pour les charges en euros.
     * @param charges représente le coût du bloc en euros
     */
    public void setCharges(double charges) { this.charges = charges; }
}