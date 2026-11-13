package grafenbergapp.controleurs;

import grafenbergapp.modeles.InterventionDAO;
import grafenbergapp.modeles.Intervention;
import grafenbergapp.modeles.Personnel;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;

/**
 * Contrôleur gérant la logique métier et les opérations liées aux interventions.
 * Fait le lien entre la base de données via InterventionDAO et les objets du modèle.
 * 
 * @author Antoine / Équipe Grafenberg / Équipe Tribord
 * @version 1.0
 */
public final class InterventionControleur {
    
    /** Objet d'accès aux données (DAO) pour les interventions. */
    private final InterventionDAO interventionDAO;
    
    /** Contrôleur gérant les types d'opération. */
    private final TypeOperationControleur typeOperationControleur;
    
    /** Contrôleur gérant le personnel. */
    private final PersonnelControleur personnelControleur; 
    
    /**
     * Constructeur par défaut initialisant les différents contrôleurs et DAO nécessaires.
     * 
     * @throws SQLException en cas d'erreur lors de l'initialisation des connexions ou des DAO.
     */
    public InterventionControleur() throws SQLException {
        interventionDAO = new InterventionDAO();
        typeOperationControleur = new TypeOperationControleur();
        personnelControleur = new PersonnelControleur();
    }
    
    /**
     * Convertit un ResultSet en un objet Intervention complet
     * en y attachant son type d'opération et la liste du personnel associé.
     * 
     * @param resultSetInter Le résultat de la requête SQL contenant les informations de l'intervention.
     * @return L'objet Intervention
     * @throws SQLException en cas d'erreur de lecture du ResultSet ou lors de requêtes en cascade.
     */
    private Intervention createIntervention(ResultSet resultSetInter) throws SQLException {
        var intervention = new Intervention();
                
        intervention.setId(resultSetInter.getInt("inter_id"));
        intervention.setDate(resultSetInter.getDate("inter_date"));
        intervention.setNuit(resultSetInter.getBoolean("inter_nuit"));
        intervention.setDuree(resultSetInter.getInt("inter_duree"));

        var typeOperation = typeOperationControleur.getOneTypeOperation(
                resultSetInter.getInt("inter_top_id")
        );
        intervention.setTypeOperation(typeOperation);
        
        try (var resultSetPers = interventionDAO.selectTiedPersonnel(intervention.getId());) {
            var lesPersonnel = new ArrayList<Personnel>();
            
            while (resultSetPers.next()) {
                lesPersonnel.add(
                        personnelControleur.getOnePersonnel(resultSetPers.getInt("aff_per_id"))
                );
            }
            
            intervention.setLesPersonnel(lesPersonnel);
        }
        
        return intervention;
    }
    
    /**
     * Récupère la liste de toutes les interventions enregistrées dans la base de données.
     * 
     * @return ArrayList contenant l'ensemble des Intervention.
     * @throws SQLException en cas d'erreur d'accès à la base de données.
     */
    public ArrayList<Intervention> getAllIntervention() throws SQLException {
        try (var resultSetInter = interventionDAO.selectAllIntervention();) {
            var lesInterventions = new ArrayList<Intervention>();
            
            while (resultSetInter.next()) {
                lesInterventions.add(createIntervention(resultSetInter));
            }
            
            return lesInterventions;
        }
    }
    
    /**
     * Récupère une intervention spécifique grâce à son identifiant unique.
     * 
     * @param id L'identifiant unique de l'intervention recherchée.
     * @return Intervention correspondant ou null s'il n'existe pas.
     * @throws SQLException en cas d'erreur d'accès à la base de données.
     */
    public Intervention getOneIntervention(int id) throws SQLException {
        try (var resultSetInter = interventionDAO.selectOneIntervention(id);) {
            return resultSetInter.next() ? createIntervention(resultSetInter) : null;
        }
    }
    
    /**
     * Crée et insère une nouvelle intervention dans la base de données avec son personnel affecté.
     * 
     * @param date La date de l'intervention.
     * @param nuit Un booléen indiquant si l'intervention s'est déroulée de nuit.
     * @param duree La durée de l'intervention (en minutes ou heures selon le modèle).
     * @param typeOperationId L'identifiant du type d'opération associé.
     * @param lesPersonnelIds Une liste d'identifiants représentant le personnel participant à l'intervention.
     * @throws SQLException en cas d'erreur lors de l'insertion en base de données.
     */
    public void postIntervention(
            Date date,
            boolean nuit,
            int duree,
            int typeOperationId,
            ArrayList<Integer> lesPersonnelIds
    ) throws SQLException {
        var lesPersonnel = new ArrayList<Personnel>();
        for (int id : lesPersonnelIds) {
            lesPersonnel.add(personnelControleur.getOnePersonnel(id));
        }
        
        var intervention = new Intervention(
                0,
                date,
                nuit,
                duree,
                typeOperationControleur.getOneTypeOperation(typeOperationId),
                lesPersonnel
        );
        interventionDAO.insertIntervention(intervention);
    }
    
    public void afficherCoutIntervention(int id) throws SQLException {
        if (getOneIntervention(id) != null) {
            System.out.println(interventionDAO.callCalculerCoutIntervention(id) + " euros");
        } else {
            System.out.println("L'intervention spécifiée est introuvable.");
        }
    }
}
