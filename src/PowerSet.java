import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class PowerSet {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};

        // 1. 조합 이용
        // [1, 2, 3] 중에서 0 ~ 3개 선택
        System.out.println("1. 조합 이용");
        for(int i = 0; i <= arr.length; i++) {
            powerSetWithCombination(arr, 0, i, 0, new int[i]);
        }

        // 2. 재귀 이용
        System.out.println("2. 재귀 이용");
        powerSetWithRecursive(arr, 0, new boolean[arr.length]);

        // 3. Stack 이용 (재귀의 visited[]와 stack이 비슷한 용도)
        System.out.println("3. Stack 이용");
        ArrayList<int[]> result = powerSetWithStack(new Stack<Integer>(), 0, arr, new ArrayList<int[]>());
        for(int[] intArr: result) {
            System.out.println(Arrays.toString(intArr));
        }
    }

    // 1. 조합 이용
    public static void powerSetWithCombination(int[] arr, int start, int end, int index, int[] subset) {
        if(start == end) {
            System.out.println(Arrays.toString(subset));
            return;
        }
        for(int i = index; i < arr.length; i++) {
            subset[start] = arr[i];
            powerSetWithCombination(arr, start + 1, end, i + 1, subset);
        }
    }

    // 2. 재귀 이용
    public static void powerSetWithRecursive(int[] arr, int start, boolean[] visited) {
        if(start == arr.length) {
            for(int i = 0; i < arr.length; i++) {
                if(visited[i]) System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        // arr의 start번 째 요소 선택하고 재귀 진행
        visited[start] = true;
        powerSetWithRecursive(arr, start + 1, visited);
        // arr의 start번 째 요소 선택하지 않고 재귀 진행
        visited[start] = false;
        powerSetWithRecursive(arr, start + 1, visited);
    }

    // 3. Stack 이용
    public static ArrayList<int[]> powerSetWithStack(Stack<Integer> stack, int idx, int[] arr, ArrayList<int[]> result) {
        if (idx >= arr.length) {
            result.add(Arrays.asList(stack.toArray()).stream().mapToInt(m -> (int) m).toArray());
            return result;
        } else {
            // arr의 idx번 째 요소가 부분집합(stack)에 포함된 경우
            stack.push(arr[idx]);
            powerSetWithStack(stack, idx + 1, arr, result);

            // arr의 idx번 째 요소가 부분집합(stack)에 포함되지 않은 경우
            stack.pop();
            powerSetWithStack(stack, idx + 1, arr, result);
        }
        return result;
    }
}
