/*
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and 
sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:
    Input: [7,1,5,3,6,4]
    Output: 5
    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), 
                profit = 6-1 = 5. Not 7-1 = 6, as selling price needs to be larger 
                than buying price.

Example 2:
    Input: [7,6,4,3,1]
    Output: 0
    Explanation: In this case, no transaction is done, i.e. max profit = 0.
*/



public class BestTimeToBuyAndSellStock {
    /* Solution 1 (Brute Force):
    想要找到最大的profit，也就是要找到给定prices数组中最大和最小两个数的差。
    与此同时，这两个数（一个是 购入价 一个是卖出价）中，卖出价 > 购入价。
    换句话说就是  对于每个 j > i，找到 max(prices[j] - prices[i])。
    */
    public int maxProfit1(int[] prices){
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++){
            for (int j = i + 1; j < prices.length; j++){
                int profit = prices[j] - prices[i];
                if (profit > maxprofit)
                    maxprofit = profit;
            }
        }
        return maxprofit;
    }


    /* Solution 2 (One Pass):
    以第一个例子为例， prices = [7, 1, 5, 3, 6, 4]，
     ^
    7|   .
    6|                   .(peak)
    5|           .
    4|                       .
    3|               .
    2|
    1|       .(valley)
    0|----------------------------->
     0   1   2   3   4   5   6
    
    （假设各个点从左到右有折线相连）那么 max profit就是 peak - valley。那么该如何确定valley和peak？
    我们可以定义两个变量，一个是 minPrice，一个是maxPrice，与之对应的值就是 smallest valley和 maximum profit
    而maximum profit就是 出售价与购入价之间的最大差。
    */
    public int maxProfit2(int prices[]){
        int minPrice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++){
            if (prices[i] < minPrice)
                minPrice = prices[i];
            else if (prices[i] - minPrice > maxprofit)
                maxprofit = prices[i] - minPrice;
        }
        return maxprofit;
    }


    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};   // Example 1
        BestTimeToBuyAndSellStock s = new BestTimeToBuyAndSellStock();
        System.out.println(Integer.toString(s.maxProfit1(prices)));
        System.out.println(Integer.toString(s.maxProfit2(prices)));
    }
}

