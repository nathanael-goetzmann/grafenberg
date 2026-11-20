package grafenbergapp.controleurs;

import grafenbergapp.modeles.CategoriePersonnelDAO;
import grafenbergapp.modeles.CategoriePersonnel;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;

public final class CategoriePersonnelControleur {
    
    private final CategoriePersonnelDAO categoriePersonnelDAO;
    
    public CategoriePersonnelControleur() throws SQLException {
        categoriePersonnelDAO = new CategoriePersonnelDAO();
    }
    
    private CategoriePersonnel createCategoriePersonnel(
            ResultSet resultSetCatPers
    ) throws SQLException {
        var categoriePersonnel = new CategoriePersonnel();
                
        categoriePersonnel.setCode(resultSetCatPers.getString("cat_code"));
        categoriePersonnel.setCoutHoraire(resultSetCatPers.getDouble("cat_cout_horaire"));
        
        return categoriePersonnel;
    }
    
    public ArrayList<CategoriePersonnel> getAllCategoriePersonnel() throws SQLException {
        try (var resultSetCatPers = categoriePersonnelDAO.selectAllCategoriePersonnel();) {
            var lesCategoriesPersonnel = new ArrayList<CategoriePersonnel>();
            
            while (resultSetCatPers.next()) {
                lesCategoriesPersonnel.add(createCategoriePersonnel(resultSetCatPers));
            }
            
            return lesCategoriesPersonnel;
        }
    }
    
    public CategoriePersonnel getOneCategoriePersonnel(String code) throws SQLException {
        try (var resultSetCatPers = categoriePersonnelDAO.selectOneCategoriePersonnel(code);) {
            return resultSetCatPers.next() ? createCategoriePersonnel(resultSetCatPers) : null;
        }
    }
}
