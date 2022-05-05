public class AtomReport {

    String name;
    int priceAVG;
    int milesAVG;
    int count;


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
