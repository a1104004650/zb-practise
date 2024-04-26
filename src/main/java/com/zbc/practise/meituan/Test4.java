package com.zbc.practise.meituan;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.*;

public class Test4 {

        public class ShopInfo {
            // 商户id
            private Integer id;
            // 商户名称
            private String name;
            // 商户地址
            private String address;

        }

        public class Shopservice {
            // 已经提供的方法1：批量根据商户id查询商户名称
            // 限制:入参shopIds不大于10个
            public Map<Integer, String> getShopName(List<Integer> shopIds) {
                return Collections.emptyMap();
            }

            // 已经提供的方法2：批量根据商户id查询商户地址
            // 限制:入参shopIds不大于10个
            public Map<Integer, String> getShopAddress(List<Integer> shopIds) {
                return Collections.emptyMap();
            }
        }

        public class AService {

            private Shopservice shopService = new Shopservice();
            // 本地缓存，可以使用coffine替代
            private final Map<Integer, ShopInfo> cache = new ConcurrentHashMap<>();

            /**
             * 目标：根据商户id批量查询商户信息，入参shopIds不大于100个
             * 要求：
             * 1，并行调用ShopService提供的getShopName和getShopAddress方法获取商户名称和地址，构造ShopInfo
             * 2. 从ShopService获取数据时需要满足ShopService 入参shopIds不大于10个的限制条件，需要分批调用
             * 3. 实现本地缓存，优先从本地缓存获取数据，如果本地缓存没有数据再从ShopService获取数据
             */
            public Map<Integer, ShopInfo> getShopInfo(List<Integer> shopIds) {

                // 返回结果集
                Map<Integer, ShopInfo> result = new HashMap<>();
                // 将不存在于缓存中的 ID 存入新的数组
                List<Integer> idsToFetch = new ArrayList<>();

                if(CollectionUtils.isEmpty(shopIds)){
                    return result;
                }

                // 先从缓存中获取已有信息
                for (Integer id : shopIds) {
                    if (cache.containsKey(id)) {
                        result.put(id, cache.get(id));
                    }else{
                        idsToFetch.add(id);
                    }
                }

                // 按照每十个为一组进行分割
                List<List<Integer>> batches = new ArrayList<>();
                // 常量可以维护起来
                int batchSize = 10;
                for (int i = 0; i < idsToFetch.size(); i += batchSize) {
                    batches.add(idsToFetch.subList(i, Math.min(i + batchSize, idsToFetch.size())));
                }

                // 并行调用getShopName和getShopAddress方法来组装ShopInfo
                for (List<Integer> batch : batches) {
                    CompletableFuture<Void> future = CompletableFuture.allOf(
                            CompletableFuture.runAsync(() -> {
                                Map<Integer, String> names = shopService.getShopName(shopIds);
                                Map<Integer, String> addresses = shopService.getShopAddress(shopIds);

                                for (Integer id : shopIds) {
                                    // 没有构造函数先这样写
                                    ShopInfo info = new ShopInfo();
                                    // 省略判空直接赋值
                                    info.id = id;
                                    info.address = addresses.get(id);
                                    info.name = names.get(id);

                                    result.put(id, info);
                                    cache.put(id, info);
                                }
                            })
                    );

                    try {
                        future.get(); // 等待所有异步任务完成
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }

                }
                return result;
            }

        }

}
