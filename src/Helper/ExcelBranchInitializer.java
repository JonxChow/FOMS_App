package Helper;

import Entity.Actor.Gender;
import Entity.Actor.Manager;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Interface.Admin.IAllBranches;
import Interface.Controllers.IBranchController;
import Interface.Controllers.IStaffManager;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;


/**
 * The {@code ExcelBranchInitializer} class is responsible for initializing
 * branches and their associated staff from an Excel file.
 * It uses the Apache POI library to read XLSX documents.
 */
public class ExcelBranchInitializer {
    private IBranchController branchController;
    private IStaffManager staffManagementController;
    private IAllBranches allBranches;

    /**
     * Constructs an ExcelBranchInitializer with the necessary controllers and repositories.
     *
     * @param branchController         The controller responsible for branch operations.
     * @param staffManagementController The controller responsible for staff operations.
     * @param allBranches              The repository containing all branches.
     */
    public ExcelBranchInitializer(IBranchController branchController, IStaffManager staffManagementController, IAllBranches allBranches) {
        this.branchController = branchController;
        this.staffManagementController = staffManagementController;
        this.allBranches = allBranches;
    }

    /**
     * Initializes branches and their staff members from the specified Excel file.
     * The method reads the Excel file, creating new branches and staff members as defined in the file.
     * It assumes a specific format for the Excel file, where the first row is considered to be header information
     * and is skipped, and the following rows contain the data for branches and staff.
     *
     * @param filePath the path to the Excel file that contains branch and staff information
     */
    public void initializeBranchesFromFile(String filePath) {
        try (FileInputStream excelFile = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(excelFile)) {
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            // Skip the header row if present
            if (iterator.hasNext()) {
                iterator.next();
            }

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();

                // Check if the row is effectively empty by checking necessary cells
                if (isRowEffectivelyEmpty(currentRow)) {
                    continue; // Skip this row if it is empty
                }

                // Assuming that cells 0, 1, 2, 3, 4, 5, 6 must not be blank for valid data processing
                String branchName = currentRow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String location = currentRow.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                int id = (int) currentRow.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                String name = currentRow.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                int age = (int) currentRow.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                String genderStr = currentRow.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().trim().toUpperCase();
                System.out.println("Gender read from Excel: '" + genderStr + "'");
                Gender gender = Gender.valueOf(genderStr);
                String role = currentRow.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                Branch branch;
                if (!allBranches.branchExists(branchName)) {
                    branch = branchController.createBranch(branchName, location);
                } else {
                    branch = allBranches.getBranchByName(branchName);
                }

                if ("Manager".equalsIgnoreCase(role)) {
                    Manager newManager = new Manager(id, name, age, gender);
                    staffManagementController.addManager(branch, newManager);
                } else {
                    Staff newStaff = new Staff(id, name, age, gender);
                    staffManagementController.addStaff(branch, newStaff);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a row is effectively empty. An effectively empty row is defined as a row
     * where the first seven cells are blank, as these cells are expected to contain branch and staff information.
     *
     * @param row the row to check for effective emptiness
     * @return {@code true} if the row is effectively empty, {@code false} otherwise
     */
    private boolean isRowEffectivelyEmpty(Row row) {
        for (int cellNum = 0; cellNum < 7; cellNum++) { // Check the first 7 cells expected to contain data
            Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false; // Return false if any cell is not blank
            }
        }
        return true; // Return true if all checked cells are blank
    }
}
