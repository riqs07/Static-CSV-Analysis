import java.io.IOException;
import java.util.ArrayList;

public class SuiteRunner {



    public static void run(String filepath) throws IOException {

        SmokeReader fileReader = new SmokeReader();
        CarMapper carMapper = new CarMapper();

        ArrayList<String> testSuite;

        ArrayList<String> functionalSteps = new ArrayList<>();
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
                functionalSteps.addAll(currentFuncStepList);
            }

            // FOR EACH STEP IN TEST SUITE AKA STATE
            // RUN STEP BEFORE MOVING ON TO NEXT STEP

        }

        int count = 0;
        int sum = 0;

        int foo = 0;


        ////// FUNCTIONAL STEP LEVEL //////
        for (String functionalStep : functionalSteps) {

            // Reads Atom Data File
            currentAtomStepList = fileReader.run(("./txt/functionalSteps/" + functionalStep + ".txt"));

            if (currentAtomStepList != null) {
                functionalDictionary.addAll(currentAtomStepList);



                //// ATOM STEP LEVEL ////
                for (String atom : currentAtomStepList){

                    // Maps Atom to Workable POJO

                    atomData = carMapper.run(("./txt/atoms/" + atom + ".txt"));

                    if (atomData != null){
                        // go thru each file and compile stats
                        // pass stats up on level
                        foo = carMapper.analyzeList2(atomData);


                        // Bubble FStep Info from atom so
                    }

                }
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
