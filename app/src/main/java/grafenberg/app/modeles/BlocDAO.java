package grafenberg.app.modeles;

import java.sql.SQLException;
import java.sql.ResultSet;

public final class BlocDAO {
    
    
    private final ConnexionDAO connexionDAO;
    
    public BlocDAO() throws SQLException {
        connexionDAO = new ConnexionDAO();
    }
    
    public ResultSet selectAllBloc() throws SQLException {
        var statement = connexionDAO.getSimpleStatement();
        return statement.executeQuery("select * from bloc");
    }
    
    public ResultSet selectUnBloc(int id) throws SQLException {
        String requete = "select * from bloc where bloc_num = ?";
        var preparedStatement = connexionDAO.getPreparedStatement(requete);
        
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }
}