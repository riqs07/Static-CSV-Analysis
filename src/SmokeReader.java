import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class SmokeReader implements CSVReader {

    // Reads file and outputs to console.
    @Override
    public void read(String filepath) {

        BufferedReader reader;
        String line;


        try {
            reader = new BufferedReader(new FileReader(filepath));

            while ((line = reader.readLine()) != null){

                String[] row = line.split(",");
                System.out.println(row[0]);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Reads file and delivers info for next level Reader
    public ArrayList<String> run(String filepath){

        BufferedReader reader;
        String line;

        ArrayList<String> items = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(filepath));

            while ((line = reader.readLine()) != null){

                String[] row = line.split(",");

                items.add(row[0]);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    public static void main(String[] args) {

        SmokeReader s = new SmokeReader();

        s.read("./txt/Smoke.txt");
    }
}
