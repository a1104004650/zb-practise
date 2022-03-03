package com.zbc.practise.pattern.proxy;

// 代理模式
public class ProxyTest {
	public static void main(String args[]){
        //定义一个java码农
        ICoder coder = new JavaCoder("Zhang");
        //定义一个产品经理
        ICoder proxy = new CoderProxy(coder);
        //让产品经理实现一个需求
        proxy.copyCode("123");
    }
}


interface ICoder{
    public void copyCode(String demandName);
}

class JavaCoder implements ICoder{
    private String name;

    public JavaCoder(String name){
        this.name = name;
    }

	@Override
	public void copyCode(String demandName) {
		System.out.println(name + " implemented demand:" + demandName + " in JAVA!");
	}

}

class CoderProxy implements ICoder{

    private ICoder coder;

    public CoderProxy(ICoder coder){
        this.coder = coder;
    }

    @Override
    public void copyCode(String demandName) {
        coder.copyCode(demandName);
    }

}