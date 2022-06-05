import java.io.IOException;
import java.util.ArrayList;

public class SuiteRunner {

    public void run(String filepath) throws IOException {
        ReportBuilder fileReader = new ReportBuilder();

        System.out.println("1. Run Smoke ~ 2. Enter Suite URL ~ 3. Settings");
        System.out.println("1.Toggle Console Reporting ~ 2. Change output");

        String testLevel = fileReader.identifyTestLevel(filepath);

        switch (testLevel) {
            case "suite":
                this.runSuiteFileLogic(filepath, fileReader);
                break;
            case "steps":
                System.out.println("Running Step Logic");
                break;
            case "routines":
                System.out.println("Routine Identified");
                break;
        }

    }




    public void runSuiteFileLogic(String filepath, ReportBuilder fileReader) throws IOException {
        System.out.println("<<< TEST LEVEL IDENTIFIED: SUITE");
        System.out.println(">>> DISPLAYING STEPS >>>");

        ArrayList<String> suite = fileReader.run(filepath);
        ArrayList<String> routines = fileReader.getRoutinesFromSuite(suite);

        ArrayList<StepReport> stepReports = new ArrayList<>();

        ArrayList<RoutineReport> suiteReport = new ArrayList<>();
        RoutineReport routineReport = new RoutineReport();

        for (String routine :routines){

            ArrayList<String> steps = fileReader.getStepsFromRoutine(routine);

            for (String step: steps){
                System.out.println(">>>>> (S) " + step + " >>>>>");

                ArrayList<String> atoms = fileReader.getAtomsFromStep(step);

                ArrayList<AtomReport> analyzedAtoms = fileReader.analyzeAtoms(atoms);

                StepReport stepReport = fileReader.getStepReport(analyzedAtoms);
                stepReport.setName(step);

                stepReports.add(stepReport);

            }

            routineReport = fileReader.getRoutineReport(stepReports);

            stepReports.removeAll(stepReports);

            routineReport.setName(routine);
            suiteReport.add(routineReport);
        }

        System.out.println(">>>>> SUITE FILE FINISHED RUNNING >>>>>");

        System.out.println("Would you like to view the report in the console, in the browser, or build a XML file?");

        System.out.println(suiteReport.get(0).getName() + " has " + suiteReport.get(0).getCount() + " cars. The avg price being " + suiteReport.get(0).getPriceAVG());

    }


    // JAX B MARSHALL

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
