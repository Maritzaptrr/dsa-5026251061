package lw03.prelab;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
    }

    public static void problem1() {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("playlist.txt"));

        List<String> playlist = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" ", 2);

            String operation = parts[0];

            if (operation.equals("ADD")) {
                String song = parts[1];
                playlist.add(song);

            } else if (operation.equals("REMOVE")) {
                String song = parts[1];
                playlist.remove(song);

            } else if (operation.equals("INSERT")) {
                String[] insertParts = parts[1].split(" ", 2);

                int index = Integer.parseInt(insertParts[0]);
                String songName = insertParts[1];
                playlist.add(index, songName);
            }
        }

        sc.close();

        System.out.println("\n===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    public static void problem2() {
        Set<String> participants = new LinkedHashSet<>();
        int duplicateRegistrations = 0;

        Scanner sc = new Scanner(Main.class.getResourceAsStream("participants.txt"));

        while (sc.hasNextLine()) {
            String name = sc.nextLine();

            if (!participants.add(name)) {
                duplicateRegistrations++;
            }
        }

        sc.close();

        System.out.println("\n===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());

        int number = 1;

        for (String participant : participants) {
            System.out.println(number + ". " + participant);
            number++;
        }

        System.out.println("Duplicate registrations: " + duplicateRegistrations);
    }

    public static void problem3() {
        Map<String, Integer> scores = new LinkedHashMap<>();
        int failedSales = 0;

        Scanner sc = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

        while (sc.hasNextLine()) {
            String operation = sc.next();
            String product = sc.next();
            int quantity = sc.nextInt();

            if (operation.equals("ADD")) {

                scores.put(
                        product,
                        scores.getOrDefault(product, 0) + quantity
                );

            } else if (operation.equals("SELL")) {

                if (scores.containsKey(product)
                        && scores.get(product) >= quantity) {

                    scores.put(
                            product,
                            scores.get(product) - quantity
                    );

                } else {
                    failedSales++;
                }
            }
        }

        sc.close();

        System.out.println("\n===== Problem 3 =====");

        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Failed sales: " + failedSales);
    }
}