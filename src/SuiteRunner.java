import java.io.IOException;
import java.util.ArrayList;

public class SuiteRunner {

/// TEsting 2 machine

    public static void run(String filepath) throws IOException {

        SmokeReader fileReader = new SmokeReader();
        CarMapper carMapper = new CarMapper();

        ArrayList<String> suite;

        ArrayList<String> routines = new ArrayList<>();
        ArrayList<String> functionalDictionary = new ArrayList<>();

        ArrayList<String> currentFuncStepList;
        ArrayList<String> currentAtomStepList;
        ArrayList<Car> atomData;




        String currentRoutine;
        String currentFunctionalStep = "";
        String currentAtom = "";

        ArrayList<Car> inventory = new ArrayList<>();





        suite = fileReader.run(filepath);

        System.out.println(">>> Getting Routines");
        // Get Test steps from Origin
        for (String routine : suite) {
            currentRoutine  = routine;

            // Check if in Routines File
            currentFuncStepList = fileReader.run("./txt/routines/" + routine + ".txt");

            if (currentFuncStepList != null) {
                routines.addAll(currentFuncStepList);
            }

            /// if it is null check to see if they exist in the next level down
            /// if they do add if not then dont

            if (currentFuncStepList == null){
                currentFuncStepList = fileReader.run("./txt/functionalSteps/" + routine + ".txt");

//                routines.addAll(currentFuncStepList);

                if (currentFuncStepList != null){
                    routines.addAll(currentFuncStepList);
                }
            }

            System.out.println(currentRoutine);
        }

        int count = 0;
        int sum = 0;

        int foo = 0;

        System.out.println(">>> Functional Runner Started >>>");

        ////// TEST STEP LEVEL //////

        for (String functionalStep : routines) {


            ////// FUNCTIONAL STEP LEVEL //////
            System.out.println(">>>>>> OPEN F STEP: "  + functionalStep + "  >>");
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
                        count += carMapper.analyzeList2(atomData);

                        /// Basically this loop is where I can run analysis on the chosen files
                        /// Analysis can then be streamed in any way after that
                        /// Also dictionary keeps track of everything

                    }
                }
            }

            System.out.println("<----- CLOSE F STEP ");
        }


    }

    public static void main(String[] args)  throws Exception {


        SuiteRunner s = new SuiteRunner();

//        s.run("./txt/testSuites/Smoke.txt");
        s.run("./txt/routines/north-carolina.txt");
//


    }

}
