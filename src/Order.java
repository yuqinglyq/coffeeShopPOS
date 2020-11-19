/**
 *
 * @author LIN YU QING
 */

import static java.lang.Character.toUpperCase;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Order {
    
    private String billNum;
    private String staffInCharge;
    private double grandTotal;
    private double taxCharge;
    private double amountPaid;
    private double change;
    private String date;
    private static final String BILLCODE = "BE";
    private static final double TAX = 0.07;
 
    public Order(){
    }

    public Order(String billNum, String staffInCharge, double grandTotal, double taxCharge, double amountPaid, double change, String date) {
        this.billNum = billNum;
        this.staffInCharge = staffInCharge;
        this.grandTotal = grandTotal;
        this.taxCharge = taxCharge;
        this.amountPaid = amountPaid;
        this.change = change;
        this.date = date;
    }

    public String getBillNum() {
        return billNum;
    }

    public void setBillNum(String billNum) {
        this.billNum = billNum;
    }

    public String getStaffInCharge() {
        return staffInCharge;
    }

    public void setStaffInCharge(String staffInCharge) {
        this.staffInCharge = staffInCharge;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public double getTaxCharge() {
        return taxCharge;
    }

    public void setTaxCharge(double taxCharge) {
        this.taxCharge = taxCharge;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public static String generateBillNum(){
        return BILLCODE + LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddhhmmss"));
    }
    
    public static void orderProcess( Cart[] cart, Order[] bill, Product[] product, String staff ){

        int cartCount = 1;
        int quantity;
        double grandTotal = 0;
        double payment = 0;
        char confirmation1;
        char confirmation2;
        boolean checkExit;
        boolean checkOrder = false;
        boolean confirmOrder = true;
        boolean checkPayment;
        boolean checkRemove;
        boolean checkAmount;
        boolean verifyRemove;
        boolean verifyOrder;
        boolean verifyPayment = false;
        boolean verifyExit;
        Scanner scan = new Scanner(System.in);
        
            Product.displayProduct(product);
            String billNum = generateBillNum();
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YY-MM-dd"));
        
            do{
            verifyOrder = false;
            verifyExit = false;
            System.out.print("What would you like to order ( E - Exit ) ? \nPlease Enter product Code to order. : ");
            String orderEntry = scan.nextLine();
            String order = orderEntry.toUpperCase();
            
            if(order.equals("e") || order.equals("E")){
                do{
                    checkExit = false;
                    for(int q=0; q<cart.length; q++){
                        if(cart[q]!=null){
                            if(cart[q].getCartID().equals(billNum)){

                                System.out.print("\nYour cart have product added, do you want to Cancel your order and Exit directly? ( C - Cancel / N - No ) : ");
                                char exitConfirmation = scan.next().charAt(0);
                                exitConfirmation = toUpperCase(exitConfirmation);

                                if( exitConfirmation == 'N'){
                                    System.out.print("\n");
                                    verifyPayment = true;
                                    verifyExit = true;
                                    checkExit = true;
                                    break;
                                }
                                else if( exitConfirmation == 'C'){
                                    System.out.println("Cancel(C) entered !! Order cancel and exit to Main Menu...\n");
                                    verifyPayment = false;
                                    verifyExit = true;
                                    checkOrder = true;
                                    checkExit = true;
                                    break;
                                }
                                else{
                                    System.out.println("Please Enter ( C ) for cancel order or ( N ) for continue order only..\n");
                                    checkExit = false;
                                    break;
                                }
                            }
                        }
                    }
                    
                if(verifyExit == false){
                    checkOrder = true;
                    verifyPayment = false;
                    System.out.println("Exit(E) entered !! You will be sending back to Main Menu...\n");
                    break;
                }
                    
                }while(checkExit == false);
                        
            }
            else{
                for(int i=0;i< product.length;i++){
                    
                    if(product[i]!=null){
                        if(order.equals(product[i].getProductID())){
                            verifyOrder = true;
                            for(int j=0; j<cart.length; j++){
                                if(cart[j]==null){

                                    String orderName = product[i].getPdtName();
                                    System.out.printf("How many [ "+orderName+ " ] do you want to order ?? ( 0 - to cancel ): ");

                                    try{
                                        quantity = scan.nextInt();
                                    }catch(InputMismatchException ex){
                                        System.out.println("Invalid Quantity entered !! Please Try Again. \n");
                                        scan.nextLine();
                                        break;
                                    }

                                    if(quantity == 0){
                                        System.out.println("(0) entered...Cancel Order...\n");
                                        scan.nextLine();
                                        break;
                                    }
                                    else{
                                    int cartNum = cartCount;
                                    double pdtPrice = product[i].getPrice();
                                    String pdtName = product[i].getPdtName();
                                    double subTotal = Cart.calcSubTotal(pdtPrice, quantity);
                                    subTotal = Math.round(subTotal * 100.0) / 100.0;

                                    cart[j] = new Cart (cartNum, order, pdtName, pdtPrice, quantity, subTotal, billNum); 
                                    cartCount++;
                                    grandTotal = grandTotal + subTotal;

                                    System.out.print("Order added to cart...\n\n");
                                    verifyPayment = true;

                                    }
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
                if(verifyOrder == false){
                    System.out.println("Invalid Product ID !! Please enter product ID to order. ( M - Product Menu )\n");
                    checkOrder = false;
                }
            }
            if(verifyPayment == true){
                OUTER:
                do {
                    checkPayment = true;
                    System.out.println("+=======================================================================================================================================+");
                    System.out.println("|\tNo\t|\tProduct ID\t|\t Name \t\t\t|\t Quantity \t|\t Price \t|\t Sub Total \t|");
                    System.out.println("+=======================================================================================================================================+");
                    for(int k = 0; k <cart.length ; k++){
                        if(cart[k]!=null){
                            if(cart[k].getCartID().equals(billNum))
                                System.out.println(cart[k].toString());
                        }
                    }
                    System.out.println("+=======================================================================================================================================+");
                    System.out.printf("Current Grand Total ( Without tax ) : %.2f . \n", grandTotal );
                    System.out.printf("**Enter [ M ] to remove a product from your cart.\n");
                    System.out.print("\nAnything to order again? ( Y - Yes / N - No ) : ");
                    confirmation1 = scan.next().charAt(0);
                    confirmation1 = toUpperCase(confirmation1);
                    scan.nextLine();
                    switch (confirmation1) {
                        case 'N':

                            do{
                                checkOrder = true;
                                System.out.print("Confirm with your order and proceed to Payment ?( Y - yes / N - no ) : ");
                                confirmation2 = scan.next().charAt(0);
                                confirmation2 = toUpperCase(confirmation2);

                                switch (confirmation2) {
                                    case 'Y':
                                        do{

                                            checkAmount = true;
                                            for ( int m = 0; m < bill.length; m++ ){
                                                if(bill[m]==null){

                                                    System.out.println("\n=========================================");
                                                    System.out.println("| \t\tPayment Menu\t\t|");
                                                    System.out.println("=========================================");
                                                    System.out.println(" Transaction Date :  "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("YY-MM-dd hh:mm")));
                                                    System.out.printf(" Service Tax : %.2f\n", TAX);
                                                    System.out.printf(" Sub Total : %.2f\n", grandTotal);

                                                    double taxCharge = grandTotal * TAX;
                                                    taxCharge = Math.round(taxCharge * 100.0) / 100.0;
                                                    double amountDue = grandTotal + taxCharge;

                                                    System.out.printf(" Tax Charge : %.2f\n", taxCharge);
                                                    System.out.printf(" Grand Total : %.2f\n", amountDue);
                                                    System.out.print(" Payments : ");

                                                    try{
                                                    payment = scan.nextDouble();
                                                    }catch(InputMismatchException ex){
                                                        scan.nextLine();
                                                    }

                                                    if(payment >= amountDue){
                                                        double change = payment - amountDue;
                                                        change = Math.round(change * 100.0) / 100.0;
                                                        grandTotal = Math.round(grandTotal * 100.0) / 100.0;

                                                        System.out.printf(" Amount to return : %.2f\n", change);
                                                        System.out.println(" Order has been processed, Thank you !!. \n");

                                                        bill[m] = new Order(billNum, staff, grandTotal, taxCharge, payment, change, date);
                                                        confirmOrder = true;
                                                        checkOrder = true;
                                                        checkAmount = true;
                                                        checkPayment = true;
                                                        break;
                                                    }
                                                    else{
                                                        System.out.println("Invalid payment amount, please try again.");
                                                        checkAmount = false;
                                                        break;
                                                    }
                                                }
                                            }
                                        }while(checkAmount == false);
                                        break;
                                    case 'N':
                                        confirmOrder = false;
                                        checkOrder = false;
                                        checkPayment = true;
                                        scan.nextLine();
                                        break;
                                    default:
                                        System.out.println("Invalid option !! You may enter alphabet [ Y ] for Yes, [ N ] for No only. \n");
                                        checkPayment = false;
                                        break;
                                }
                            }while(checkPayment == false);
                            break;
                        case 'Y':
                            Product.displayProduct(product);
                            break OUTER;
                        case 'M':

                            do{
                                int remove = -1;
                                verifyRemove = false;
                                checkRemove = true;
                                System.out.print("\nWhich product do you want to remove from cart ??\nPlease enter the Number to remove ( 0 - Cancel Remove ) : ");

                                try{
                                    remove = scan.nextInt();
                                }catch(InputMismatchException ex){
                                    verifyRemove = false;
                                    scan.nextLine();
                                }

                                if(remove == 0){
                                    System.out.println("(0)Entered...Cancel Removing.\n");
                                    scan.nextLine();
                                    verifyRemove = true;
                                    confirmOrder = false;
                                }
                                else if( remove > 0 ){
                                    for(int q=0; q<cart.length;q++){

                                        if(cart[q] != null && remove == cart[q].getCartNum()){
                                            if(cart[q].getCartID().equals(billNum)){

                                            double removeTotal = cart[q].getSubTotal();
                                            grandTotal = grandTotal - removeTotal;
                                            System.out.println("\n\n**[ " + cart[q].getPdtName() + " ] at number [ " + cart[q].getCartNum() +" ] has been removed from your cart.. ");
                                            cart[q] = null;

                                            confirmOrder = false;
                                            verifyRemove = true;
                                            break;
                                            }
                                        }
                                    }
                                    
                                    if(verifyRemove == false){
                                        System.out.println("Product not found !!. \n");
                                        scan.nextLine();
                                    }
                                    
                                }
                                else{
                                    checkRemove = false;
                                }
                            }while(checkRemove == false);

                            break;

                        default:
                            System.out.println(" Invalid option !! You may enter alphabet [ Y ] for Yes, [ N ] for No, and [ M ] for Modify only. ");
                            confirmOrder = false;
                            break;
                    }
                } while (confirmOrder == false);
            }
        }while(checkOrder == false);
    }
    
    public static void displayBill(Order[] displayBill){
        
        int totalBill = 0;
        System.out.println("+=======================================================================================================================================================================================================+");
        System.out.println("| \tBill ID\t\t|\t Staff \t\t|\tAmount Due\t\t|\tTax Charge\t\t|\t Payment \t\t|\t Change \t\t|\t Date ( Y/M/D ) |");
        System.out.println("+=======================================================================================================================================================================================================+");
    	
    	for(int i = 0; i <displayBill.length ; i++){
    		if(displayBill[i] instanceof Order){
    			System.out.println(displayBill[i].toString());
                                                    totalBill++;
    		}
    	}
        System.out.println("+=======================================================================================================================================================================================================+");
        System.out.printf("\nTotal %d bill displayed. Sending back to Main Menu...\n\n",+totalBill);
    }
    
    @Override
    public String toString() {
        return  "|\t" + billNum + "\t|\t" + staffInCharge + "\t\t|\tRM " + grandTotal + " \t\t|\tRM " + taxCharge + " \t\t|\tRM " + amountPaid + " \t\t|\tRM " + change + " \t\t|\t" + date + "\t|";
    }
    
}

