import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SmokeReader implements CSVReader {

    @Override
    public void read(String filepath) {

        BufferedReader reader;
        String line;


        try {
            reader = new BufferedReader(new FileReader(filepath));

            while ((line = reader.readLine()) != null) {

                String[] row = line.split(",");
                System.out.println(row[0]);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> run(String filepath) throws IOException {

        BufferedReader reader;
        String line;

        ArrayList<String> items = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(filepath));

        } catch (Exception e) {
// Alert
//            System.out.println(filepath + "  | File was not found. Are you sure it exists? Did you type it correctly? | Msg from Smoke Reader");

            return null;
        }

        while ((line = reader.readLine()) != null) {

            String[] row = line.split(",");

            items.add(row[0]);
        }

        return items;
    }

    public static void main(String[] args) {

    }
}
