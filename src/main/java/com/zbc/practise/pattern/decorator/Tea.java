package com.zbc.practise.pattern.decorator;

/**
 * @author StormT1King
 */
public class Tea extends BaseDrink{

	@Override
	public void createDrink() {
		System.out.println("createDrink for Tea start");
		System.out.println("createDrink for Tea end");
	}

	@Override
	public int sell() {
		return 5;
	}

}
