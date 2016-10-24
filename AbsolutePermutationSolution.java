import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class AbsolutePermutationSolution {
    //solution to HackerRank Absolute Permutation problem
    //solves all test suites within 2 seconds

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCount = in.nextInt();
        for(int i = 0; i < testCount; i++){
            int N = in.nextInt();
            int K = in.nextInt();
            int[] absolutePermutation = getAbsolutePermutation(N, K);
            if(absolutePermutation.length == 0) System.out.println(-1);
            else {
                for(int j = 0; j < absolutePermutation.length; j++) {
                    System.out.print(absolutePermutation[j] +  " ");
                }
                System.out.println();
            }
        }
    }
    
    public static int[] getAbsolutePermutation(int N, int K) {
        //always attempt to put the desired integer at a a smaller position (to maintain lexicographical sorting)
        //if that is not possible (if the spot is occupied or out of array range), then try to put it higher
        //if that is not possible (if the spot is occupied or out of array range), then there is no permutation
        int[] res = new int[N];
        for(int i = 1; i <= N; i++) {
            int location = (i - K > 0 && res[i - K - 1] == 0) ? i - K - 1: i + K - 1;
            if(location >= N) return new int[0];
            else res[location] = i;
        }
        return res;
    }
}
