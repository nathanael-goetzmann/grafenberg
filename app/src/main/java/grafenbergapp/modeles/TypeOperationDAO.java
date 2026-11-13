package grafenbergapp.modeles;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * La classe soumet des requêtes SQL prédéterminées à la base de données Grafenberg,
 * en lien avec l'entité TypeOperation.<br>
 * Chacune d'elles maintient une connexion constante pour garantir de bonnes performances.
 * @author Nathanaël Goetzmann
 * @version 1.0
 * @since 2026-11-13
 */
public final class TypeOperationDAO {
    
    /**
     * Représente un objet Statement, avec une connexion vers la base pour toute requête
     * sans saisie utilisateur.
     */
    private final Statement statement;
    /**
     * Représente un objet PreparedStatement et sa requête SQL précompilée pour sélectionner
     * un seul enregistrement de la table « type_operation ».
     */
    private final PreparedStatement preparedSelectOneTypeOp;
    
    /**
     * Instancie un objet TypeOperationDAO en précompilant au besoin certaines requêtes SQL.
     * @throws SQLException 
     */
    public TypeOperationDAO() throws SQLException {
        statement = ConnexionDAO.getStatement();
        preparedSelectOneTypeOp = ConnexionDAO.getPreparedStatement(
                "select * from type_operation where top_id = ?"
        );
    }
    
    /**
     * Renvoie toutes les données de la table « type_operation » sous la forme d'un objet ResultSet.
     * @return un objet ResultSet comprenant les enregistrements à parcourir
     * @throws SQLException 
     */
    public ResultSet selectAllTypeOperation() throws SQLException {
        return statement.executeQuery("select * from type_operation");
    }
    
    /**
     * Renvoie toutes les données d'un enregistrement de la table « type_operation » selon l'identifiant renseigné
     * par l'utilisateur, sous la forme d'un objet ResultSet. 
     * @param id l'identifiant du type d'opération à sélectionner
     * @return un objet ResultSet comprenant l'enregistrement à parcourir
     * @throws SQLException 
     */
    public ResultSet selectOneTypeOperation(int id) throws SQLException {
        preparedSelectOneTypeOp.setInt(1, id);
        return preparedSelectOneTypeOp.executeQuery();
    }
}
