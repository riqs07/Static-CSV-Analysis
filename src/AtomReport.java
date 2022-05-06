public class AtomReport {

    String name;
    int priceAVG =0;
    int priceSUM =0;
    int milesAVG =0;
    int milesSUM =0;
    int count =0;

    public int getPriceSUM() {
        return priceSUM;
    }

    public void setPriceSUM(int priceSUM) {
        this.priceSUM = priceSUM;
    }

    public int getMilesSUM() {
        return milesSUM;
    }

    public void setMilesSUM(int milesSUM) {
        this.milesSUM = milesSUM;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriceAVG() {
        return priceAVG;
    }

    public void setPriceAVG(int priceAVG) {
        this.priceAVG = priceAVG;
    }

    public int getMilesAVG() {
        return milesAVG;
    }

    public void setMilesAVG(int milesAVG) {
        this.milesAVG = milesAVG;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AtomReport{" +
                "name='" + name + '\'' +
                ", priceAVG=" + priceAVG +
                ", milesAVG=" + milesAVG +
                ", count=" + count +
                '}';
    }


    // Can do other stuff in future, like amount of certain make or model or mean year etc


}
