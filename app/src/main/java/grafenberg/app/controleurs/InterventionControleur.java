package grafenberg.app.controleurs;

import grafenberg.app.modeles.InterventionDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class InterventionControleur {
    private InterventionDAO interventionDAO;

    public InterventionControleur() {
        try {
            this.interventionDAO = new InterventionDAO();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'initialisation du DAO : " + e.getMessage());
        }
    }

    public void insererIntervention(Date date, int nuit, int duree, String topNum) {
        try {
            boolean success = interventionDAO.insererIntervention(date, nuit, duree, topNum);
            if (success) {
                System.out.println("Intervention insérée avec succès.");
            } else {
                System.out.println("Échec de l'insertion de l'intervention.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion");
        }
    }

public void listerToutesLesInterventions() {
        try {
            ResultSet rs = interventionDAO.getAllInterventions();
            
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Date: " + rs.getDate("date") + " | Durée: " + rs.getInt("duree") + " min");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération");
        }
    }
}