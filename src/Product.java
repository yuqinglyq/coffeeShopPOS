/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LIN YU QING
 */
public class Product {
    
    private String productType;
    private String productID;
    private String pdtName;
    private double price;

    public Product(){
    }

    public Product(String productType, String productID, String pdtName, double price){
            this.productType = productType;
            this.productID = productID;
            this.pdtName = pdtName;
            this.price = price;
    }
    
     public String getProductType(){
            return productType;
    }

    public void setProductType(String productType){
            this.productType = productType;
    }

    public String getProductID(){
            return productID;
    }

    public void setProductID(String productID){
            this.productID = productID;
    }

    public String getPdtName(){
            return pdtName;
    }

    public void setPdtName(String pdtName){
            this.pdtName = pdtName;
    }

    public double getPrice(){
            return price;
    }

    public void setPrice(double price){
            this.price = price;
    }

    public String toString(){
            return "|\t" + productID + " \t|\t" + productType + " \t\t|\t"+ pdtName +"\t\t|\t" + price + "\t| "; 
    }
        
    public static void displayProduct(Product[] product){
        
        int totalPdt = 1;
        System.out.println("\n\t\t\t\tProduct Menu\t");
        System.out.println("==========================================================================================================");
        System.out.println(" No\t| Product Code \t|\t Type \t\t\t|\t Name \t\t\t| Price \t|");
        System.out.println("==========================================================================================================");

        for(int i = 0; i <product.length ; i++){
                if(product[i] != null){
                        System.out.println( totalPdt + "\t" + product[i].toString() );
                        totalPdt++;
            }
        }
        System.out.println("==========================================================================================================");
        System.out.printf("Total %d product listed...\n\n",+totalPdt-1);
    }
    
}
