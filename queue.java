package queue;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class queue {

    record Ticket(String name, int urgency, int arrival) {}
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
         PriorityQueue<Ticket> queue = new PriorityQueue<>(
                Comparator.comparingInt(Ticket::urgency)
                          .thenComparingInt(Ticket::arrival)
        );
 
        System.out.println("Valid words: Printer, Network, Login");
        System.out.println("Type 'done' to finish.");
 
        int arrival = 1;
        while (true) {
            System.out.print("\nTicket word: ");
            String input = scanner.nextLine().trim();
 
            if (input.equalsIgnoreCase("done")) {
                break;
            }
 
            if (input.equalsIgnoreCase("Printer")) {
                queue.offer(new Ticket("Printer", 3, arrival++));
            } else if (input.equalsIgnoreCase("Network")) {
                queue.offer(new Ticket("Network", 1, arrival++));
            } else if (input.equalsIgnoreCase("Login")) {
                queue.offer(new Ticket("Login", 2, arrival++));
            } else {
                System.out.println("Wrong word. Choose Printer, Network, or Login. Try again.");
            }
        }
 
        
        System.out.println("\n=== Poll Order ===");
        while (!queue.isEmpty()) {
            Ticket t = queue.poll();
            System.out.println(t.name());
        }
 
        scanner.close();
    }
}