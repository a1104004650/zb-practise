package com.zbc.practise;


import com.zbc.practise.spring.bean.Person;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author StormT1King
 */
public class Test {
    // String[] args 参数接收的是在运行过程中命令行中传入的参数
    public static void main(String[] args) {
        for(int i=0;i<args.length;i++){
            System.out.println(args[i]);
        }

        List list = new ArrayList();
        list.add("");
        list.get(0);
        Map map = new HashMap();
        map.put("","");
        map.get("");

        // 使用jol库 打印JAVA在内存的存储情况
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        // 上锁之后ObjectHeader变化。markworld中存储了三大信息。 锁，hashcode，GC信息
        o.hashCode();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

    }
}
