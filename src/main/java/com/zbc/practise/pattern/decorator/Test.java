package com.zbc.practise.pattern.decorator;

public class Test {

	// 装饰者模式
	public static void main(String[] args) {
		BaseDrink water = new Water();
		SugarDecorator sugarWater = new SugarDecorator(water);
		sugarWater.createDrink();
		System.out.println(sugarWater.sell());
	}
	
}
