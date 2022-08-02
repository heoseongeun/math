import java.util.ArrayList;
import java.util.Arrays;

public class Combination {

    /**
     * <조합>
     * n개의 크기를 가진 배열 중에서 r개의 조합 구하기
     * 조합은 순열과 달리 요소 간의 순서를 고려하지 않음!
     */
    public static void main(String[] args) {
        ArrayList<int[]> combination = combination(new ArrayList<int[]>(), new int[]{1, 2, 3}, new int[2], new boolean[3], 0, 0);
        for(int[] arr: combination) {
            System.out.println(Arrays.toString(arr));
        }
    }//Correct number in parameter

    /**
     * [1, 2, 3] 이라는 3개의 크기를 가진 배열에서 2개의 조합 구하기
     * - result : 조합 저장할 ArrayList
     *  ex) [1, 2], [1, 3], [2, 3]
     * - arr : [1, 2, 3] 처럼 n개의 크기를 가진 배열
     * - output : r개(2개)의 크기를 가진 result 에 저장할 배열
     * - visitied : output에 중복된 값을 넣는 것을 방지하기 위한 배열
     *  ([false, false, false]가 초기화 값)
     * - depth : output의 크기가 r이 되었을 때 return 해주기 위한 변수
     * - start : 39번 째 줄의 for문 안에서 i 의미
     *  (몇 번째 요소부터 반복할 것인지 설정)
     * - n : arr 배열의 크기와 동일
     *  (34번 째 줄에서 arr.length로 대체)
     * - r : output 배열의 크기와 동일
     *  (20번 째 줄에서 output.length로 대체)
     */
    static ArrayList<int[]> combination(ArrayList<int[]> result, int[] arr, int[] output, boolean[] visited, int depth, int start) {
        // output의 크기가 r개가 되면 result에 넣기
        if (depth == output.length) {
            result.add(output.clone());
            return result;
        }

        for (int i = start; i < arr.length; i++) {
            // output 배열 안의 값과 중복되는 값이 없어야 output에 추가할 수 있음
            if (!visited[i]) {
                // output에 넣었던 값이라고 표시하기위해 visitied를 true로 설정
                visited[i] = true;
                // output 배열의 depth번 째 요소에 arr배열의 i번째 요소 넣기
                output[depth] = arr[i];
                // output 배열에 r개의 수가 채워질 때까지 재귀 호출
                // depth 와 start 값을 1씩 늘려주기
                // 순열과 달리 순서가 없기 때문에 현재 요소 뒤의 값만 넣어주어야하기 때문
                result = combination(result, arr, output, visited, depth + 1, i + 1);
                // output 배열에 넣었던 값의 visited를 false로 다시 되돌려 놓기
                visited[i] = false;
            }
        }

        return result;
    }

    /**
     * <중복 조합>
     * 조합과 달리 중복이 허용되므로 visited 배열이 불필요
     */
    static ArrayList<int[]> repetitionCombination(ArrayList<int[]> result, int[] arr, int[] output, boolean[] visited, int depth, int start) {
        // output의 크기가 r개가 되면 result에 넣기
        if (depth == output.length) {
            result.add(output.clone());
            return result;
        }

        for (int i = 0; i < arr.length; i++) {
            // output 배열 안의 값과 중복되는 값이 없어야 output에 추가할 수 있음
            if (!visited[i]) {
                // output 배열의 depth번 째 요소에 arr배열의 i번째 요소 넣기
                output[depth] = arr[i];
                // output 배열에 r개의 수가 채워질 때까지 재귀 호출
                // 조합과 달리 값의 중복이 허용되므로 start 값인 i를 그대로 넘겨주기
                result = repetitionCombination(result, arr, output, visited, depth + 1, i);
            }
        }

        return result;
    }

}
