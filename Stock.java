//Carl Dahlén cada7128

public class Stock extends Valuable {
    private int quantity;
    private double rate;
    private double newRate = rate;

    public Stock(String name, int quantity, double rate){
        super(name);
        this.quantity=quantity;
        this.rate=rate;
    }

    public double getRate(){
        return rate;
    }
    public void setRate(double newRate){
        this.rate = newRate;
    }
    public int getQuantity(){
        return this.quantity;
    }
    @Override
    public double getValue(){
        return  getQuantity() * getRate();

    }
    @Override
    public String toString(){
        return super.toString() + String.format("antal: %d kurs: %.2f", getQuantity(), getRate() );

    }

}
