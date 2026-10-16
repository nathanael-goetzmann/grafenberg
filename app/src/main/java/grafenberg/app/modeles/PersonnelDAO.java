
package grafenberg.app.modeles;

import grafenberg.app.vue.ConnexionDAO;

import java.sql.*;

public class PersonnelDAO {

    private ConnexionDAO connexion;

    public PersonnelDAO(ConnexionDAO connexion) {
        this.connexion = connexion;
    }

    // Sélectionner tous les personnels
    public ResultSet getAll() throws SQLException {
        String sql = "SELECT * FROM personnel";

        Statement statement = connexion.getSimpleStatement();
        return statement.executeQuery(sql);
    }

    // Sélectionner un personnel par son numéro
    public ResultSet getByNum(short numero) throws SQLException {
        String sql = "{CALL getPersonnelByNum(?)}";

        CallableStatement statement =
                connexion.getCallableStatement(sql);

        statement.setShort(1, numero);

        return statement.executeQuery();
    }

    // Insérer un nouveau personnel
    public void insert(Personnel personnel) throws SQLException {
        String sql = "INSERT INTO personnel "
                   + "(per_num, per_nom, per_cat_code) "
                   + "VALUES (?, ?, ?)";

        try (PreparedStatement statement =
                 connexion.getPreparedStatement(sql)) {

            statement.setShort(1, personnel.getPer_num());
            statement.setString(2, personnel.getPer_nom());
            statement.setString(3, personnel.getPer_cat_code());

            statement.executeUpdate();
        }
    }
}
