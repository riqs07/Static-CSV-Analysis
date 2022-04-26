import java.util.ArrayList;

public class SuiteRunner {


    static ArrayList<String> testSuite = new ArrayList<>();
    static ArrayList<String> testSteps = new ArrayList<>();
    static ArrayList<String> functionalTestSteps = new ArrayList<>();
    static ArrayList<Car> inventory = new ArrayList<>();

    /// can make runner aware of all pojo java objects o

    static String currentTestStep= "";
    static String currentFunctionalStep = "";



    public static void main(String[] args)  throws Exception {

        SmokeReader fileReader = new SmokeReader();
        CarMapper carMapper = new CarMapper();



        ArrayList<String> citiesInState;
        ArrayList<String> dealersInCity;
        ArrayList<Car> singleDealerInventory;


        testSuite = fileReader.run("./txt/Smoke.txt");


        // Get Test steps from Origin
        for (String step : testSuite) {
            currentTestStep = step;
            citiesInState = fileReader.run("./txt/state/" + step);

                if (citiesInState != null) {
                    testSteps.addAll(citiesInState);
                }

        }


        // really need one to dig down at a time and start again at the top
        // rather then reading because thats a lot of loops mane
        //


        for (String functionalStep : testSteps) {

            currentFunctionalStep = functionalStep;


            dealersInCity = fileReader.run(("./txt/cities/" + functionalStep + ".txt"));



            if (dealersInCity != null) {
                functionalTestSteps.addAll(dealersInCity);
            }




        }

        // maybe need to do nested array??? so i have access to the current city and print time
        /// or can make it global or something like that
        /// just need to have asces to multple tears of objects
        // so at print time it cann be content aware of where stats are coming from


        ArrayList<Integer> singleInventoryStats ;

//        for (String dealer : babySteps) {
//            singleDealerInventory = carMapper.run(("./txt/dealerships/" + dealer + ".txt"));
//            if (singleDealerInventory != null) {
//                inventory.addAll(singleDealerInventory);
////                System.out.println("---> " + dealer + " Inventory: " + carMapper.count(singleDealerInventory) + " --> Miles Avg:" + carMapper.avgMiles(singleDealerInventory)  + " --> Price Avg: $" + carMapper.avgPrice(singleDealerInventory));
//                System.out.println(currentBabyStep + " " + currentTestStep);
////                singleInventoryStats = carMapper.analyzeList(singleDealerInventory);
//
//                if (singleDealerInventory != null){
//
//
//
//
//                }
//            }
//        }


        /// at end of test step level give mini report of state wide level
        // eventually add sorting
        // --> california inventory: 55 --> Miles Avg --> Price Avg




    }

}
