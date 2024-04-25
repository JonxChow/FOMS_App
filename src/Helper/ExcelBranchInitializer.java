package Helper;

import Controller.BranchController;
import Controller.StaffManagementController;
import Entity.Actor.Gender;
import Entity.Actor.Manager;
import Entity.Actor.Staff;
import Entity.Branch.Branch;
import Interface.Controllers.IBranchController;
import Interface.Controllers.IStaffManager;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class ExcelBranchInitializer {
    private IBranchController branchController;
    private IStaffManager staffManagementController;

    public ExcelBranchInitializer(IBranchController branchController, IStaffManager staffManagementController) {
        this.branchController = branchController;
        this.staffManagementController = staffManagementController;
    }

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

                // Assuming the Excel columns are: Branch Name, Location, ID, Name, Age, Gender, Role
                String branchName = currentRow.getCell(0).getStringCellValue();
                String location = currentRow.getCell(1).getStringCellValue();

                // Create an empty branch first
                Branch newBranch = branchController.createBranch(branchName, location);

                // Parse staff and managers from the Excel row
                int id = (int) currentRow.getCell(2).getNumericCellValue();
                String name = currentRow.getCell(3).getStringCellValue();
                int age = (int) currentRow.getCell(4).getNumericCellValue();
                Gender gender = Gender.valueOf(currentRow.getCell(5).getStringCellValue().toUpperCase());
                String role = currentRow.getCell(6).getStringCellValue();

                if ("Manager".equalsIgnoreCase(role)) {
                    // Create a manager
                    Manager newManager = new Manager(id, name, age, gender);
                    staffManagementController.addManager(newBranch, newManager);
                } else {
                    // Create staff
                    Staff newStaff = new Staff(id, name, age, gender);
                    staffManagementController.addStaff(newBranch, newStaff);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
