package spoj;

import java.util.Scanner;

public class MaxNumberOfKids
{
    static int findMaxNumOfKids(int len, int breadth) {
        int kidCount = 1;
        while(len != breadth) {
            if(len < breadth) {
                breadth -= len;
            } else {
                len -= breadth;
            }
            ++kidCount;
        }
        return kidCount;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int N = scanner.nextInt();
        int P = scanner.nextInt();
        int Q = scanner.nextInt();

        int maxPossibleKids = 0;
        for(int i = M; i <= N; ++i) {
            for(int j = P; j <= Q; ++j) {
                maxPossibleKids += MaxNumberOfKids.findMaxNumOfKids(i, j);
            }
        }
        System.out.print(maxPossibleKids);
    }
}
