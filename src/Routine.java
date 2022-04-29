import java.util.ArrayList;

public class Routine {
    ArrayList<String> steps;
    int amountOfSteps;
    String name;

    public Routine(ArrayList<String> steps){
        steps = steps;
        amountOfSteps = steps.size();
    }
}
