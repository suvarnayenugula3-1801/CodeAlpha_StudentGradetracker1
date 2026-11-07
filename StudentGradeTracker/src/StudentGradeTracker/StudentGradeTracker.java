package StudentGradeTracker;


import java.util.ArrayList;
import java.util.Scanner;


class Student {
    private String name;
    private double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("=========================================");
        System.out.println("       STUDENT GRADE TRACKER SYSTEM      ");
        System.out.println("=========================================\n");

        
        while (true) {
            System.out.print("Enter student name (or type 'done' to finish): ");
            String name = scanner.nextLine().trim();

            if (name.equalsIgnoreCase("done")) {
                break;
            }

            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Please try again.\n");
                continue;
            }

            double grade = -1;
            while (grade < 0 || grade > 100) {
                System.out.print("Enter grade for " + name + " (0 - 100): ");
                if (scanner.hasNextDouble()) {
                    grade = scanner.nextDouble();
                    if (grade < 0 || grade > 100) {
                        System.out.println("Invalid grade! Please enter a score between 0 and 100.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a numeric value.");
                    scanner.next(); 
                }
            }
            scanner.nextLine(); 

            students.add(new Student(name, grade));
            System.out.println("Student added successfully!\n");
        }

        if (students.isEmpty()) {
            System.out.println("\nNo student records entered.");
        } else {
            
            displaySummaryReport(students);
        }

        scanner.close();
    }

    
    public static void displaySummaryReport(ArrayList<Student> students) {
        double totalSum = 0;
        double highestGrade = Double.NEGATIVE_INFINITY;
        double lowestGrade = Double.POSITIVE_INFINITY;
        
        String topStudent = "";
        String lowestStudent = "";

        for (Student s : students) {
            double currentGrade = s.getGrade();
            totalSum += currentGrade;

            if (currentGrade > highestGrade) {
                highestGrade = currentGrade;
                topStudent = s.getName();
            }

            if (currentGrade < lowestGrade) {
                lowestGrade = currentGrade;
                lowestStudent = s.getName();
            }
        }

        double averageGrade = totalSum / students.size();

        
        System.out.println("\n=========================================");
        System.out.println("            SUMMARY REPORT              ");
        System.out.println("=========================================");
        System.out.printf("%-20s | %-10s\n", "Student Name", "Grade");
        System.out.println("-----------------------------------------");

        for (Student s : students) {
            System.out.printf("%-20s | %-10.2f\n", s.getName(), s.getGrade());
        }

        System.out.println("-----------------------------------------");
        System.out.printf("Total Students : %d\n", students.size());
        System.out.printf("Average Score  : %.2f\n", averageGrade);
        System.out.printf("Highest Score  : %.2f (%s)\n", highestGrade, topStudent);
        System.out.printf("Lowest Score   : %.2f (%s)\n", lowestGrade, lowestStudent);
        System.out.println("=========================================");
    }
}