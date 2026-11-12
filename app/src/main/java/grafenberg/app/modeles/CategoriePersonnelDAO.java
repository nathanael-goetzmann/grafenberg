package grafenberg.app.modeles;

import java.sql.SQLException;
import java.sql.ResultSet;

public final class CategoriePersonnelDAO {
    
    private final ConnexionDAO connexionDAO;
    
    public CategoriePersonnelDAO() { connexionDAO = new ConnexionDAO(); }
    
    public ResultSet selectAllCategoriePersonnel() throws SQLException {
        return connexionDAO.getStatement().executeQuery(
                "select * from categorie_personnel"
        );
    }
    
    public ResultSet selectOneCategoriePersonnel(String code) throws SQLException {
        var preparedStatement = connexionDAO.getPreparedStatement(
                "select * from categorie_personnel where cat_code = ?"
        );
        preparedStatement.setString(1, code);
        return preparedStatement.executeQuery();
    }
}
