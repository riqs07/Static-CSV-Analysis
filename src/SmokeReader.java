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

//        System.out.println(">> (A) " + atom);

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


        report.name = atom;
        return report;
    };

    public ArrayList<AtomReport> analyzeAtoms(ArrayList<String> atoms) throws IOException {

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
        priceTotal += atomReport.getPriceSUM();
        milesTotal += atomReport.getMilesSUM();
        stepReport.atoms.add(atomReport.name);

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

        // porb gonna want to attach atoms to stepReport and ROutine report?
        // when i return to front end do i want want huge objeect or lots of small ones.
        // for now will leave alone
        return stepReport;
    };


    public RoutineReport getRoutineReport(ArrayList<StepReport> stepReports){

        RoutineReport routineReport = new RoutineReport();

        int priceTotal = 0;
        int milesTotal = 0;
        int count = 0;

        for (StepReport stepReport : stepReports){
            count += stepReport.getCount();
            priceTotal += stepReport.getPriceAVG();
            milesTotal += stepReport.getMilesAVG();


            routineReport.steps.add(stepReport.name);
            routineReport.atoms.addAll(stepReport.getAtoms());

        }


        routineReport.setCount(count);
        routineReport.setMilesAVG(milesTotal/stepReports.size());
        routineReport.setPriceAVG(priceTotal/stepReports.size());



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
