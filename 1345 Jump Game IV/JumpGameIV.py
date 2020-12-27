'''
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
'''

class Solution:
    def solution1(self, arr):
        n = len(arr)
        if n <= 1: return 0
        graph = {}
        for i in range(n):
            if arr[i] in graph:
                graph[arr[i]].append(i)
            else:
                graph[arr[i]] = [i]
        curr = [0]
        visited = {0}
        step = 0
        while curr:
            next = []
            for node in curr:
                if node == n - 1:
                    return step
            
                for child in graph[arr[node]]:
                    if child not in visited:
                        visited.add(child)
                        next.append(child)
                
                graph[arr[node]].clear()

                for child in [node-1, node+1]:
                    if 0 <= child < len(arr) and child not in visited:
                        visited.add(child)
                        next.append(child)
            curr = next
            step += 1
        return -1

    def solution2(self, arr):
        n = len(arr)
        if n <= 1: return 0

        graph = {}
        for i in range(n):
            if arr[i] in graph:
                graph[arr[i]].append(i)
            else:
                graph[arr[i]] = [i]
            
        curr = [0]
        visited = {0, n-1}
        step = 0
        other = [n-1]

        while curr:
            if len(curr) > len(other):
                curr, other = other, curr
            next = []
        
            # Iterate the layer:
            for node in curr:
                for child in graph[arr[node]]:
                    if child in other:
                        return step + 1
                    if child not in visited:
                        visited.add(child)
                        next.append(child)
                
                graph[arr[node]].clear()
                # check neighbors:
                for child in [node-1, node+1]:
                    if child in other:
                        return step + 1
                    if 0 <= child < len(arr) and child not in visited:
                        visited.add(child)
                        next.append(child)
            curr = next
            step += 1
        return -1
    
if __name__ == "__main__":
    s = Solution()
    arr1 = [100,-23,-23,404,100,23,23,23,3,404]
    arr2 = [7]
    arr3 = [7,6,9,6,9,6,9,7]
    arr4 = [6,1,9]
    arr5 = [11,22,7,7,7,7,7,7,7,22,13]
    print("Solution 1 with example 1: ", s.solution1(arr1))
    print("Solution 1 with example 2: ", s.solution1(arr2))
    print("Solution 1 with example 3: ", s.solution1(arr3))
    print("Solution 1 with example 4: ", s.solution1(arr4))
    print("Solution 1 with example 5: ", s.solution1(arr5))
    print("Solution 2 with example 1: ", s.solution1(arr1))
    print("Solution 2 with example 2: ", s.solution1(arr2))
    print("Solution 2 with example 3: ", s.solution1(arr3))
    print("Solution 2 with example 4: ", s.solution1(arr4))
    print("Solution 2 with example 5: ", s.solution1(arr5))