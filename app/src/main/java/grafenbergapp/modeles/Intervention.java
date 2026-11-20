package grafenbergapp.modeles;

import java.util.ArrayList;
import java.sql.Date;

public final class Intervention {
    
    private int id;
    private Date date;
    private boolean nuit;
    private int duree;
    private TypeOperation typeOperation;
    private ArrayList<Personnel> lesPersonnel;
    
    public Intervention(
            int id,
            Date date,
            boolean nuit,
            int duree,
            TypeOperation typeOperation,
            ArrayList<Personnel> lesPersonnel
    ) {
        this.id = id;
        this.date = date;
        this.nuit = nuit;
        this.duree = duree;
        this.typeOperation = typeOperation;
        this.lesPersonnel = lesPersonnel;
    }
    public Intervention() { }
    
    public int getId() { return id; }
    public Date getDate() { return date; }
    public boolean getNuit() { return nuit; }
    public int getDuree() { return duree; }
    public TypeOperation getTypeOperation() { return typeOperation; }
    public ArrayList<Personnel> getLesPersonnel() { return lesPersonnel; }
    
    public void setId(int id) { this.id = id; }
    public void setDate(Date date) { this.date = date; }
    public void setNuit(boolean nuit) { this.nuit = nuit; }
    public void setDuree(int duree) { this.duree = duree; }
    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }
    public void setLesPersonnel(ArrayList<Personnel> lesPersonnel) {
        this.lesPersonnel = lesPersonnel;
    }
    
    public void afficher() {
        System.out.printf("\n[N°%03d, %s]", id, typeOperation.getNom());
        System.out.printf("\nDe %s le %tF, ", nuit ? "nuit" : "jour", date);
        System.out.printf("d'une durée de %d heure(s).", duree);
        System.out.println("\nListe du personnel présent :");
        
        for (var personnel : lesPersonnel) {
            System.out.printf("\t* %s\n", personnel.getNom());
        }
    }
}
