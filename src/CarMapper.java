import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CarMapper {

    // Raw File Input
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

                cars.add(new Car(make, model, year, miles, price));
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

    // Map CSV to POJOs
    public ArrayList<Car> map(String filepath) throws IOException {

        String file = filepath;

        BufferedReader reader;
        String line = "";


        ArrayList<Car> cars = new ArrayList<>();

        Map<String, Integer> carMakeStats = new HashMap<>();


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

            cars.add(new Car(make, model, year, miles, price));


            // check to see if make exists if it does increase count
            // if not then add to map

            carMakeStats.put(make, 1);

        }


        Collections.sort(cars);
        return cars;
    }

    // Analyze List of POJOs
    public AtomReport analyzeAtom(ArrayList<Car> cars) {
        // i mean i guess i canp ull the analysis intto seperate funcs but this is ok for now

        AtomReport stats = new AtomReport();

        Integer priceSum = 0, milesSum = 0, count = 0;
        double priceDeviation = 0, milesDeviation = 0;
        int priceMedian = 0;
        int milesMedian = 0;


        int currentMakeTotal = 0;

        Collections.sort(cars, Comparator.comparing(Car::getPriceAsInteger));

        HashMap<String, Map<String, Integer>> carLotMap = new HashMap<>();
        HashMap<String, Integer> carModelsMap = new HashMap<>();



        // Get Sums & Create Hashmap
        for (Car car : cars) {
            milesSum += Integer.parseInt(car.miles);
            priceSum += Integer.parseInt(car.price);
            count++;


            String currentCarMake = car.getMake();
            String currentCarModel = car.getModel();


            // Car Map
            if (carLotMap.containsKey(currentCarMake)) {

                // Make exists , model exists, increment
                if (carLotMap.get(currentCarMake).containsKey(currentCarModel)) {

                    int amount = carLotMap.get(currentCarMake).get(currentCarModel);

                    carLotMap.get(currentCarMake).put(currentCarModel, amount + 1);

                } else {

                    // Make exists , model not exists, put new
                    carLotMap.get(currentCarMake).put(currentCarModel, 1);
                }


            } else {
                // Make not exists , place new
                HashMap<String, Integer> currentCarMap = new HashMap<>();
                currentCarMap.put(currentCarModel, 1);
                carLotMap.put(currentCarMake, currentCarMap);
            }

        }


        // For each Make in the CarLotMap
        for (String currentMake : carLotMap.keySet()) {
            /// Get all models by that Maker
            for (String currentModel : carLotMap.get(currentMake).keySet()) {
                // Start the counter
                if (carLotMap.get(currentMake).containsKey(currentModel)) {
                    // if make already exists get current total and add onto it

                    currentMakeTotal = carLotMap.get(currentMake).get(currentModel);

                    if (carModelsMap.containsKey(currentMake)) {
                        // get cur value and add to

                        int runningModelTotal = carModelsMap.get(currentMake);

                        runningModelTotal += currentMakeTotal;
                        carModelsMap.put(currentMake, runningModelTotal);

                    } else {
                        // if not exists add to mapp
                        carModelsMap.put(currentMake, currentMakeTotal);
                    }

                }
            }

        }


        // Get standard Deviations
        for (Car car : cars) {
            priceDeviation += Math.pow(car.getPriceAsInteger() - (priceSum / count), 2);
            milesDeviation += Math.pow(car.getMilesAsInteger() - (milesSum / count), 2);
        }


        priceDeviation = Math.sqrt(priceDeviation / count);
        milesDeviation = Math.sqrt(milesDeviation / count);


        // prob need to find a better way to skip empty files
        // Build Report
        if (count > 0) {
            // Get Price Range
            int priceRange = cars.get(count - 1).getPriceAsInteger() - cars.get(0).getPriceAsInteger();
//

            // get medians
            if (count % 2 == 0) {

                int left = (count / 2) - 1;
                int right = (count / 2) + 1;

                priceMedian = (cars.get(left).getPriceAsInteger() + cars.get(right).getPriceAsInteger()) / 2;
                milesMedian = (cars.get(left).getPriceAsInteger() + cars.get(right).getPriceAsInteger()) / 2;

            } else {
                priceMedian = cars.get(count / 2).getPriceAsInteger();
                milesMedian = cars.get(count / 2).getPriceAsInteger();

            }


            // Build Report
            stats.setMilesAVG(milesSum / count);
            stats.setPriceAVG(priceSum / count);
            stats.setCount(count);
            stats.setMilesSUM(milesSum);
            stats.setPriceSUM(priceSum);
            stats.setMilesDeviation(milesDeviation);
            stats.setPriceDeviation(priceDeviation);
            stats.setPriceRange(priceRange);
            stats.setPriceMedian(priceMedian);
            stats.setMilesMedian(milesMedian);
//            stats.setMilesRange(milesRange);
            stats.setCarsMap(carLotMap);

//            stats.setMaxMiles();
//            stats.setMinMiles();
//            stats.setMaxPrice();
//            stats.setMinPrice();
        }

        return stats;

    }


    public static void main(String[] args) {

        CarMapper c = new CarMapper();

        c.read("./txt/atoms/san-diego-1.txt");
    }


}
