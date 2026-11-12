package grafenberg.app.modeles;

import java.sql.SQLException;
import java.sql.ResultSet;

public final class TypeOperationDAO {
    
    private final ConnexionDAO connexionDAO;
    
    public TypeOperationDAO() { connexionDAO = new ConnexionDAO(); }
    
    public ResultSet selectAllTypeOperation() throws SQLException {
        return connexionDAO.getStatement().executeQuery(
                "select * from type_operation"
        );
    }
    
    public ResultSet selectOneTypeOperation(int id) throws SQLException {
        var preparedStatement = connexionDAO.getPreparedStatement(
                "select * from type_operation where top_id = ?"
        );
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }
}
