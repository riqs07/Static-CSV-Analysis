import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CarMapper  {

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

    public ArrayList<Car> map(String filepath) throws IOException {

        String file = filepath;

        BufferedReader reader;
        String line = "";


        ArrayList<Car> cars = new ArrayList<>();

        Map<String,Integer> carMakeStats = new HashMap<>();






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


            // check to see if make exists if it does increase count
            // if not then add to map

            carMakeStats.put(make,1);

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

 public AtomReport analyzeAtom(ArrayList<Car> cars){

     // this dosent really need to make the object does it?
     // thic can just analyzse stream and give data points while
     // level above attaches name to map

        AtomReport stats = new AtomReport();

        Integer priceSum = 0, milesSum = 0, count = 0;
        double priceDeviation = 0, milesDeviation = 0;


          Collections.sort(cars, Comparator.comparing(Car ::getPriceAsInteger));


     for(Car car:cars){

         milesSum += Integer.parseInt(car.miles);
         priceSum += Integer.parseInt(car.price);
         count++;
     }







//Integer range = cars.get(1).getPriceAsInteger() - cars.get(0).getPriceAsInteger();
//     System.out.println(range);







//Integer median = 0;
//
//if (count % 2 ==1 ){
//median = count/2;
//} else {
//    median = (a[n/2-1]+a[n/2])/2
//}







        /// keep track of highest value & lowest value // use to get range
        /// get median price and miles
     /// use that to get standard deviation

     // sortby price and by rnage
     /// need comparators






     for(Car car: cars) {
         priceDeviation += Math.pow(car.getPriceAsInteger() - (priceSum/count), 2);
         milesDeviation += Math.pow(car.getMilesAsInteger() - (milesSum/count),2);
     }


    priceDeviation = Math.sqrt(priceDeviation / count) ;
     milesDeviation = Math.sqrt(milesDeviation / count);


     if (count > 0){
         stats.setMilesAVG(milesSum/count);
         stats.setPriceAVG(priceSum/count);
         stats.setCount(count);
         stats.setMilesSUM(milesSum);
         stats.setPriceSUM(priceSum);
         stats.setMilesDeviation(milesDeviation);
         stats.setPriceDeviation(priceDeviation);
     }

return stats;

    };

    //same as the averages but only loops thru once and analayzes all at the same time
    // Return type ?
    // array of string ans


    public static void main(String[] args) {

        CarMapper c = new CarMapper();

        c.read("./txt/cars.txt");
    }


}
