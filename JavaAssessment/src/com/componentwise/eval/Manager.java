package com.componentwise.eval;

import java.util.Date;

public class Manager extends Employee {


	public Manager(Date dateHired, String name, int id) {
		super(dateHired, name, id);
	}

	@Override
	boolean isManager() {
		return true;
	}

	@Override
	boolean isPartTime() {
		return false;
	}
}
