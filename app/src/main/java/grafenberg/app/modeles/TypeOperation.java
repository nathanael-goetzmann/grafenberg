package grafenberg.app.modeles;

public class TypeOperation {
    private int top_num;
    private double top_charges;
    private int top_bloc_nul;
    
    public TypeOperation(int top_num, double top_charges, int top_bloc_nul) {
        this.top_num = top_num;
        this.top_charges = top_charges;
        this.top_bloc_nul = top_bloc_nul;
    }

    public int getTop_num() {
        return top_num;
    }
    public void setTop_num(int top_num) {
        this.top_num = top_num;
    }
    public double getTop_charges() {
        return top_charges;
    }
    public void setTop_charges(double top_charges) {
        this.top_charges = top_charges;
    }
    public int getTop_bloc_nul() {
        return top_bloc_nul;
    }
    public void setTop_bloc_nul(int top_bloc_nul) {
        this.top_bloc_nul = top_bloc_nul;
    }
    
}
