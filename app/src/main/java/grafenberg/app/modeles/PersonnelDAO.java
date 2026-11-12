package grafenberg.app.modeles;

import java.sql.SQLException;
import java.sql.ResultSet;

public final class PersonnelDAO {
    
    private final ConnexionDAO connexionDAO;
    
    public PersonnelDAO() { connexionDAO = new ConnexionDAO(); }
    
    public ResultSet selectAllPersonnel() throws SQLException {
        return connexionDAO.getStatement().executeQuery(
                "select * from personnel"
        );
    }
    
    public ResultSet selectOnePersonnel(int id) throws SQLException {
        var preparedStatement = connexionDAO.getPreparedStatement(
                "select * from personnel where per_id = ?"
        );
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }
}
