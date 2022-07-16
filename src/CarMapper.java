import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CarMapper {

    // Raw File Input will be useful for non suite runs
    public void read(String filepath) {
        /// Read CSV File
        /// Map them to java car objects
        /// then read + output just car objects

        String file = fitlepath;

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

        try {

            reader = new BufferedReader(new FileReader(file));

        } catch (Exception e) {
//            e.printStackTrace();
            // Alert
//            System.out.println(filepath + "  | File was not found. Are you sure it exists? Did you type it correctly? | Msg from Car Mapper");

            return null;
        }


        while ((line = reader.readLine()) != null) {

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


        Collections.sort(cars);
        return cars;
    }

    // Analyze List of POJOs
    public AtomReport analyzeAtom(ArrayList<Car> cars) {
        // i mean i guess i canp ull the analysis intto seperate funcs but this is ok for now
        // 6-5 OKAY OFFICALY THIS METHOD IS WAY TO HUGE NEEDS REFREACTOR

        AtomReport stats = new AtomReport();

        Integer priceSum = 0, milesSum = 0, count = 0;
        double priceDeviation = 0, milesDeviation = 0;

        /// since i am getting it in the quartiles method IDK if i still need to declare them right here
        int priceMedian = 0, priceMin= 0, priceMax = 0;
        int milesMedian = 0 ,milesMin = 0, milesMax = 0;


        HashMap<String, Map<String, Integer>> carLotMap = new HashMap<>();
        HashMap<String, Integer> carMakesMap = new HashMap<>();
        HashMap<String,Integer> carModelsMap = new HashMap<>();

        Collections.sort(cars, Comparator.comparing(Car::getPriceAsInteger));

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

        // Create Make & Models Map
        for (String currentMake : carLotMap.keySet()) {
            /// Get all models by that Maker


            for (String currentModel : carLotMap.get(currentMake).keySet()) {

                int currentModelTotal = 0;
                // Start the make counter
                if (carLotMap.get(currentMake).containsKey(currentModel)) {
                    // if make already exists get current total and add onto it


                    /// this is the num of each model so right here i take this number and put it itno model mao
                    // and just add onto make map
                    currentModelTotal = carLotMap.get(currentMake).get(currentModel);

                    if (carMakesMap.containsKey(currentMake)) {
                        // get cur value and add to

                        int runningMakeTotal = carMakesMap.get(currentMake);

                        runningMakeTotal += currentModelTotal;

                        carMakesMap.put(currentMake, runningMakeTotal);

                    } else {
                        // if not exists add to mapp
                        carMakesMap.put(currentMake, currentModelTotal);
                    }


                 ////////////////////////////////////////////////////////////////

                    // Create a Map of just the Model count
                    if (carModelsMap.containsKey(currentModel)) {
                        // get cur value and add to

                        int runningModelTotal = 0;

                        runningModelTotal += currentModelTotal;
                        carModelsMap.put(currentModel, runningModelTotal);

                    } else {
                        // if not exists add to mapp
                        carModelsMap.put(currentModel, currentModelTotal);
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

            int[] priceQuartiles = getPriceQuartiles(cars);
            int[] milesQuartiles = getMilesQuartiles(cars);

            // Build Report
            stats.setMilesAVG(milesSum / count);
            stats.setPriceAVG(priceSum / count);
            stats.setCount(count);
            stats.setMilesSUM(milesSum);
            stats.setPriceSUM(priceSum);
            stats.setMilesDeviation(milesDeviation);
            stats.setPriceDeviation(priceDeviation);


            stats.setLotValue(priceSum);
            stats.setCarLotMap(carLotMap);

            stats.setCarMakesMap(carMakesMap);
            stats.setCarModelsMap(carModelsMap);


            // NOt sure if i want to keep its "cleaner" but less desciprtive
            // maybe in the fewtch i will set min and max to the variable i declard but IDK for right now
            stats.setMinPrice(priceQuartiles[0]);
            stats.setPrice1stQuartile(priceQuartiles[1]);
            stats.setPriceMedian(priceQuartiles[2]);
            stats.setPrice3rdQuartile(priceQuartiles[3]);
            stats.setMaxPrice(priceQuartiles[4]);
            int priceRange = priceQuartiles[4]- priceQuartiles[0];


            stats.setMinMiles(milesQuartiles[0]);
            stats.setMiles1stQuartile(milesQuartiles[1]);
            stats.setMilesMedian(milesQuartiles[2]);
            stats.setMiles3rdQuartile(milesQuartiles[3]);
            stats.setMaxMiles(milesQuartiles[4]);
            int milesRange = milesQuartiles[4]- milesQuartiles[0];


            stats.setPriceRange(priceRange);
            stats.setMilesRange(milesRange);


        }

        return stats;

    }

    public int[] getPriceQuartiles(ArrayList<Car> cars){
        Collections.sort(cars, Comparator.comparing(Car::getPriceAsInteger));

        // can prob get min-max stats here since i am sorting
        // and return the 5 number summar

        int min = cars.get(0).getPriceAsInteger();

        int max = cars.get(cars.size() -1).getPriceAsInteger();
        int firstQuartile = 0;
        int median = 0;
        int thirdQuartile = 0;


        int medianIndex = 0;

        int count = cars.size();

        List<Car> priceLowerHalf = null;
        List<Car> priceUpperHalf = null;

        int left = 0;
        int right = 0;


        /// SPLIT INTO 2 SUBLISTS BASED ON MEDIAN
        if (count % 2 == 0) {

            // SET L+R POINTERS
             left = (count / 2) - 1;
             right = (count / 2) + 1;

             median = (cars.get(left).getPriceAsInteger() + cars.get(right).getPriceAsInteger()) / 2;
             medianIndex = (left + right) / 2;

             priceLowerHalf = cars.subList(0,left + 1);
             priceUpperHalf = cars.subList(right,count);


/// should sepearte median index from median number
        } else {
            medianIndex = count/2;
            median = cars.get(medianIndex).getPriceAsInteger();
            priceLowerHalf = cars.subList(0, medianIndex);
            priceUpperHalf = cars.subList(medianIndex,count);
        }

        // If lower half is even
        if (priceLowerHalf.size() % 2 == 0){
            // Reset L+R pointers
            left = (priceLowerHalf.size()/2) -1;
            right = (priceLowerHalf.size()/2) + 1;
            System.out.println(left);
            System.out.println(right);


            // OK so the issue occurs if i dont have neough cars in the lot. this one threw an error at 4 cars
            // prob need exception handling all thru application
            // current minumum to not break is 7
            // prbably to do with the way i have the algo splitting it in half
            firstQuartile = (priceLowerHalf.get(left).getPriceAsInteger() + priceLowerHalf.get(right).getPriceAsInteger()) / 2;
        } else {
            // 1st quartile rounds down needs fixing --> integer division
            firstQuartile = priceLowerHalf.get(priceLowerHalf.size()/2).getPriceAsInteger();
        }

        // If upper half is even
        if (priceUpperHalf.size() % 2 == 0){
            // Reset L+R pointers
            left = (priceUpperHalf.size()/2) -1;
            right = (priceUpperHalf.size()/2) + 1;
            thirdQuartile = (priceUpperHalf.get(left).getPriceAsInteger() + priceUpperHalf.get(right).getPriceAsInteger()) /2 ;
        } else {
            thirdQuartile = priceUpperHalf.get(priceUpperHalf.size()/2).getPriceAsInteger();
        }

        return new int[] {min,firstQuartile,median,thirdQuartile,max};
    }

    public int[] getMilesQuartiles(ArrayList<Car> cars){

        Collections.sort(cars, Comparator.comparing(Car::getMilesAsInteger));

        int min = cars.get(0).getMilesAsInteger();

        int max = cars.get(cars.size() -1).getMilesAsInteger();
        int firstQuartile = 0;
        int median = 0;
        int thirdQuartile = 0;

        int medianIndex = 0;

        int count = cars.size();

        List<Car> milesLowerHalf = null;
        List<Car> milesUpperHalf = null;

        int left = 0;
        int right = 0;


        /// SPLIT INTO 2 SUBLISTS BASED ON MEDIAN
        if (count % 2 == 0) {

            // SET L+R POINTERS
             left = (count / 2) - 1;
             right = (count / 2) + 1;

             median = (cars.get(left).getMilesAsInteger() + cars.get(right).getMilesAsInteger()) / 2;
             medianIndex = (left + right) / 2;

             milesLowerHalf = cars.subList(0,left + 1);
             milesUpperHalf = cars.subList(right,count);


        } else {
            medianIndex = count/2;
            median = cars.get(medianIndex).getMilesAsInteger();
            milesLowerHalf = cars.subList(0, medianIndex);
            milesUpperHalf = cars.subList(medianIndex,count);
        }




        // If lower half is even
        if (milesLowerHalf.size() % 2 == 0){
            // Reset L+R pointers
            left = (milesLowerHalf.size()/2) -1;
            right = (milesLowerHalf.size()/2) + 1;
            firstQuartile = (milesLowerHalf.get(left).getMilesAsInteger() + milesLowerHalf.get(right).getMilesAsInteger())/2;
        } else {
            firstQuartile = milesLowerHalf.get(milesLowerHalf.size()/2).getMilesAsInteger();
        }

        // If upper half is even
        if (milesUpperHalf.size() % 2 == 0){
            // Reset L+R pointers
            left = (milesUpperHalf.size()/2) -1;
            right = (milesUpperHalf.size()/2) + 1;
            thirdQuartile = (milesUpperHalf.get(left).getMilesAsInteger() + milesUpperHalf.get(right).getMilesAsInteger()) / 2;
        } else {
            thirdQuartile = milesUpperHalf.get(milesUpperHalf.size()/2).getMilesAsInteger();
        }

        return new int[] {min,firstQuartile,median,thirdQuartile,max};
    }

    public static void main(String[] args) {

        CarMapper c = new CarMapper();

        c.read("./txt/atoms/san-diego-1.txt");
    }


}
