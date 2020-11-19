
import static java.lang.Character.toUpperCase;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author LIN YU QING
 */
public class Manager extends Person {
    private String managerID;
    private String password;

    public Manager ( String name, char gender, int age, String phoneNo, String email, String address, String managerID, String password ) {
        super ( name, gender, age, phoneNo, email, address );
        this.managerID = managerID;
        this.password = password;
    }

    public Manager() {
        
    }

    public String getManagerID() {
        return managerID;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setManagerID ( String managerID ) {
        this.managerID = managerID;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }
    
    public static void managerMenu(){
 
    System.out.println("+===============================+");
    System.out.println("| \tManager Main Menu\t|");
    System.out.println("+===============================+");
    System.out.println("| 1. Add Staff \t\t\t|");
    System.out.println("| 2. Edit Staff \t\t|");
    System.out.println("| 3. Search Staff \t\t|");
    System.out.println("| 4. Display Staff \t\t|");
    System.out.println("+-------------------------------+");
    System.out.println("| 5. Add Product \t\t|");
    System.out.println("| 6. Edit Product \t\t|");
    System.out.println("| 7. Product Menu \t\t|");
    System.out.println("+-------------------------------+");
    System.out.println("| 8. Display Bill \t\t|");
    System.out.println("| 9. Search Bill \t\t|");
    System.out.println("| 10. Start Order \t\t|");
    System.out.println("| 11. Exit \t\t\t|");
    System.out.println("+===============================+");
        
    }
    
    public static int managerLogin (Manager[] logManager) {
        
    int managerIndex = -1;
    
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter your Manager ID: \t");
    String managerIDEntry = scan.nextLine();

    System.out.print("Enter the password: \t");
    String passwordEntry = scan.nextLine();
    
            for( int i = 0; i < logManager.length; i++ ) {
                
                if( logManager[i]==null ) {
                    System.out.println("Invalid Manager ID or Password !!");
                    break;
                }
                else if(managerIDEntry.equals(logManager[i].getManagerID())){
                    if(passwordEntry.equals(logManager[i].getPassword())){
                        
                        System.out.println("\nWelcome back "+ logManager[i].getName() +"!!" );
                        managerIndex = i;
                        break;
                    }
                    else {
                        System.out.println("Invalid Manager ID or Password !!");
                        break;
                    }
                }
            }
                    return managerIndex;
    }
    
        public static void displayStaff( Staff[] dStaff ){
                
    	int totalStaff = 0;
    	
    	for(int i = 0; i <dStaff.length ; i++){
    		if(dStaff[i] instanceof Staff){
                                                    System.out.printf("\n [ Staff ( %d ) ] ",totalStaff+1);
    			System.out.println("\t\t" + dStaff[i].toString());
                                                    totalStaff++;
    		}
    	}
        System.out.printf("Total %d staff displayed. Sending back to Main Menu...\n\n",totalStaff);
        
    }
            
        public static void addStaff(Manager[] verifyManager, Staff[] verifyStaff) {
        
        boolean genderChk = false;
        boolean ageChk = false;
        boolean idChk;
        char newGender = 0;
        int newAge = 0;
        
        System.out.println("\nAdding new staff in progress...");
        
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter new staff Name : \t");
        String newName = scan.nextLine();
        newName = newName.toUpperCase();
            
        do{
            try{
                System.out.print("Enter staff Gender ( M - Male / F - Female ) : ");
                newGender = scan.next().charAt(0);
                newGender = toUpperCase(newGender);
            }catch(InputMismatchException ex){
                    scan.nextLine();
            }
                if( newGender == 'M' || newGender == 'F' ){
                    genderChk = true;
                    break;
                }
                else{
                    System.out.println("Invalid Input !! Please enter ( M / F ) only..\n");
                }
        }while(genderChk == false);
    
        do{
            try{
                System.out.print("Enter new staff Age : ");
                newAge = scan.nextInt();
                ageChk = true;
            }catch(InputMismatchException ex){
                System.out.println("Invalid age entered, Please try again..\n");
                    scan.nextLine();
            }
        }while(ageChk == false);
        
        scan.nextLine();
        System.out.print("Enter new staff Phone Number : ");
        String newPhoneNo = scan.nextLine();
        System.out.print("Enter new staff Email : ");
        String newEmail = scan.nextLine();
        System.out.print("Enter new staff Address : ");
        String newAddress = scan.nextLine();
        
        do{
            idChk = false;
            
            System.out.print("Enter new staff Login ID : ");
            String newStaffID = scan.nextLine();
            
            for(int i = 0; i < verifyStaff.length; i++ ){
                if(verifyStaff[i] == null){

                    for(int j = 0; j < verifyManager.length; j++ ){
                        if( verifyManager[j] == null ){
   
                            System.out.print("Enter new staff User Password : ");
                            String newPassword = scan.nextLine();

                            verifyStaff[i] = new Staff (newName, newGender, newAge, newPhoneNo, newEmail, newAddress, newStaffID, newPassword);
                            System.out.println("New Staff Successfully Added !! Sending back to Main Menu...\n");
                            break;
                        }
                        else if( newStaffID.equals(verifyManager[j].getManagerID())){
                            System.out.println("Login ID already exist !! Please try again for another Login ID.\n");
                            idChk = true;
                            break;
                        }
                    }
                    break;
                }
                else if(newStaffID.equals(verifyStaff[i].getStaffID())){
                        System.out.println("Staff Login ID already exist !! Please try for another Login ID.\n");
                        idChk = true;
                        break;
                }
            }
        }while(idChk == true);
        
    }

    public static void editStaff(Staff[] eStaff, Manager[] vManager){
    	
                 boolean check1 = false;
                 Scanner scan = new Scanner(System.in);
                 int selection;
                 int confirmation1;
                 int eStaffIndex = 0;
                 int deleteConfirm;
                 String editStaffID;
                 
                 System.out.println("\nEdit Staff in progress...");
    	System.out.print("Enter the Staff Id you would like to edit : \t");
    	editStaffID = scan.nextLine();
        
                  for(int i = 0; i < eStaff.length; i++){
                      if(eStaff[i] != null){
                          if(eStaff[i].getStaffID().equals(editStaffID)){
                              eStaffIndex = i;
                              check1 = true;
                              break;
                          }
                      }
                  }
                  
                  if( check1 == true ){
                      do{
                            confirmation1 = 0;
                          try{
                            System.out.print("\nAre you sure you want to edit "+ eStaff[eStaffIndex].getName() +" 's profile ? ( 1. Yes / 2. No ) : \t" );
                            confirmation1 = scan.nextInt();
                            }catch(InputMismatchException ex){
                                scan.nextLine();
                            }
                            if(confirmation1 != 1 && confirmation1 !=2 ){
                                System.out.println("Please Enter 1 or 2 only !!. \t");
                            }
                            else if(confirmation1 == 1){
                                
                                   do{
                                                selection = 0;
                                                
                                                System.out.println("\n+=======================+");
                                                System.out.println("|  \tEdit Menu\t|");
                                                System.out.println("+=======================+");
                                                System.out.println("| 1. Login ID \t\t|");
                                                System.out.println("| 2. Password \t\t|");
                                                System.out.println("| 3. Phone Number \t|");
                                                System.out.println("| 4. Email \t\t|");
                                                System.out.println("| 5. Address \t\t|");
                                                System.out.println("| 6. Delete Staff \t|");
                                                System.out.println("| 7. Exit \t\t|");
                                                System.out.println("+=======================+");
                                                System.out.print("Select information you would like to edit : \t");
                                                 
                                                try{
                                                     selection = scan.nextInt();
                                                     scan.nextLine();
                                                }catch(InputMismatchException ex){
                                                        scan.nextLine();
                                                }
                                                 switch(selection){
                                                         case 1:
                                                                    System.out.print("Enter New Login ID: \t");
                                                                    String newLoginID = scan.nextLine();
                                                                                                        
                                                                    for(int k = 0; k < eStaff.length; k++ ){
                                                                        if(eStaff[k] == null){
                                                                            for(int j = 0; j < vManager.length; j++ ){
                                                                                if( vManager[j] == null ){
                                                                                    eStaff[eStaffIndex].setStaffID(newLoginID);
                                                                                    System.out.println("Profile Successfully Updated !!. \n");
                                                                                    break;
                                                                                }
                                                                                else if( newLoginID.equals(vManager[j].getManagerID())){
                                                                                    System.out.println("Login ID already exist !! Please try again for another Login ID.\n");
                                                                                    break;
                                                                                }
                                                                            }
                                                                            break;
                                                                        }
                                                                        else if(newLoginID.equals(eStaff[k].getStaffID())){
                                                                                System.out.println("Staff Login ID already exist !! Please try for another Login ID.\n");
                                                                                break;
                                                                        }
                                                                    }
                                                                    break;
                                                                    
                                                         case 2:
                                                                    System.out.print("Enter New Login Password: \t");
                                                                    String newLoginPass = scan.nextLine();
                                                                    eStaff[eStaffIndex].setPassword(newLoginPass);
                                                                    System.out.print("Profile successfully updated!!\n");
                                                                    break;
                                                         case 3:
                                                                 System.out.print("Enter Phone Number: \t");
                                                                 String newPhoneNo = scan.nextLine();
                                                                 eStaff[eStaffIndex].setPhoneNo(newPhoneNo);
                                                                 System.out.print("Profile successfully updated!!\n");
                                                                 break;
                                                         case 4:
                                                                 System.out.print("Enter Email: \t");
                                                                 String newEmail = scan.nextLine();
                                                                 eStaff[eStaffIndex].setEmail(newEmail);
                                                                 System.out.print("Profile successfully updated!!\n");
                                                                 break;
                                                         case 5:
                                                                 System.out.print("Enter Address:	");
                                                                 String newAddress = scan.nextLine();
                                                                 eStaff[eStaffIndex].setAddress(newAddress);
                                                                 break;
                                                         case 6:
                                                             
                                                             do{
                                                                    deleteConfirm = 0;
                                                                    try{
                                                                        System.out.print("Do you sure you want to remove staff " + eStaff[eStaffIndex].getName() +" ?? ( 1. Yes / 2. No ) : ");
                                                                        deleteConfirm = scan.nextInt();
                                                                    }catch(InputMismatchException ex){
                                                                        scan.nextLine();
                                                                    }
                                                                    
                                                                    if(deleteConfirm == 1){
                                                                        eStaff[eStaffIndex] = null;
                                                                        System.out.println("Staff Successfully deleted..\n");
                                                                        selection = 7;
                                                                        break;
                                                                    }
                                                                    else if(deleteConfirm == 2){
                                                                        System.out.println(" 2(No) Entered ! You will be sending back to Edit Menu. ");
                                                                        break;
                                                                    }
                                                                    else{
                                                                        System.out.println("Invalid Option, please enter [ 1 ] for Yes and [ 2 ] for No only.\n");
                                                                    }
                                                                }while(deleteConfirm != 1 || deleteConfirm != 2);
                                                                    break;
                                                             
                                                         case 7:
                                                                  System.out.println("Exit to Main Menu...\n");
                                                                  break;
                                                         default:
                                                             System.out.print("Please Enter 1 to 7 only !!.\n");
                                                             break;
                                                 }
                                   } while (selection != 7);
                                   break;
                            }
                            else if ( confirmation1 == 2 ){
                                System.out.println(" 2(No) Entered ! You will be sending back to Main Menu. ");
                                break;
                           }
                      }while( confirmation1 != 1 || confirmation1 != 2 );
                  }
                  else{
                      System.out.println("\nStaff not found !! Sending back to Main Menu...\n");
                  }
    }

    public static void addProduct( Product[] addProduct ) {
        Scanner scan = new Scanner(System.in);
        double newPrice = 0;
        String newProductID = null;
        String newPdtName = null;
        boolean priceChk;
        boolean idChk;
        boolean nameChk;
        
        System.out.println("Adding new Product...\n");
        
        do{
            idChk = false;
            System.out.printf("Enter new Product ID : ");
            newProductID = scan.nextLine();
            newProductID = newProductID.toUpperCase();
        
            for(int i = 0; i < addProduct.length; i++){
                if(addProduct[i] == null){
                   idChk = true;
                   break;
                }
                else{
                    if(addProduct[i].getProductID().equals(newProductID)){
                        System.out.println("Product ID already exist !! Please try again.\n");
                        idChk = false;
                        break;
                    }
                }
            }
        }while(idChk == false);
        
        for(int i = 0; i < addProduct.length ; i++){
            if( addProduct[i] == null ){
                
                System.out.printf("Enter Product Type : ");
                String newProductType = scan.nextLine();
                
                do{
                        nameChk = false;
                        System.out.printf("Enter Product Name : ");
                        newPdtName = scan.nextLine();
                        newPdtName = newPdtName.toUpperCase();

                        for(int j = 0; j < addProduct.length; j++){
                            if(addProduct[j] == null){
                               nameChk = true;
                               break;
                            }
                            else{
                                if(addProduct[j].getPdtName().equals(newPdtName)){
                                    System.out.println("Product Name already exist !! Please try again.\n");
                                    nameChk = false;
                                    break;
                                }
                            }
                        }
                    }while(nameChk == false);

                do{
                    try{
                        System.out.printf("Enter Product Price : ");
                        newPrice = scan.nextDouble();
                        priceChk = true;
                    }catch(InputMismatchException ex){
                        System.out.println("Invalid Input !! Please enter numeric value only.\n");
                        scan.nextLine();
                        priceChk = false;
                    }
                }while(priceChk == false);
                
                addProduct[i] = new Product (newProductType, newProductID, newPdtName, newPrice);
                System.out.println("New Product Successfully Added !! Sending back to Main Menu...\n");
                break;
            }
            else if( newProductID.equals(addProduct[i].getProductID())){
                System.out.println("This Product ID already exist !! Please try for another Product ID.\n");
                break;
            }
        }        
    }

    public static void editProduct(Product[] editPdt) {
        
        Scanner scan = new Scanner(System.in);
        String eProduct;
        int eSelection = 0;
        int confirmation1 = 0;
        int confirmation2 = 0;
        int pdtIndex = 0;
        boolean check1 = false;
        boolean check2 = false;
        boolean check3 = true;
        
        System.out.println("Edit Product...\n");
        
        System.out.print("Enter the product ID you would like to edit : \t");
        eProduct = scan.nextLine();
        eProduct = eProduct.toUpperCase();
        
        for( int i = 0; i < editPdt.length; i++ ){

            if(editPdt[i] != null){
                if(editPdt[i].getProductID().equals(eProduct)){
                    pdtIndex = i;
                    check1 = true;
                    break;
                }
            }
        }
            
            if( check1 == true ){
                do{
                    confirmation1 = 0;
                    try{
                            System.out.print("Are you sure you want to edit [ " + editPdt[pdtIndex].getPdtName() + " ] ? ( 1. Yes / 2. No ) : " );
                            confirmation1 = scan.nextInt();
                            }catch(InputMismatchException ex){
                                scan.nextLine();
                            }
                            if(confirmation1 != 1 && confirmation1 !=2 ){
                                System.out.println("Please Enter 1 or 2 only !! \n");
                            }
                            else if( confirmation1 == 1 ){
                                                            
                                do{
                                        eSelection = 0;
                                        
                                        System.out.println("\n+=======================+");
                                        System.out.println("|  \tEdit Menu\t|");
                                        System.out.println("+=======================+");
                                        System.out.println("| 1. Name \t\t|");
                                        System.out.println("| 2. Price \t\t|");
                                        System.out.println("| 3. Delete Product \t|");
                                        System.out.println("| 4. Exit \t\t|");
                                        System.out.println("+=======================+");
                                        System.out.print("Select information you would like to edit : \t");
                                             
                                    try{
                                            eSelection = scan.nextInt();
                                        }catch(InputMismatchException ex){
                                            
                                            scan.nextLine();
                                        }
                                        
                                        switch(eSelection){
                                            case 1:
                                            scan.nextLine();
                                            System.out.print("Enter New Product Name: \t");
                                            String newPdtName = scan.nextLine();
                                            newPdtName = newPdtName.toUpperCase();

                                            for(int j = 0; j < editPdt.length; j++ ){
                                                if(editPdt[j] == null){
                                                    editPdt[pdtIndex].setPdtName(newPdtName);
                                                    System.out.println("Product Successfully Updated !!. \n");
                                                    break;
                                                }
                                                else if(newPdtName.equals(editPdt[j].getPdtName())){
                                                        System.out.println("This Product Name already exist !! Please Try Again. \n");
                                                        break;
                                                }
                                            }
                                            break;
                                            
                                            case 2:
     
                                            do{
                                                try{
                                                    System.out.print("Enter New Price: \t");
                                                    double newPrice = scan.nextDouble();
                                                    editPdt[pdtIndex].setPrice(newPrice);
                                                    System.out.print("Profile successfully updated!!\n");
                                                    check2 = true;
                                                }catch(InputMismatchException ex){
                                                    System.out.println("Invalid Input !! Please enter numeric value only.\n");
                                                    scan.nextLine();
                                                }
                                            }while(!check2);                                   
                                            break;
                                            
                                 case 3:
                                     do{
                                         confirmation2 = 0;
                                         try{
                                             System.out.print("Do you sure you want to remove [ " + editPdt[pdtIndex].getPdtName() +" ] ?? ( 1. Yes / 2. No ) : ");
                                             confirmation2 = scan.nextInt();
                                         }catch(InputMismatchException ex){
                                             scan.nextLine();
                                         }
                                         if(confirmation2 == 1){
                                             editPdt[pdtIndex] = null;
                                             System.out.println("Product Successfully deleted..\n");
                                             eSelection = 4;
                                             break;
                                         }
                                         else if(confirmation2 == 2){
                                             System.out.println(" 2(No) Entered ! You will be sending back to Edit Menu. ");
                                             break;
                                         }
                                         else{
                                             System.out.println("Invalid Input, Please enter [ 1 ] for Yes and [ 2 ] for No only.\n");
                                         }
                                     }while(confirmation2 != 1 || confirmation2 != 2);
                                         break;
                                 case 4:
                                     System.out.print("Exit...\n\n");
                                     break;
                                 default:
                                     System.out.print("Invalid Option !! Please Enter 1 to 4 only .\n");
                                     break;
                                        }
                                } while (eSelection != 4);
                                break;                              
                            }
                            else if ( confirmation1 == 2 ){
                                System.out.println(" 2(No) Entered ! You will be sending back to Main Menu. ");
                                break;
                           }
                }while( confirmation1 != 1 || confirmation1 != 2 );
            }
            else{
                System.out.println("Product ID Not Found !! .\n");
            }
        }
    
    public static void searchStaff(Staff[] sStaff) {
        
        Scanner scan = new Scanner(System.in);
        int searchOption = 0;
        boolean searchCheck;
               
        do{
            searchCheck = false;
            searchOption = 0;
            try{
            System.out.println("\n+=======================+");
            System.out.println("|  Search Menu\t\t|");
            System.out.println("+=======================+");
            System.out.println("| 1. Staff ID \t\t|");
            System.out.println("| 2. Staff Name \t|");
            System.out.println("| 3. Exit \t\t|");
            System.out.println("+=======================+");
            System.out.print("Search by : ");
            searchOption = scan.nextInt();
            scan.nextLine();
            
            }catch(InputMismatchException ex){
                scan.nextLine();
            }
            
                switch(searchOption){
                    case 1 :
                        System.out.print("Enter Staff ID you looking for : \t");
                        String searchID = scan.nextLine();
                        
                        for( int i = 0; i < sStaff.length; i++ ){
                            if( sStaff[i] != null ){
                                if (sStaff[i].getStaffID().equals(searchID)){
                            System.out.println(sStaff[i].toString());
                            System.out.println("\nStaff Found . Back to Search Menu...\n");
                            searchCheck = true;
                            break;
                                }
                            }
                        }
                        
                        if(searchCheck == false){
                            System.out.println("Staff not found!!");
                        }
                        
                        break;
                    case 2 :
                        System.out.print("Enter the name of staff you looking for : \t");
                        String searchName = scan.nextLine();
                        searchName = searchName.toUpperCase();
                        
                        for( int j = 0; j < sStaff.length; j++ ){
                            if( sStaff[j] != null ){
                                if (sStaff[j].getName().equals(searchName)){
                                    System.out.println(sStaff[j].toString());
                                    System.out.println("\nStaff Found . Back to Main Menu...\n");
                                    searchCheck = true;
                                    break;
                                }
                            }
                        }
                        if(searchCheck==false){
                            System.out.println("Staff not found!!\n");
                        }
                        
                        break;
                    case 3 :
                        System.out.print("Exit to Main Menu...\n\n");
                        break;
                    default :
                        System.out.print("Invalid Option !! Please Enter 1 to 3 .\n");
                        break;
                }
        }while(searchOption!=3);
    }
    
    public static void searchBill( Order[] searchBill, Cart[] searchCart ){
        
        Scanner scan = new Scanner(System.in);
        int searchBillOption = 0;
        
        do{
            searchBillOption = 0;
            try{
            System.out.println("\n+===============================+");
            System.out.println("|  Search Menu\t\t\t|");
            System.out.println("+===============================+");
            System.out.println("| 1. Bill ID ( Detailed ) \t|");
            System.out.println("| 2. Staff Name \t\t|");
            System.out.println("| 3. Date \t\t\t|");
            System.out.println("| 4. Exit \t\t\t|");
            System.out.println("+===============================+");

            System.out.print("Search by : ");
            searchBillOption = scan.nextInt();
            scan.nextLine();
            
            }catch(InputMismatchException ex){
                scan.nextLine();
            }
            
                switch(searchBillOption){
                    case 1 :
                        System.out.print("Enter the Bill ID you looking for : \t");
                        String searchID = scan.nextLine();
                        int totalCart = 0;
                        for( int i = 0; i < searchBill.length; i++ ){
                            if( searchBill[i] == null ){
                                System.out.println("Bill not found !!");
                                break;
                            }
                            else if (searchBill[i].getBillNum().equals(searchID)){
                            System.out.println("\n+=======================================================================================================================================================================================================+");
                            System.out.println("| \tBill ID\t\t|\t Staff \t\t|\tAmount Due\t\t|\tTax Charge\t\t|\t Payment \t\t|\t Change \t\t|\t Date ( Y/M/D ) |");
                            System.out.println("+=======================================================================================================================================================================================================+");
                            System.out.println(searchBill[i].toString());
                            System.out.println("+=======================================================================================================================================================================================================+");
                            

                            System.out.println("\nOrder Detail of this bill...");

                                for( int j = 0; j < searchCart.length; j++ ){
                                    if(searchCart[j]==null){
                                        
                                    }
                                    else if( searchCart[j].getCartID().equals(searchID) ){
                                        
                                        if(totalCart == 0){
                                            System.out.println("+=======================================================================================================================================+");
                                            System.out.println("|\tNo\t|\tProduct ID\t|\t Name \t\t\t|\t Quantity \t|\t Price \t|\t Sub Total \t|");
                                            System.out.println("+=======================================================================================================================================+");
                                        }
                                        System.out.println(searchCart[j].toString());
                                        System.out.println("+=======================================================================================================================================+");
                                        totalCart++;
                                    }
                                }
                                break;
                            }
                        }
                        
                        break;
                    case 2 :
                        System.out.print("Enter the name of staff you looking for : \t");
                        String searchName = scan.nextLine();
                        searchName = searchName.toUpperCase();
                        int staffFound = 0;
                        
                        for( int j = 0; j < searchBill.length; j++ ){
                            if( searchBill[j] == null ){
                                break;
                            }
                            else if (searchBill[j].getStaffInCharge().equals(searchName)){
                            System.out.println("+=======================================================================================================================================================================================================+");
                            System.out.println("| \tBill ID\t\t|\t Staff \t\t|\tAmount Due\t\t|\tTax Charge\t\t|\t Payment \t\t|\t Change \t\t|\t Date ( Y/M/D ) |");
                            System.out.println("+=======================================================================================================================================================================================================+");
                            System.out.println(searchBill[j].toString());
                            System.out.println("+=======================================================================================================================================================================================================+\n");
                            staffFound++;
                            }
                        }
                        System.out.printf("\nTotal %d bill found. \n\n",staffFound);
                        
                        break;
                    case 3 :
                        System.out.print("Enter the Date you looking for ( YY-MM-DD ) : \t");
                        String searchDate = scan.nextLine();
                        searchDate = searchDate.toUpperCase();
                        int dateFound = 0;
                        
                        for( int j = 0; j < searchBill.length; j++ ){
                            if( searchBill[j] == null ){
                                
                                break;
                            }
                            else if (searchBill[j].getDate().equals(searchDate)){
                                System.out.println("+=======================================================================================================================================================================================================+");
                                System.out.println("| \tBill ID\t\t|\t Staff \t\t|\tAmount Due\t\t|\tTax Charge\t\t|\t Payment \t\t|\t Change \t\t|\t Date ( Y/M/D ) |");
                                System.out.println("+=======================================================================================================================================================================================================+");
                                System.out.println(searchBill[j].toString());
                                System.out.println("+=======================================================================================================================================================================================================+\n");
                                dateFound++;
                            }
                        }
                        System.out.printf("\nTotal %d bill found.\n\n",dateFound);
                        break;
                    case 4 :
                        System.out.print("Exit to Main Menu...\n\n");
                        break;
                    default :
                        System.out.print("Invalid Option !! Please Enter 1 to 4 .\n");
                        break;
                }
        }while(searchBillOption!=4);
    }
    
}
