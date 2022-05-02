import java.io.IOException;
import java.util.ArrayList;

public class SuiteRunner {


    public void run(String filepath) throws IOException {

        SmokeReader fileReader = new SmokeReader();

        String testLevel = fileReader.identifyTestLevel(filepath);

        switch (testLevel) {
            case "suite":
                this.runSuiteFileLogic(filepath, fileReader);
            case "steps":
                System.out.println("Running Step Logic");
                break;
            case "routines":
                System.out.println("Routine Identified");
                break;
        }

    }




    public void runSuiteFileLogic(String filepath, SmokeReader fileReader) throws IOException {
        System.out.println("<<< TEST LEVEL: Suite");
        System.out.println(">>> DISPLAYING STEPS >>>");
        ArrayList<String> suite = fileReader.run(filepath);

        ArrayList<String> routines = fileReader.getRoutinesFromSuite(suite);

        ArrayList<String> steps = fileReader.getStepsFromRoutines(routines);

        ///  maybe arraysist of arraylissts

        for (String step: steps){


            ArrayList<String> atoms = fileReader.getAtomsFromStep(step);

            System.out.println(">>> " + step + " >>>");

            fileReader.turnAtomsIntoReport(atoms);
        }


//        fileReader.turnAtomsIntoReport(atoms);

        // make a loop at step level...
        // want step to run because each step will have a report
        // Ask if duplicates okay

        // Still need logic for recursivly searching folders to find in case it gets some bs
        // which mean i have a reason for that monster loop

    }


    public static void main(String[] args)  throws Exception {


        SuiteRunner s = new SuiteRunner();

//        s.run("./txt/testSuites/Smoke.txt");
        s.run("./txt/suite/smoke.txt");
//        s.run("./txt/routines/north-carolina.txt");
//





    }

}
