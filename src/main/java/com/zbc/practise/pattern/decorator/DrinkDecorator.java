package com.zbc.practise.pattern.decorator;

public abstract class DrinkDecorator extends BaseDrink{

	protected BaseDrink baseDrink;

	/**
	 * @param baseDrink
	 */
	public DrinkDecorator(BaseDrink baseDrink) {
		super();
		this.baseDrink = baseDrink;
	}
	
	@Override
	public int sell() {
		return baseDrink.sell();
	}
	
	@Override
	public void createDrink() {
		baseDrink.createDrink();
	}
	
}
