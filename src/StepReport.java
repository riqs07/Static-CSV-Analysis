import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StepReport {
    String name;
    int count;
    int milesAVG;
    int priceAVG;
    int stepTotalValue;

    double priceDeviation = 0;


    ArrayList<String> atoms = new ArrayList<>();
    ArrayList<AtomReport> atomReports = new ArrayList<>();

    Map<String, Integer> carMakesMaps = new HashMap<>();
    Map<String, Integer> carModelsMaps = new HashMap<>();


    HashMap<String, Map<String, Integer>> carLotMasterMap = new HashMap<>();

    Map<String, Integer> leastExpensiveAtom = new HashMap<>();
    Map<String, Integer> mostExpensiveAtom = new HashMap<>();

    Map<String, Integer> mostPopularCar = new HashMap<>();
    Map<String, Integer> leastPopularCar = new HashMap<>();

    Map<String, Integer> mostPopularMaker = new HashMap<>();
    Map<String, Integer> leastPopularMaker  = new HashMap<>();



    public double getPriceDeviation() {
        return priceDeviation;
    }

    public void setPriceDeviation(double priceDeviation) {
        this.priceDeviation = priceDeviation;
    }

    public Map<String, Integer> getLeastExpensiveAtom() {
        return leastExpensiveAtom;
    }

    public void setLeastExpensiveAtom(Map<String, Integer> leastExpensiveAtom) {
        this.leastExpensiveAtom = leastExpensiveAtom;
    }

    public Map<String, Integer> getMostExpensiveAtom() {
        return mostExpensiveAtom;
    }

    public void setMostExpensiveAtom(Map<String, Integer> mostExpensiveAtom) {
        this.mostExpensiveAtom = mostExpensiveAtom;
    }


    public Map<String, Integer> getCarMakesMaps() {
        return carMakesMaps;
    }

    public void setCarMakesMaps(Map<String, Integer> carMakesMaps) {
        this.carMakesMaps = carMakesMaps;
    }


    public Map<String, Integer> getCarModelsMaps() {
        return carModelsMaps;
    }

    public void setCarModelsMaps(Map<String, Integer> carModelsMaps) {
        this.carModelsMaps = carModelsMaps;
    }

    public int getStepTotalValue() {
        return stepTotalValue;
    }

    public void setStepTotalValue(int stepTotalValue) {
        this.stepTotalValue = stepTotalValue;
    }

    public ArrayList<AtomReport> getAtomReports() {
        return atomReports;
    }

    public void setAtomReports(ArrayList<AtomReport> atomReports) {
        this.atomReports = atomReports;
    }


    @Override
    public String toString() {
        return "StepReport{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", milesAVG=" + milesAVG +
                ", priceAVG=" + priceAVG +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMilesAVG() {
        return milesAVG;
    }

    public void setMilesAVG(int milesAVG) {
        this.milesAVG = milesAVG;
    }

    public int getPriceAVG() {
        return priceAVG;
    }

    public void setPriceAVG(int priceAVG) {
        this.priceAVG = priceAVG;
    }

    public ArrayList<String> getAtoms() {
        return atoms;
    }

    public void setAtoms(ArrayList<String> atoms) {
        this.atoms = atoms;
    }
}

