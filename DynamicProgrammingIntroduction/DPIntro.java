import java.util.Arrays;

/*
这里简单介绍一下动态规划算法，以及一个经典案例

对于一个问题能不能使用动态规划算法求解，首先要看一下几个问题：
    1. 目标函数是什么？如何进行优化？
    2. 如何划分子问题的边界？
    3. 优化函数值与子问题的优化函数值之间存在什么依赖关系？（即递推方程是什么）
    4. 是否满足优化原则？（即如果子问题的解被优化后，原问题的解是否也被优化了）
    5. 最小资问题如何界定？其优化函数值（即初始值）是什么？

E.g. 矩阵链相乘:
假设有 A1, A2, ..., An 矩阵，其中第i个矩阵Ai 是 P_(i-1) × P_i 阶矩阵（i = 1,2,...,n）。
（每两个相邻矩阵之间是一定可以进行乘法运算的）
问如何确定一个矩阵相乘的顺序，使得元素相乘的总次数最少？

Input: 
    P = < P_0, P_1, ..., P_n >， 其中 P_i表示第 i 个矩阵的行数和列数
    ┍         ┐     ┍         ┐         ┍         ┐
    |  P_0行  |     |  P_1行  |         |  P_n-1行|
    |  P_1列  |     |  P_2列  |   ...   |  P_n列  |
    ┕         ┘     ┕         ┘         ┕         ┘
Output:
    矩阵乘法添加括号的位置

在求解之前，我们需要先了解一下矩阵相乘的运算次数。假设有A, B 两个矩阵，分别是 i行j列 和 j行k列。
    ┍           ...          ┐     ┍  b_1s  ┐         ┍         ┐
    | a_t1, a_t2, ..., a_tj  |  .  |  b_2s  |    =    |  c_ts   |
    |           ...          |     |  ...   |         |         |
    ┕           ...          ┘     ┕  b_js  ┘         ┕         ┘
    其中 t = 1, 2, ..., i;  s = 1, 2, ..., k
    其中 c_ts = a_t1 × b_1s + a_t2 × b_2s +...+ a_tj × b_js。这里一共进行了 j次乘法，(j-1)次加法
    又因为一共有 k 个 c_ts要计算，所以A, B两个矩阵相乘的 所用到的乘法的次数 是 i * j * k 次。
    最后得到了一个 i行k列 的矩阵C。

来看一个例子：
P = < 10, 100, 5, 50 >
A1: 10 × 100, A2: 100 × 5, A3: 5 × 50
(A1A2)A3: 10 × 100 × 5 + 10 × 5 × 50 = 7500次
A1(A2A3): 10 × 100 × 50 + 100 × 5 × 50 = 75000次
*/

public class DPIntro {

    public int Solution1(int[] P){
        /*
        第一种解法是brute-force。这里不给出.
        */
        return -1;
    }

    
    public int Solution2(int[] P, int i, int j) {
        /* 第二种解法是 Dynamic Programming
        我们以此来看该问题是否符合上面所提到的5个要求：
        1. 子问题划分：
            对于A_(i...j): 矩阵链 A_i A_(i+1) ... A_j，它的边界是 i, j。
            此时的输入向量是 < P_(i-1), P_i, ..., P_j >。其最好划分的运算次数为 m[i, j]
        2. 子问题的依赖关系：
            最优化分最后一次相乘发生在矩阵 k 的位置， 即： A_(i...j) = A_(i...k) A_(k+1...j)
            A_(i...j)最优运算次数依赖于A_(i...k) 与 A_(k+1...j) 的最优运算次数
            （其中，k是某一次最优的划分，它使得A_(i...k) 是之前已经算好的那部分矩阵链，A_(k+1...j)是之后要算的那部分矩阵链。
        3. 有了上面的依赖关系之后我们给出该问题的递推方程：
            m[i, j]: 得到 A_(i...j) 的最少的相乘次数 m[i, j] = 
            { 0                                              ,  if i = j 
            { min { m[i, j] + m[k+1, j] + P_(i-1) * P_k * P_j,  if i < j   (where i <= k < j)
            上面大括号中，当 i<j 时，前半部分矩阵链是从 Ai 乘到 Ak， 后半部分是 A_(k+1) 到 Aj
            对于当前层，因为Ai 乘到 Ak会得到一个 P_(i-1) * P_k 的矩阵，A_(k+1) 乘到 Aj 会得到一个P_k * P_j的矩阵
            所以当前层需要进行的计算量为：P_(i-1) * P_k * P_j。也就是大括号的后半部分。
            Ai * ...... * Ak * A_(k+1) * ...... * Aj
            |  P_(i-1) * P_k |     P_k * P_j       |
            又因为最优的 k 可以是 [i, j) 之间的任意一个，所以我们要找到 [i, j) 中使得计算量最小的一个 k
            最后我们发现该问题是满足动态规划的求解要求的
        
        该方法的Pseudo-Code:
        function RecurMatrixChain(P, i, j)
            m[i, j] ← ∞
            s[i, j] ← i
            for k ← i to j do
                q ← RecurMatrixChain(P, i, k)
                  + RecurMatrixChain(P, k+1, j) + p_(i-1) * p_k * p_j
                if q < m[i, j]
                then m[i, j] ← q
                     s[i, j] ← k
            return m[i, j]
        观察上面的Pseudo-Code不难发现，该问题是从i 到 j的一个子问题
        在第一个for循环中，我们定义了划分位置 k，并从 i 到 j 中寻找所有的m[i, j] 的最优解
        */
        if (i == j) { return 0; }
        int m = Integer.MAX_VALUE;
        int q;
        for (int k = i; k < j; k++){
            q = Solution2(P, i, k) + Solution2(P, k+1, j) + P[i-1] * P[k] * P[j];
            if (q < m) { m = q; }
        }
        return m;
        /* 复杂度分析
        T(n) = 
            { O(1)                      ,  n = 1
            { Σ ( T(k) + T(n-k) + O(1) ),  n > 1
        T(n) >= O(n) + Σ T(k) + Σ T(n-k)
        T(n) >= O(n) + 2 Σ T(k)
        ---- 数学归纳法可证明 ----> 该算法的时间复杂度 T(n) >= 2 ^ (n-1)
        相比于brute-force，动态规划算法的时间复杂度稍微有所降低，但效率仍然不高。
        原因是同一个子问题多次重复出现，每次出现都需要重新计算一遍。
        想要进一步优化该动态规划问题，我们需要用到空间换时间的策略。即 记录每个子问题首次计算的结果，
        后面再用到时，就直接取值，这样每个子问题只需要计算一次即可。该方法就是Dynamic Programming + Memoization
        */
    }


