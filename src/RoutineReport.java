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

    int totalRoutineValue;
    int totalMilesValue;

    ArrayList<String> atoms = new ArrayList<>();

    public ArrayList<StepReport> getStepReports() {
        return stepReports;
    }

    public void setStepReports(ArrayList<StepReport> stepReports) {
        this.stepReports = stepReports;
    }

    ArrayList<String> steps = new ArrayList<>();
    ArrayList<StepReport> stepReports = new ArrayList<>();

    Map<String,Integer> carMakesMaps = new HashMap<>();
    Map<String,Integer> carModelsMaps = new HashMap<>();
    HashMap<String, Map<String, Integer>> carLotMasterMap = new HashMap<>();

    // can prob also have a method that gets least and most popular cars from the maps
    // should i add step reports here?

    Map<String, Integer> mostPopulatedStep = new HashMap<>();
    Map<String, Integer> leastPopulatedStep = new HashMap<>();


    Map<String, Integer> leastExpensiveAtom = new HashMap<>();
    Map<String, Integer> mostExpensiveAtom = new HashMap<>();

    Map<String, Integer> mostPopularCar = new HashMap<>();
    Map<String, Integer> leastPopularCar = new HashMap<>();

    Map<String, Integer> mostPopularMaker = new HashMap<>();
    Map<String, Integer> leastPopularMaker  = new HashMap<>();

    // prob to get the most least
    // i can jsut cycle and yknow set minmax outside loop and then reset whenever x is lower than min and when x is higher than max



    public int getTotalMilesValue() {
        return totalMilesValue;
    }

    public void setTotalMilesValue(int totalMilesValue) {
        this.totalMilesValue = totalMilesValue;
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

    public Map<String, Integer> getMostPopulatedStep() {
        return mostPopulatedStep;
    }

    public void setMostPopulatedStep(Map<String, Integer> mostPopulatedStep) {
        this.mostPopulatedStep = mostPopulatedStep;
    }

    public Map<String, Integer> getLeastPopulatedStep() {
        return leastPopulatedStep;
    }

    public void setLeastPopulatedStep(Map<String, Integer> leastPopulatedStep) {
        this.leastPopulatedStep = leastPopulatedStep;
    }

    public Map<String, Integer> getMostPopularCar() {
        return mostPopularCar;
    }

    public void setMostPopularCar(Map<String, Integer> mostPopularCar) {
        this.mostPopularCar = mostPopularCar;
    }

    public Map<String, Integer> getLeastPopularCar() {
        return leastPopularCar;
    }

    public void setLeastPopularCar(Map<String, Integer> leastPopularCar) {
        this.leastPopularCar = leastPopularCar;
    }

    public Map<String, Integer> getMostPopularMaker() {
        return mostPopularMaker;
    }

    public void setMostPopularMaker(Map<String, Integer> mostPopularMaker) {
        this.mostPopularMaker = mostPopularMaker;
    }

    public Map<String, Integer> getLeastPopularMaker() {
        return leastPopularMaker;
    }

    public void setLeastPopularMaker(Map<String, Integer> leastPopularMaker) {
        this.leastPopularMaker = leastPopularMaker;
    }

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
