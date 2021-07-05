package com.componentwise.eval;
import java.util.Date;

public class FulltimeEmployee extends Employee {

	public FulltimeEmployee(Date dateHired, String name, int id) {
		super(dateHired, name, id);
	}

	@Override
	boolean isManager() {
		return false;
	}

	@Override
	boolean isPartTime() {
		return false;
	}
}
