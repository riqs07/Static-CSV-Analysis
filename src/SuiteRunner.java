import java.io.IOException;
import java.util.ArrayList;

public class SuiteRunner {

/// TEsting 2 machine

    public static void run(String filepath) throws IOException {

        SmokeReader fileReader = new SmokeReader();
        CarMapper carMapper = new CarMapper();

        ArrayList<String> testSuite;

        ArrayList<String> testSteps = new ArrayList<>();
        ArrayList<String> functionalDictionary = new ArrayList<>();

        ArrayList<String> currentFuncStepList;
        ArrayList<String> currentAtomStepList;
        ArrayList<Car> atomData;




        String currentTestStep= "";
        String currentFunctionalStep = "";
        String currentAtom = "";

        ArrayList<Car> inventory = new ArrayList<>();





        testSuite = fileReader.run(filepath);

        System.out.println(">>> Getting Tests");
        // Get Test steps from Origin
        for (String step : testSuite) {
            currentTestStep = step;
            currentFuncStepList = fileReader.run("./txt/testSteps/" + step);


            if (currentFuncStepList != null) {
                testSteps.addAll(currentFuncStepList);
            }

            System.out.println(currentTestStep);
        }

        int count = 0;
        int sum = 0;

        int foo = 0;

        System.out.println(">>> Runner Started >>>");

        ////// TEST STEP LEVEL //////

        for (String functionalStep : testSteps) {


            ////// FUNCTIONAL STEP LEVEL //////
            System.out.println(">>>>>> F STEP: "  + functionalStep + "  >>");
            currentFunctionalStep = functionalStep;

            // Reads Atom Data File
            currentAtomStepList = fileReader.run(("./txt/functionalSteps/" + functionalStep + ".txt"));

            if (currentAtomStepList != null) {
                functionalDictionary.addAll(currentAtomStepList);

                //// ATOM STEP LEVEL ////
                for (String atom : currentAtomStepList){

                    System.out.println(">> A: "  + atom);
                    currentAtom = atom;

                    // Maps Atom to Workable POJO
                    atomData = carMapper.run(("./txt/atoms/" + atom + ".txt"));

                    if (atomData != null){

                        // prototype analysis
                        count = carMapper.analyzeList2(atomData);

                        /// Basically this loop is where I can run analysis on the chosen files
                        /// Analysis can then be streamed in any way after that
                        /// Also dictionary keeps track of everything

                    }
                }
            }

            System.out.println(">>>>>>>>");
        }


    }

    public static void main(String[] args)  throws Exception {


        SuiteRunner s = new SuiteRunner();

        s.run("./txt/testSuites/Smoke.txt");


    }

}
