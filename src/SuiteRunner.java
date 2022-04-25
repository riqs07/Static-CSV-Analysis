import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class SuiteRunner {


    static ArrayList<String> testSuite = new ArrayList<>();
    static ArrayList<String> cities = new ArrayList<>();
    static ArrayList<String> dealers = new ArrayList<>();
    static ArrayList<Car> inventory = new ArrayList<>();


    public static void main(String[] args)  throws Exception {

        SmokeReader fileReader = new SmokeReader();
        CarMapper carMapper = new CarMapper();



        ArrayList<String> singleCityList;
        ArrayList<String> singleCityDealers;
        ArrayList<Car> singleDealerInventory;


        testSuite = fileReader.run("./txt/Smoke.txt");

        for (String testStep : testSuite) {
                singleCityList = fileReader.run("./txt/state/" + testStep);
                if (singleCityList != null) {
                    cities.addAll(singleCityList);
                }
        }


        for (String city : cities) {
            singleCityDealers = fileReader.run(("./txt/cities/" + city + ".txt"));
            if (singleCityDealers != null) {
                dealers.addAll(singleCityDealers);
            }
        }


        for (String dealer : dealers) {
            singleDealerInventory = carMapper.run(("./txt/dealerships/" + dealer + ".txt"));
            if (singleDealerInventory != null) {
                inventory.addAll(singleDealerInventory);
                System.out.println("---> " + dealer + " Inventory: " + carMapper.count(singleDealerInventory) + " -- Miles Avg:" + carMapper.avgMiles(singleDealerInventory)  + " -- Price Avg:" + carMapper.avgPrice(singleDealerInventory));
            }
        }

    }

}
