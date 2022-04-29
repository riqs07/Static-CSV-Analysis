import java.io.IOException;
import java.util.ArrayList;

public class SuiteRunner {


    public void run(String filepath) throws IOException {

        SmokeReader fileReader = new SmokeReader();
        CarMapper carMapper = new CarMapper();

        ArrayList<String> suite;

        ArrayList<String> routines = new ArrayList<>();
        ArrayList<String> functionalDictionary = new ArrayList<>();

        ArrayList<String> currentFuncStepList;
        ArrayList<String> currentAtoms;
        ArrayList<Car> atomData;


        String currentRoutine;
        String currentFunctionalStep = "";
        String currentAtom = "";

        ArrayList<Car> inventory = new ArrayList<>();


        String testLevel = fileReader.identifyTestLevel(filepath);


        switch (testLevel) {
            case "suite":
                this.runSuiteFileLogic(filepath, fileReader);
            case "steps":
                System.out.println("Running Step Logic");
                break;
            case "routines":
                System.out.println("Routine Identified");
                break;
        }

    }




    public void runSuiteFileLogic(String filepath, SmokeReader fileReader) throws IOException {
        System.out.println("<< TEST LEVEL: Suite");
        ArrayList<String> suite = fileReader.run(filepath);

        ArrayList<String> routines = fileReader.getRoutinesFromSuite(suite);

        ArrayList<String> steps = fileReader.getStepsFromRoutines(routines);

        ///  maybe arraysist of arraylissts
        ArrayList<String> atoms = fileReader.getAtomsFromSteps(steps);

        fileReader.turnAtomsIntoReport(atoms);

    }


    public static void main(String[] args)  throws Exception {


        SuiteRunner s = new SuiteRunner();

//        s.run("./txt/testSuites/Smoke.txt");
        s.run("./txt/suite/smoke.txt");
//        s.run("./txt/routines/north-carolina.txt");
//





    }

}
