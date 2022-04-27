import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CarMapper implements CSVReader {

    @Override
    public void read(String filepath) {
        /// Read CSV File
        /// Map them to java car objects
        /// then read + output just car objects


        String file = filepath;

        // Init Reader Object
        BufferedReader reader = null;
        String line = "";


        ArrayList<Car> cars = new ArrayList<>();

        try {

            reader = new BufferedReader(new FileReader(file));

            while ((line = reader.readLine()) != null) {


                /// Puts each line into a new row array
                /// map array to a cars object

                String[] row = line.split(",");
                if (row[0].equalsIgnoreCase("make"))
                    continue;

                String make = row[0];
                String model = row[1];
                String year = row[2];
                String miles = row[3];
                String price = row[4];

                cars.add(new Car(make, model, year, miles,price));
            }

            /// Sort using our custom comparator in car class

            Collections.sort(cars);


            System.out.println(cars);


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Car> run(String filepath) throws IOException {

        String file = filepath;

        BufferedReader reader;
        String line = "";


        ArrayList<Car> cars = new ArrayList<>();

        try {

            reader = new BufferedReader(new FileReader(file));

        } catch (Exception e) {
//            e.printStackTrace();
            // Alert
//            System.out.println(filepath + "  | File was not found. Are you sure it exists? Did you type it correctly? | Msg from Car Mapper");


            return null;
        }


        while ((line = reader.readLine()) != null) {


            /// Puts each line into a new row array
            /// map array to a cars object

            String[] row = line.split(",");
            if (row[0].equalsIgnoreCase("make"))
                continue;

            String make = row[0];
            String model = row[1];
            String year = row[2];
            String miles = row[3];
            String price = row[4];

            cars.add(new Car(make, model, year, miles,price));
        }


        Collections.sort(cars);

        return cars;
    }

    public long count(ArrayList<Car> cars){

        return cars.size();
    }


    public long avgMiles(ArrayList<Car> cars){

        long sum = 0;
        long count =0;

        for(Car car:cars){

            sum += Integer.parseInt(car.miles);
            count++;
        }


        if (count == 0){
            return sum/1;
        }

        return sum/count;
    }


    public long avgPrice(ArrayList<Car> cars){

        long sum = 0;
        long count =0;

        for(Car car:cars){

            sum += Integer.parseInt(car.price);
            count++;
        }


        if (count == 0){
            return sum/1;
        }

        return sum/count;
    }

    public ArrayList<Integer> analyzeList(ArrayList<Car> cars){


        ArrayList<Integer> listStats = new ArrayList<>();

        Integer priceSum = 0;
        Integer milesSum = 0;
        Integer count = 0;

        for(Car car:cars){

            milesSum += Integer.parseInt(car.miles);
            priceSum += Integer.parseInt(car.price);
            count++;
        }

        if (count == 0){
            return null;
        }

        listStats.add(milesSum/count);
        listStats.add(priceSum/count);


        /// maybe this should be a map but like as  to be content aware and not a random list of numbers
        /// but like as a pogram and in terms of getting it done
        /// having it just be an array like this is quick and dirty
        /// may even keep it as a string but that may be a bridge to far



        return listStats;
    };
 public int analyzeList2(ArrayList<Car> cars){


        ArrayList<Integer> listStats = new ArrayList<>();

        Integer priceSum = 0;
        Integer milesSum = 0;
        Integer count = 0;

        for(Car car:cars){

            milesSum += Integer.parseInt(car.miles);
            priceSum += Integer.parseInt(car.price);
            count++;
        }


        if (count == 0){
            return 1;
        }

        listStats.add(milesSum/count);
        listStats.add(priceSum/count);




        /// maybe this should be a map but like as  to be content aware and not a random list of numbers
        /// but like as a pogram and in terms of getting it done
        /// having it just be an array like this is quick and dirty
        /// may even keep it as a string but that may be a bridge to far

return count;

    };

    //same as the averages but only loops thru once and analayzes all at the same time
    // Return type ?
    // array of string ans


    public static void main(String[] args) {

        CarMapper c = new CarMapper();

        c.read("./txt/cars.txt");
    }


}
