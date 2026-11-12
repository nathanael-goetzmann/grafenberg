package grafenberg.app.modeles;

import java.sql.SQLException;
import java.sql.ResultSet;

public final class InterventionDAO {
    
    private final ConnexionDAO connexionDAO;
    
    public InterventionDAO() { connexionDAO = new ConnexionDAO(); }
    
    public ResultSet selectTiedPersonnel(int id) throws SQLException {
        var preparedStatement = connexionDAO.getPreparedStatement(
                "select aff_per_id from affecter where aff_inter_id = ?"
        );
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }
    
    public ResultSet selectAllIntervention() throws SQLException {
        return connexionDAO.getStatement().executeQuery(
                "select * from intervention"
        );
    }
    
    public ResultSet selectOneIntervention(int id) throws SQLException {
        var preparedStatement = connexionDAO.getPreparedStatement(
                "select * from intervention where inter_id = ?"
        );
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }
    
    public void insertOneIntervention(Intervention intervention) throws SQLException {
        String requeteInter = "insert into intervention values (DEFAULT, ?, ?, ?, ?)";
        String requeteAff = "insert into affecter values (?, ?)";
        
        try (
                var preparedStatementInter = connexionDAO.getPreparedStatement(requeteInter);
                var preparedStatementAff = connexionDAO.getPreparedStatement(requeteAff);
        ) {
            preparedStatementInter.setDate(1, intervention.getDate());
            preparedStatementInter.setBoolean(2, intervention.getNuit());
            preparedStatementInter.setInt(3, intervention.getDuree());
            preparedStatementInter.setInt(4, intervention.getTypeOperation().getId());
            preparedStatementInter.executeUpdate();
            
            var resultSetInter = preparedStatementInter.getGeneratedKeys();
            resultSetInter.next();
            
            for (var personnel : intervention.getLesPersonnel()) {
                preparedStatementAff.setLong(1, resultSetInter.getLong(1));
                preparedStatementAff.setInt(2, personnel.getId());
                preparedStatementAff.executeUpdate();
            }
        }
    }
}
