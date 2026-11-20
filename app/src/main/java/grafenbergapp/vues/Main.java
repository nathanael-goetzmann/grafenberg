package grafenbergapp.vues;

import grafenbergapp.controleurs.InterventionControleur;
import grafenbergapp.controleurs.PersonnelControleur;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.SQLException;
import org.apache.pdfbox.*;

public final class Main {
    
    private static final Scanner clavier = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    private static java.sql.Date getDateValide() {
        while (true) {
            try {
                var date = new java.sql.Date(dateFormat.parse(clavier.nextLine()).getTime());
                
                if (date.before(new java.util.Date())) { return date; }
                else { System.out.print("\nLa date indiquée n'est pas pertinente. Veuillez réessayer : "); }
            } catch (ParseException e) {
                System.out.print("\nUne erreur de saisie est survenue. Vérifiez votre syntaxe : ");
            }
        }
    }
    
    private static int getNombreValideDansIntervalle(int min, int max) {
        while (true) {
            try {
                int saisie = Integer.parseInt(clavier.nextLine());
                
                if (saisie >= min && saisie <= max) { return saisie; }
                else { 
                    System.out.printf(
                            "\nLa valeur doit être comprise dans l'intervalle [%d; %d] : ",
                            min,
                            max
                    );
                }
            } catch (NumberFormatException e) {
                System.out.print(
                        "\nLa donnée saisie n'est pas un nombre entier supporté. Veuillez retenter : "
                );
            }
        }
    }
    
    public static void main(String[] args) {
        try (clavier) {
            var interventionControleur = new InterventionControleur();
            var personnelControleur = new PersonnelControleur();
            String choix = "0";
            
            while (!choix.equals("6")) {
                System.out.println("\n===== Clinique Grafenberg : gestion des interventions =====");
                System.out.println("Les options suivantes sont disponibles :");
                System.out.println("1 - Afficher toutes les interventions enregistrées");
                System.out.println("2 - Rechercher une intervention à partir d'un identifiant");
                System.out.println("3 - Calculer le coût de revient d'une intervention");
                System.out.println("4 - Ajouter une nouvelle intervention");
                System.out.println("5 - Imprimer une intervention au format PDF");
                System.out.println("6 - Quitter le programme");
                
                System.out.print("\nSaisissez le numéro de votre choix : ");
                choix = clavier.nextLine();
                
                switch (choix) {
                    case "1" -> {
                        interventionControleur.afficherAllIntervention();
                    }
                    case "2" -> {
                        System.out.print("\nIndiquez l'identifiant de l'intervention à rechercher : ");
                        interventionControleur.afficherOneIntervention(
                                getNombreValideDansIntervalle(1, Integer.MAX_VALUE)
                        );
                    }
                    case "3" -> {
                        System.out.print("\nRenseignez l'identifiant de l'intervention à traiter : ");
                        interventionControleur.afficherCoutIntervention(
                                getNombreValideDansIntervalle(1, Integer.MAX_VALUE)
                        );
                    }
                    case "4" -> {
                        System.out.print("\nIndiquez d'abord une date, au format AAAA-MM-JJ : ");
                        var date = getDateValide();
                        
                        System.out.print("\nÉcrivez « true » si elle fut réalisée de nuit, « false » sinon : ");
                        boolean nuit = Boolean.parseBoolean(clavier.nextLine());
                        
                        System.out.print("\nMaintenant, donnez sa durée en heure : ");
                        int duree = getNombreValideDansIntervalle(1, 10);
                        
                        System.out.println("\nRappel des différents types d'opération :");
                        System.out.println("1 - Orthopédie");
                        System.out.println("2 - Plastique");
                        System.out.println("3 - Neurologie");
                        System.out.println("4 - Urologie");
                        
                        System.out.print("\nSaisissez le chiffre correspondant : ");
                        int typeOperationId = getNombreValideDansIntervalle(1, 4);
                        
                        personnelControleur.afficherAllPersonnel();
                    }
                    case "5" -> {
                        System.out.println("\nWIP");
                    }
                    case "6" -> {
                        System.out.println("\nAu revoir !");
                    }
                    default -> {
                        System.out.println("\nLe nombre renseigné est incorrect. Veuillez réessayer.");
                    }
                }
            }
        } catch (SQLException e) {
            
        } catch (InputMismatchException e) {

        } catch (Exception e) {
            
        }
    }
}
