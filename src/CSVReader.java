import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public static void main(String[] args) {



        // Find Relative File path of file you want to read
        String file = "./txtFiles/cars.csv";

        // Init Reader Object
        BufferedReader reader = null;
        String line = "";

        try {

            // feed reader the selected file
            reader = new BufferedReader(new FileReader(file) );

            // while there are more lines read the line
            while ((line = reader.readLine()) != null){


                /// separate values by ,
                String[] row = line.split(",");


                // print each item in the row and format
                for (String item : row){
                    System.out.printf("%-10s",item );
                }

                // go to a new line when at the end of the current row
                System.out.println();
            }

        } catch (Exception e){
            // handle exception
            e.printStackTrace();

        }
finally {
            // close reader
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
