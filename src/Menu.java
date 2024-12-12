import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    //Skapar objektet "system" från klassen "StudentManagementSystem" med getInstance metoden så att det bar skapas en enda instans.
    StudentManagementSystem system = StudentManagementSystem.getInstance();

    public void schoolMenu() {

        boolean running = true;

        while (running) {
            //HUVUDMENYN
            System.out.println("Välkommen till Studenthantering!");
            System.out.println("--------------------------------");
            System.out.println("1. Lägg till Studenter ");
            System.out.println("2. Visa alla Studenter ");
            System.out.println("3. Sök Student via ID");
            System.out.println("4. Läs studentposter från fil ");
            System.out.println("5. Spara studentposter till fil ");
            System.out.println("6. Räkna ut medeltalet av betygen (EXTRA FUNKTION) ");
            System.out.println("7. Avsluta ");

            //Skapar choice variabel för att spara användarens val i menyn
            int choice = sc.nextInt();
            sc.nextLine();

            //Om 1, fråga efter studenters egenskaper och spara det i variabler, hämta sedan "addStudent" metoden för att skapa en ny student med dessa egenskaper
            //Skapar även en addMoreStudents boolean variabel för att göra det möjligt att lägga in fler studenter i samma veva.
            if (choice == 1) {
                try {
                    boolean addMoreStudents = true;
                    while (addMoreStudents) {
                        System.out.println("Ange Student-ID: ");
                        String studentID = sc.nextLine().trim();

                        System.out.println("Ange Studentens namn: ");
                        String studentName = sc.nextLine().trim();

                        System.out.println("Ange Studentens betyg (t.ex 71,00): ");
                        double studentGrade = sc.nextDouble();

                        system.addStudent(studentID, new Student(studentID, studentName, studentGrade));

                        String returnChoice;
                        System.out.println("Vill du lägga till en till Student? Ja/Nej: ");
                        sc.nextLine();
                        returnChoice = sc.nextLine().trim();

                        if (returnChoice.equalsIgnoreCase("nej")) {
                            addMoreStudents = false;
                        }
                    }
                    pressEnter();

                }catch (InputMismatchException e) {
                    System.out.println("Något blev fel! Du kommer nu tillbaka till huvudmenyn");
                    sc.nextLine();
                }
            //Om 2, hämta displatStudents metoden
            }else if (choice == 2) {
                system.displayStudents();
                pressEnter();
            //Om 3, fråga efter ID man vill söka efter och spara det i en variabel, hämta sedan getStudentByID metoden för att hämta ut rätt student
            }else if (choice == 3) {
                System.out.println("Skriv in ID du vill söka efter: ");
                String idSearch = sc.nextLine();
                Student student = system.getStudentByID(idSearch);
                if (student != null) {
                    System.out.println(system.getStudentByID(idSearch));
                    pressEnter();
                }else{
                    System.out.println("Din sökning med ID: " + idSearch + " hittades inte");
                    pressEnter();
                }
            //Om 4, hämta readFromFile metoden
            }else if (choice == 4) {
                //Läs in studenter från fil
                system.readFromFile("students.txt");
                pressEnter();
            //Om 5, hämta saveToFile metoden
            }else if (choice == 5) {
                //Spara studenter i fil
                system.saveToFile("students.txt");
                pressEnter();
            //Om 6, hämta gradeAverage metoden
            }else if (choice == 6) {
                system.gradeAverage();
                pressEnter();
            //Om 7, sätter running till false för att avsluta programmet
            }else if (choice == 7) {
                System.out.println("Du har nu avslutat programmet");
            }
            running = false;
        }
    }   //Press enter metod för att inte behöva skriva ut det efter varje val
        private void pressEnter(){
            System.out.println("Tryck Enter för att komma tillbaka till huvudmenyn");
            sc.nextLine();
            schoolMenu();
    }
}
