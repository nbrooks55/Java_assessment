package com.componentwise.eval;
import java.util.Date;

/** <h3> Abstract Employee Class </h3>
 * <p> Structured this class as abstract and divided into classes extending 
 * this class to remove variables and make it more readable and more
 * easily to be expanded on </p>
 * Date: June 2nd 2021
 * @author Nicholas Brooks
 * @version 1.7
*/
abstract class Employee {

	private Date dateHired;
	private String name;
	private int id;

	/**
	 * <p>This is a constructor of Employee that can be used across 
	 * multiple employee classes that extend it </p>
	 * @param dateHired the date employee was hired
	 * @param name the name of the employee
	 * @param id the id of the employee 
	 */
	public Employee(Date dateHired, String name, int id) {
		this.dateHired = dateHired;
		this.name = name;
		this.id = id;
	}
	/**
	 * @return the name of the employee
	 */
	public String getName() {
		return name;
	};
	/**
	 * @return the id of the employee that is a string
	 */
	public String getID() {
		return Integer.toString(id);
	}
	/**
	 * @return the date the employee was hired 
	 */
	public Date getDateHired() {
		return dateHired;
	}
	/**
	 * This function does not use a variable but instead is overridden in classes extending employee  
	 * @return returns true if employee is Manager
	 */
	abstract boolean isManager(); 
	/**
	 * This function does not use a variable but instead is overridden in classes extending employee  
	 * @return returns true if employee is Part-time 
	 * 
	 */
	abstract boolean isPartTime(); 
}
