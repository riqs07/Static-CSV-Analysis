import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

//// SOMEONE i am feeding int state and not the lines
// need more time figuinrg out recursion
public class SuiteRunner {


    static int count = 0;
    static ArrayList<String> testSuite = new ArrayList<>();
    static ArrayList<String> stateOptions = new ArrayList<>();
    static ArrayList<String> cities = new ArrayList<>();
    static ArrayList<String> dealers = new ArrayList<>();
    static ArrayList<Car> inventory = new ArrayList<>();


    public static void main(String[] args)  throws Exception {

        SmokeReader fileReader = new SmokeReader();

        // turn smoke file into arraylist
        testSuite = fileReader.run("./txt/Smoke.txt");

        //  dig into next level aka State
        Path currentPath = Paths.get("./txt/state");

        // GO TO Directory and find all files in there and add no array list
        directoryToArrayList(currentPath);


        // for each item in states list
        // find which ones match in state directory
        // if match found then print cities in that file

        ArrayList<String> singleCityList = new ArrayList<>();

        // Outputs all cities in Given state(s)


        for (int i = 0; i < testSuite.size(); i++) {


            try {
                singleCityList = fileReader.run("./txt/state/" + testSuite.get(i));
                if (singleCityList != null){
                    cities.addAll(singleCityList);
                }

                if (singleCityList == null){
                    System.out.println("okkk ");
                }

            } catch (Exception e){

            }

            /// Finds all Dealearships in given city


        }


        /// ootu put all dealers in given city
        ArrayList<String> singleCityDealers = new ArrayList<>();


        for (int j = 0; j < cities.size(); j++) {

            singleCityDealers = fileReader.run(("./txt/cities/" + cities.get(j) + ".txt"));
            if (singleCityDealers != null){
                dealers.addAll(singleCityDealers);

            }

        }


        ArrayList<Car> singleDealerInventory = new ArrayList<>();

        for (int k =0; k < dealers.size(); k++){
            ///check if the file exists if it does
            // map it to car object
            // carmapper can track ovarall stats

            CarMapper carMapper = new CarMapper();

         singleDealerInventory =  carMapper.run(("./txt/dealerships/" + dealers.get(k) + ".txt"));
            if (singleDealerInventory != null){
                inventory.addAll(singleDealerInventory);
            }
        }



        System.out.println(inventory);
    }



    public static void directoryToArrayList(Path path) throws Exception {


        // turns a CSV To Array list,
        BasicFileAttributes attr = Files.readAttributes(path,BasicFileAttributes.class);

        if (attr.isDirectory()){
            // Drill Down if directory
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);


            for (Path p : paths) {
                directoryToArrayList(p);
            }


        } else {

            // ADD File to list
            count++;
            stateOptions.add(String.valueOf(path.getFileName()));
        }



    }




}
