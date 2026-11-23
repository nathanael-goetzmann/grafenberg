package grafenbergapp.vues;

import grafenbergapp.controleurs.*;
import java.util.Scanner;
import java.sql.SQLException;

public final class Main {
    
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        try (var clavier = new Scanner(System.in);) {
            var interventionControleur = new InterventionControleur();
            for (var intervention : interventionControleur.getAllIntervention()) {
                intervention.afficher();
            }
            
            System.out.print("\nTest de la procédure stockée : ");
            int id = Integer.parseInt(clavier.nextLine());
            interventionControleur.afficherCoutIntervention(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
