import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RoutineReport {

    String name;

    int amountOfAtoms = 0;
    int amountOfSteps = 0;

    int count;
    int priceAVG;
    int milesAVG;

    ArrayList<String> steps = new ArrayList<>();
    ArrayList<String> atoms = new ArrayList<>();

    Map<String,Integer> carMakesMaps = new HashMap<>();

    Map<String, Integer> leastExpensiveAtom = new HashMap<>();
    Map<String, Integer> mostExpensiveAtom = new HashMap<>();

    Map<String, Integer> mostPopulatedStep = new HashMap<>();
    Map<String, Integer> leastPopulatedStep = new HashMap<>();

    Map<String, Integer> mostPopularCar = new HashMap<>();
    Map<String, Integer> leastPopularCar = new HashMap<>();

    Map<String, Integer> mostPopularMaker = new HashMap<>();
    Map<String, Integer> leastPopularMaker  = new HashMap<>();



    Map<String,Integer> carModelsMaps = new HashMap<>();
    HashMap<String, Map<String, Integer>> carLotMasterMap = new HashMap<>();



    public ArrayList<String> getAtoms() {
        return atoms;
    }

    public void setAtoms(ArrayList<String> atoms) {
        this.atoms = atoms;
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

    public HashMap<String, Map<String, Integer>> getCarLotMasterMap() {
        return carLotMasterMap;
    }

    public void setCarLotMasterMap(HashMap<String, Map<String, Integer>> carLotMasterMap) {
        this.carLotMasterMap = carLotMasterMap;
    }

    public int getAmountOfAtoms() {
        return amountOfAtoms;
    }

    public void setAmountOfAtoms(int amountOfAtoms) {
        this.amountOfAtoms = amountOfAtoms;
    }

    public int getAmountOfSteps() {
        return amountOfSteps;
    }

    public void setAmountOfSteps(int amountOfSteps) {
        this.amountOfSteps = amountOfSteps;
    }


    public int getTotalRoutineValue() {
        return totalRoutineValue;
    }

    public void setTotalRoutineValue(int totalRoutineValue) {
        this.totalRoutineValue = totalRoutineValue;
    }

    int totalRoutineValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public RoutineReport() {
    }

}
