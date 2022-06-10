import java.util.HashMap;
import java.util.Map;

public class AtomReport {

    String name;
    int count =0;

    int priceAVG =0 , priceSUM =0, priceMedian = 0, priceRange = 0, price1stQuartile = 0, price3rdQuartile = 0;
    int milesAVG =0, milesSUM =0, milesMedian = 0, milesRange = 0, miles1stQuartile = 0, miles3rdQuartile = 0;

    public int getPrice1stQuartile() {
        return price1stQuartile;
    }

    public void setPrice1stQuartile(int price1stQuartile) {
        this.price1stQuartile = price1stQuartile;
    }

    public int getPrice3rdQuartile() {
        return price3rdQuartile;
    }

    public void setPrice3rdQuartile(int price3rdQuartile) {
        this.price3rdQuartile = price3rdQuartile;
    }

    public int getMiles1stQuartile() {
        return miles1stQuartile;
    }

    public void setMiles1stQuartile(int miles1stQuartile) {
        this.miles1stQuartile = miles1stQuartile;
    }

    public int getMiles3rdQuartile() {
        return miles3rdQuartile;
    }

    public void setMiles3rdQuartile(int miles3rdQuartile) {
        this.miles3rdQuartile = miles3rdQuartile;
    }

    double priceDeviation =0 , milesDeviation =0;
    int minPrice = 0, maxPrice =0;
    int minMiles = 0, maxMiles = 0;

    HashMap<String, Map<String,Integer>> carLotMap = new HashMap<>();
    HashMap<String,Integer> carModelsMap = new HashMap<>();
    HashMap<String,Integer> carMakesMap = new HashMap<>();

    public HashMap<String, Integer> getCarModelsMap() {
        return carModelsMap;
    }

    public void setCarModelsMap(HashMap<String, Integer> carModelsMap) {
        this.carModelsMap = carModelsMap;
    }


    int lotValue =0;

    public HashMap<String, Integer> getCarMakesMap() {
        return carMakesMap;
    }

    public void setCarMakesMap(HashMap<String, Integer> carMakesMap) {
        this.carMakesMap = carMakesMap;
    }



    public int getLotValue() {
        return lotValue;
    }

    public void setLotValue(int lotValue) {
        this.lotValue = lotValue;
    }


    public int getMilesMedian() {
        return milesMedian;
    }

    public void setMilesMedian(int milesMedian) {
        this.milesMedian = milesMedian;
    }

    public int getMilesRange() {
        return milesRange;
    }

    public void setMilesRange(int milesRange) {
        this.milesRange = milesRange;
    }

    public int getMaxMiles() {
        return maxMiles;
    }

    public void setMaxMiles(int maxMiles) {
        this.maxMiles = maxMiles;
    }

    public HashMap<String, Map<String, Integer>> getCarLotMap() {
        return carLotMap;
    }

    public void setCarLotMap(HashMap<String, Map<String, Integer>> carLotMap) {
        this.carLotMap = carLotMap;
    }


    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinMiles() {
        return minMiles;
    }

    public void setMinMiles(int minMiles) {
        this.minMiles = minMiles;
    }

    public int getMaxMies() {
        return maxMiles;
    }

    public void setMaxMies(int maxMies) {
        this.maxMiles = maxMies;
    }

    public int getPriceMedian() {
        return priceMedian;
    }

    public void setPriceMedian(int priceMedian) {
        this.priceMedian = priceMedian;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    public double getPriceDeviation() {
        return priceDeviation;
    }

    public void setPriceDeviation(double priceDeviation) {
        this.priceDeviation = priceDeviation;
    }

    public double getMilesDeviation() {
        return milesDeviation;
    }

    public void setMilesDeviation(double milesDeviation) {
        this.milesDeviation = milesDeviation;
    }


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
