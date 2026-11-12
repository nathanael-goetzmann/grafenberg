package grafenbergapp.modeles;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public final class TypeOperationDAO {
    
    private final Statement statement;
    private final PreparedStatement preparedSelectOneTypeOp;
    
    public TypeOperationDAO() throws SQLException {
        statement = ConnexionDAO.getStatement();
        preparedSelectOneTypeOp = ConnexionDAO.getPreparedStatement(
                "select * from type_operation where top_id = ?"
        );
    }
    
    public ResultSet selectAllTypeOperation() throws SQLException {
        return statement.executeQuery("select * from type_operation");
    }
    
    public ResultSet selectOneTypeOperation(int id) throws SQLException {
        preparedSelectOneTypeOp.setInt(1, id);
        return preparedSelectOneTypeOp.executeQuery();
    }
}
