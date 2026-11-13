package grafenbergapp.controleurs;

import grafenbergapp.modeles.PersonnelDAO;
import grafenbergapp.modeles.Personnel;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Fournit les opérations de consultation du personnel et construit les objets
 * {@link Personnel} avec leur catégorie à partir des résultats du DAO.
 *
 * @author Matteo / Équipe Grafenberg / Équipe Tribord
 * @version 1.0
 */
public final class PersonnelControleur {
    
    /** Accès aux données du personnel. */
    private final PersonnelDAO personnelDAO;
    /** Contrôleur permettant de retrouver la catégorie du personnel. */
    private final CategoriePersonnelControleur categoriePersonnelControleur;
    
    /**
     * Initialise le DAO du personnel et le contrôleur des catégories.
     *
     * @throws SQLException si l'initialisation des accès aux données échoue
     */
    public PersonnelControleur() throws SQLException {
        personnelDAO = new PersonnelDAO();
        categoriePersonnelControleur = new CategoriePersonnelControleur();
    }
    
    /**
     * Construit un membre du personnel à partir de la ligne courante du résultat
     * et retrouve sa catégorie.
     *
     * @param resultSetPers résultat dont le curseur est placé sur le personnel
     * @return membre du personnel avec sa catégorie
     * @throws SQLException si la lecture du résultat ou la recherche de la
     *                      catégorie échoue
     */
    private Personnel createPersonnel(ResultSet resultSetPers) throws SQLException {
        var personnel = new Personnel();
                
        personnel.setId(resultSetPers.getInt("per_id"));
        personnel.setNom(resultSetPers.getString("per_nom"));

        var categoriePersonnel = categoriePersonnelControleur.getOneCategoriePersonnel(
                resultSetPers.getString("per_cat_code")
        );
        personnel.setCategoriePersonnel(categoriePersonnel);

        return personnel;
    }
    
    /**
     * Retourne l'ensemble du personnel avec les catégories associées.
     *
     * @return liste des membres du personnel, vide si aucun n'est enregistré
     * @throws SQLException si la consultation du personnel ou de ses catégories
     *                      échoue
     */
    public ArrayList<Personnel> getAllPersonnel() throws SQLException {
        try (var resultSetPers = personnelDAO.selectAllPersonnel();) {
            var lesPersonnel = new ArrayList<Personnel>();
            
            while (resultSetPers.next()) {
                lesPersonnel.add(createPersonnel(resultSetPers));
            }
            
            return lesPersonnel;
        }
    }
    
    /**
     * Retourne le membre du personnel correspondant à l'identifiant indiqué.
     *
     * @param id identifiant du personnel recherché
     * @return membre du personnel avec sa catégorie, ou {@code null} si
     *         l'identifiant n'existe pas
     * @throws SQLException si la consultation du personnel ou de sa catégorie
     *                      échoue
     */
    public Personnel getOnePersonnel(int id) throws SQLException {
        try (var resultSetPers = personnelDAO.selectOnePersonnel(id);) {
            return resultSetPers.next() ? createPersonnel(resultSetPers) : null;
        }
    }
}
