package grafenberg.app.modeles;

import java.sql.Date;

public class Intervention {

    private int id;
    private Date date;
    private int nuit;
    private int duree;
    private String topNum; 
    private TypeOperation typeOperation;
    private ArrayList<personnel> personnels;

    public Intervention(int id, Date date, int nuit, int duree, String topNum, TypeOperation typeOperation, ArrayList<personnel> personnels) {
        this.id = id;
        this.date = date;
        this.nuit = nuit;
        this.duree = duree;
        this.topNum = topNum;
        this.typeOperation = typeOperation;
        this.personnels = personnels;
    }

    public ArrayList<personnel> getPersonnels() {
        return personnels;
    }
    public void setPersonnels(ArrayList<personnel> personnels) {
        this.personnels = personnels;
    } 
    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNuit() {
        return nuit;
    }

    public void setNuit(int nuit) {
        this.nuit = nuit;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getTopNum() {
        return topNum;
    }

    public void setTopNum(String topNum) {
        this.topNum = topNum;
    }
}