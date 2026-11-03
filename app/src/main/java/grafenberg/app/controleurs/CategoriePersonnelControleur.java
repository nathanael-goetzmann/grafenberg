package grafenberg.app.controleurs;

import grafenberg.app.modeles.CategoriePersonnelDAO;
import grafenberg.app.modeles.CategoriePersonnel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class CategoriePersonnelControleur {
    private final CategoriePersonnelDAO categoriePersonnelDAO;

    public CategoriePersonnelControleur() {
        this.categoriePersonnelDAO = new CategoriePersonnelDAO();
    }

    public ArrayList<CategoriePersonnel> getAllCategoriePersonnel() throws SQLException {
        ArrayList<CategoriePersonnel> categories = new ArrayList<>();
        ResultSet resultSet = categoriePersonnelDAO.getAllCategoriePersonnel();

        while (resultSet.next()) {
            String cat_code = resultSet.getString("cat_code");
            int cat_couthoraire = resultSet.getInt("cat_couthoraire");
            CategoriePersonnel categorie = new CategoriePersonnel(cat_code, cat_couthoraire);
            categories.add(categorie);
        }

        return categories;
    }

    public CategoriePersonnel getOneCategoriePersonnel(String categorie) throws SQLException {
        ResultSet resultSet = categoriePersonnelDAO.getOneCategoriePersonnel(categorie);

        if (resultSet.next()) {
            String cat_code = resultSet.getString("cat_code");
            int cat_couthoraire = resultSet.getInt("cat_couthoraire");
            return new CategoriePersonnel(cat_code, cat_couthoraire);
        }

        return null;
    }
}