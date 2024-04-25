package Boundary;

import Controller.PaymentController;
import Entity.Branch.Branch;
import Entity.Payment.PaymentMethod;
import Helper.InputHelper;
import Interface.Admin.IAllBranches;
import Interface.Boundaries.IPaymentMethodUI;
import Interface.Display.IDisplayMenu;

import java.util.Scanner;
import java.util.Set;

public class PaymentMethodUI implements IDisplayMenu, IPaymentMethodUI {
    private PaymentController paymentController;
    private IAllBranches allBranches;
    private Scanner scanner;


    public PaymentMethodUI(PaymentController paymentController, IAllBranches allBranches) {
        this.paymentController = paymentController;
        this.allBranches = allBranches;
        this.scanner = new Scanner(System.in);
    }

    @Override
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

            Branch branch; // Declare the variable outside the switch to use in multiple cases.

            switch (choice) {
                case 1:
                    branch = getBranch(); // Only get the branch when needed.
                    addPaymentMethod(branch);
                    break;
                case 2:
                    branch = getBranch(); // Only get the branch when needed.
                    removePaymentMethod(branch);
                    break;
                case 3:
                    branch = getBranch(); // Only get the branch when needed.
                    showCurrentPaymentMethods(branch);
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

    private Branch getBranch() {
        System.out.print("Enter the branch name for payment method management: ");
        String branchName = scanner.nextLine();
        return allBranches.getBranchByName(branchName);
    }

    private void addPaymentMethod(Branch branch) {
        System.out.println("Available Methods: ");
        for (PaymentMethod method : PaymentMethod.values()) {
            System.out.println("- " + method);
        }
        System.out.print("Enter the name of the payment method to add: ");
        String methodName = scanner.nextLine();
        try {
            PaymentMethod method = PaymentMethod.valueOf(methodName.toUpperCase());
            if (branch != null && paymentController.addPaymentMethod(branch, method)) {
                System.out.println(method + " added successfully to " + branch.getBranchName());
            } else {
                System.out.println("Failed to add payment method to the branch.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid payment method. Please try again.");
        }
    }

    private void removePaymentMethod(Branch branch) {
        System.out.print("Enter the name of the payment method to remove: ");
        String methodName = scanner.nextLine();
        try {
            PaymentMethod method = PaymentMethod.valueOf(methodName.toUpperCase());
            if (branch != null && paymentController.removePaymentMethod(branch, method)) {
                System.out.println(method + " removed successfully from " + branch.getBranchName());
            } else {
                System.out.println("Payment method not found in the current accepted list or failed to remove.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid payment method. Please try again.");
        }
    }

    @Override
    public void showCurrentPaymentMethods(Branch branch) {
        if (branch != null) {
            Set<PaymentMethod> methods = branch.getPaymentMethods();
            if (methods != null && !methods.isEmpty()) {
                System.out.println("Current Accepted Payment Methods for " + branch.getBranchName() + ":");
                methods.forEach(method -> System.out.println("- " + method));
            } else {
                System.out.println("No payment methods found for " + branch.getBranchName());
            }
        } else {
            System.out.println("Branch not found.");
        }
    }

}
