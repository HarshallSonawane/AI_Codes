
public class CSP {
    static int s = 1, f = -1, u = -1, n = -1, w = -1, m = -1, i = -1;

    static boolean check(int temp) {
        return (temp != s && temp != u && temp != n && temp != f && temp != w && temp != i && temp != m);
    }

    static char getChar(int d) {
        if (d == s)
            return 's';
        else if (d == u) {
            return 'u';
        } else if (d == n) {
            return 'n';
        } else if (d == f) {
            return 'f';
        } else if (d == w) {
            return 'w';
        } else if (d == i) {
            return 'i';
        } else if (d == m) {
            return 'm';
        } else
            return '0';
    }

    public static void main(String[] args) {

        for (int t = 2; t < 10; t++) {
            int ans = s + t;
            ans = ans / 10;
            ans = ans % 2;
            if (ans == 1 && check(t)) {
                f = t;
                w = ans / 2;
                break;
            }
        }
        for (int t = 1; t < 10; t++) {
            if (check(t)) {
                u = t;
                if (check(u + u)) {
                    i = u + u;
                    break;
                }
                break;
            }
        }
        for (int t = 1; t < 10; t++) {
            if (check(t)) {
                n = t;
                if (check(n + n)) {
                    m = n + n;
                    break;
                }
                break;
            }
        }
        System.out.println(s + " " + u + " " + n);
        System.out.println((f) + " " + u + " " + n);
        System.out.println((s + f) + " " + (u + u) + " " + (n + n));

        System.out.println();
        System.out.println("s" + "u" + "n");
        System.out.println("f" + "u" + "n");
        int ans = s + f;
        System.out.print(getChar(ans/10));
        System.out.print(getChar(ans%10));
        System.out.print(getChar(u + u));
        System.out.print(getChar(n + n));
    }

}
