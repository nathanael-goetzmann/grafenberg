package grafenberg.app.modeles;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * 
 * @author Nathanaël Goetzmann
 * @version v1.0
 * @since 2026-10-16
 */

public final class TypeOperationDAO {
    
    private final ConnexionDAO connexionDAO;
    
    /**
     * 
     * @throws SQLException 
     */
    public TypeOperationDAO() throws SQLException {
        connexionDAO = new ConnexionDAO();
    }
    
    /**
     * 
     * @return
     * @throws SQLException 
     */
    public ResultSet selectAllTypeOperation() throws SQLException {
        var statement = connexionDAO.getSimpleStatement();
        return statement.executeQuery("select * from type_operation");
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws SQLException 
     */
    public ResultSet selectUnTypeOperation(int id) throws SQLException {
        String requete = "select * from type_operation where top_num = ?";
        var preparedStatement = connexionDAO.getPreparedStatement(requete);
        
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }
}