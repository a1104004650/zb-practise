package com.zbc.practise.pattern.decorator;

public class Water extends BaseDrink{

	@Override
	public void createDrink() {
		System.out.println("createDrink for Water start");
		System.out.println("createDrink for Water end");
	}

	@Override
	public int sell() {
		return 3;
	}

}
