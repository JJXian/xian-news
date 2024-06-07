package com.xian.article.tetet;

public class CostPriceCalculator {
    public static void main(String[] args) {
        // 示例现价和收益率
        double currentPrice = 71286;  // 现价
        double rateOfReturn = 0.1372;   // 收益率，例如20%

        double costPrice = calculateCostPrice(currentPrice, rateOfReturn);
        System.out.println("成本价: " + costPrice);
    }

    /**
     * 根据现价和收益率计算成本价
     *
     * @param currentPrice 现价
     * @param rateOfReturn 收益率
     * @return 成本价
     */
    public static double calculateCostPrice(double currentPrice, double rateOfReturn) {
        return currentPrice / (1 + rateOfReturn);
    }
}
