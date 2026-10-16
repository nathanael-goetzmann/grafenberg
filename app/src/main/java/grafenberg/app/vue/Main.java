package grafenberg.app.vue;

import grafenberg.app.modeles.ConnexionDAO;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            var connexion = new ConnexionDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }   
}
