import java.util.*;

public class puzzle {
    static int countH(int[] state) {
        int c = 0;
        int goal[] = { 1, 2, 3,
                4, 5, 6,
                7, 8, 0 };
        for (int i = 0; i < goal.length; i++) {
            if (state[i] != 0 && state[i] != goal[i])
                c++;
        }
        return c;
    }

    static void printStates(int state[]) {
        for (int i = 0; i < state.length; i++) {
            if (i % 3 == 0 && i > 0) {
                System.out.println();
            }
            System.out.print(state[i] + " ");
        }
    }

    static int rh = 9999;
    static int pos = -1;

    static int[] move(int ar[], int pos, int state[]) {
        int store_st[] = Arrays.copyOf(state, state.length);
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 0)
                pos = i;
        }
        for (int i = 0; i < ar.length; i++) {
            int dup[] = Arrays.copyOf(store_st, store_st.length);
            int temp = dup[pos];
            dup[pos] = dup[ar[i]];
            dup[ar[i]] = temp;

            int temp_rh = countH(dup);
            if (temp_rh < rh) {
                rh = temp_rh;
                store_st = Arrays.copyOf(dup, dup.length);
            }
        }
        return store_st;
    }

    public static void main(String[] args) {
        int state[] = { 1, 2, 3,
                4, 5, 6,
                0, 7, 8 };
        int h = countH(state);
        System.out.println(h);
        int level = 1;
        System.out.println("------- Level " + level + "-------");
        printStates(state);
        System.out.println("Heuristic Value (Misplaced) : " + h);

        while (h > 0) {

            for (int i = 0; i < state.length; i++) {
                if (state[i] == 0)
                    pos = i;
            }
            level++;

            if (pos == 0) {
                int arr[] = { 1, 3 };
                state = move(arr, pos, state);
                h = rh;

            }
            if (pos == 1) {

                int arr[] = { 0, 2, 4 };
                state = move(arr, pos, state);
                h = rh;
            }
            if (pos == 2) {

                int arr[] = { 1, 5 };
                state = move(arr, pos, state);
                h = rh;
            }
            if (pos == 3) {
                int arr[] = { 0, 4, 6 };
                state = move(arr, pos, state);
                h = rh;
            }
            if (pos == 4) {

                int arr[] = { 1, 3, 5, 7 };
                state = move(arr, pos, state);
                h = rh;
            }
            if (pos == 5) {
                int arr[] = { 2, 4, 8 };
                state = move(arr, pos, state);
                h = rh;
            }
            if (pos == 6) {
                int arr[] = { 3, 7 };
                state = move(arr, pos, state);
                h = rh;
            }
            if (pos == 7) {
                int arr[] = { 4, 6, 8 };
                state = move(arr, pos, state);
                h = rh;
            }
            if (pos == 8) {
                int arr[] = { 5, 6 };
                state = move(arr, pos, state);
                h = rh;
            }
            System.out.println("------- Level " + level + "-------");
            printStates(state);
            System.out.println("Heuristic Value (Misplaced) : " + h);
            if (h == 0) {
                System.out.println("Goal State!");
                break;
            }
        }

    }
}