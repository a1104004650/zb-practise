package com.zbc.practise.pattern.chain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author StormT1King
 * 责任链模式
 * 设计模式原则是让简单的问题复杂化，需要调整的位置抽象出来
 */
public class ChainDemo {

    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setMsg("1 大家好<script> http欢迎来到 *** ");
        System.out.println(msg);

        String msg1 = msg.getMsg();
        msg1 = msg1.replace("<" , "%259%");
        msg1 = msg1.replace(">" , "%261%");
        msg.setMsg(msg1);
        System.out.println(msg);

        // 使用过滤器解决问题，但是如果多几个Fitler还是需要改造代码
        msg.setMsg("2 大家好<script> http欢迎来到 *** ");
        System.out.println(msg);
        new HtmlFilter().doFilter(msg);
        new SensitiveFilter().doFilter(msg);
        System.out.println(msg);

        // 集合在一起
        msg.setMsg("3 大家好<script> http欢迎来到 *** ");
        System.out.println(msg);
        List<Filter> fitlers = new ArrayList<>();
        fitlers.add(new HtmlFilter());
        fitlers.add(new SensitiveFilter());
        for (Filter f : fitlers) {
            f.doFilter(msg);
        }
        System.out.println(msg);

        // filter链条
        msg.setMsg("4 大家好<script> http欢迎来到 *** ");
        System.out.println(msg);
        FilterChain fc = new FilterChain();
        fc.add(new HtmlFilter())
                .add(new SensitiveFilter());

        FilterChain fc2 = new FilterChain();
        fc2.add(new SpaceFilter())
                .add(new UrlFilter());
        // 两个链条合并
        fc.add(fc2);
        fc.doFilter(msg);
        System.out.println(msg);

    }

}

@Getter
@Setter
@ToString
class Msg{
    String name;
    String msg;
}


interface Filter {
    boolean doFilter(Msg m);
}

class HtmlFilter implements Filter {

    @Override
    public boolean doFilter(Msg m) {
        String msg1 = m.getMsg();
        msg1 = msg1.replace("<" , "%259%");
        msg1 = msg1.replace(">" , "%261%");
        m.setMsg(msg1);
        return true;
    }

}

class SensitiveFilter implements Filter {

    @Override
    public boolean doFilter(Msg m) {
        String msg1 = m.getMsg();
        msg1 = msg1.replace("***" , "呵呵呵");
        m.setMsg(msg1);
        return false;
    }

}

class SpaceFilter implements Filter {

    @Override
    public boolean doFilter(Msg m) {
        String msg1 = m.getMsg();
        msg1 = msg1.replace(" " , "");
        m.setMsg(msg1);
        return true;
    }

}

class UrlFilter implements Filter {

    @Override
    public boolean doFilter(Msg m) {
        String msg1 = m.getMsg();
        msg1 = msg1.replace("http" , "https");
        m.setMsg(msg1);
        return true;
    }

}

class FilterChain implements Filter{
    List<Filter> fitlers = new ArrayList<>();

    public FilterChain add(Filter f) {
        fitlers.add(f);
        return this;
    }

    public void remove(Filter f) {
        fitlers.remove(f);
    }

    @Override
    public boolean doFilter(Msg msg) {
        for (Filter f : fitlers) {
            // 任何一个责任链失败不往下执行
            if(!f.doFilter(msg)) {
                return false;
            }
        }
        return true;
    }
}