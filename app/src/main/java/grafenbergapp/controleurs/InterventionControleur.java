package grafenbergapp.controleurs;

import grafenbergapp.modeles.InterventionDAO;
import grafenbergapp.modeles.Intervention;
import grafenbergapp.modeles.Personnel;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;

public final class InterventionControleur {
    
    private final InterventionDAO interventionDAO;
    private final TypeOperationControleur typeOperationControleur;
    private final PersonnelControleur personnelControleur; 
    
    public InterventionControleur() throws SQLException {
        interventionDAO = new InterventionDAO();
        typeOperationControleur = new TypeOperationControleur();
        personnelControleur = new PersonnelControleur();
    }
    
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
    
    public ArrayList<Intervention> getAllIntervention() throws SQLException {
        try (var resultSetInter = interventionDAO.selectAllIntervention();) {
            var lesInterventions = new ArrayList<Intervention>();
            
            while (resultSetInter.next()) {
                lesInterventions.add(createIntervention(resultSetInter));
            }
            
            return lesInterventions;
        }
    }
    
    public void afficherAllIntervention() throws SQLException {
        var lesInterventions = getAllIntervention();
        
        if (lesInterventions.isEmpty()) {
            System.out.println("\nAucune intervention n'est mémorisée à l'heure actuelle.");
        } else {
            System.out.println("\nVoici toutes les interventions mémorisées : ");
            for (var intervention : lesInterventions) { intervention.afficher(); }
        }
    }
    
    public Intervention getOneIntervention(int id) throws SQLException {
        try (var resultSetInter = interventionDAO.selectOneIntervention(id);) {
            return resultSetInter.next() ? createIntervention(resultSetInter) : null;
        }
    }
    
    public void afficherOneIntervention(int id) throws SQLException {
        var intervention = getOneIntervention(id);
        
        if (intervention == null) {
            System.out.println("\nCette intervention n'existe pas.");
        } else {
            System.out.println("\nVoici ses informations : ");
            intervention.afficher();
        }
    }
    
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
        if (getOneIntervention(id) == null) {
            System.out.println("\nL'intervention souhaitée n'existe pas.");
        } else {
            System.out.printf(
                    "\nSon coût de revient est de %.2f euros.\n",
                    interventionDAO.callCalculerCoutIntervention(id)
            );
        }
    }
}
