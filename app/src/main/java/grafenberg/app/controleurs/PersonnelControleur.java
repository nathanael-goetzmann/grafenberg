
package grafenberg.app.controleurs;

import grafenberg.app.modeles.Personnel;
import grafenberg.app.modeles.PersonnelDAO;

import java.sql.SQLException;
import java.util.List;

public class PersonnelControleur {

    private PersonnelDAO personnelDAO;

    public PersonnelControleur() {
        this.personnelDAO = new PersonnelDAO();
    }

    // Récupérer tous les personnels
    public List<Personnel> getAllPersonnels() throws SQLException {
        return personnelDAO.getAll();
    }

    // Récupérer un personnel par son numéro
    public Personnel getPersonnelByNum(short numero) throws SQLException {
        return personnelDAO.getByNum(numero);
    }

    // Ajouter un nouveau personnel
    public void ajouterPersonnel(Personnel personnel) throws SQLException {
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
