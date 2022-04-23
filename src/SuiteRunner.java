import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class SuiteRunner {
    public static void main(String[] args)  throws Exception{
        SmokeReader s = new SmokeReader();

        ArrayList<String> locations = new ArrayList<>();
        locations = s.run("./txt/Smoke.txt");

        // use whatver is in the locations array list to go find file
        // then use apporrite reader to find next file
        // all the way down until we can see the car lot

        //need to find text file based on whatevr i rea d




        // find current working directory

        Path currentPath = Paths.get(System.getProperty("user.dir"));

        listDir(currentPath,0);

        /// recursion


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

    public static String spacesForDepth(int depth){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth ; i++) {
            builder.append("  ");
        }

        return builder.toString();
    }
}
