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
            System.out.println(">>>>> (S) " + step + " >>>>>");


            /// for each step get stepreport
            /// step reoirt us aggregate of atom report arraylist
            // each atom in step must give and append a report to list
            // so
            ArrayList<String> atoms = fileReader.getAtomsFromStep(step);

           ArrayList<AtomReport> analyzedAtoms = fileReader.analyzeAtoms(atoms);


            StepReport foo = fileReader.getStepReport(analyzedAtoms);
            // cane set in func or here
            foo.setName(step);


        }


//        fileReader.turnAtomsIntoReport(atoms);

        // make a loop at step level...
        // want step to run because each step will have a report
        // Ask if duplicates okay

        // Still need logic for recursivly searching folders to find in case it gets some bs
        // which mean i have a reason for that monster loop

        /// need to figure out what happens if i feed a routine or a step
        /// lets fix core and then i can see about trying to fix
    }


    public static void main(String[] args)  throws Exception {


        SuiteRunner s = new SuiteRunner();

//        s.run("./txt/suite/east-coast.txt");
        s.run("./txt/suite/smoke.txt");
//        s.run("./txt/routines/north-carolina.txt");
//





    }

}
