import java.util.Scanner;
/**
 *
 * @author LIN YU QING
*/

public class Staff extends Person {
    private String staffID;
    private String password;

    public Staff ( String name, char gender, int age, String phoneNo, String email, String address, String staffID, String password ) {
        super ( name, gender, age, phoneNo, email, address );
        this.staffID = staffID;
        this.password = password;
    }

    public Staff() {

    }

    public String getStaffID() {
        return staffID;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setStaffID ( String staffID ) {
        this.staffID = staffID;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }
    
    public static int staffLogin (Staff[] logStaff) {
        
    int staffIndex = -1;
    
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter your Staff ID: \t");
    String staffIDEntry = scan.nextLine();

    System.out.print("Enter the password: \t");
    String passwordEntry = scan.nextLine();
    
            for( int i = 0; i < logStaff.length; i++ ) {
                
                if( logStaff[i]==null ) {
                    System.out.println("Invalid Staff ID or Password !!");
                    break;
                }
                else if(staffIDEntry.equals(logStaff[i].getStaffID())){
                    if(passwordEntry.equals(logStaff[i].getPassword())){
                        
                        System.out.println("\nWelcome back "+ logStaff[i].getName() +"!!" );
                        staffIndex = i;
                        break;
                    }
                    else {
                        System.out.println("Invalid Staff ID or Password !!");
                        break;
                    }
                }
            }
                    return staffIndex;
    }
    
    public static void staffMenu(){
 
    System.out.println("+===============================+");
    System.out.println("| \tStaff Main Menu\t\t|");
    System.out.println("+===============================+");
    System.out.println("| 1. Product Menu \t\t|");
    System.out.println("| 2. Start Order \t\t|");
    System.out.println("| 3. Exit \t\t\t|");
    System.out.println("+===============================+");
        
    }
    
    public String toString(){
    	return "\n===========================\nID: " + staffID +"\nPassword: " + password +"\n--------------------------" + super.toString() + "\n===========================";
    }

}

