package com.componentwise.eval;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Main{
    public static void main(String[] args) {
    	//testParser();
    	//testUserKey();
    	//testEmployee();
    	Result result = JUnitCore.runClasses(AllTests.class);
    	for(Failure failure : result.getFailures()) {
    		System.out.println(failure.toString());
    	}
		System.out.println(result.wasSuccessful());

    }
 
	public static void testEmployee() {
    	Date date = new GregorianCalendar(2021, Calendar.JULY, 3).getTime();
    	Employee EmployeeA = new FulltimeEmployee(date, "Sarah", 1);
    	Employee EmployeeB = new ParttimeEmployee(date, "Dave", 2);
    	Employee EmployeeC = new Manager(date, "Jeff", 3);
    	
    	System.out.println("is Jeff the manager : " + EmployeeC.isManager());
    	System.out.println("is Dave part time : " + EmployeeB.isPartTime());
    	System.out.println("is Sarah not part time : " + EmployeeA.isPartTime());
    	System.out.println("What is Sarah's name : " + EmployeeA.getName());
    	
    			
    	
    }
    public static void testUserKey() {
    	UserKey b1 = new UserKey("Bill Smith", "BSMITH");
    	UserKey b2 = new UserKey("Bill Smith", "BSMITH");
    	UserKey b3 = new UserKey("Susan Smith", "SSMITH");
    	UserKey b4 = new UserKey(null,null);
    	System.out.println( b1.equals(b1) );  // prints true
    	System.out.println( b1.equals(b2) );  // prints true
    	System.out.println( b1.equals(b3) );  // prints false
    	System.out.println( b1.equals(null) ); // prints false
    	System.out.println( b1.equals("Some String") ); // prints false
    	System.out.println( b4.equals(b1) ); // prints false

    	java.util.Hashtable ht = new java.util.Hashtable();

    	ht.put(b1,"Some Data");

    	String s = (String) ht.get(b1);
    	System.out.println(s);  // prints true

    	System.out.println( s.equals("Some Data") );  // prints true
	
    }
    public static void testParser() {
    	//put your local system's path here to test parser
		String path = "C:\\Users\\nickb\\Downloads\\JavaAssessment\\src\\test.txt";

        try {
            String content= new String(Files.readAllBytes(Paths.get(path)));
            Parser xml= new Parser();
            TokenConverter lexer = new TokenConverter(xml);
            lexer.analyze(content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
