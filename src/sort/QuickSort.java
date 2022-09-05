package sort;

import java.io.IOException;
import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) throws IOException {
        int[] output = quickSort(new int[]{4, 5, 1, 2, 8, 3, 6, 7});
        System.out.println(Arrays.toString(output)); // --> [1, 2, 3, 4, 5, 6, 7, 8]
    }

    public static int[] quickSort(int[] arr) {
        if(arr.length == 0) return arr;
        sort(arr, 0, arr.length - 1);
        return arr;
    }

    public static void sort(int[] arr, int left, int right) {
        if(left >= right) return; // 배열의 길이가 1 이하일 경우 종료
        int part = partition_left(arr, left, right);
        if(left < part - 1) sort(arr, left, part - 1);
        if(right > part + 1) sort(arr, part + 1, right);
    }

    // pivot이 맨 첫 번째 값일 경우
    public static int partition_left(int[] arr, int start, int end) {
        int pivot = start;
        int left = start + 1;
        int right = end;

        // right < left 일 때까지 반복
        while(left <= right) {
            // left 가 배열의 맨 마지막 인덱스보다 작거나 같을 때까지 pivot 보다 큰 수 찾기
            while(left <= end && arr[left] <= arr[pivot]) left++;
            // right 가 pivot을 제외한 배열의 맨 처음 인덱스보다 크거나 같을 때까지 pivot 보다 작은 수 찾기
            while(start < right && arr[right] >= arr[pivot]) right--;

            // left <= right 이면, left 랑 right swap 해서 pivot 값을 제외한 약한 정렬
            if(left <= right) swap(arr, left, right);
            // right < left 이면, right 랑 pivot swap 해서 약한 정렬 완성
            else swap(arr, pivot, right);
        }

        return right;
    }

    // pivot이 맨 마지막 값일 경우
    public static int partition_right(int[] arr, int start, int end) {
        int pivot = end;
        int left = start;
        int right = end - 1;

        // right < left 일 때까지 반복
        while(left <= right) {
            // left 가 pivot을 제외한 배열의 맨 마지막 인덱스보다 작거나 같을 때까지 pivot 보다 큰 수 찾기
            while(left < end && arr[left] <= arr[pivot]) left++;
            // right 가 배열의 맨 처음 인덱스보다 크거나 같을 때까지 pivot 보다 작은 수 찾기
            while(start <= right && arr[right] >= arr[pivot]) right--;

            // left <= right 이면, left 랑 right swap 해서 pivot 값을 제외한 약한 정렬
            if(left <= right) swap(arr, left, right);
            // right < left 이면, left 랑 pivot swap 해서 약한 정렬 완성
            else swap(arr, pivot, left);
        }

        return left;
    }

    // pivot이 중간 값일 경우
    // sort 메서드에서 22번 째 줄에 아래와 같이 호출
    // if(right > part) sort(arr, part, right);
    public static int partition(int[] arr, int left, int right) {
        int pivot = (left + right) / 2;

        // right < left 일 때까지 반복
        while(left <= right) {
            // pivot 보다 큰 수 찾기
            while(arr[left] < arr[pivot]) left++;
            // pivot 보다 작은 수 찾기
            while(arr[right] > arr[pivot]) right--;

            // left <= right 이면, left 랑 right swap
            if(left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }

        return left;
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
