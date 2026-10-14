package queue;
import java.util.*;

public class queue {

    record Ticket(String name, int urgency, int arrival) {}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PriorityQueue<Ticket> queue = new PriorityQueue<>(
                Comparator.comparingInt(Ticket::urgency)
                          .thenComparingInt(Ticket::arrival)
        );

        System.out.println("Enter tickets");
        System.out.println("Type 'done' as the ticket name to finish.");

        int arrival = 1;
        while (true) {
            System.out.print("\nTicket name: ");
            String name = scanner.nextLine().trim();

            if (name.equalsIgnoreCase("done")) {
                break;
            }

            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Try again.");
                continue;
            }

            System.out.print("Urgency number for " + name + ": ");
            String urgencyInput = scanner.nextLine().trim();

            try {
                int urgency = Integer.parseInt(urgencyInput);
                queue.offer(new Ticket(name, urgency, arrival++));
                System.out.println("Added: " + name + " (urgency " + urgency + ")");
            } catch (NumberFormatException e) {
                System.out.println("Urgency must be a whole number. Ticket not added. Try again.");
            }
        }

        System.out.println("\n=== Poll Order ===");
        if (queue.isEmpty()) {
            System.out.println("No tickets were entered.");
        }
        while (!queue.isEmpty()) {
            Ticket t = queue.poll();
            System.out.println(t.name() + " (urgency " + t.urgency() + ")");
        }

        scanner.close();
    }