package grafenbergapp.modeles;

/**
 * Représente un membre du personnel, défini par un identifiant,
 * un nom et une catégorie.
 * 
 * @author Matteo / Équipe Grafenberg / Équipe Tribord
 * @version 1.0
 */
public final class Personnel {

    /** Identifiant du membre du personnel. */
    private int id;

    /** Nom du membre du personnel. */
    private String nom;

    /** Catégorie du membre du personnel. */
    private CategoriePersonnel categoriePersonnel;

    /**
     * Construit un membre du personnel avec les informations indiquées.
     *
     * @param id identifiant du membre du personnel
     * @param nom nom du membre du personnel
     * @param categoriePersonnel catégorie du membre du personnel
     */
    public Personnel(int id, String nom, CategoriePersonnel categoriePersonnel) {
        this.id = id;
        this.nom = nom;
        this.categoriePersonnel = categoriePersonnel;
    }

    /**
     * Construit un membre du personnel avec les valeurs par défaut :
     * identifiant égal à {@code 0}, nom et catégorie à {@code null}.
     */
    public Personnel() { }

    /**
     * Retourne l'identifiant du membre du personnel.
     *
     * @return identifiant du membre du personnel
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne le nom du membre du personnel.
     *
     * @return nom du membre du personnel
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la catégorie du membre du personnel.
     *
     * @return catégorie du membre du personnel
     */
    public CategoriePersonnel getCategoriePersonnel() {
        return categoriePersonnel;
    }

    /**
     * Modifie l'identifiant du membre du personnel.
     *
     * @param id nouvel identifiant
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Modifie le nom du membre du personnel.
     *
     * @param nom nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Modifie la catégorie du membre du personnel.
     *
     * @param categoriePersonnel nouvelle catégorie
     */
    public void setCategoriePersonnel(CategoriePersonnel categoriePersonnel) {
        this.categoriePersonnel = categoriePersonnel;
    }
}