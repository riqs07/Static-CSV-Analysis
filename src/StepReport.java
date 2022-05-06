import java.util.ArrayList;

public class StepReport {
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

    String name;
    int count;
    int milesAVG;
    int priceAVG;
    ArrayList<String> atoms = new ArrayList<>();

    public ArrayList<String> getAtoms() {
        return atoms;
    }

    public void setAtoms(ArrayList<String> atoms) {
        this.atoms = atoms;
    }
}

