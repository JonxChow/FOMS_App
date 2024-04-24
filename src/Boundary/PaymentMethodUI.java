package Boundary;

import Controller.PaymentMethodController;
import Entity.Payment.PaymentMethod;

import java.util.Scanner;

public class PaymentMethodUI {
    private PaymentMethodController paymentMethodController;
    private Scanner scanner;

    public PaymentMethodUI(PaymentMethodController paymentMethodController) {
        this.paymentMethodController = paymentMethodController;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n--- Payment Method Management Menu ---");
            System.out.println("1: Add Payment Method");
            System.out.println("2: Remove Payment Method");
            System.out.println("3: Show Current Payment Methods");
            System.out.println("4: Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    addPaymentMethod();
                    break;
                case 2:
                    removePaymentMethod();
                    break;
                case 3:
                    showCurrentPaymentMethods();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void addPaymentMethod() {
        System.out.println("Available Methods: ");
        for (PaymentMethod method : PaymentMethod.values()) {
            System.out.println("- " + method);
        }
        System.out.print("Enter the name of the payment method to add: ");
        String methodName = scanner.nextLine();
        try {
            PaymentMethod method = PaymentMethod.valueOf(methodName.toUpperCase());
            paymentMethodController.addPaymentMethod(method);
            System.out.println(method + " added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid payment method. Please try again.");
        }
    }

    private void removePaymentMethod() {
        System.out.print("Enter the name of the payment method to remove: ");
        String methodName = scanner.nextLine();
        try {
            PaymentMethod method = PaymentMethod.valueOf(methodName.toUpperCase());
            if (paymentMethodController.getAcceptedPaymentMethods().contains(method)) {
                paymentMethodController.removePaymentMethod(method);
                System.out.println(method + " removed successfully.");
            } else {
                System.out.println("Payment method not found in the current accepted list.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid payment method. Please try again.");
        }
    }

    private void showCurrentPaymentMethods() {
        System.out.println("Current Accepted Payment Methods:");
        paymentMethodController.getAcceptedPaymentMethods().forEach(
                method -> System.out.println("- " + method)
        );
    }
}

