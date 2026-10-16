package grafenberg.app.vue;

import java.sql.SQLException;

import grafenberg.app.modeles.ConnexionDAO;

public class Main {

    public static void main(String[] args) {
        try {
            var connexion = new ConnexionDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }   
}