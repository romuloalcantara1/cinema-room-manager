import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    private static int purchasedTickets = 0;
    private static int currentIncome = 0;
    private static int totalIncome = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get number of rows and seats
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        // Create and initialize the seat matrix
        char[][] cinema = new char[rows][seats];
        for (char[] row : cinema) {
            Arrays.fill(row, 'S'); // Fill with 'S' for available seats
        }

        totalIncome = calculateTotalIncome(rows, seats);

        int choice;
        do {
            // Display the menu
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    printCinema(cinema);
                    break;
                case 2:
                    buyTicket(cinema, rows, seats, scanner);
                    break;
                case 3:
                    showStatistics(rows, seats);
                    break;
                case 0:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    // Method to display the seating arrangement
    private static void printCinema(char[][] cinema) {
        System.out.println("Cinema:");
        System.out.print("  "); // Space for row numbers
        for (int i = 1; i <= cinema[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < cinema.length; i++) {
            System.out.print((i + 1) + " "); // Print row number
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void buyTicket(char[][] cinema, int rows, int seats, Scanner scanner) {
        int rowNumber;
        int seatNumber;

        while (true) {
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt() - 1; // Adjust for 0-based indexing
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt() - 1;

            if (rowNumber < 0 || rowNumber >= rows || seatNumber < 0 || seatNumber >= seats) {
                System.out.println("Wrong input!");
            } else if (cinema[rowNumber][seatNumber] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                break;
            }
        }

        int ticketPrice = calculateTicketPrice(rows, seats, rowNumber);
        cinema[rowNumber][seatNumber] = 'B'; // Mark as booked
        purchasedTickets++;
        currentIncome += ticketPrice;

        System.out.println("Ticket price: $" + ticketPrice);
    }

    // Method to calculate ticket price based on row and number of seats
    private static int calculateTicketPrice(int rows, int seats, int rowNumber) {
        int frontRows = rows / 2;
        if (rowNumber < frontRows) {
            return 10; // Price for front rows
        } else {
            return 8; // Price for back rows
        }
    }

    // Method to calculate the total income if all tickets are sold
    private static int calculateTotalIncome(int rows, int seats) {
        int totalIncome = 0;
        int frontRows = rows / 2;
        int backRows = rows - frontRows;

        totalIncome += frontRows * seats * 10;
        totalIncome += backRows * seats * 8;

        return totalIncome;
    }

    // Method to show statistics
    private static void showStatistics(int rows, int seats) {
        double percentage = (double) purchasedTickets / (rows * seats) * 100;
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }
}