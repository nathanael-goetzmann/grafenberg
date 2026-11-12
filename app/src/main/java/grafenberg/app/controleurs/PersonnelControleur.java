package grafenberg.app.controleurs;

import grafenberg.app.modeles.PersonnelDAO;
import grafenberg.app.modeles.Personnel;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;

public final class PersonnelControleur {
    
    private final PersonnelDAO personnelDAO;
    private final CategoriePersonnelControleur categoriePersonnelControleur;
    
    public PersonnelControleur() {
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
    
    public Personnel getOnePersonnel(int id) throws SQLException {
        try (var resultSetPers = personnelDAO.selectOnePersonnel(id);) {
            return resultSetPers.next() ? createPersonnel(resultSetPers) : null;
        }
    }
}
