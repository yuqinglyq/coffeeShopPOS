
/**
 *
 * @author LIN YU QING
 */

public class Person {
    
	String name;
	private char gender;
	private int age;
	private String phoneNo;
	private String email;
	private String address;
	
    public Person ( String name, char gender, int age, String phoneNo, String email, String address ) {
    	this.name = name;
    	this.gender = gender;
    	this.age = age;
    	this.phoneNo = phoneNo;
    	this.email = email;
    	this.address = address;
	}
	
    public Person() {

	}
    
    public String getName() {
        return name;
    }
    
    public char getGender(){
        return gender;
    }
    
    public int getAge(){
        return age;
    }
    
    public String getPhoneNo(){
        return phoneNo;
   }
    
    public String getEmail(){
        return email;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setGender(char gender){
        this.gender = gender;
    }
    
    public void setAge(int age){
        this.age = age;
    }
    
   public void setPhoneNo(String phoneNo){
       this.phoneNo = phoneNo;
   }
   
   public void setEmail(String email){
       this.email = email;
    }
   	
    public void setAddress(String address){
        this.address = address;
    }
    
     public String toString(){
        return "\nName:	" + name +"\nGender:	 " + gender + "\nAge:	" + age + "\nTel: " + phoneNo + "\nEmail: " + email + "\nAddress:" + address;
    }
  
}