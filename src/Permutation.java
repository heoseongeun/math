import java.util.ArrayList;

public class Permutation {
    /**
     * n개의 크기를 가진 배열 중에서 r개의 순열 구하기
     *
     * <visited 배열 이용>
     * DFS 떠올리기
     */
    public static void main(String[] args) {
        ArrayList<int[]> permutation = perm(new ArrayList<int[]>(), new int[]{1,2,3}, new int[3], new boolean[3], 0);
    }

    /**
     * [1, 2, 3] 이라는 3개의 크기를 가진 배열에서 2개의 순열 구하기
     * - result : 순열 저장할 ArrayList
     *  ex) [1, 2], [1, 3], ...
     * - arr : [1, 2, 3] 처럼 n개의 크기를 가진 배열
     * - output : r개(2개)의 크기를 가진 result 에 저장할 배열
     * - visitied : output에 중복된 값을 넣는 것을 방지하기 위한 배열
     *  ([false, false, false]가 초기화 값)
     * - depth : output의 크기가 r이 되었을 때 return 해주기 위한 변수
     * - n : arr 배열의 크기와 동일
     *  (34번 째 줄에서 arr.length로 대체)
     * - r : output 배열의 크기와 동일
     *  (20번 째 줄에서 output.length로 대체)
     */
    static ArrayList<int[]> perm(ArrayList<int[]> result, int[] arr, int[] output, boolean[] visited, int depth) {
        // output의 크기가 r개가 되면 result에 넣기
        if (depth == output.length) {
            result.add(output.clone());
            return result;
        }

        for (int i = 0; i < arr.length; i++) {
            // output 배열 안의 값과 중복되는 값이 없어야 output에 추가할 수 있음
            if (!visited[i]) {
                // output에 넣었던 값이라고 표시하기위해 visitied를 true로 설정
                visited[i] = true;
                // output에 arr 배열 값 넣기
                output[depth] = arr[i];
                // output 배열에 r개의 수가 채워질 때까지 재귀 호출
                result = perm(result, arr, output, visited, depth + 1);
                // output 배열에 넣었던 값의 visited를 false로 되돌리기
                visited[i] = false;
            }
        }

        return result;
    }

    /**
     * <swap 이용할 경우>
     * 순열의 순서가 보장되지 않음
     * 예를 들어,
     * [3, 1, 2], [3, 2, 1] 처럼 저장되지 않고
     * [3, 2, 1], [3, 1, 2] 로 저장됨
     */
    static void permutation(int[] arr, int depth, int r) {
        if (depth == r) {
            print(arr, r);
            return;
        }

        for (int i = depth; i < arr.length; i++) {
            swap(arr, depth, i);
            // 다음 index를 swap하기 위해 재귀 호출
            permutation(arr, depth + 1, r);
            // 다시 swap해서 원래대로 돌려놓음
            swap(arr, depth, i);
        }
    }

    static void swap(int[] arr, int depth, int i) {
        int temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }

    // 배열 출력
    static void print(int[] arr, int r) {
        for (int i = 0; i < r; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
