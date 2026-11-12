package grafenbergapp.modeles;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public final class InterventionDAO {
    
    private final Statement statement;
    private final PreparedStatement preparedSelectOneTiedPers;
    private final PreparedStatement preparedSelectOneInter;
    private final PreparedStatement preparedInsertTiedPers;
    private final PreparedStatement preparedInsertInter;
    private final CallableStatement callableCalculerCoutInter;
    
    public InterventionDAO() throws SQLException {
        statement = ConnexionDAO.getStatement();
        preparedSelectOneTiedPers = ConnexionDAO.getPreparedStatement(
                "select aff_per_id from affecter where aff_inter_id = ?"
        );
        preparedSelectOneInter = ConnexionDAO.getPreparedStatement(
                "select * from intervention where inter_id = ?"
        );
        preparedInsertTiedPers = ConnexionDAO.getPreparedStatement(
                "insert into affecter values (?, ?)"
        );  
        preparedInsertInter = ConnexionDAO.getPreparedStatement(
                "insert into intervention values (DEFAULT, ?, ?, ?, ?)"
        );
        callableCalculerCoutInter = ConnexionDAO.getCallableStatement(
                "{call p_calculer_cout_intervention(?, ?)}"
        );
    }
    
    public ResultSet selectTiedPersonnel(int id) throws SQLException {
        preparedSelectOneTiedPers.setInt(1, id);
        return preparedSelectOneTiedPers.executeQuery();
    }
    
    public ResultSet selectAllIntervention() throws SQLException {
        return statement.executeQuery("select * from intervention");
    }
    
    public ResultSet selectOneIntervention(int id) throws SQLException {
        preparedSelectOneInter.setInt(1, id);
        return preparedSelectOneInter.executeQuery();
    }
    
    public void insertIntervention(Intervention intervention) throws SQLException {
        preparedInsertInter.setDate(1, intervention.getDate());
        preparedInsertInter.setBoolean(2, intervention.getNuit());
        preparedInsertInter.setInt(3, intervention.getDuree());
        preparedInsertInter.setInt(4, intervention.getTypeOperation().getId());
        preparedInsertInter.executeUpdate();

        var resultSetKeys = preparedInsertInter.getGeneratedKeys();
        resultSetKeys.next();

        for (var personnel : intervention.getLesPersonnel()) {
            preparedInsertTiedPers.setLong(1, resultSetKeys.getLong(1));
            preparedInsertTiedPers.setInt(2, personnel.getId());
            preparedInsertTiedPers.executeUpdate();
        }
    }
    
    public double callCalculerCoutIntervention(int id) throws SQLException {
        callableCalculerCoutInter.setInt(1, id);
        callableCalculerCoutInter.registerOutParameter("p_total", java.sql.Types.DECIMAL);
        callableCalculerCoutInter.execute();
        return callableCalculerCoutInter.getDouble("p_total");
    }
}
