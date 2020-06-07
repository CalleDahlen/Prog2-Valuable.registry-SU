
//Carl Dahlén cada7128
public class Jewellery extends Valuable {
    private static final int PRICEOFJEWEL = 500;
    private int numberOfJewels;
    private final Material material;

    public Jewellery(String name, int numberOfJewels, String material) {
        super(name);
        this.numberOfJewels=numberOfJewels;
        this.material= Material.valueOf(material);
    }
    public int getJewels(){
        return numberOfJewels;
    }
    enum Material{
        Guld(2000), Silver(700);

        private final int value;

        Material(int value){
            this.value=value;
        }

        public double getValue(){
            return value;
        }

    }
    public Material getMaterial(){
        return material;
    }
    //public boolean isGold(){
       // if(material.equals("Guld"));
      //  return true;
   // }
    @Override
    public double getValue(){
        return  material.getValue() + PRICEOFJEWEL * numberOfJewels;
    }
    @Override
    public String toString(){
        return super.toString() + String.format("material: %s antal ädelstenar: %d", getMaterial(), getJewels() );

    }

}


