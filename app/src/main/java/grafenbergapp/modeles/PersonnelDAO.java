package grafenbergapp.modeles;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public final class PersonnelDAO {
    
    private final Statement statement;
    private final PreparedStatement preparedSelectOnePers;
    
    public PersonnelDAO() throws SQLException {
        statement = ConnexionDAO.getStatement();
        preparedSelectOnePers = ConnexionDAO.getPreparedStatement(
                "select * from personnel where per_id = ?"
        );
    }
    
    public ResultSet selectAllPersonnel() throws SQLException {
        return statement.executeQuery("select * from personnel");
    }
    
    public ResultSet selectOnePersonnel(int id) throws SQLException {
        preparedSelectOnePers.setInt(1, id);
        return preparedSelectOnePers.executeQuery();
    }
}
