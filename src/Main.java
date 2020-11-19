/**
 *
 * @author Lin Yu Qing
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        final int TRIES = 3;
        int indexM;
        int indexS;
        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        
        Product[] product = new Product[20];
        product[0] = new Product("Beverages","C001", "ESPRESSO", 7.50);
        product[1] = new Product("Beverages","C002", "CAFFE AMERICANO", 7.30);
        product[2] = new Product("Beverages","C003", "CAFFE LATTE", 6.70);
        product[3] = new Product("Beverages","C004", "CAFFE MOCHA", 5.50);
        product[4] = new Product("Beverages","C005", "BREWED COFFEE", 12.50);
        product[5] = new Product("Beverages","C006", "MATCHA LATTE", 9.80);
        product[6] = new Product("Beverages","C007", "HOT CHOCOLATE", 8.20);
        product[7] = new Product("Beverages","C008", "MACCHIATO", 13.20);
        product[8] = new Product("Dessert","D010", "CHOCOLATE CAKE", 14.00);
        product[9] = new Product("Dessert","D020", "CHEESE CAKE", 17.00);
        product[10] = new Product("Dessert","D030", "CARROT CAKE", 24.00);
        
        Manager[] manager = new Manager[100];
        manager[0] = new Manager("JACK KI", 'M' ,19, "01258742558" , "king@gmail.com", "Setapak", "K100","PASS");
        manager[1] = new Manager("JOHN KO", 'F',20, "09876543211", "kong@gmail.com", "Setapak", "K101", "PASS1");
        
        Staff[] staff = new Staff[100];
        staff[0] = new Staff("KAY KEE", 'M' , 20 , "012121251548" , "kay123@gmail.com", "Setapak", "S001", "100PASS");
        staff[1] = new Staff("LIM MU", 'M' , 20 , "012121257845" , "lim788@gmail.com", "Setapak", "S002", "101PASS");
        
        Cart[] cart = new Cart[500];
        Order[] bill = new Order[100];
        bill[0] = new Order("BE12050402","KAY KEE", 21.9, 1.53, 24.0, 0.57,"20-08-12");
        bill[1] = new Order("BE31030601","LIM MU", 118.8, 8.32, 150, 22.88,"20-08-31");
        bill[2] = new Order("BE11231620","LIM MU", 219.5, 15.37, 250, 15.13,"20-08-11");
        
        do{
                   int position = 0;
                   position = LoginMenu();

                    switch (position) {
                        case 1: 
                            int passError = 0;
                            int managerChoice = 0;
                            do{
                                    indexM = Manager.managerLogin(manager);
                                    
                                   if( indexM == -1 ){
                                       passError++;
                                            if(passError == 3){
                                            System.out.printf("You have entered %d times error, sending back to main menu...\n\n",+passError);
                                            break;
                                            }
                                            else{
                                            System.out.printf("You have entered %d error(s). You will be sending back to main menu after %d errors have reached !!\n\n", +passError, +TRIES);
                                            }
                                   }
                                   else{
                                        do{
                                            
                                            managerChoice = 0;
                                            
                                             try{
                                                Manager.managerMenu();
                                                System.out.print("What do you want to do ?.( Enter 1 to 10 only.) : ");
                                                managerChoice = scan.nextInt();
                                             }catch(InputMismatchException ex){
                                                 scan.nextLine();
                                             }
                                                switch (managerChoice) {
                                                    case 1: 
                                                        Manager.addStaff(manager,staff);
                                                        break;
                                                    case 2: 
                                                        Manager.editStaff(staff,manager);
                                                        break;
                                                    case 3: 
                                                        Manager.searchStaff(staff);
                                                        break;
                                                    case 4:
                                                        Manager.displayStaff(staff);
                                                        break;
                                                    case 5:
                                                        Manager.addProduct(product);
                                                        break;
                                                    case 6: 
                                                        Manager.editProduct(product);
                                                        break;
                                                    case 7:
                                                        Product.displayProduct(product);
                                                        break;
                                                    case 8: 
                                                        Order.displayBill(bill);
                                                        break;
                                                    case 9:
                                                        Manager.searchBill(bill, cart);
                                                        break;
                                                    case 10:
                                                        String staffName = manager[indexM].getName();
                                                        Order.orderProcess(cart, bill, product, staffName);
                                                        break;
                                                    case 11:
                                                        int totalBill = 0;
                                                        double grandTotal = 0;
                                                        double subTotal;
                                                        
                                                        System.out.println("Exit and display Daily Sales Report...\n\n");
                                                        System.out.println("\t\t\t\t\t\t\t\tDaily Sales Report\t\t\t\t");
                                                        System.out.println("+=======================================================================================================================================================================================================+");
                                                        System.out.println("| \tBill ID\t\t|\t Staff \t\t|\tAmount Due\t\t|\tTax Charge\t\t|\t Payment \t\t|\t Change \t\t|\t Date ( Y/M/D ) |");
                                                        System.out.println("+=======================================================================================================================================================================================================+");

                                                        String dateToday = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YY-MM-dd"));
                                                        
                                                        for(int i = 0; i <bill.length ; i++){
                                                            if(bill[i]==null){
                                                                break;
                                                            }
                                                            else if(bill[i].getDate().equals(dateToday)){
                                                                System.out.println(bill[i].toString());
                                                                subTotal = bill[i].getGrandTotal() + bill[i].getTaxCharge();
                                                                grandTotal = grandTotal + subTotal;
                                                                totalBill++;
                                                            }
                                                        }
                                                        System.out.println("+=======================================================================================================================================================================================================+");
                                                        System.out.printf("\nNumber of Sales made by today : %d \n",totalBill);
                                                        System.out.printf("Total Amount of Sales for today : %.2f\n\n",grandTotal);
                                                        
                                                        break;
                                                    default : 
                                                        System.out.println("INVALID OPTION !! Please Try Again.");
                                                        break;
                                                }
                                            }while( managerChoice != 11 );
                                        break;
                                   }
                            }while( passError <= TRIES );
                            break;
                        case 2: 
                            
                            int staffPassError = 0;
                            int staffChoice;
                            do{
                                    indexS = Staff.staffLogin(staff);
                                    
                                   if( indexS == -1 ){
                                       staffPassError++;
                                            if(staffPassError == 3){
                                            System.out.printf("You have entered %d times error, sending back to main menu...\n\n",+staffPassError);
                                            break;
                                            }
                                            else{
                                            System.out.printf("You have entered %d error(s). You will be sending back to main menu after %d errors have reached !!\n\n", +staffPassError, +TRIES);
                                            }
                                   }
                                   else{
                                        do{
                                            staffChoice = 0;
                                             try{
                                                Staff.staffMenu();
                                                System.out.print("What do you want to do ?.( Enter 1 to 3 only.) : ");
                                                staffChoice = scan.nextInt();
                                             }catch(InputMismatchException ex){
                                                 scan.nextLine();
                                             }
                                             
                                             switch(staffChoice){
                                                 case 1:
                                                     Product.displayProduct(product);
                                                     break;
                                                 case 2:
                                                     String staffName = staff[indexS].getName();
                                                     Order.orderProcess(cart, bill, product, staffName);
                                                     break;
                                                 case 3:
                                                     int totalBill = 0;
                                                     double grandTotal = 0;
                                                     double subTotal;
                                                     
                                                     System.out.println("Exit and display Daily Sales Report...\n\n");
                                                     System.out.println("\t\t\t\t\t\t\t\tDaily Sales Report\t\t\t\t");
                                                     System.out.println("+=======================================================================================================================================================================================================+");
                                                     System.out.println("| \tBill ID\t\t|\t Staff \t\t|\tAmount Due\t\t|\tTax Charge\t\t|\t Payment \t\t|\t Change \t\t|\t Date ( Y/M/D ) |");
                                                     System.out.println("+=======================================================================================================================================================================================================+");
                                                     
                                                     String dateToday = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YY-MM-dd"));
                                                     
                                                     for(int i = 0; i <bill.length ; i++){
                                                         if(bill[i]==null){
                                                             break;
                                                         }
                                                         else if(bill[i].getDate().equals(dateToday)){
                                                             System.out.println(bill[i].toString());
                                                             subTotal = bill[i].getGrandTotal() + bill[i].getTaxCharge();
                                                             grandTotal = grandTotal + subTotal;
                                                             totalBill++;
                                                         }
                                                     }
                                                     System.out.println("+=======================================================================================================================================================================================================+");
                                                     System.out.printf("\nNumber of Sales made by today : %d \n",totalBill);
                                                     System.out.printf("Total Amount of Sales for today : %.2f\n\n",grandTotal);
                                                     break;
                                                 default:
                                                     System.out.println("INVALID OPTION !! Please Enter 1 to 3 only.");
                                                     break;
                                             }
                                             
                                            }while( staffChoice != 3 );
                                        break;
                                   }
                            }while( staffPassError <= TRIES );
                            
                            break;
                        default:
                            System.out.println("Please Enter '1' or '2' only.");
                            break;
                    }
        }while(!exit);                                                           
    }
    
    public static int LoginMenu(){
        
        boolean check0 = false;
        boolean check1 = true;
        Scanner scan = new Scanner(System.in);
        int position = 0;
        
        System.out.println("+=======================+");
        System.out.println("|\tLogin Page\t|");
        System.out.println("+=======================+");
        System.out.println("| 1. Manager Login\t|");
        System.out.println("| 2. Staff Login\t|");
        System.out.println("+=======================+");
        
        do{
            try {
                System.out.print("Enter Your Selection ( 1 / 2 ) : ");   
                position = scan.nextInt();
                
                if(position == 1 || position == 2){
                            check0 = true;
                            break;
                }      
                else{
                        System.out.println("Please Enter '1' or '2' only.\n");
                 }
                    
                 } catch (InputMismatchException ex) {
                     System.out.println("Please Enter '1' or '2' only.\n");
                     check1 = false;
                     scan.nextLine();
                 }
        }while(!check0);
        
        return position;
    }
    
}