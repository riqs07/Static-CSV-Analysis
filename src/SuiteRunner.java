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



        ArrayList<StepReport> stepReports = new ArrayList<>();

        ///  maybe arraysist of arraylissts
        ArrayList<RoutineReport> foo = new ArrayList<>();

        for (String routine :routines){

            RoutineReport routineReport = new RoutineReport();
            ArrayList<String> steps = fileReader.getStepsFromRoutine(routine);

            for (String step: steps){
                System.out.println(">>>>> (S) " + step + " >>>>>");

                ArrayList<String> atoms = fileReader.getAtomsFromStep(step);

                ArrayList<AtomReport> analyzedAtoms = fileReader.analyzeAtoms(atoms);


                StepReport stepReport = fileReader.getStepReport(analyzedAtoms);
                stepReport.setName(step);

                stepReports.add(stepReport);
                 routineReport = fileReader.getRoutineReport(stepReports);
                routineReport.setName(routine);


            }

            foo.add(routineReport);

        }

        System.out.println(foo);


    }


    // Still need logic for recursivly searching folders to find in case it gets some bs
    // which mean i have a reason for that monster loop

    /// need to figure out what happens if i feed a routine or a step
    /// lets fix core and then i can see about trying to fix


    public static void main(String[] args)  throws Exception {


        SuiteRunner s = new SuiteRunner();

//        s.run("./txt/suite/east-coast.txt");
        s.run("./txt/suite/smoke.txt");
//        s.run("./txt/routines/north-carolina.txt");
//





    }

}
