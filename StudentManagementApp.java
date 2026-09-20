import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class representing individual Student records
class Student {
    private String name;
    private int rollNumber;
    private String grade;
    private String email;

    public Student(String name, int rollNumber, String grade, String email) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.email = email;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("Roll No: %-5d | Name: %-18s | Grade: %-4s | Email: %s", 
                rollNumber, name, grade, email);
    }
}

// Class managing the list of students
class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    public boolean addStudent(Student student) {
        for (Student s : students) {
            if (s.getRollNumber() == student.getRollNumber()) {
                return false; // Roll number must be unique
            }
        }
        students.add(student);
        return true;
    }

    public boolean removeStudent(int rollNumber) {
        return students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                return s;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("\n--- All Student Records ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}

// Main Interactive Class
public class StudentManagementApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManagementSystem sms = new StudentManagementSystem();

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("==========================================");
        System.out.println("        STUDENT MANAGEMENT SYSTEM         ");
        System.out.println("==========================================");

        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Select an option (1-5): ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    handleAddStudent();
                    break;
                case 2:
                    handleRemoveStudent();
                    break;
                case 3:
                    handleSearchStudent();
                    break;
                case 4:
                    sms.displayAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option! Please enter a number between 1 and 5.");
            }
        }
        scanner.close();
    }

    private static void handleAddStudent() {
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Enter Student Name: ");
            name = scanner.nextLine().trim();
        }

        System.out.print("Enter Roll Number: ");
        int rollNumber = getIntInput();

        System.out.print("Enter Grade (e.g., A, B, C): ");
        String grade = scanner.nextLine().trim();

        System.out.print("Enter Email Address: ");
        String email = scanner.nextLine().trim();

        Student student = new Student(name, rollNumber, grade, email);
        if (sms.addStudent(student)) {
            System.out.println("✅ Student added successfully!");
        } else {
            System.out.println("❌ Error: A student with Roll Number " + rollNumber + " already exists.");
        }
    }

    private static void handleRemoveStudent() {
        System.out.print("Enter Roll Number to remove: ");
        int rollNumber = getIntInput();

        if (sms.removeStudent(rollNumber)) {
            System.out.println("✅ Student record removed successfully.");
        } else {
            System.out.println("❌ Student with Roll Number " + rollNumber + " not found.");
        }
    }

    private static void handleSearchStudent() {
        System.out.print("Enter Roll Number to search: ");
        int rollNumber = getIntInput();

        Student student = sms.searchStudent(rollNumber);
        if (student != null) {
            System.out.println("\n✅ Record Found:\n" + student);
        } else {
            System.out.println("❌ Student with Roll Number " + rollNumber + " not found.");
        }
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input! Please enter a valid number: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        return val;
    }
}
