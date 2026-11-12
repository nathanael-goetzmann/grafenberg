package grafenbergapp.modeles;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public final class BlocDAO {
    
    private final Statement statement;
    private final PreparedStatement preparedSelectOneBloc;
    
    public BlocDAO() throws SQLException {
        statement = ConnexionDAO.getStatement();
        preparedSelectOneBloc = ConnexionDAO.getPreparedStatement(
                "select * from bloc where bloc_id = ?"
        );
    }
    
    public ResultSet selectAllBloc() throws SQLException {
        return statement.executeQuery("select * from bloc");
    }
    
    public ResultSet selectOneBloc(int id) throws SQLException {
        preparedSelectOneBloc.setInt(1, id);
        return preparedSelectOneBloc.executeQuery();
    }
}
