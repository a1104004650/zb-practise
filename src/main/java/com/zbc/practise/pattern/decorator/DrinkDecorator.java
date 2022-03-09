package com.zbc.practise.pattern.decorator;

/**
 * @author StormT1King
 * 饮料装饰器
 */
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
