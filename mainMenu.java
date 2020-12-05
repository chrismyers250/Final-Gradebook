import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class mainMenu {




    public void mainMenu(String name) throws IOException {
        /**
         * This method functions as a way to either enter new grades into the gradebook.txt file,
         * or view previously inputted grades in the gradebook.txt file.
         */
        Scanner input = new Scanner(System.in);
       System.out.println("Would you like to enter new grades, or view old grades?");
       System.out.println("Type \"New\" to enter new grades, type \"Old\" to view old grades.");
       File file = new File("gradebook.txt");

        FileWriter writer = new FileWriter("gradebook.txt", true);

        Scanner reader = new Scanner(file);
        String userInput = input.nextLine().toLowerCase();

        if (userInput.equals("new")) {
            try {
                File gradebook= new File("gradebook.txt");
                if (gradebook.createNewFile()) {
                    System.out.println("File created: " + gradebook.getName());
                }
                else {

                }
            }
            catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            try {
                writer.write("Name: " + name + "\n");

                writeClasses(name, userInput, reader);
                return;




            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        }
        else if(userInput.equals("old")){
            viewGrades(input, name, reader, userInput, file);
        }
        else{
            System.out.println("Invalid input, try again.");

            mainMenu(name);
        }
    }
    public void viewGrades(Scanner input, String name, Scanner reader, String userInput, File file) throws FileNotFoundException {
        /**
         * Helper function that prints out grades for selected class, or will print out an error message if
         * the selected class doesn't exist in the gradebook text file.
         */
        System.out.println("Which class would you like to view? Input must be capitalized.");
        String selection = input.next();
        System.out.println(selection);
        String nameLine = "Name: " + name;
        String classLine = "Class: " + selection;
        int x = 0;

        String logic = "";

        while(x == 0){


            try {
                String read = reader.nextLine();
                if (read.contains(nameLine) && reader.nextLine().contains(classLine)) {
                    x = 1;


                }
            }
            catch(NoSuchElementException e){
                System.out.println("No such class found.");
                 x = 1;
            }
        }

        while(x == 1) {
            try {

                logic = reader.nextLine().toLowerCase();
                if(logic.contains("name")==false) {
                    System.out.println(logic);
                }
                if (logic.contains("name")) {
                    continueGrades(input, userInput, reader, name, file);
                    return;
                }
            }
            catch(NoSuchElementException e){
                continueGrades(input, userInput, reader, name, file);
                return;
            }
        }
        return;
    }
    public void writeClasses(String name, String userInput, Scanner reader) throws IOException {
        /**
         * Helper function that writes user inputted grades to the gradebook text file
         */

        FileWriter writer = new FileWriter("gradebook.txt", true);
        Scanner input = new Scanner("gradebook.txt");
        gradebook myGradebook = new gradebook();
        String nameLine = "Name: " + name;
        String classLine = "Class: " + myGradebook.classSelection;
        String x = "Stop";
        while(x == "Stop"){
            try {
                String read = reader.nextLine();
                //System.out.println(read);
                if (read.contains(nameLine) && reader.nextLine().contains(classLine)) {

                    x = "The class is already in the gradebook";
                    System.out.println("Class is already located in gradebook. Data not added to gradebook.");
                    continueClass(input, writer, name, userInput, reader);
                    return;


                }
            }
            catch(NoSuchElementException e){

                x = "The class is not here";
            }
        }


        if(myGradebook.classSelection != "unknown") {

            writer.write("Name: " + name + "\n");
            writer.write("Class: " + myGradebook.classSelection + "\n");
            HashMap<String, Integer> allGrades = myGradebook.allGrades;
            HashMap<String, Integer> averageGrades = myGradebook.averageGrades;

            for (Map.Entry<String, Integer> entry : allGrades.entrySet()) {
                writer.write(entry.getKey() + "= " + entry.getValue() + "% \n");

            }

            for (Map.Entry<String, Integer> entry : averageGrades.entrySet()) {

                writer.write(entry.getKey() + "= " + entry.getValue() + "% \n");
            }

            writer.write("Total grade= " + myGradebook.totalGrade + "% \n \n");
            writer.flush();
            System.out.println("Successfully wrote to the file.");
        }
        else {

        }
        continueClass(input, writer, name, userInput, reader);
        return;
    }
    public void continueClass(Scanner input, FileWriter writer, String name, String userInput, Scanner reader) throws IOException {
        /**
         *Helper class that determines if the user wants to enter an additional class, or exit the gradebook.
         */
        System.out.println("Would you like to enter another class? (yes/no)");
        input = new Scanner(System.in);
        String lineInput = input.nextLine();

        if(lineInput.equals("yes")){
            writer.write("\n");
            writeClasses(name, userInput, reader);
            return;
        }
        else if (lineInput.equals("no")){
            writer.write("\n");
            System.out.println("Goodbye.");
            writer.close();
            return;
        }
        return;
    }
    public void continueGrades(Scanner input, String userInput, Scanner reader, String name, File file) throws FileNotFoundException {
        /**
         * Helper function that allows the user to either exit the program or view additional grades.
         */
        System.out.println("Would you like to view additional grades? (yes/no)");
        userInput = input.next().toLowerCase();
        //System.out.println(userInput);
        if(userInput.contains("yes")){
            reader = new Scanner(file);

            viewGrades(input, name, reader, userInput, file);
            return;
        }
        else if(userInput.contains("no")){
            System.out.println("Goodbye.");
            reader.close();
            return;
        }

        else{
            System.out.println("Invalid input, try again.");
        }
    }

}
