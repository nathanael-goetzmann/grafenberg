package grafenberg.app.modeles;

import java.sql.SQLException;
import java.sql.ResultSet;

public final class BlocDAO {
    
    private final ConnexionDAO connexionDAO;
    
    public BlocDAO() { connexionDAO = new ConnexionDAO(); }
    
    public ResultSet selectAllBloc() throws SQLException {
        return connexionDAO.getStatement().executeQuery(
                "select * from bloc"
        );
    }
    
    public ResultSet selectOneBloc(int id) throws SQLException {
        var preparedStatement = connexionDAO.getPreparedStatement(
                "select * from bloc where bloc_id = ?"
        );
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }
}
