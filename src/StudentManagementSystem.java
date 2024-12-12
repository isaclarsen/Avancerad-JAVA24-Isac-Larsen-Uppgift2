import java.io.*;
import java.util.HashMap;

public class StudentManagementSystem {

    //Skapar singleton instans av klassen
    private static StudentManagementSystem instance;

    //Skapar hashmap som innehåller studenter
    private HashMap<String, Student> students;

    //Privat konstruktor
    private StudentManagementSystem() {
        students = new HashMap<>();
    }
    //Skapar getInstance metoden som gör att man bara kan skapa en enda instans av StudentManagerSystem klassen
    public static StudentManagementSystem getInstance() {
        if (instance == null) {
            instance = new StudentManagementSystem();
        }
        return instance;
    }

    //Skapar metoden addStudent
    public void addStudent(String studentID, Student student) {
        //Om studentID är tomt, skriv ut att det måste finnas ett ID
        if (studentID == null || studentID.trim().isEmpty()) {
            System.out.println("StudentID kan inte vara tomt!");
            return;
        }
        //Om ID redan finns, skriv ut att den redan finns
        if(students.containsKey(studentID)) {
            System.out.println("Student med " + studentID + " som id finns redan");
        //Om allt stämmer, lägg in studenten i hashmapen
        }else {
            students.put(studentID, student);
        }
    }

    public Student getStudentByID(String studentID) {
            return students.get(studentID);
    }

    //Metod som skriver ut studenterna och deras egenskaper, om listan är tom skriv ut att det ints finns några studenter
    public void displayStudents() {
        if(students.isEmpty()) {
            System.out.println("Det finns inga studenter!");
        }else
            for (Student student : students.values()) {
            System.out.println(student);
        }
    }
    //Metod för att spara studenterna till "students.txt"
    public void saveToFile(String filename) {
        try {
            //Skapar en ny bufferedwriter som skapar filen "students.txt" (Om inte
            BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"));
            //För varje studen i listan lägg in studenternas egenskaper med "," mellan.
            for (Student student : students.values()) {
                writer.write(student.getStudentID() + "," + student.getStudentName() + "," + student.getStudentGrade() + "\n");
            }
            writer.close();
            System.out.println("Studenterna är sparade i filen: students.txt");
        } catch (IOException e) {
            System.out.println("Det gick inte att spara studenterna till students.txt, prova igen");
        }
    }

    //Metod för att läsa in studenterna från "students.txt"
    public void readFromFile(String filename) {
        try {
            //Skapar en ny bufferedreader som läser in "students.txt" filen
            BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    Student student = parseLine(line);
                    if (student != null) {
                        students.put(student.getStudentID(), student);
                    }
                }
                System.out.println("Studenterna har blivit inlästa från students.txt");
            } catch (IOException e) {
                System.out.println("Det gick inte att läsa in studenterna från students.txt, prova igen");
        }
    }

    //Metod som delar upp studenternas egenskaper och skapar ny student av egenskaperna
    private Student parseLine(String line) {
        try {
            //Skapar en lista där egenskaperna läggs in och splittar mellan varje ","
            String[] split = line.split(",");
            //Om längden är mindre än tre saknas någon egenskap
            if (split.length != 3) {
                System.out.println("Något stämmer inte med filen, kontrollera så att ID, namn och betyg finns med");
                return null;
            }
            //Skapar variabler och lägger in egenskapperna i variablerna
            String studentID = split[0].trim();
            String studentName = split[1].trim();
            double studentGrade = Double.parseDouble(split[2].trim());
            //Skapar en ny student med hjälp av variablerna
            return new Student(studentID, studentName, studentGrade);
        }catch (Exception e) {
            System.out.println("Något stämmer inte med filen, kontrollera så att ID, namn och betyg finns med");
            return null;
        }
    }
    //Metod som räknar ut medeltalet av betygen (Extra)
    public void gradeAverage() {
        //Skapar variabler för totalen och medeltal
        double total = 0;
        double average = 0;
        //Loopar igenom hela Student listan
        for (Student student : students.values()) {
            //Adderar studenternas betyg in i total
            total += student.getStudentGrade();
        }
        //Totalen / listans storlek blir medeltalet
        average = total / students.size();
        //Skriv ut medeltalet
        System.out.println("Medeltalet av betygen är: " + average);
    }
}
