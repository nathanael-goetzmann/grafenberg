package grafenbergapp.modeles;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Fournit les opérations de consultation de la table {@code personnel}.
 * 
 * @author Matteo / Équipe Grafenberg / Équipe Tribord
 * @version 1.0
 */
public final class PersonnelDAO {

    /** Instruction SQL utilisée pour sélectionner l'ensemble du personnel. */
    private final Statement statement;

    /** Requête préparée permettant de sélectionner un personnel par identifiant. */
    private final PreparedStatement preparedSelectOnePers;

    /**
     * Initialise les instructions SQL à partir de {@link ConnexionDAO}.
     *
     * @throws SQLException si la création des instructions SQL échoue
     */
    public PersonnelDAO() throws SQLException {
        statement = ConnexionDAO.getStatement();
        preparedSelectOnePers = ConnexionDAO.getPreparedStatement(
                "select * from personnel where per_id = ?"
        );
    }

    /**
     * Sélectionne tous les enregistrements de la table {@code personnel}.
     * Le curseur du résultat est initialement placé avant la première ligne.
     *
     * @return résultat contenant l'ensemble du personnel
     * @throws SQLException si l'exécution de la requête échoue
     */
    public ResultSet selectAllPersonnel() throws SQLException {
        return statement.executeQuery("select * from personnel");
    }

    /**
     * Sélectionne le personnel correspondant à l'identifiant indiqué.
     * Le curseur du résultat est initialement placé avant la première ligne.
     *
     * @param id identifiant du personnel recherché
     * @return résultat contenant le personnel correspondant, ou aucune ligne
     *         si l'identifiant n'existe pas
     * @throws SQLException si la définition du paramètre ou l'exécution
     *                      de la requête échoue
     */
    public ResultSet selectOnePersonnel(int id) throws SQLException {
        preparedSelectOnePers.setInt(1, id);
        return preparedSelectOnePers.executeQuery();
    }
}