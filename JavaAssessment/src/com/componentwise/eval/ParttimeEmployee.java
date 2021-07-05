package com.componentwise.eval;

import java.util.Date;

public class ParttimeEmployee extends Employee {


	public ParttimeEmployee(Date dateHired, String name, int id) {
		super(dateHired, name, id);
	}

	@Override
	boolean isManager() {
		return false;
	}

	@Override
	boolean isPartTime() {
		return true;
	}
}
