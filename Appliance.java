//Carl Dahlén cada7128

public class Appliance extends Valuable{
    private static final int VALUECOEFFICIENT = 10;
    private double price;
    private int wear;


    public Appliance(String name, double price, int wear){
        super(name);
        this.price=price;
        this.wear=wear;
    }
    public double getPrice(){
        return price;
    }
    public int getWear(){
        return wear;
    }

    @Override
    public double getValue(){
        return  getPrice() * (((double)getWear())/ VALUECOEFFICIENT);
    }
    @Override
    public String toString(){
        return super.toString() + String.format("pris: %.2f slitage: %d", getPrice(), getWear() );


    }

}