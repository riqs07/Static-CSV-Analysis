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


    public static void main(String[] args)  throws Exception{

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

        try {

            for (int i = 0; i < testSuite.size(); i++) {

              singleCityList = fileReader.run("./txt/state/" + testSuite.get(i));
              cities.addAll(singleCityList);
            }

        } catch (Exception e){
            e.printStackTrace();
        }





        System.out.println(cities);


    }




    public static void listDir(Path path, int depth) throws Exception {


        BasicFileAttributes attr = Files.readAttributes(path,BasicFileAttributes.class);

        // If its a directory, list every file inside files , and travese down recursivly
        if (attr.isDirectory()){
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);

            System.out.println(spacesForDepth(depth) + "-> " + path.getFileName());

            for (Path p : paths) {
                listDir(p, depth + 1);
            }


        } else {
            // if not a directory ie a normal file
            // print it


            System.out.println(spacesForDepth(depth) + "--" + path.getFileName());
        }
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


    public static void fileToArrayList(Path p) throws Exception{

    }










    public static String spacesForDepth(int depth){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth ; i++) {
            builder.append("  ");
        }

        return builder.toString();
    }
}
