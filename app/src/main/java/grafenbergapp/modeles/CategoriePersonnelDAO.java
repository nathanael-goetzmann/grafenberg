package grafenbergapp.modeles;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public final class CategoriePersonnelDAO {
    
    private final Statement statement;
    private final PreparedStatement preparedSelectOneCatPers;
    
    public CategoriePersonnelDAO() throws SQLException {
        statement = ConnexionDAO.getStatement();
        preparedSelectOneCatPers = ConnexionDAO.getPreparedStatement(
                "select * from categorie_personnel where cat_code = ?"
        );
    }
    
    public ResultSet selectAllCategoriePersonnel() throws SQLException {
        return statement.executeQuery("select * from categorie_personnel");
    }
    
    public ResultSet selectOneCategoriePersonnel(String code) throws SQLException {
        preparedSelectOneCatPers.setString(1, code);
        return preparedSelectOneCatPers.executeQuery();
    }
}
