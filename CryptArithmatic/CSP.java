import java.io.IOException;
import java.util.Scanner;

public class CSP {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        String w1 = sc.next();
        String w2 = sc.next();
        String w3 = sc.next();

        solve(w1, w2, w3);
    }

    static void solve(String w1, String w2, String w3) {
        usedLetter = new boolean[26];
        usedDigit = new boolean[26];
        assignedDigit = new int[26];
        markLetters(w1);
        markLetters(w2);
        markLetters(w3);
        backtrack(0, w1, w2, w3);
        System.out.println("No more solutions :(");
    }

    static boolean[] usedLetter;
    static boolean[] usedDigit;
    static int[] assignedDigit;

    static void markLetters(String w) {
        for (int i = 0; i < w.length(); ++i)
            usedLetter[w.charAt(i) - 'A'] = true;
    }

    static boolean check(String w1, String w2, String w3) {

        if (leadingZero(w1) || leadingZero(w2) || leadingZero(w3))
            return false;

        return value(w1) + value(w2) == value(w3);
    }

    static boolean leadingZero(String w) {
        return assignedDigit[w.charAt(0) - 'A'] == 0;
    }

    static int value(String w) {
        int val = 0;
        for (int i = 0; i < w.length(); ++i)
            val = val * 10 + assignedDigit[w.charAt(i) - 'A'];
        return val;
    }

    static void backtrack(int char_idx, String w1, String w2, String w3) {
        if (char_idx == 26) {

            if (check(w1, w2, w3)) {
                System.out.println("Found a solution!");
                for (int i = 0; i < 26; ++i)
                    if (usedLetter[i])
                        System.out.printf("[%c = %d]", (char) (i + 'A'), assignedDigit[i]);
                System.out.println("\n------");
            }
            return;
        }

        if (!usedLetter[char_idx]) {
            backtrack(char_idx + 1, w1, w2, w3);
            return;
        }

        for (int digit = 0; digit < 10; ++digit)
            if (!usedDigit[digit]) {
                usedDigit[digit] = true;
                assignedDigit[char_idx] = digit;
                backtrack(char_idx + 1, w1, w2, w3);
                usedDigit[digit] = false;
            }
    }
}