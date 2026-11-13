package grafenbergapp.modeles;

import java.util.ArrayList;
import java.sql.Date;

/**
 * Représente une intervention réalisée, caractérisée par son identifiant,
 * sa date, sa période (jour/nuit), sa durée, le type d'opération associé
 * ainsi que le personnel y ayant participé.
 * 
 * 
 * @author Antoine / Équipe Grafenberg / Équipe Tribord
 * @version 1.0
 */

public final class Intervention {
    
    /** L'identifiant unique de l'intervention. */
    private int id;
    
    /** La date à laquelle a eu lieu l'intervention. */
    private Date date;
    
    /** Indique si l'intervention s'est déroulée de nuit (vrai) ou de jour (faux). */
    private boolean nuit;
    
    /** La durée de l'intervention (exprimée en minutes). */
    private int duree;
    
    /** Le type d'opération lié à cette intervention. */
    private TypeOperation typeOperation;
    
    /** La liste du personnel participant à cette intervention. */
    private ArrayList<Personnel> lesPersonnel;
    
    /**
     * Construit une nouvelle instance code Intervention avec tous ses attributs.
     * 
     * @param id L'identifiant unique de l'intervention
     * @param date La date de l'intervention
     * @param nuit Vrai si l'intervention est de nuit, faux sinon
     * @param duree La durée de l'intervention en minutes
     * @param typeOperation Le type d'opération concerné
     * @param lesPersonnel La liste du personnel présent lors de l'intervention
     */
    public Intervention(
            int id,
            Date date,
            boolean nuit,
            int duree,
            TypeOperation typeOperation,
            ArrayList<Personnel> lesPersonnel
    ) {
        this.id = id;
        this.date = date;
        this.nuit = nuit;
        this.duree = duree;
        this.typeOperation = typeOperation;
        this.lesPersonnel = lesPersonnel;
    }
    
    /** Construit une instance vide code Intervention */
    public Intervention() { }
    
    /**
     * Récupère l'identifiant de l'intervention.
     * 
     * @return L'identifiant sous forme d'entier
     */
    public int getId() { return id; }

    /**
     * Récupère la date de l'intervention.
     * 
     * @return La date de l'intervention
     */
    public Date getDate() { return date; }

    /**
     * Vérifie si l'intervention s'est déroulée de nuit.
     * 
     * @return Vrai si c'est une intervention de nuit, faux sinon
     */
    public boolean getNuit() { return nuit; }

    /**
     * Récupère la durée de l'intervention.
     * 
     * @return La durée en minutes
     */
    public int getDuree() { return duree; }

    /**
     * Récupère le type d'opération de l'intervention.
     * 
     * @return Le type d'opération
     */
    public TypeOperation getTypeOperation() { return typeOperation; }

    /**
     * Récupère la liste du personnel présent lors de l'intervention.
     * 
     * @return Une liste d'objets {@link Personnel}
     */
    public ArrayList<Personnel> getLesPersonnel() { return lesPersonnel; }
    
     /**
     * Modifie la liste du personnel présent lors de l'intervention.
     * 
     * @param lesPersonnel La nouvelle liste du personnel
     */
    public void setLesPersonnel(ArrayList<Personnel> lesPersonnel) {
        this.lesPersonnel = lesPersonnel;
    }
    
    /**
     * Modifie l'identifiant de l'intervention.
     * 
     * @param id Le nouvel identifiant à attribuer
     */
    public void setId(int id) { this.id = id; }

    /**
     * Modifie la date de l'intervention.
     * 
     * @param date La nouvelle date à attribuer
     */
    public void setDate(Date date) { this.date = date; }

    /**
     * Modifie le caractère nocturne ou diurne de l'intervention.
     * 
     * @param nuit Vrai pour une intervention de nuit, faux pour le jour
     */
    public void setNuit(boolean nuit) { this.nuit = nuit; }

    /**
     * Modifie la durée de l'intervention.
     * 
     * @param duree La nouvelle durée en minutes
     */
    public void setDuree(int duree) { this.duree = duree; }

    /**
     * Modifie le type d'opération de l'intervention.
     * 
     * @param typeOperation Le nouveau type d'opération
     */
    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }
    
    /**
     * Affiche dans la console les détails complets de l'intervention
     * (numéro, type d'opération, période, date, durée) ainsi que 
     * la liste du personnel présent.
     */
    public void afficher() {
        String periode = nuit ? "De nuit le " : "De jour le ";
        System.out.println("\n[N°" + id + ", " + typeOperation.getNom() + "]");
        System.out.println(periode + date + ", d'une durée de " + duree + " minutes.");
        System.out.println("Liste du personnel présent :");
        
        for (var personnel : lesPersonnel) {
            System.out.println("\t* " + personnel.getNom());
        }
    }
}
