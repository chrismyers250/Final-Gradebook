import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;

public class gradebook {
    ArrayList<String> numberOfClasses = new ArrayList<>();
    String classSelection = "unknown";
    ArrayList<Integer> grades = new ArrayList<>();
    HashMap<String, Integer> weight = new HashMap<>();
    ArrayList<String> gradeTypes = new ArrayList<>();
    HashMap<String, Integer> averageGrades = new HashMap<>();
    HashMap<String, Integer> allGrades = new HashMap<String, Integer>();
    HashMap<String, ArrayList<Integer>> gradeCategories = new HashMap<String, ArrayList<Integer>>();
    double totalGrade = 0;
    Scanner input = new Scanner(System.in);
    ArrayList<String> availableClasses = new ArrayList<>();



    public gradebook(){
        /**
         * Creates a single instance of a class, collects all grades for
         * each category of grade, computes average grades for each category,
         * and weighs the average grades.
         */
        grades = new ArrayList<>();
        weight = new HashMap<String, Integer>();
        gradeTypes.add("Quiz");
        weight.put("Quiz", 20);
        gradeTypes.add("Exam");
        weight.put("Exam", 30);
        gradeTypes.add("Homework");
        weight.put("Homework", 25);
        gradeTypes.add("Project");
        weight.put("Project", 25);
        availableClasses.add("Programming");
        availableClasses.add("Physics");
        availableClasses.add("Statistics");
        availableClasses.add("Humanities");
        availableClasses.add("English");
        availableClasses.add("German");
        availableClasses.add("Psychology");
        availableClasses.add("History");


        System.out.println("Enter class here: ");
        String inputString = "";

        inputString = input.nextLine();
        if(availableClasses.contains(inputString)){
            numberOfClasses.add(classSelection);
            for (int i = 0; i<gradeTypes.size(); i++){
                gradeCategories.put(gradeTypes.get(i), grades);
            }
            gradeCategories = getGrades(gradeCategories, gradeTypes);
            averageGrades = averageGrades(gradeCategories,gradeTypes);
            totalGrade = overallGrade(averageGrades, weight, gradeTypes);
            availableClasses.remove(inputString);
            classSelection = inputString;
        }
        else{
            System.out.println("Class not available in listing.");
        }
    }

    public HashMap<String, ArrayList<Integer>> getGrades(HashMap<String, ArrayList<Integer>> gradeTypeMap, ArrayList<String> gradeTypes){
        /**
         * Adds values to empty HashMap that contains the values of the individual categories
         * of grades.
         */


        System.out.println("To finish entering grades for one category, enter -1. ");
        int counter = 1;
        for(int i = 0; i< gradeTypes.size(); i++){

            ArrayList<Integer> gradeArray = new ArrayList<Integer>();
            System.out.print("Enter ");
            System.out.print(gradeTypes.get(i));
            System.out.println(" grades here: ");

            int grade = 0;

                while(grade != (-1)){
                    try{

                        grade = input.nextInt();
                        if(grade != -1){


                        gradeArray.add(grade);
                        }

                        String gradeName = Integer.toString(counter);

                        gradeName = gradeTypes.get(i) + gradeName;
                        if(grade != -1) {
                            allGrades.put(gradeName, grade);
                        }
                        counter++;

                    }
                    catch(InputMismatchException e){
                        System.out.println("Noninteger value entered. Try again. ");
                        String nonInteger = input.next();


                    }




                }
            String test = gradeTypes.get(i);
            gradeTypeMap.put(test, gradeArray);
            counter = 1;

        }

        return gradeTypeMap;
    }


    public HashMap<String, Integer> averageGrades(HashMap<String, ArrayList<Integer>> grades, ArrayList<String> gradeTypes){
        /**
         * Calculates the average grade of each category of classes.
         */
        HashMap<String, Integer> averages = new HashMap<>();

        for (int i = 0; i< grades.size(); i++){

            int sum = 0;
            int counter = 0;
            for (int j = 0; j<grades.get(gradeTypes.get(i)).size(); j++){
                sum+= grades.get(gradeTypes.get(i)).get(j);
                counter++;
            }
            if(counter == 0){

            }
            else {
                sum = sum / counter;
            }
            averages.put(gradeTypes.get(i), sum);
        }

        return averages;
    }

    public double overallGrade(HashMap<String, Integer> averageGrades, HashMap<String, Integer> weight, ArrayList<String> gradeTypes){
        /**
         * Calculates the overall grade for the class selected.
         */
        int overallGrade = 0;
        for (int i = 0; i< averageGrades.size(); i++){
            String currentGrade = gradeTypes.get(i);
            double x = averageGrades.get(currentGrade)* (weight.get(currentGrade)/100.0);

            overallGrade+= x;


        }
        return overallGrade;
    }
}
