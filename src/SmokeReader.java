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

    public String identifyTestLevel(String filepath){

        System.out.println(filepath);

        // Deletes leading ".txt"
        String xx = filepath.substring(6);
        // Deletes trailing /example.txt
        String level = xx.split("/")[0];


        System.out.println(">>> Getting Test lvl of " + filepath);





        return level;
    };


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

    public void turnAtomsIntoReport(ArrayList<String> atoms) throws IOException {
        CarMapper carMapper = new CarMapper();
        System.out.println(atoms);

        for (String atom : atoms) {

            System.out.println(">> A: " + atom);

            // Maps Atom to Workable POJO
            ArrayList<Car> atomData = carMapper.run(("./txt/atoms/" + atom + ".txt"));

            if (atomData != null) {

                // prototype analysis
                /// Basically this loop is where I can run analysis on the chosen files
                /// Analysis can then be streamed in any way after that
                /// Also dictionary keeps track of everything

            }
        }
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
