import java.io.IOException;
import java.util.ArrayList;

public class SuiteRunner {

/// Core Version just in case
    /// shwoing each step but failing because of divide by zero

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

        // Get Test steps from Origin
        for (String step : testSuite) {
            currentTestStep = step;
            currentFuncStepList = fileReader.run("./txt/testSteps/" + step);


            if (currentFuncStepList != null) {
                testSteps.addAll(currentFuncStepList);
            }

            // FOR EACH STEP IN TEST SUITE AKA STATE
            // RUN STEP BEFORE MOVING ON TO NEXT STEP

        }

        Integer[] foo;

        ////// TEST STEP LEVEL //////

        for (String functionalStep : testSteps) {

            ////// FUNCTIONAL STEP LEVEL //////

            int count = 0;
            int psum = 0;
            int msum = 0;

            currentFunctionalStep = functionalStep;

            // Reads Atom Data File
            currentAtomStepList = fileReader.run(("./txt/functionalSteps/" + functionalStep + ".txt"));

            if (currentAtomStepList != null) {
                functionalDictionary.addAll(currentAtomStepList);


                System.out.println("---> F Step: " + currentFunctionalStep);

                //// ATOM STEP LEVEL ////
                for (String atom : currentAtomStepList){

                    // Maps Atom to Workable POJO

                    currentAtom = atom;
                    atomData = carMapper.run(("./txt/atoms/" + atom + ".txt"));

                    if (atomData != null){
                        // go thru each file and compile stats
                        // pass stats up on level
                        foo = carMapper.analyzeList2(atomData);

                        // def should make an object or key value pair or something
                        // just cause pulling out random array is fonna look weird
                        // will be oay for now
                        count += foo[0];
                        psum += foo[1];
                        msum += foo[2];


//                        System.out.println(count + " " + "cars");
                        /// fix / by zero later



                        // Bubble FStep Info from atom so
                    }

                    System.out.println("-----> A Step: " + currentAtom);
                }
                System.out.println(functionalStep + " report >>>>" + " Cars " + count + " PAVG: $" + psum/count + " MAVG:" + msum/count );

            }




            // LIST of POJO Car objects
//            if (singleDealerInventory != null) {
//                inventory.addAll(singleDealerInventory);
////                System.out.println("---> " + atom + " Inventory: " + carMapper.count(singleDealerInventory) + " --> Miles Avg:" + carMapper.avgMiles(singleDealerInventory)  + " --> Price Avg: $" + carMapper.avgPrice(singleDealerInventory));
////                System.out.println(functionalDictionary + " " + currentTestStep);
//////                singleInventoryStats = carMapper.analyzeList(singleDealerInventory);
//
//
//            }




        }

        System.out.println(functionalDictionary);

        ArrayList<Integer> singleInventoryStats ;

//        for (String functionalTestStep : functionalDictionary) {
//            atomData = carMapper.run(("./txt/atoms/" + functionalTestStep + ".txt"));
//            if (atomData != null) {
////                inventory.addAll(singleDealerInventory);
////                System.out.println("---> " + functionalTestStep + " Inventory: " + carMapper.count(singleDealerInventory) + " --> Miles Avg:" + carMapper.avgMiles(singleDealerInventory)  + " --> Price Avg: $" + carMapper.avgPrice(singleDealerInventory));
////                System.out.println(functionalDictionary + " " + currentTestStep);
////                singleInventoryStats = carMapper.analyzeList(singleDealerInventory);
//
//                if (atomData != null){
//
//                }
//            }
//        }



    }

    public static void main(String[] args)  throws Exception {


        SuiteRunner s = new SuiteRunner();

        s.run("./txt/testSuites/Smoke.txt");


    }

}
