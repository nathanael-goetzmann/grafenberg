package grafenberg.app.modeles;

import java.sql.SQLException;
import java.sql.ResultSet;

public final class TypeOperationDAO {
    
    
    private final ConnexionDAO connexionDAO;
    
    public TypeOperationDAO() throws SQLException {
        connexionDAO = new ConnexionDAO();
    }
    
    public ResultSet selectAllTypeOperation() throws SQLException {
        var statement = connexionDAO.getSimpleStatement();
        return statement.executeQuery("select * from type_operation");
    }
    
    public ResultSet selectUnTypeOperation(int id) throws SQLException {
        String requete = "select * from type_operation where top_num = ?";
        var preparedStatement = connexionDAO.getPreparedStatement(requete);
        
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }
}