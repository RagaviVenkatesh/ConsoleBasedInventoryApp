package controller;

import dao.ProductDAO;
import dao.ProductDAOImpl;
import dao.TransactionDAO;
import dao.TransactionDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import service.AdminService;
import service.AgentService;

public class Main {
    public static void main(String[] args) {
        // Initialize DAOs
        ProductDAO productDAO = new ProductDAOImpl();
        TransactionDAO transactionDAO = new TransactionDAOImpl();
        UserDAO userDAO = new UserDAOImpl();

        // Initialize Services
        AdminService adminService = new AdminService(productDAO);
        AgentService agentService = new AgentService(productDAO, transactionDAO);

        // Initialize Controller
        AppController appController = new AppController(adminService, agentService, userDAO);

        // Start the application
        appController.start();
    }
}

