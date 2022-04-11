package com.zbc.practise.thread;

/**
 * 主线程等待子线程执行完再执行
 * @author StormT1King
 */
public class RunableTest {

    public static void main(String[] args) {

        Thread th1=new Thread(new RunableImp("A"));
        Thread th2=new Thread(new RunableImp("B"));
        th1.start();
        th2.start();
        try{
            th1.join();
            th2.join();

        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("主线程结束");

        // 保障顺序
        //初始化线程t1,由于后续有匿名内部类调用这个对象,需要用final修饰
        final Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("t1 is running");
            }
        });
        //初始化线程t2,由于后续有匿名内部类调用这个对象,需要用final修饰
        final Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    //t1调用join方法,t2会等待t1运行完之后才会开始执行后续代码
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("t2 is running");
                }
            }
        });
        //初始化线程t3
        Thread t3 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    //t2调用join方法,t3会等待t2运行完之后才会开始执行后续代码
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("t3 is running");
                }
            }
        });
        //依次启动3个线程
        t1.start();
        t2.start();
        t3.start();
    }

    static class RunableImp implements Runnable
    {
        private String name;

        public RunableImp(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println(this.name+"开始执行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.name+"执行结束");
        }
    }
}
