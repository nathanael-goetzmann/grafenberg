package grafenbergapp.modeles;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * La classe soumet des requêtes SQL prédéterminées à la base de données Grafenberg,
 * en lien avec l'entité Bloc.<br>
 * Chacune d'elles maintient une connexion constante pour garantir de bonnes performances.
 * @author Nathanaël Goetzmann
 * @version 1.0
 * @since 2026-11-13
 */
public final class BlocDAO {
    
    /**
     * Représente un objet Statement, avec une connexion vers la base pour toute requête
     * sans saisie utilisateur.
     */
    private final Statement statement;
    /**
     * Représente un objet PreparedStatement et sa requête SQL précompilée pour sélectionner
     * un seul enregistrement de la table « bloc ».
     */
    private final PreparedStatement preparedSelectOneBloc;
    
    /**
     * Instancie un objet BlocDAO en précompilant au besoin certaines requêtes SQL.
     * @throws SQLException 
     */
    public BlocDAO() throws SQLException {
        statement = ConnexionDAO.getStatement();
        preparedSelectOneBloc = ConnexionDAO.getPreparedStatement(
                "select * from bloc where bloc_id = ?"
        );
    }
    
    /**
     * Renvoie toutes les données de la table « bloc » sous la forme d'un objet ResultSet.
     * @return un objet ResultSet comprenant les enregistrements à parcourir
     * @throws SQLException 
     */
    public ResultSet selectAllBloc() throws SQLException {
        return statement.executeQuery("select * from bloc");
    }
    
    /**
     * Renvoie toutes les données d'un enregistrement de la table « bloc » selon l'identifiant renseigné
     * par l'utilisateur, sous la forme d'un objet ResultSet. 
     * @param id l'identifiant du bloc à sélectionner
     * @return un objet ResultSet comprenant l'enregistrement à parcourir
     * @throws SQLException 
     */
    public ResultSet selectOneBloc(int id) throws SQLException {
        preparedSelectOneBloc.setInt(1, id);
        return preparedSelectOneBloc.executeQuery();
    }
}
