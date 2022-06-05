import java.util.ArrayList;
import java.util.HashMap;

public class StepReport {
    String name;
    int count;
    int milesAVG;
    int priceAVG;
    int stepTotalValue;

    public ArrayList<HashMap<String, Integer>> getCarMakesMaps() {
        return carMakesMaps;
    }

    public void setCarMakesMaps(ArrayList<HashMap<String, Integer>> carMakesMaps) {
        this.carMakesMaps = carMakesMaps;
    }

    public ArrayList<HashMap<String, Integer>> getCarModelsMaps() {
        return carModelsMaps;
    }

    public void setCarModelsMaps(ArrayList<HashMap<String, Integer>> carModelsMaps) {
        this.carModelsMaps = carModelsMaps;
    }

    // probably just want the aggragrate on the step reoprt
    // i mean i guess i can attach the maps as well but still
    // they dont need to be a list here
    // list can just be used inside of the method to help with the aggregate
    // esp considering that i am attaching the atom report

    ArrayList<HashMap<String,Integer>> carMakesMaps = new ArrayList<>();
    ArrayList<HashMap<String,Integer>> carModelsMaps = new ArrayList<>();
    ArrayList<String> atoms = new ArrayList<>();
    ArrayList<AtomReport> atomReports = new ArrayList<>();

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



    // total count, "avg car"
    // list of atom report
    // combine the maps into one

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

