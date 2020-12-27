import java.util.List;
import java.util.Map;
import java.util.HashMap; // import the HashMap class
import java.util.*;

/*
Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:
    i + 1 where: i + 1 < arr.length.
    i - 1 where: i - 1 >= 0.
    j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.

Example 1:
    Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
    Output: 3
    Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is 
    the last index of the array.

Example 2:
    Input: arr = [7]
    Output: 0
    Explanation: Start index is the last index. You don't need to jump.

Example 3:
    Input: arr = [7,6,9,6,9,6,9,7]
    Output: 1
    Explanation: You can jump directly from index 0 to index 7 which is last index of the array.

Example 4:
    Input: arr = [6,1,9]
    Output: 2

Example 5:
    Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
    Output: 3
*/

public class JumpGameIV {
    /* Solution 1: BFS
    本题从暴破开始，逐步优化算法。最朴素的暴破方法就是将所有的路线进行一次遍历。然后找到所有路线中最短的那一个。
    但是，如果我们已经遍历过 某一个index，那么第二次遇到该index时，就不需要再继续遍历了。这样就可以节省时间。
    我们可以建立一个名为visited的set来存储这些已经遍历过的 index。
    为了方便，我们还可以将所有拥有相同值的节点 存入一个名为graph的字典中。有了该方法，在遍历的过程中，我们就
    不再需要遍历整个数组 去寻找 拥有相同值的节点，而只需要搜索graph这个字典即可。但我们每次得到值之后，需要
    清空字典。
    */
    public int solution1(int[] arr){
        int n = arr.length;
        if (n <= 1) return 0;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++){
            graph.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);
        }
        List<Integer> curr = new LinkedList<>();
        curr.add(0);
        Set<Integer> visited = new HashSet<>();
        int step = 0;

        while (!curr.isEmpty()) {
            List<Integer> next = new LinkedList<>();
            for (int node: curr) {
                if (node == n - 1) { 
                    return step;
                }

                for (int child: graph.get(arr[node])) {
                    if (!visited.contains(child)){
                        visited.add(child);
                        next.add(child);
                    }
                }
                graph.get(arr[node]).clear();

                if (node + 1 < n && !visited.contains(node + 1)) {
                    visited.add(node + 1);
                    next.add(node + 1);
                }

                if (node - 1 >= 0 && !visited.contains(node - 1)){
                    visited.add(node - 1);
                    next.add(node - 1);
                }
            }
            curr = next;
            step++;
        }
        return -1;
    }


    /* Bidirectional BFS
    在上面BFS算法的后半部分，每一层可能会花很长时间来计算下一层。这种情况下，我们可以计算
    当前层到最后层，从而节省时间。
    */
    public int solution2(int[] arr){
        int n = arr.length;
        if (n <= 1) return 0;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++){
            graph.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);
        }

        List<Integer> curr = new LinkedList<>();  // 从开始存储 层
        curr.add(0);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        visited.add(n - 1);
        int step = 0;

        List<Integer> other = new LinkedList<>();  // 从结尾存储 层
        other.add(n - 1);
        
        while (!curr.isEmpty()) {
            if (curr.size() > other.size()) {
                List<Integer> temp = curr;
                curr = other;
                other = temp;
            }

            List<Integer> next = new LinkedList<>();

            for (int node : curr) {
                for (int child : graph.get(arr[node])) {
                    if (other.contains(child)) {
                        return step + 1;
                    }
                    if (!visited.contains(child)) {
                        visited.add(child);
                        next.add(child);
                    }
                }

                // 清空链表，防止冗余
                graph.get(arr[node]).clear();

                // check neighbors:
                if (other.contains(node + 1) || other.contains(node - 1)) {
                    return step + 1;
                }
                if (node + 1 < n && !visited.contains(node + 1)) {
                    visited.add(node + 1);
                    next.add(node + 1);
                }
                if (node - 1 >= 0 && !visited.contains(node - 1)) {
                    visited.add(node - 1);
                    next.add(node - 1);
                }
            }
            curr = next;
            step++;
        }
        return -1;
    }


    public static void main (String[] args){
        JumpGameIV s = new JumpGameIV();
        int[] arr1 = {100,-23,-23,404,100,23,23,23,3,404};
        int[] arr2 = {7};
        int[] arr3 = {7,6,9,6,9,6,9,7};
        int[] arr4 = {6,1,9};
        int[] arr5 = {11,22,7,7,7,7,7,7,7,22,13};
        System.out.println("Solution 1 with example 1: " + Integer.toString(s.solution1(arr1)));
        System.out.println("Solution 1 with example 2: " + Integer.toString(s.solution1(arr2)));
        System.out.println("Solution 1 with example 3: " + Integer.toString(s.solution1(arr3)));
        System.out.println("Solution 1 with example 4: " + Integer.toString(s.solution1(arr4)));
        System.out.println("Solution 1 with example 5: " + Integer.toString(s.solution1(arr5)));
        System.out.println("Solution 2 with example 1: " + Integer.toString(s.solution2(arr1)));
        System.out.println("Solution 2 with example 2: " + Integer.toString(s.solution2(arr2)));
        System.out.println("Solution 2 with example 3: " + Integer.toString(s.solution2(arr3)));
        System.out.println("Solution 2 with example 4: " + Integer.toString(s.solution2(arr4)));
        System.out.println("Solution 2 with example 5: " + Integer.toString(s.solution2(arr5)));
    }
}