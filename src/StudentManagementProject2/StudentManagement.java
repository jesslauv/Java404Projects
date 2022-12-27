package StudentManagementProject2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many new students will be added? ");
        int numStudents = Integer.valueOf(sc.nextLine());

        ArrayList<Student> studentList = new ArrayList<>();
        //adding each student to the database
        for (int i=1; i<=numStudents; i++) {
            System.out.println("What's the first and last name of student " + i);
            String name = sc.nextLine();
            System.out.println("What's the year of student " + i);
            String year = sc.nextLine();
            studentList.add(new Student(name, year));
            System.out.println("The ID of this student is: " + studentList.get(i-1).getID());
        }

        //student can enter id in order to choose classes, check balance, pay tuition
        System.out.println("Enter your ID: ");
        String id = sc.nextLine();
        ArrayList<Student> filteredStudent = studentList.stream().filter(stu -> stu.getID().equals(id)).collect(Collectors.toCollection(ArrayList::new));
        Student selectedStudent = filteredStudent.get(0);
        selectedStudent.toString();

        while (true) {
            System.out.println("What would you like to do? Choose classes (type c), check balance (type b), pay tuition (type t), status (type s), quit (type q),");
            String input = sc.nextLine();
            if (input.equals("c")) {
                selectedStudent.displayCourses();
                System.out.println("Pick class (type the course code like MA101)");
                String chosenClass = sc.nextLine();
                addClass(chosenClass, selectedStudent);
            }
            else if (input.equals("b")) {
                selectedStudent.displayBalance();
            }
            else if (input.equals("t")) {
                System.out.println("How much do you want to pay?");
                int payment = Integer.valueOf(sc.nextLine());
                selectedStudent.setBalance(payment);
                selectedStudent.displayBalance();
            }
            else if (input.equals("q")){
                break;
            }
            else if (input.equals("s")) {
                selectedStudent.toString();
            }
            else {
                System.out.println("That's not a valid choice. Please try again.");
            }
        }

    }
    public static void addClass(String chosenClass, Student student) {
        String className="";
        switch(chosenClass) {
            case "MA101": className="MA101 Calculus"; break;
            case "CIS101": className="CIS101 Basic Excel"; break;
            case "CIS201": className="CIS201 Hardware"; break;
            case "HS221": className="HS221 Introduction to Public Health"; break;
            case "BI325": className="BI325 Systems Physiology $600"; break;
            case "CIS404": className="CIS404 Java Projects"; break;
            default: className="Not valid"; break;
        }

        student.setStudentEntrolledCoursesList(className);

    }
}

class Student {
    private String name;
    private String year;
    private String id;
    private int balance;
    private ArrayList<String> courseList = new ArrayList<>();

    private ArrayList<String> studentEntrolledCoursesList = new ArrayList<>();
    public Student(String name, String year) {
        this.name = name;
        this.year = year;
        this.id = generateID.genID(year);
        this.balance = 0;
        courseList.add("MA101 Calculus: $600");
        courseList.add("BUS101 Business Communications: $600");
        courseList.add("CIS101 Basic Excel: $600");
        courseList.add("CIS201 Hardware: $600");
        courseList.add("HS221 Introduction to Public Health: $600");
        courseList.add("BI325 Systems Physiology $600");
        courseList.add("CIS404 Java Projects: $600");

    }
    interface IdInterface {
        String genID(String year);
    }

    IdInterface generateID = (String year) -> {
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        for (int i=1; i<5; i++) {
            int randomNum = (int)(Math.random() * 10);
            sb.append(randomNum);
        }
        return String.valueOf(sb);
    };

    public void setBalance(int payment) {
        this.balance = this.balance + payment;
    }


    public String getID() {
        return this.id;
    }

    public String toString() {
        System.out.println("-------------------------------");
        System.out.println("Status of " + this.name);
        System.out.println("Student ID#: " + this.id);
        System.out.println("Courses: ");
        displayEnrolledCourses();
        System.out.println("Balance: " + this.balance);
        return null;
    }

    public void displayEnrolledCourses() {
        this.studentEntrolledCoursesList.stream().forEach(course -> System.out.println(course));
    }


    public void displayCourses() {
        courseList.stream().forEach(course -> System.out.println(course));

    }

    public void setStudentEntrolledCoursesList(String course) {
        studentEntrolledCoursesList.add(course);
        this.balance -= 600;
    }

    public void displayBalance() {
        System.out.println("Your balance is: "+this.balance);
    }


}