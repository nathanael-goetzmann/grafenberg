package grafenberg.app.modeles;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Ce fichier sélectionne puis renvoie des données relatives
 * aux blocs opératoires depuis la base, en utilisant
 * la classe « bloc » comme modèle.
 * @author Nathanaël Goetzmann
 * @version v1.0
 * @since 2026-10-16
 */

public final class BlocDAO {
    
    private final ConnexionDAO connexionDAO;
    
    /**
     * 
     * @throws SQLException 
     */
    
    public BlocDAO() throws SQLException {
        connexionDAO = new ConnexionDAO();
    }
    
    /**
     * 
     * @return
     * @throws SQLException 
     */
    
    public ResultSet selectAllBloc() throws SQLException {
        var statement = connexionDAO.getSimpleStatement();
        return statement.executeQuery("select * from bloc");
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws SQLException 
     */
    
    public ResultSet selectUnBloc(int id) throws SQLException {
        String requete = "select * from bloc where bloc_num = ?";
        var preparedStatement = connexionDAO.getPreparedStatement(requete);
        
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }
}