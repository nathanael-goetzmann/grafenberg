package grafenberg.app.vue;

import grafenberg.app.controleurs.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.SQLException;
import java.sql.Date;

public final class Main {
    
    private final Scanner clavier;
    private final InterventionControleur interventionControleur;
    private final TypeOperationControleur typeOperationControleur;
    
    public Main() {
        clavier = new Scanner(System.in);
        interventionControleur = new InterventionControleur();
        typeOperationControleur = new TypeOperationControleur();
    }
    
    private void afficherAllIntervention() throws SQLException {
        System.out.println("\nAffichage des interventions :");
        for (var intervention : interventionControleur.getAllIntervention()) {
            intervention.afficher();
        }
    }
    
    private void afficherUneIntervention() throws SQLException {
        System.out.print("\nIndiquez l'identifiant de l'intervention souhaitée : ");
        int id = Integer.parseInt(clavier.nextLine());
        var intervention = interventionControleur.getOneIntervention(id);

        if (intervention != null) { intervention.afficher(); }
        else { System.out.println("\nL'intervention spécifiée est introuvable."); }
    }
    
    private void creerIntervention() throws SQLException, ParseException {
        System.out.print("\nIndiquez d'abord la date de l'opération, au format AAAA-MM-JJ : ");
        var formatDate = new SimpleDateFormat("yyyy-MM-dd");
        var date = new Date(formatDate.parse(clavier.nextLine()).getTime());

        System.out.print("\nFut-elle menée de nuit ? Répondez par « true » ou « false » : ");
        boolean nuit = clavier.nextBoolean();
        clavier.skip("\n");

        System.out.print("\nIndiquez maintenant sa durée en minutes : ");
        int duree = Integer.parseInt(clavier.nextLine());

        System.out.println("\nListe des types d'intervention :");
        for (var type : typeOperationControleur.getAllTypeOperation()) {
            System.out.println("\t[" + type.getId() + "] " + type.getNom());
        }
        System.out.print("\nChoisissez le numéro correspondant : ");
        int typeOperationId = Integer.parseInt(clavier.nextLine());
        
        String choix = "5";
        var lesPersonnelId = new ArrayList<Integer>();
        
        System.out.println("\nEnfin, inscrivez jusqu'à sept membres du corps médical.");
        System.out.println("Ou renseignez 0 pour mettre fin à la saisie.");
        while (!choix.equals("0") && lesPersonnelId.size() < 7 ) {
            lesPersonnelId.add(Integer.valueOf(clavier.nextLine()));
        }
        
        interventionControleur.postOneIntervention(
                date,
                nuit,
                duree,
                typeOperationId,
                lesPersonnelId
        );
        System.out.println("\nInsertion réussie.");
    }
    
    private void calculerCoutIntervention() {
        
    }
    
    @SuppressWarnings("CallToPrintStackTrace")
    public void main() {
        String choix = "0";
        
        try (clavier) {
            while (!choix.equals("5")) {
                System.out.println("\n===== Grafenberg : gestion des interventions =====");
                System.out.println("\nLes options suivantes sont disponibles :");
                System.out.println("1 - Lister toutes les interventions");
                System.out.println("2 - Rechercher une intervention");
                System.out.println("3 - Ajouter une intervention");
                System.out.println("4 - Calculer le coût d'une intervention");
                System.out.println("5 - Quitter le programme");

                System.out.print("\nSaisissez le numéro correspondant : ");
                choix = clavier.nextLine();

                switch(choix) {
                    case "1" -> {
                        afficherAllIntervention();
                    } case "2" -> {
                        afficherUneIntervention();
                    } case "3" -> {
                        creerIntervention();
                    } case "4" -> {
                        calculerCoutIntervention();
                    } case "5" -> {
                        System.out.println("\nÀ bientôt !");
                    } default -> {
                        System.out.println("\nNuméro non reconnu. Veuillez réessayer.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
