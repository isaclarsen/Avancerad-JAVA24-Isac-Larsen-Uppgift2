public class Student {
    String studentID;
    String studentName;
    double studentGrade;

    //Konstruktor
    public Student(String studentID, String studentName, double studentGrade) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentGrade = studentGrade;
    }

    //Getter för studentID
    public String getStudentID() {
        return studentID;
    }

    //Setter för studentID
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    //Getter för namn
    public String getStudentName() {
        return studentName;
    }

    //Setter för namn
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    //Getter för studentGrade
    public double getStudentGrade() {
        return studentGrade;
    }

    //Setter för studentGrade
    public void setStudentGrade(double studentGrade) {
        this.studentGrade = studentGrade;
    }
    //Overridear toString metoden för att snyggare skriva ut studenterna
    @Override
    public String toString() {
        return "ID: " + studentID
                +"\nName: " + studentName
                +"\nGrade: " + studentGrade
                +"\n";
    }

}
