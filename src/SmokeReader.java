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

//            if (report.getCount() > 0 ){
//                System.out.println("$" + String.format("%,d", report.getPriceAVG())); // outputs 100,000
//                System.out.println(report);
//            }

        }

        if (atomData == null) {
            report.count = 0;
            report.milesAVG = 0;
            report.priceAVG = 0;
        }


        report.name = atom;
        return report;
    };

    public ArrayList<AtomReport> analyzeAtoms(ArrayList<String> atoms) throws IOException {


        // step report will take in a arraylist of atom reports to aggregrate
        // this needs to broken down
        // core loop which turns 1 atom into report
        // call that as many times as needed

        // So a func that turns mapped atom to report /// getAtomReport
        /// call that for each and append to stepReport

        // each time append the report to the main list
        // one report

        ArrayList<AtomReport> analyzedAtomsList = new ArrayList<>();
        CarMapper carMapper = new CarMapper();




        for (String atom : atoms) {

            AtomReport atomReport = getAtomReport(atom,carMapper);

            atomReport.setName(atom);
            analyzedAtomsList.add(atomReport);


        }

        return analyzedAtomsList;


    };




    public StepReport getStepReport(ArrayList<AtomReport> atomReports){

        /// can do a lot from here
        // for now just want aggregrate but really i can do sorting and other stuff
        // highest lowest etc
        // they are diffrent objects rn but maybe they can be one?

        int count = 0;
        int priceTotal = 0;
        int milesTotal = 0;

        StepReport stepReport = new StepReport();

    for (AtomReport atomReport : atomReports){

        count += atomReport.getCount();
        priceTotal += atomReport.getPriceAVG();
        milesTotal += atomReport.getMilesAVG();

    }

    stepReport.setCount(count);

    if (count > 0){
        stepReport.setPriceAVG(priceTotal/count);
        stepReport.setMilesAVG(milesTotal/count);
    } else {
        stepReport.setPriceAVG(priceTotal/1);
        stepReport.setMilesAVG(milesTotal/1);
    }


        // turn each atom to report and then aggregrate


        return stepReport;
    };




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
