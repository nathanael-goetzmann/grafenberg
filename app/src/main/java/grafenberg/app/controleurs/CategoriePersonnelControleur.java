package grafenberg.app.controleurs;

import grafenberg.app.modeles.CategoriePersonnelDAO;
import grafenberg.app.modeles.CategoriePersonnel;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;

public final class CategoriePersonnelControleur {
    
    private final CategoriePersonnelDAO categoriePersonnelDAO;
    
    public CategoriePersonnelControleur() {
        categoriePersonnelDAO = new CategoriePersonnelDAO();
    }
    
    private CategoriePersonnel createCategoriePersonnel(
            ResultSet resultSetCat
    ) throws SQLException {
        var categoriePersonnel = new CategoriePersonnel();
                
        categoriePersonnel.setCode(resultSetCat.getString("cat_code"));
        categoriePersonnel.setCoutHoraire(resultSetCat.getDouble("cat_cout_horaire"));
        
        return categoriePersonnel;
    }
    
    public ArrayList<CategoriePersonnel> getAllCategoriePersonnel() throws SQLException {
        try (var resultSetCat = categoriePersonnelDAO.selectAllCategoriePersonnel();) {
            var lesCategoriesPersonnel = new ArrayList<CategoriePersonnel>();
            
            while (resultSetCat.next()) {
                lesCategoriesPersonnel.add(createCategoriePersonnel(resultSetCat));
            }
            
            return lesCategoriesPersonnel;
        }
    }
    
    public CategoriePersonnel getOneCategoriePersonnel(String code) throws SQLException {
        try (var resultSetCat = categoriePersonnelDAO.selectOneCategoriePersonnel(code);) {
            return resultSetCat.next() ? createCategoriePersonnel(resultSetCat) : null;
        }
    }
}
