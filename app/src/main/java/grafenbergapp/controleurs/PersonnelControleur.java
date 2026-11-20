package grafenbergapp.controleurs;

import grafenbergapp.modeles.PersonnelDAO;
import grafenbergapp.modeles.Personnel;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;

public final class PersonnelControleur {
    
    private final PersonnelDAO personnelDAO;
    private final CategoriePersonnelControleur categoriePersonnelControleur;
    
    public PersonnelControleur() throws SQLException {
        personnelDAO = new PersonnelDAO();
        categoriePersonnelControleur = new CategoriePersonnelControleur();
    }
    
    private Personnel createPersonnel(ResultSet resultSetPers) throws SQLException {
        var personnel = new Personnel();
                
        personnel.setId(resultSetPers.getInt("per_id"));
        personnel.setNom(resultSetPers.getString("per_nom"));

        var categoriePersonnel = categoriePersonnelControleur.getOneCategoriePersonnel(
                resultSetPers.getString("per_cat_code")
        );
        personnel.setCategoriePersonnel(categoriePersonnel);

        return personnel;
    }
    
    public ArrayList<Personnel> getAllPersonnel() throws SQLException {
        try (var resultSetPers = personnelDAO.selectAllPersonnel();) {
            var lesPersonnel = new ArrayList<Personnel>();
            
            while (resultSetPers.next()) {
                lesPersonnel.add(createPersonnel(resultSetPers));
            }
            
            return lesPersonnel;
        }
    }
    
    public void afficherAllPersonnel() throws SQLException {
        var lesPersonnel = getAllPersonnel();
        
        if (lesPersonnel.isEmpty()) {
            System.out.println("\nAucun personnel n'est mémorisé à l'heure actuelle.");
        } else {
            System.out.println("\nVoici tout le personnel mémorisé : ");
            for (var personnel : lesPersonnel) { personnel.afficher(); }
        }
    }
    
    public Personnel getOnePersonnel(int id) throws SQLException {
        try (var resultSetPers = personnelDAO.selectOnePersonnel(id);) {
            return resultSetPers.next() ? createPersonnel(resultSetPers) : null;
        }
    }
    
    public void afficherOnePersonnel(int id) throws SQLException {
        var personnel = getOnePersonnel(id);
        
        if (personnel == null) {
            System.out.println("\nCe membre du personnel n'existe pas.");
        } else {
            System.out.println("\nVoici ses informations : ");
            personnel.afficher();
        }
    }
}
