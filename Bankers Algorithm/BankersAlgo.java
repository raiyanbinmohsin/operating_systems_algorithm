import java.io.*;
import java.util.*;
class BankersAlgo {
    static int n, m;
    static int safe[] = new int[n + 10];
    public static boolean safety(int available[], int allocation[][], int need[][], int n, int m,
            int changeInAvailable[][]) {
        boolean checksafe[] = new boolean[n];
        for (int i = 0; i < n; i++)
            checksafe[i] = false;
        int check = 0;
        int check1 = 0;
        do {
            for (int i = 0; i < n; i++) {
                boolean flag = true;
                if (checksafe[i] == false) {
                    for (int j = 0; j < m; j++) {
                        if (available[j] < need[i][j])
                            flag = false;
                    }
                    if (flag == true) {
                        for (int j = 0; j < m; j++) {
                            available[j] = available[j] + allocation[i][j];
                            changeInAvailable[check][j] = available[j];
                        }
                        safe[check] = i;
                        check++;
                        checksafe[i] = true;
                    }
                }
            }
            check1++;
        } while (check < n && check1 < n);
        if (check > n)
            return false;
        else
            return true;
    }
    public static void main(String[] args) throws IOException {
        File file = new File("G:\\BRAC University\\9th Semester\\CSE321\\CSE321\\input.txt");
        Scanner pooh = new Scanner(file);
        // no. of processes
        n = pooh.nextInt();
        // no. of resources
        m = pooh.nextInt();
        // Max. resource Matrix
        int max[][] = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                max[i][j] = pooh.nextInt();
            }
        // Resource allocation Matrix
        int allocation[][] = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                allocation[i][j] = pooh.nextInt();
            }
        // resource available
        int available[] = new int[m];
        for (int i = 0; i < m; i++) {
            available[i] = pooh.nextInt();
        }
        int need[][] = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        int[][] changeInAvailable = new int[n][m];
        System.out.println("Need matrix is: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(need[i][j] + "  ");
            }
            System.out.println();
        }
        if (safety(available, allocation, need, n, m, changeInAvailable)) {
            System.out.println("Safe sequence is :");
            for (int i = 0; i < n; i++) {
                System.out.print((char) ('A' + safe[i]) + " ");
            }
            System.out.println();
            System.out.println("Change in available resource matrix : ");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(changeInAvailable[i][j] + "  ");
                }
                System.out.println();
            }
        }
        else
            System.out.println("System is in unsafe state");
    }
}