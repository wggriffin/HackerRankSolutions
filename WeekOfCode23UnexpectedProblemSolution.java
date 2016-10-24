import java.io.*;
import java.util.*;
import java.math.*;

public class WeekOfCode23UnexpectedProblemSolution {

    public static void main(String[] args) {
        /* we need to find any repeating pattern that lasts thru the whole string, then take 
           the length of that and divide m by the length 
           in order to find repeating patterns we need to check all numbers that divide evenly 
           into the length of the string up to the halfway point (because no pattern longer than 
           half of the string can repeat more than once, for obvious reasons, and no pattern with a 
           length that doesn't divide evenly into the length can repeat evenly) */
        /* passes all test cases in under a third of a second */
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        BigInteger m = sc.nextBigInteger();
        BigInteger len = new BigInteger(Integer.toString(getLengthRepeatedPattern(str)));
        BigInteger mod = new BigInteger("1000000007");
        System.out.print(m.divide(len).mod(mod));
    }
    
    public static int getLengthRepeatedPattern(String str) {
        List<Integer> lengths = getDivisors(str.length());
        for(int i : lengths) {
            if(isPattern(str, i)) return i;
        }
        return str.length();
    }
    
    public static boolean isPattern(String str, int step) {
        CharSequence pattern = str.subSequence(0, step);
        int len = str.length(), patternIndex = 0;
        for(int i = 0; i < len; i++) {
            if(str.charAt(i) != pattern.charAt(patternIndex)) return false;
            patternIndex = (patternIndex == step - 1) ? patternIndex = 0 : patternIndex + 1;
        }
        return true;
    }
    
    public static List<Integer> getDivisors(int num) {
        List<Integer> res = new LinkedList<>();
        Stack<Integer> conjugates = new Stack<>(); //keep natural ordering
        res.add(1);
        double sqrt = Math.sqrt(num);
        for(int i = 2; i <= sqrt; i++) {
            if(num % i == 0) {
                res.add(i);
                if(num / i != i) {
                    conjugates.push(num / i);
                }
            }
        }
        //conjugates are added in reverse natural order, so popping w stack gives us desired ordering
        while(!conjugates.isEmpty()) {
            res.add(conjugates.pop());
        }
        return res;
    }
}
