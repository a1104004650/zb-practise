package com.zbc.practise.pattern.decorator;

public class SugarDecorator extends DrinkDecorator{

	public SugarDecorator(BaseDrink baseDrink) {
		super(baseDrink);
	}
	
	@Override
	public int sell() {
		return baseDrink.sell() + 2;
	}
	
	@Override
	public void createDrink() {
		baseDrink.createDrink();
		System.out.println("+ Sugar");
	}
	
	public void addMoreSugar() {
		System.out.println("add more sugar");
	}
}
