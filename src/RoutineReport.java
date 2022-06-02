import java.util.ArrayList;


public class RoutineReport {

    String name;
    ArrayList<String> steps = new ArrayList<>();
    ArrayList<String> atoms = new ArrayList<>();
    int count;
    int priceAVG;
    int milesAVG;

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
    // north carolina
    // vehicle count
    // avg
    // can even get fancy in the future and find like most popular stores
    // et
}
