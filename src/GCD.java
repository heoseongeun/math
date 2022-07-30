import java.math.BigInteger;

/**
 *   <유클리드 호제법>
 *   2개의 자연수 a, b
 *   r = a % b (a > b)
 *   a와 b의 최대 공약수(Greatest Common Divisor) : b와 r의 GCD와 동일
 *   r' = b % r
 *   ... 반복하다 나머지가 0이 되었을 때의 나누는 수가 GCD
 *
 *   a > b 조건은 반복하다보면 자동으로 만족됨
 *
 *   Ex) 1071과 1029의 GCD : 21
 *    1071 % 1029 = 42
 *    1029 % 42 = 21
 *    42 % 21 = 0
 */
public class GCD {
    // 내장 메서드 사용
    int gcd(int a, int b) {
        BigInteger b1 = BigInteger.valueOf(a);
        BigInteger b2 = BigInteger.valueOf(b);
        BigInteger gcd = b1.gcd(b2);

        return gcd.intValue();
    }

    // 재귀 사용
    int gcdRecursive(int a, int b) {
        return b == 0 ? a : gcdRecursive(b, a % b);
    }

    // 반복문 사용
    int gcdRepetition(int a, int b) {
        while(b > 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }

    /**
     *  <Lowest(Least) Common Multiple>
     *  정수 a, b의 GCD G에 대해
     *  a = Gx, b = Gy (x, y는 정수)
     *  a * b = G^2 * x * y (x, y는 서로소 관계)
     *
     *  a와 b의 합집합 : G, x, y
     *  LCM(최소 공배수) : G * x * y
     *
     *  a * b / G = G^2 * x * y / G (양변에 GCD 나눠줌)
     *  a * b / G = G * x * y
     *
     *  cf) 서로소 : 1이외에 공약수를 갖지 않는 둘 이상의 양의 정수
     */
    // 파라미터가 두 개일 경우
    int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    // 여러 수의 최소공배수를 구해야 할 경우
    int lcmArr(int[] arr) {
        if(arr.length == 1) return arr[0];

        int gcd = gcd(arr[0], arr[1]);
        int lcm = lcm(arr[0], arr[1]);

        for(int i = 2; i < arr.length; i++) {
            gcd = gcd(lcm, arr[i]);
            lcm = lcm(lcm, arr[i]);
        }

        return lcm;
    }
}