
package grafenberg.app.controleurs;

import grafenberg.app.modeles.Personnel;
import grafenberg.app.modeles.PersonnelDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonnelControleur {

    private PersonnelDAO personnelDAO;

    public PersonnelControleur(PersonnelDAO personnelDAO) {
        this.personnelDAO = personnelDAO;
    }

    // Récupérer tous les personnels
    public List<Personnel> getAllPersonnels() throws SQLException {
        List<Personnel> liste = new ArrayList<>();

        try (ResultSet resultat = personnelDAO.getAll()) {
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

    // Récupérer un personnel par son numéro
    public Personnel getPersonnelByNum(short numero)
            throws SQLException {

        try (ResultSet resultat = personnelDAO.getByNum(numero)) {
            if (resultat.next()) {
                return new Personnel(
                    resultat.getShort("per_num"),
                    resultat.getString("per_nom"),
                    resultat.getString("per_cat_code")
                );
            }
        }

        return null;
    }

    // Ajouter un nouveau personnel
    public void ajouterPersonnel(Personnel personnel)
            throws SQLException {

        if (personnel == null) {
            throw new IllegalArgumentException(
                "Le personnel ne peut pas être null."
            );
        }

        if (personnel.getPer_nom() == null
                || personnel.getPer_nom().trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Le nom du personnel est obligatoire."
            );
        }

        if (personnel.getPer_cat_code() == null
                || personnel.getPer_cat_code().trim().isEmpty()) {
            throw new IllegalArgumentException(
                "La catégorie du personnel est obligatoire."
            );
        }

        personnelDAO.insert(personnel);
    }
}
