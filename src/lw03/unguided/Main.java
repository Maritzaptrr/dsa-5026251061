package lw03.unguided;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));

        Map<String, Integer> enrollment = new HashMap<>();
        List<String> courseOrder = new ArrayList<>();
        List<String> checkResults = new ArrayList<>();

        int rejectedOperations = 0;

        while (sc.hasNext()) {
            String operation = sc.next();
            String courseCode = sc.next();

            if (operation.equals("REGISTER")) {
                int count = sc.nextInt();

                if (count <= 0) {
                    rejectedOperations++;
                } else if (enrollment.containsKey(courseCode)) {
                    enrollment.put(courseCode, enrollment.get(courseCode) + count);
                } else {
                    enrollment.put(courseCode, count);
                    courseOrder.add(courseCode);
                }

            } else if (operation.equals("WITHDRAW")) {
                int count = sc.nextInt();

                if (count <= 0) {
                    rejectedOperations++;
                } else if (enrollment.containsKey(courseCode)
                        && enrollment.get(courseCode) >= count) {

                    enrollment.put(courseCode, enrollment.get(courseCode) - count);

                } else {
                    rejectedOperations++;
                }

            } else if (operation.equals("CHECK")) {

                if (enrollment.containsKey(courseCode)) {
                    checkResults.add(courseCode + ": "+ enrollment.get(courseCode)+ " students");
                } else {
                    checkResults.add(courseCode + ": Not found");
                }
            }
        }

        sc.close();

        System.out.println("===== Enrollment Checks =====");

        for (String result : checkResults) {
            System.out.println(result);
        }

        System.out.println("\n===== Final Enrollment =====");

        for (String courseCode : courseOrder) {
            System.out.println(courseCode + ": "+ enrollment.get(courseCode)+ " students");
        }

        System.out.println("\nRejected operations: " + rejectedOperations);
    }
}