package in.co.rays.project_3.util;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.inject.internal.util.Function;


/**
 * DataValidator class is used to validate the data entered by user
 * @author Ankit Rajput
 *
 */
public class DataValidator 
{
	
	public static boolean isName(String name) { 

		String namereg = "^[^-\\s][\\p{L} .']+$";
		

		//String sname = name.trim();

		if (isNotNull(name) && name.matches(namereg)) {

			return true;
			
			
		} else {
			return false;
		}
	}
	
	
	public static boolean isLetter(String name) { 

		String namereg = "^[^-\\s][\\p{L} .']+$";
		

		//String sname = name.trim();

		if (isNotNull(name) && name.matches(namereg)) {

			return true;
			
			
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if value of Password is in between 8 and 12 characters
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isPasswordLength(String val) {

		if (isNotNull(val) && val.length() >= 8 && val.length() <= 12) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/*public static boolean isValidAge(String val)
	{
		
		boolean pass = false;
		if (isDate(val)) {
			Date cdate = new Date();
			try {
				Date userdate = DataUtility.formatter.parse(val);
				int age = cdate.getYear()-userdate.getYear();
				System.out.println("final age  "+age);
				if(age>=18){
					pass=true;
				}
			} catch (ParseException e) {
				
			}
		}
		
		return pass;
	}*/
	
	public static boolean isPassword(String pass) { // my method created
        
		System.out.println("validate pass");
		String passreg = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
		//String passreg="^[0-9a-zA-Z]{5}$";
		//String spass = pass.trim();
		//int checkIndex = spass.indexOf(" ");
                                //checkIndex==-1
		if (isNotNull(pass) && pass.matches(passreg) ) {
			System.out.println("true");
			return true;
		}

		else {
			return false;
		}
	}
	
	 public static boolean isTooLong(String val, int maxLength) {
		    if (isNotNull(val)) {
		        return val.length() > maxLength;
		    } else {
		        return false;
		    }
		}
	
	public static boolean isRollNo(String roll) { // my method created
	
		String rollreg = "[a-zA-Z]{2}[0-9]{3}";
		//String sroll = roll.trim();

		if (DataValidator.isNotNull(roll)) {

			boolean check = roll.matches(rollreg);
			System.out.println(check);
			return check;
		}

		else {

			return false;
		}
	}
	
	/**
	 * Ckeck if value is Null
	 * @param val :val
	 * @return boolean
	 */
 
	public static boolean isNull(String val)
	{
		if(val==null || val.trim().length()==0)
		{
			return true;
		}else
		{
			return false;
		}
	}
	/**
	 * check if value is Not Null
	 * @param val :value
		 * @return boolean
	 */
     
	public static boolean isNotNull(String val)
	{
		return !isNull(val);
	}
	/**
	 * check if an value is an Integer
	 * @param val :value
		 * @return boolean
	 */
	 public static boolean isInteger(String val)
	 {
		 if(isNotNull(val))
		 {
			 try {
				  Integer.parseInt(val);
				 return true;
			} catch (Exception e) {
				return false;
			}
		 }else
		 {
			 return false;
		 }
	 }
	 /**
		 * check if an value is an Long
		 * @param val :value
		 * @return boolean
		 */
	 public static boolean isLong(String val)
	 {     System.out.println("in datavalidator..........."+val);
		 if(isNotNull(val))
		 {   
			 try {
				 System.out.println("method is start....."+val);
			      long l = Long.parseLong(val);
				 System.out.println("-------->in datavalidator"+val+"----"+l);
				 return true;
			} catch (Exception e) {
				return false;
			}
		 }else
		 {
			 return false;
		 }
	 }
		 
	 
	 
		 
		 
		 public static boolean isValidAge(String val)
			{
				
				boolean pass = false;
				if (isDate(val)) {
					Date cdate = new Date();
					try {
						Date userdate = DataUtility.formatter.parse(val);
						int age = cdate.getYear()-userdate.getYear();
						System.out.println("final age  "+age);
						if(age>=18){
							pass=true;
						}
					} catch (ParseException e) {
						
					}
				}
				
				return pass;
			}
		 
		 
		 public static boolean isAge(String val){
			    
		    	Date today = new Date();
		    	Date enterDate = DataUtility.getDate(val);
		    	
		    	int age = today.getYear() - enterDate.getYear();

		    	if(age > 18 && isNotNull(val)){
		    		return true;
		    	}else{
		    		return false;							
		    	}
		    }
		 
		    public static boolean isValidDate(String val) {
		        // Get today's date
		        Date today = new Date();
		        
		        // Convert the entered date string to a Date object (assuming DataUtility.getDate() handles this)
		        Date enterDate = DataUtility.getDate(val);
		        
		        // Check if the entered date is in the future
		        if (enterDate.after(today)) {
		            // If the entered date is after today, return false
		            return false;
		        }
		        
		        // If the entered date is not in the future, return true
		        return true;
		    }
		

	 /**
	  * Check if value is valid EmailId
	  * @param val :val
	  * @return boolean
	  */
	 public static boolean isEmail(String val)
	 {
		 String emailregex= "^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	  
		 if(isNotNull(val))
		 {
			 try {
				 return val.matches(emailregex);
			} catch (Exception e) {
				return false;
			}	 
		 }else
		 {
			 return false;
		 } 
	 }
	 
	 
	 public static boolean isDouble(String val) {
		    String dregex = "^[-+]?\\d+(\\.\\d*)?|\\.\\d+$";

		    if (isNotNull(val)) {
		        return val.matches(dregex);
		    } else {
		        return false;
		    }
		}

	
	 
	 
	
	 
	 public static boolean isAlphabet(String val) {
		    String dregex = "^[a-zA-Z]+$";

		    if (isNotNull(val)) {
		        return val.matches(dregex);
		    } else {
		        return false;
		    }
		}

		



	 
	 
	 public static boolean isValidDouble(String input) {
	        String regex = "^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)$\r\n";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(input);
	        return matcher.matches();
	    }
	 /**
	  * check if value is date
	  * @param val :val
	  * @return boolean
	  */
	   public static boolean isDate(String val)
	   {
		    Date d=null;
//		    String s = val;
//			s = s.replace("-", "/");
		    if(isNotNull(val))
		    {
		    d =	DataUtility.getDate(val);
		    }
		    return d!=null;
	   }
	   
	   /**
		 * Checks if value is valid Phone No.
		 * 
		 * @param val :val
		 * @return boolean
		 */
		public static boolean isPhoneNo(String val) {

			String phonereg = "^[6-9][0-9]{9}$";
//			String phonereg = "^[6-9]{10}$";

			if (isNotNull(val)) {
				try {
					return val.matches(phonereg);
				} catch (NumberFormatException e) {
					return false;
				}

			} else {
				return false;
			}
		}
		
		
		public static boolean isDatee(String val) {
		    String dreg = "\\b(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}\\b";

		    if (isNotNull(val)) {
		        try {
		            return val.matches(dreg);
		        } catch (NumberFormatException e) {
		            return false; // This catch block may not be necessary since we're not parsing numbers.
		        }
		    } else {
		        return false;
		    }
		}

		/**
		 * Checks if value of Mobile No is 10
		 * 
		 * @param val :value
		 * @return boolean
		 */
		public static boolean isPhoneLength(String val) {

			if (isNotNull(val) && val.length() == 10) {
				return true;
			} else {
				return false;
			}
		}
	   
		
	

	   
	   
	 
	 /**
	  * Test Above Methods
	  * @param args :args
	  */
	public static void main(String[] args) 
	{
//	  System.out.println(isNull("ssd"));
//	  System.out.println(isNotNull(""));//dought
//	  System.out.println(isInteger("2147483649"));
//	  System.out.println(isLong("50.5"));
//	  System.out.println(isEmail("rah@g.com"));
//	  System.out.println(isDate("18/11/1989"));
	  System.out.println(isName("Ankur Agrawal"));
	}
	   
}
