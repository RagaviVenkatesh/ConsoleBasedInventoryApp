package controller;

import java.util.Scanner;

import dao.UserDAO;
import model.Product;
import model.User;
import service.AdminService;
import service.AgentService;

public class AppController {
    private AdminService adminService;
    private AgentService agentService;
    private UserDAO userDAO;

    public AppController(AdminService adminService, AgentService agentService, UserDAO userDAO) {
        this.adminService = adminService;
        this.agentService = agentService;
        this.userDAO = userDAO;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Admin Login");
            System.out.println("2. Agent Login");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                adminLogin(scanner);
            } else if (choice == 2) {
                agentLogin(scanner);
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private void adminLogin(Scanner scanner) {
        System.out.print("Enter Admin Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        User admin = userDAO.findUserByName(name);
        if (admin != null && "Admin".equalsIgnoreCase(admin.getRole()) && password.equals(admin.getPassword())) {
            System.out.println("Admin Login Successful");
            adminMenu(scanner);
        } else {
            System.out.println("Invalid credentials or not an Admin.");
        }
    }

    private void adminMenu(Scanner scanner) {
        while (true) {
        	System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Display Inventory Details");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Logout");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                addProduct(scanner);
            } else if (choice == 2) {
                adminService.displayInventory();
            } else if (choice == 3) {
                updateProduct(scanner);
            } else if (choice == 4) {
                deleteProduct(scanner);
            } else if (choice == 5) {
                System.out.println("Logging out from Admin...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addProduct(Scanner scanner) {
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter Minimum Sell Quantity: ");
        int minSellQuantity = scanner.nextInt();

        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Available Quantity: ");
        int quantityAvailable = scanner.nextInt();

        Product product = new Product(productId, productName, minSellQuantity, price, quantityAvailable);
        adminService.addProduct(product);
        System.out.println("Product added successfully.");
    }
    
    private void updateProduct(Scanner scanner) {
        System.out.print("Enter Product ID to update: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter New Product Name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter New Minimum Sell Quantity: ");
        int minSellQuantity = scanner.nextInt();

        System.out.print("Enter New Product Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter New Available Quantity: ");
        int quantityAvailable = scanner.nextInt();

        Product product = new Product(productId, productName, minSellQuantity, price, quantityAvailable);
        adminService.updateProduct(product);
        System.out.println("Product updated successfully.");
    }

    private void deleteProduct(Scanner scanner) {
        System.out.print("Enter Product ID to delete: ");
        int productId = scanner.nextInt();

        adminService.deleteProduct(productId);
        System.out.println("Product deleted successfully.");
    }

    private void agentLogin(Scanner scanner) {
        System.out.print("Enter Agent Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        User agent = userDAO.findUserByName(name);
        if (agent != null && "Agent".equalsIgnoreCase(agent.getRole()) && password.equals(agent.getPassword())) {
            System.out.println("Agent Login Successful");
            agentMenu(scanner, agent.getId());
        } else {
            System.out.println("Invalid credentials or not an Agent.");
        }
    }

    private void agentMenu(Scanner scanner, int userId) {
        while (true) {
            System.out.println("\nAgent Menu:");
            System.out.println("1. Buy/Sell Product");
            System.out.println("2. Show Transaction History");
            System.out.println("3. Logout");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                processTransaction(scanner, userId);
            } else if (choice == 2) {
                agentService.showTransactionHistory(userId);
            } else if (choice == 3) {
                System.out.println("Logging out from Agent...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void processTransaction(Scanner scanner, int userId) {
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Transaction Type (Buy/Sell): ");
        String transactionType = scanner.nextLine();

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        agentService.processTransaction(userId, productId, transactionType, quantity);
    }
}

