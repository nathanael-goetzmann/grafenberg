package grafenbergapp.modeles;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Classe DAO (Data Access Object) gérant les opérations de persistance
 * et les requêtes en base de données pour l'entité Intervention.
 * 
 * @author Antoine / Équipe Grafenberg / Équipe Tribord
 * @version 1.0
 */
public final class InterventionDAO {
    
    /** Statement standard pour l'exécution de requêtes simples. */
    private final Statement statement;
    
    /** Requête préparée pour sélectionner les identifiants du personnel lié à une intervention. */
    private final PreparedStatement preparedSelectOneTiedPers;
    
    /** Requête préparée pour sélectionner une intervention spécifique par son identifiant. */
    private final PreparedStatement preparedSelectOneInter;
    
    /** Requête préparée pour insérer une association entre une intervention et un membre du personnel. */
    private final PreparedStatement preparedInsertTiedPers;
    
    /** Requête préparée pour insérer une nouvelle intervention en base de données. */
    private final PreparedStatement preparedInsertInter;
    
    /** Appel préparé pour la procédure stockée de calcul du coût d'une intervention. */
    private final CallableStatement callableCalculerCoutInter;
    
    /**
     * Construit une nouvelle instance code InterventionDAO et initialise 
     * l'ensemble des requêtes préparées et statements via la classe de connexion.
     * 
     * @throws SQLException Si une erreur survient lors de la préparation des requêtes SQL
     */
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
    
    /**
     * Récupère la liste sous forme de ResultSet du personnel affecté à une intervention donnée.
     * 
     * @param id L'identifiant de l'intervention
     * @return ResultSet contenant les identifiants du personnel affecté
     * @throws SQLException Si une erreur d'accès à la base de données survient
     */
    public ResultSet selectTiedPersonnel(int id) throws SQLException {
        preparedSelectOneTiedPers.setInt(1, id);
        return preparedSelectOneTiedPers.executeQuery();
    }
    
    /**
     * Récupère l'ensemble des interventions enregistrées en base de données.
     * 
     * @return ResultSet contenant toutes les interventions
     * @throws SQLException Si une erreur d'accès à la base de données survient
     */
    public ResultSet selectAllIntervention() throws SQLException {
        return statement.executeQuery("select * from intervention");
    }
    
    /**
     * Récupère une intervention spécifique à partir de son identifiant unique.
     * 
     * @param id L'identifiant de l'intervention recherchée
     * @return ResultSet contenant les informations de l'intervention
     * @throws SQLException Si une erreur d'accès à la base de données survient
     */
    public ResultSet selectOneIntervention(int id) throws SQLException {
        preparedSelectOneInter.setInt(1, id);
        return preparedSelectOneInter.executeQuery();
    }
    
    /**
     * Insère une nouvelle intervention en base de données ainsi que les associations 
     * avec les différents membres du personnel qui y participent.
     * 
     * @param intervention L'objet Intervention contenant les données à insérer
     * @throws SQLException Si une erreur d'insertion ou d'accès à la base de données survient
     */
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
    
    /**
     * Calcule le coût total d'une intervention en appelant une procédure stockée en base de données.
     * 
     * @param id L'identifiant de l'intervention dont il faut calculer le coût
     * @return Le coût total de l'intervention sous forme de double.
     * @throws SQLException Si une erreur d'exécution de la procédure stockée survient
     */
    public double callCalculerCoutIntervention(int id) throws SQLException {
        callableCalculerCoutInter.setInt(1, id);
        callableCalculerCoutInter.registerOutParameter("p_total", java.sql.Types.DECIMAL);
        callableCalculerCoutInter.execute();
        return callableCalculerCoutInter.getDouble("p_total");
    }
}
