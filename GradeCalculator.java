import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("        STUDENT GRADE CALCULATOR          ");
        System.out.println("==========================================");

        // Take input for number of subjects
        System.out.print("Enter the total number of subjects: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input! Enter a valid number of subjects: ");
            scanner.next();
        }
        int totalSubjects = scanner.nextInt();

        while (totalSubjects <= 0) {
            System.out.print("Please enter a positive number of subjects: ");
            totalSubjects = scanner.nextInt();
        }

        int[] marks = new int[totalSubjects];
        int totalMarks = 0;

        // Input marks for each subject (out of 100)
        System.out.println("\nEnter marks obtained (out of 100) for each subject:");
        for (int i = 0; i < totalSubjects; i++) {
            int score;
            while (true) {
                System.out.print("Subject " + (i + 1) + ": ");
                if (scanner.hasNextInt()) {
                    score = scanner.nextInt();
                    if (score >= 0 && score <= 100) {
                        break;
                    } else {
                        System.out.println("Invalid score! Marks must be between 0 and 100.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter an integer value.");
                    scanner.next();
                }
            }
            marks[i] = score;
            totalMarks += score;
        }

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / totalSubjects;

        // Calculate Grade based on average percentage
        char grade;
        String performance;

        if (averagePercentage >= 90) {
            grade = 'A';
            performance = "Excellent";
        } else if (averagePercentage >= 80) {
            grade = 'B';
            performance = "Very Good";
        } else if (averagePercentage >= 70) {
            grade = 'C';
            performance = "Good";
        } else if (averagePercentage >= 60) {
            grade = 'D';
            performance = "Satisfactory";
        } else if (averagePercentage >= 50) {
            grade = 'E';
            performance = "Pass";
        } else {
            grade = 'F';
            performance = "Fail";
        }

        // Display Summary Results
        System.out.println("\n==========================================");
        System.out.println("             RESULT SUMMARY               ");
        System.out.println("==========================================");
        System.out.println("Total Subjects    : " + totalSubjects);
        System.out.println("Total Marks       : " + totalMarks + " / " + (totalSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade Assigned    : " + grade + " (" + performance + ")");
        System.out.println("==========================================");

        scanner.close();
    }
}
