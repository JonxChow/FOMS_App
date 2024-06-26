package Helper;

import Entity.Actor.Gender;
import Entity.Actor.Role;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Entity.Lists.AllBranches;
import Interface.Admin.IAllBranches;
import Interface.Boundaries.IStaffDisplayUI;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The {@code StaffDisplayUI} class is responsible for displaying staff information to the user.
 * It allows different display options based on the user's role (Manager or Admin) and supports filtering
 * staff by various criteria such as role, gender, and age.
 */
public class StaffDisplayUI implements IStaffDisplayUI {
    private final Scanner scanner;
    private final IAllBranches allBranches;

    /**
     * Constructs a new StaffDisplayUI with a reference to an IAllBranches instance.
     *
     * @param allBranches The {@code IAllBranches} object that contains all branch-related information.
     */
    public StaffDisplayUI(IAllBranches allBranches) {
        this.scanner = new Scanner(System.in);
        this.allBranches = allBranches;
    }

    /**
     * Displays staff information for a manager. This method only shows staff in the manager's branch.
     *
     * @param branch The {@code Branch} object representing the manager's branch.
     */
    @Override
    public void displayForManager(Branch branch) {
        // Manager can only display staff in their own branch, no filters applied
        System.out.println("Staff List for branch: " + branch.getBranchName());
        List<Staff> staffList = branch.getStaffMembers();
        staffList.forEach(staff -> System.out.println(staff.getName() + " - " + staff.getStaffRole() + " - Age: " + staff.getAge() + " - Gender: " + staff.getGender()));
    }

    /**
     * Displays staff information for an admin with various filter options.
     * The admin can filter the staff list by role, gender, and age, or choose to display all staff.
     */
    @Override
    public void displayForAdmin() {
        // Admin can display staff across all branches with filter options
        System.out.println("Select the filter criteria for displaying staff:");
        System.out.println("1: By Role");
        System.out.println("2: By Gender");
        System.out.println("3: By Age");
        System.out.println("4: Display all staff");

        System.out.print("Enter your choice: ");
        int filterChoice = scanner.nextInt();
        scanner.nextLine();

        List<Staff> allStaff = allBranches.getAllStaff();
        List<Staff> filteredStaff = switch (filterChoice) {
            case 1 -> filterByRole(allStaff);
            case 2 -> filterByGender(allStaff);
            case 3 -> filterByAge(allStaff);
            default -> allStaff;
        };

        if (filteredStaff.isEmpty()) {
            System.out.println("No staff found matching the criteria.");
        } else {
            System.out.println("Filtered Staff List:");
            filteredStaff.forEach(staff -> System.out.println(staff.getName() + " - " + staff.getStaffRole() + " - Age: " + staff.getAge() + " - Gender: " + staff.getGender()));
        }
    }

    /**
     * Filters the list of staff by their role and returns a list of staff matching the selected role.
     *
     * @param staff The list of {@code Staff} to filter.
     * @return A list of {@code Staff} filtered by the selected role.
     */
    private List<Staff> filterByRole(List<Staff> staff) {
        int roleChoice = InputHelper.getValidatedInt("Select role for filtering: 0: Staff, 1: Manager: ", 0, 1);

        Role role = (roleChoice == 0) ? Role.STAFF : Role.MANAGER;

        return staff.stream()
                .filter(staffMember -> staffMember.getStaffRole() == role)
                .collect(Collectors.toList());
    }

    /**
     * Filters the list of staff by their gender and returns a list of staff matching the selected gender.
     *
     * @param staff The list of {@code Staff} to filter.
     * @return A list of {@code Staff} filtered by the selected gender.
     */
    private List<Staff> filterByGender(List<Staff> staff) {
        int choice = InputHelper.getValidatedInt("0: Female or 1: Male: ", 0, 1);

        Gender gender = (choice == 0) ? Gender.FEMALE : Gender.MALE;

        try {
            return staff.stream()
                    .filter(staffMember -> staffMember.getGender() == gender)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid gender. Available options are MALE, FEMALE, OTHER.");
            return List.of();
        }
    }

    /**
     * Filters the list of staff by their age and returns a list of staff matching the specified age.
     *
     * @param staff The list of {@code Staff} to filter.
     * @return A list of {@code Staff} filtered by the specified age.
     */
    private List<Staff> filterByAge(List<Staff> staff) {
        int age = InputHelper.getValidatedInt("Enter the age to filter by: ", 0, 150); // Assuming age range

        return staff.stream()
                .filter(staffMember -> staffMember.getAge() == age)
                .collect(Collectors.toList());
    }
}
