package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        LinkedList<String[]> transactions = new LinkedList<>();

        while (scanner.hasNext()) {
            String name = scanner.next();
            String type = scanner.next();
            int amount = scanner.nextInt();

            transactions.add(new String[]{name, type, String.valueOf(amount)});
        }

        scanner.close();

        LinkedList<String[]> customer = new LinkedList<>();

        for (String[] transaction : transactions) {

            String name = transaction[0];

            boolean exists = false;

            for (String[] c : customer) {
                if (c[0].equals(name)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                customer.add(new String[]{name, "0"});
            }
        }

        Queue<String[]> queue = new LinkedList<>();

        while (!transactions.isEmpty()) {
            queue.offer(transactions.removeFirst());
        }

        Stack<String[]> failedTransactions = new Stack<>();

        while (!queue.isEmpty()) {

            String[] transaction = queue.poll();

            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            String[] currentCustomer = null;

            for (String[] c : customer) {
                if (c[0].equals(name)) {
                    currentCustomer = c;
                    break;
                }
            }

            int balance = Integer.parseInt(currentCustomer[1]);

            if (type.equals("DEPOSIT")) {

                balance += amount;
                currentCustomer[1] = String.valueOf(balance);

            } else if (type.equals("WITHDRAW")) {

                if (amount > balance) {

                    failedTransactions.push(transaction);

                } else {

                    balance -= amount;
                    currentCustomer[1] = String.valueOf(balance);
                }
            }
        }

        System.out.println("=== Final Balances ===");

        for (String[] c : customer) {
            System.out.println(c[0] + " : " + c[1]);
        }
        System.out.println();
        System.out.println("=== Failed Transactions ===");

        while (!failedTransactions.isEmpty()) {

            String[] transaction = failedTransactions.pop();

            System.out.println(
                    transaction[0] + " "
                    + transaction[1] + " "
                    + transaction[2]
            );
        }
    }
}