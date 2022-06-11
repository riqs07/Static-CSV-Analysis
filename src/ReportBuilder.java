import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class ReportBuilder implements CSVReader {

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

    public String identifyTestLevel(String filepath){

        // Deletes leading ".txt"
        String xx = filepath.substring(6);
        // Deletes trailing /example.txt
        String level = xx.split("/")[0];


        System.out.println(">>> Getting Test lvl of " + filepath);

        return level;
    };

    public ArrayList<String> getRoutinesFromSuite(ArrayList<String> suite) throws IOException {

        ArrayList<String> raw = new ArrayList<>();
        ArrayList<String> routines = new ArrayList<>();
        int count = 0;
        for (String routine : suite) {

            raw = this.run("./txt/routines/" + routine + ".txt");

            if (raw != null) {
                count++;
//                System.out.println(">>> " + routine + " " + count + " >>> Routines Identified");
                routines.add(routine);
            }

        }
        return routines;
    }


    public ArrayList<String> getStepsFromRoutines(ArrayList<String> routine) throws IOException {

        // maybe dictonary object with step name as key and size as value
        // may want to keep counter so it knows where it is in program



        ArrayList<String> raw;
        ArrayList<String> steps = new ArrayList<>();
        ArrayList<Integer> routineSizeDictionary = new ArrayList<>();


        for (String step : routine){
           raw = this.getStepsFromRoutine(step);
           steps.addAll(raw);
        }




//        System.out.println(steps);
//        System.out.println(routineSizeDictionary);
        return steps;
    }


    public ArrayList<String> getStepsFromRoutine(String routine) throws IOException {
        ArrayList<String> raw = this.run("./txt/routines/" + routine + ".txt");

        if (raw != null) {
            return raw;
        }
        return  null;
    }



    public ArrayList<String> getAtomsFromSteps(ArrayList<String> steps) throws IOException {

        ArrayList<String> atoms = new ArrayList();

        ArrayList<String> raw;

        // may want to keep counter so it knows where it is in program
        for (String step : steps){

            atoms.addAll(getAtomsFromStep(step));
        }

        return atoms;
    }

    public ArrayList<String> getAtomsFromStep(String step) throws IOException {

        ArrayList<String> atoms = new ArrayList();

        ArrayList<String> raw;
        raw = this.run("./txt/steps/" + step + ".txt");

        if (raw != null) {
            atoms.addAll(raw);
        }

        return atoms;
    }

    public AtomReport getAtomReport(String atom, CarMapper carMapper) throws IOException {

        System.out.println(">> (A) " + atom);

        AtomReport report = new AtomReport();

        // Maps Atom to Workable POJO
        ArrayList<Car> atomData = carMapper.map(("./txt/atoms/" + atom + ".txt"));

        if (atomData != null) {
            report = carMapper.analyzeAtom(atomData);

        }
        report.name = atom;
        return report;
    };

    public ArrayList<AtomReport> analyzeAtoms(ArrayList<String> atoms) throws IOException {

        ArrayList<AtomReport> AtomReports = new ArrayList<>();
        CarMapper carMapper = new CarMapper();

        for (String atom : atoms) {
            AtomReport atomReport = getAtomReport(atom,carMapper);

            atomReport.setName(atom);
            AtomReports.add(atomReport);

        }

        return AtomReports;
    };

    public StepReport getStepReport(ArrayList<AtomReport> atomReports){

        int count = 0;
        int priceTotal = 0;
        int milesTotal = 0;
        double priceDeviation = 0;


        // list of nested hashmaps
        ArrayList<HashMap<String ,Map<String,Integer>>> carLotsList = new ArrayList();
        ArrayList<HashMap<String ,Integer>> carMakesList = new ArrayList();
        ArrayList<HashMap<String ,Integer>> carModelsList = new ArrayList();

        StepReport stepReport = new StepReport();

    for (AtomReport atomReport : atomReports) {

        count += atomReport.getCount();
        priceTotal += atomReport.getPriceSUM();
        milesTotal += atomReport.getMilesSUM();


        stepReport.atoms.add(atomReport.name);
        stepReport.atomReports.add(atomReport);

        carLotsList.add(atomReport.getCarLotMap());
        carMakesList.add(atomReport.getCarMakesMap());
        carModelsList.add(atomReport.getCarModelsMap());

        // Needs Testing just took logic from atom report
        // once again need away to pass over blank files
        // prob need to be in the actually file reader part
        if (count > 0) {
            priceDeviation += Math.pow(atomReport.getLotValue() - (priceTotal / count), 2);
        }
    }

        // Deviation of Price ? Feel like Miles deviation is not really that useful
        priceDeviation = Math.sqrt(priceDeviation / count);


    // LOOK MORE INTO STREAMS AND MEETHODS
        // THIS PRETTY COOL BUT LIKE IDK HOW ITS WOKRING
        Map<String, Integer> carMakesMasterMap = carMakesList.stream()
                .flatMap(m -> m.entrySet().stream())
                .collect(groupingBy(Map.Entry::getKey, summingInt(Map.Entry::getValue)));


    Map<String, Integer> carModelsMasterMap = carModelsList.stream()
                .flatMap(m -> m.entrySet().stream())
                .collect(groupingBy(Map.Entry::getKey, summingInt(Map.Entry::getValue)));


    /// will have to do something specail for the nested map will do later
        // CAR LOT AGGEGRATIONS GOES HERE


    /////////////// Build report /////////////

        // need to find a better way to deal with empty stuff
    if (count > 0){
        stepReport.setCount(count);
        stepReport.setStepTotalValue(priceTotal);
        stepReport.setPriceAVG(priceTotal/count);
        stepReport.setMilesAVG(milesTotal/count);
        /// car lot aggreate when i get to it

        stepReport.setCarMakesMaps(carMakesMasterMap);
        stepReport.setCarModelsMaps(carModelsMasterMap);


        /// idk if this is efficiant or if its better to grab the report object and then get the info like that
        // or even just save them in seperate variable instead of saving it inside of a map


        stepReport.setLeastExpensiveAtom(Map.of(
                Collections.min(atomReports, Comparator.comparing(AtomReport::getLotValue)).getName(),Collections.min(atomReports, Comparator.comparing(AtomReport::getLotValue)).getLotValue()
        ));

        stepReport.setMostExpensiveAtom(
                Map.of( Collections.max(atomReports, Comparator.comparing(AtomReport::getLotValue)).getName(),Collections.max(atomReports, Comparator.comparing(AtomReport::getLotValue)).getLotValue()
        ));


     stepReport.setPriceDeviation(priceDeviation);


    } else {
        stepReport.setPriceAVG(priceTotal/1);
        stepReport.setMilesAVG(milesTotal/1);
    }

    // attach name here or at level up
        return stepReport;
    };


    public RoutineReport getRoutineReport(ArrayList<StepReport> stepReports){

        RoutineReport routineReport = new RoutineReport();
        ArrayList<Map<String ,Integer>> carMakesList = new ArrayList();
        ArrayList<Map<String ,Integer>> carModelsList = new ArrayList();

        int priceTotal = 0;
        int milesTotal = 0;
        int count = 0;


        for (StepReport stepReport : stepReports){
            count += stepReport.getCount();
            priceTotal += stepReport.getPriceAVG();
            milesTotal += stepReport.getMilesAVG();

            routineReport.steps.add(stepReport.name);
            routineReport.atoms.addAll(stepReport.getAtoms());


//            carLotsList.add(atomReport.getCarLotMap());
            carMakesList.add(stepReport.getCarMakesMaps());
            carModelsList.add(stepReport.getCarModelsMaps());
        }


        Map<String, Integer> carMakesMasterMap = carMakesList.stream()
                .flatMap(m -> m.entrySet().stream())
                .collect(groupingBy(Map.Entry::getKey, summingInt(Map.Entry::getValue)));


        Map<String, Integer> carModelsMasterMap = carModelsList.stream()
                .flatMap(m -> m.entrySet().stream())
                .collect(groupingBy(Map.Entry::getKey, summingInt(Map.Entry::getValue)));

        routineReport.setCarMakesMaps(carMakesMasterMap);
        routineReport.setCarModelsMaps(carModelsMasterMap);

        /// need to do the car lot aggregrate still

        routineReport.setCount(count);
        routineReport.setMilesAVG(milesTotal/stepReports.size());
        routineReport.setPriceAVG(priceTotal/stepReports.size());
        routineReport.setTotalRoutineValue(priceTotal);
        //


        return routineReport;

    }


    public void turnAtomsIntoXML(){};
















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