    public int Solution3(int[] P, int n) {
        /* 该方法来自: https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/ (Using Tabulation) */
        int memo[][] = new int[n][n];
        int i, j, k, r, t;
        for (i = 1; i < n; i++) { memo[i][i] = 0; }

        for (r = 2; r < n; r++) {
            for (i = 1; i < n - r + 1; i++) {
                j = i + r - 1;
                if (j == n) continue;
                memo[i][j] = Integer.MAX_VALUE;
                for (k = i; k <= j - 1; k++) {
                    t = memo[i][k] + memo[k+1][j] + P[i-1] * P[k] * P[j];
                    if (t < memo[i][j]) {
                        memo[i][j] = t;
                    }
                }
            }
        }
        return memo[1][n-1];
    }


    public int Solution4 (int[] P, int n){
        /* 该方法来自: https://www.youtube.com/watch?v=OdPkheSV00U&list=PL0YsRvTMLh26WSRuBmNOt0-KULDaef0wl&index=5
        (个人认为, 该方法中的s[i, j] 并没有起到节省计算时间的作用)
            该方法通过迭代，实现了 Dynamic Programming + Memoization：
            如何进行迭代过程呢？
                1. 要从最小的子问题开始算起
                2. 考虑计算顺序，以保证之后用到的值在之前已经计算好
                3. 使用Memoization
            解的追踪
                1. 设计标记函数，标记每步的决策
                2. 考虑根据标记函数追踪解的算法
            矩阵链乘法迭代顺序：
                当长度为1：初值， m[i, j] = 0
                当长度为2：1...2, 2...3, 3...4, ..., n-1...n
                当长度为3: 1...3, 2...4, 3...5, ..., n-2...n
                ...
                当长度为n-1: 1...n-1, 2...n
                当长度为n: 1...n
            来看一下Pseudo-Code:
            令所有的 m[i, j] 初值为0
            for r ← 2 to n do                                   // r 为链长
                for i ← 1 to n - r + 1 do                       // 左边界 i
                    j ← i + r - 1                               // 右边界 j
                    m[i, j] ← m[i+1, j] + P_(i-1) * P_i * P_j   // k = i
                    s[i, j] ← i                                 // 记录k值
                    for k ← i to j - 1 do                       // 遍历 k
                        t ← m[i, k] + m[k+1, j] + P_(i-1) * P_i * P_j
                        if t < m[i, j]
                        then m[i, j] ← t                        // 更新解
        */
        int[][] memo = new int[n][n];
        int[][] s = new int[n][n];
        int r, i, j, k;
        int t = Integer.MAX_VALUE;
        for (r = 2; r < n; r++) {
            for (i = 1; i < n - r + 1; i++) {
                j = i + r - 1;
                memo[i][j] = memo[i+1][j] + P[i-1] * P[i] * P[j];
                s[i][j] = i;
                for (k = i; k <= j - 1; k++){
                    t = memo[i][k] + memo[k+1][j] + P[i-1] * P[k] * P[j];
                    if (t < memo[i][j]) {
                        memo[i][j] = t;
                        s[i][j] = k;
                    }
                }
            }
        }
        return t;
    }


    public static void main (String[] args){
        int[] P = { 1, 2, 3, 4, 3 }; 
        DPIntro s = new DPIntro();
        System.out.println(Integer.toString(s.Solution2(P, 1, P.length-1)));  // Output is 30.
        System.out.println(Integer.toString(s.Solution3(P, P.length)));  // Output is 30.
        System.out.println(Integer.toString(s.Solution4(P, P.length)));  // Output is 30.
    }
}
