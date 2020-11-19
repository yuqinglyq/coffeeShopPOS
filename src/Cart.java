/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LIN YU QING
 */
public class Cart {
    
    private String cartID;
    private String productID;
    private String pdtName;
    private double pdtPrice;
    private double subTotal;
    private int quantity;
    private int cartNum;
    
    public Cart(){
    }
    
    public Cart ( int cartNum, String productID, String pdtName, double pdtPrice, int quantity, double subTotal, String cartID) {
        
        this.cartID = cartID;
        this.cartNum = cartNum;
        this.productID = productID;
        this.pdtName = pdtName;
        this.pdtPrice = pdtPrice;
        this.subTotal = subTotal;
        this.quantity = quantity;
        
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public int getCartNum() {
        return cartNum;
    }

    public void setCartNum(int cartNum) {
        this.cartNum = cartNum;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getPdtName() {
        return pdtName;
    }

    public void setPdtName(String pdtName) {
        this.pdtName = pdtName;
    }

    public double getPdtPrice() {
        return pdtPrice;
    }

    public void setPdtPrice(double pdtPrice) {
        this.pdtPrice = pdtPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    
    public static double calcSubTotal ( double pdtPrice,int quantity ){
        return pdtPrice * quantity;
    }

    @Override
    public String toString() {
        return "|\t" + cartNum + "\t|\t" + productID + "\t\t|\t" + pdtName + "\t\t|\t" + quantity + "\t\t|\t" + pdtPrice + "\t|\t" + subTotal + "\t\t|" ;
    }
}
