import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
class Main {
    public static void main(String[] args) throws IOException {
        String classSelection = "unknown";
        String name = "unknown";
        ArrayList<Integer> grades = new ArrayList<Integer>();
        mainMenu MainMenu = new mainMenu();

        System.out.println("Welcome to gradebook!");

        Scanner input = new Scanner(System.in);
        System.out.println("Enter name here: ");
        name = input.nextLine();
        MainMenu.mainMenu(name);
    }
}