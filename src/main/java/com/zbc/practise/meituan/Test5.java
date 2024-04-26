package com.zbc.practise.meituan;

import java.math.BigDecimal;
import java.util.List;

public class Test5 {

    // 计算商品的优惠标签
    // originalPrice原价 finalPrice到手价 historicalPriceList 历史到手价列表
    public static String computeProductPromoTag(BigDecimal originalPrice, BigDecimal finalPrice, List<BigDecimal> historicalPriceList) {

        // 如果是历史最低，则输出 “历史最低”
        if (isHistoricalLow(finalPrice, historicalPriceList)) {
            return "历史最低";
        }

        // 如果是最近3个月最低，则输出 “近3月最低”
        if (isLowestInRecentMonths(finalPrice, historicalPriceList, 3)) {
            return "近3月最低";
        }

        // 如果有折扣（折扣要大于0.1且小于9.9，小数点向下取），则输出：“xx折”，小数点最多保留1位（向下取），如最后一位小数是-则删掉
        // eq有精度问题
        if(originalPrice.compareTo(finalPrice) != 0){
            String discountTag = calculateDiscount(originalPrice, finalPrice);
            return discountTag + "折";
        }

        // 兜底文案 输出 “到手价”
        return "到手价";
    }

    /**
     * 价格是否是历史最低
     * @param finalPrice 到手价格
     * @param historicalPriceList 历史价格列表
     * @return 是否历史最低 true是false否
     */
    private static boolean isHistoricalLow(BigDecimal finalPrice, List<BigDecimal> historicalPriceList) {
        return historicalPriceList.stream()
                .allMatch(price -> finalPrice.compareTo(price) <= 0);
    }

    /**
     * 近month个月最低
     * @param finalPrice 到手价格
     * @param historicalPriceList 历史价格列表
     * @param months 匹配近X个月的数据
     * @return 是否X个月的数据最低 true是false否
     */
    private static boolean isLowestInRecentMonths(BigDecimal finalPrice, List<BigDecimal> historicalPriceList, int months) {
        // 谨防区间溢出
        int endIndex = Math.min(historicalPriceList.size(), months);

        List<BigDecimal> recentPrices = historicalPriceList.subList(0, endIndex);
        return recentPrices.stream()
                .allMatch(price -> finalPrice.compareTo(price) <= 0);
    }

    /**
     * 算出是多少折扣 公式 到手价除原价
     * @param originalPrice 原价
     * @param finalPrice 到手价
     * @return XX折
     */
    private static String calculateDiscount(BigDecimal originalPrice, BigDecimal finalPrice) {
        BigDecimal discount = finalPrice.divide(originalPrice, 2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal("10"));
        // 去零
        discount = discount.stripTrailingZeros();
        if (discount.scale() <= 0) {
            // 如果没有小数部分，直接返回整数
            return String.valueOf(discount.intValue());
        }
        return discount.toPlainString();
    }

}
