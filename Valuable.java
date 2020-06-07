//Carl Dahlén cada7128

public abstract class Valuable {
    private static final double VAT = 0.25;
    private double newVAT = VAT;
    private String name;
    private double value;


    Valuable(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public abstract double getValue();


    public double getValuePlusVAT() {
        if(newVAT != VAT){
            return getValue() * (1 + newVAT);
        } else
            return getValue() * (1 + VAT);
    }

    public void setVAT(double newVAT){
        this.newVAT = newVAT;
    }

    public String toString(){
        return String.format( "Namn: %s värde: %.2f värde plus moms: %.2f, ", getName(), getValue(), getValuePlusVAT());
    }

}

