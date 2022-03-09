package com.zbc.practise.pattern.chain.servlet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author StormT1King
 * 责任链模式
 * 模拟Servlet 请求的时候使用第一个Filter后面使用第二个Filter
 * response时先用第二个Filter在用第一个Filter
 */
public class ServletDemo {

    public static void main(String[] args) {
        Request req = new Request();
        req.setStr("this is request");
        Response rsp = new Response();
        rsp.setStr("this is response");

        FilterChain chain = new FilterChain();
        chain.add(new HtmlFilter()).add(new SensitiveFilter());
        chain.doFilter(req, rsp, chain);
        System.out.println(req.getStr());
        System.out.println(rsp.getStr());


    }

}


@Getter
@Setter
@ToString
class Request {
    String str;
}

@Getter
@Setter
@ToString
class Response {
    String str;
}


interface Filter {
    void doFilter(Request req, Response rsp, FilterChain chain);
}

class HtmlFilter implements Filter {

    @Override
    public void doFilter(Request req, Response rsp, FilterChain chain) {
        // req处理 todo
        System.out.println("HtmlFilter req处理");
        chain.doFilter(req, rsp, chain);
        // resp处理 todo
        System.out.println("HtmlFilter resp处理");
    }

}

class SensitiveFilter implements Filter {

    @Override
    public void doFilter(Request req, Response rsp, FilterChain chain) {
        // req处理 todo
        System.out.println("SensitiveFilter req处理");
        chain.doFilter(req, rsp, chain);
        // resp处理 todo
        System.out.println("SensitiveFilter resp处理");
    }

}


class FilterChain implements Filter{
    List<Filter> fitlers = new ArrayList<>();
    int index = 0;

    public FilterChain add(Filter f) {
        fitlers.add(f);
        return this;
    }

    public void remove(Filter f) {
        fitlers.remove(f);
    }

    @Override
    public void doFilter(Request req, Response rsp, FilterChain chain) {
        if(index == fitlers.size()) {
            return;
        }
        Filter f = fitlers.get(index);
        index++;
        f.doFilter(req, rsp, chain);
    }
}