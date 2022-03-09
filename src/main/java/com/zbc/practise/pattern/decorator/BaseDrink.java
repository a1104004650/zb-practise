package com.zbc.practise.pattern.decorator;

/**
 * 
 * 被装饰的抽象类，基础饮料。
 * 
 * 被装饰类有多种时，提出抽象类，用于实现多态。
 * 
 */
public abstract class BaseDrink {
	
	public abstract void createDrink();
	public abstract int sell();

}
