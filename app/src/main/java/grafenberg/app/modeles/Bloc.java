package grafenberg.app.modeles;

public final class Bloc {
    
    private int id;
    private double charges;
    
    public Bloc(int id, double charges) {
        this.id = id;
        this.charges = charges;
    }
    public Bloc() { }
    
    public int getId() { return id; }
    public double getCharges() { return charges; }
    
    public void setId(int id) { this.id = id; }
    public void setCharges(double charges) { this.charges = charges; }
}
