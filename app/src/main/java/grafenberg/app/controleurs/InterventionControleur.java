package grafenberg.app.controleurs;

import grafenberg.app.modeles.InterventionDAO;
import grafenberg.app.modeles.Intervention;
import grafenberg.app.modeles.Personnel;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;

public final class InterventionControleur {
    
    private final InterventionDAO interventionDAO;
    private final TypeOperationControleur typeOperationControleur;
    private final PersonnelControleur personnelControleur; 
    
    public InterventionControleur() {
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

        var lesPersonnel = new ArrayList<Personnel>();
        var resultSetPers = interventionDAO.selectTiedPersonnel(intervention.getId());
        while (resultSetPers.next()) {
            lesPersonnel.add(
                    personnelControleur.getOnePersonnel(resultSetPers.getInt("aff_per_id"))
            );
        }
        intervention.setLesPersonnel(lesPersonnel);
        
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
    
    public Intervention getOneIntervention(int id) throws SQLException {
        try (var resultSetInter = interventionDAO.selectOneIntervention(id);) {
            return resultSetInter.next() ? createIntervention(resultSetInter) : null;
        }
    }
    
    public void postOneIntervention(
            Date date,
            boolean nuit,
            int duree,
            int typeOperationId,
            ArrayList<Integer> lesPersonnelId
    ) throws SQLException {
        var lesPersonnel = new ArrayList<Personnel>();
        for (int id : lesPersonnelId) {
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
        interventionDAO.insertOneIntervention(intervention);
    }
}
