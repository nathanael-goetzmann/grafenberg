package grafenberg.app.modeles;
import grafenberg.app.modeles.ConnexionDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonnelDAO {

    private Connection connexion;

    public PersonnelDAO() throws SQLException {
        this.connexion = new ConnexionDAO().getConnection();
    }

    // Sélectionner tous les personnels
    public List<Personnel> getAll() throws SQLException {
        List<Personnel> liste = new ArrayList<>();

        String sql = "SELECT * FROM personnel";

        try (Statement statement = connexion.createStatement();
             ResultSet resultat = statement.executeQuery(sql)) {

            while (resultat.next()) {
                Personnel p = new Personnel(
                    resultat.getShort("per_num"),
                    resultat.getString("per_nom"),
                    resultat.getString("per_cat_code")
                );

                liste.add(p);
            }
        }

        return liste;
    }

    // Sélectionner un personnel avec une procédure stockée
    public Personnel getByNum(short numero) throws SQLException {
        String sql = "{CALL getPersonnelByNum(?)}";

        try (CallableStatement statement = connexion.prepareCall(sql)) {
            statement.setShort(1, numero);

            try (ResultSet resultat = statement.executeQuery()) {
                if (resultat.next()) {
                    return new Personnel(
                        resultat.getShort("per_num"),
                        resultat.getString("per_nom"),
                        resultat.getString("per_cat_code")
                    );
                }
            }
        }

        return null;
    }

    // Insérer un nouveau personnel
    public void insert(Personnel personnel) throws SQLException {
        String sql = "INSERT INTO personnel (per_num, per_nom, per_cat_code) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connexion.prepareStatement(sql)) {
            statement.setShort(1, personnel.getPer_num());
            statement.setString(2, personnel.getPer_nom());
            statement.setString(3, personnel.getPer_cat_code());

            statement.executeUpdate();
        }
    }
}
