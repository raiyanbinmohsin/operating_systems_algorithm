import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BankersAlgo {
    public static void main(String[] args) {
        int n = 13;
        int m = 6;
        int[][] Alloc = new int[n][m];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                Alloc[i][j] = (2 * (i + 1) % 7) + j % i;
            }
        }

        int[][] T = new int[5][m];
        System.arraycopy(Alloc[2],0,T[0],0,m);
        System.arraycopy(Alloc[5],0,T[1],0,m);
        System.arraycopy(Alloc[7],0,T[2],0,m);
        System.arraycopy(Alloc[10],0,T[3],0,m);
        System.arraycopy(Alloc[12],0,T[4],0,m);

        System.out.println("Following is the Allocation Matrix: ");
        for (int i = 0; i < T.length; i++) {
            for (int j = 1; j < m; j++) {
                System.out.print(T[i][j] + " ");
            }
            System.out.println();
        }

        int[][] maxReq = {  {0, 7, 6, 8, 7, 8},
                            {0, 8, 7, 7, 10, 6},
                            {0, 5, 6, 5, 7, 7},
                            {0, 4, 5, 5, 5, 6},
                            {0, 6, 8, 9, 10, 10} };

        System.out.println("Following is the Maximum Requirement Matrix: ");
        for (int i = 0; i < maxReq.length; i++) {
            for (int j = 1; j < m; j++) {
                System.out.print(maxReq[i][j] + " ");
            }
            System.out.println();
        }

        int[] available = {0, 2, 2, 1, 2, 1};
        System.out.println("Following is the Available Matrix: ");
        for (int i = 0; i < available.length; i++) {
            System.out.print(available[i] + " ");
        }
        System.out.println();

        int[][] needMatrix = new int[n][m];
        List<Integer> safeSequence = new LinkedList();
        HashMap <Integer, int[]> availableResources = new HashMap<Integer, int[]>();
        boolean flag = true;

        for (int i = 0; i < T.length; i++) {

            for (int j = 1; j < m; j++) {
                needMatrix[i][j] = maxReq[i][j] - T[i][j];
                if (needMatrix[i][j] < 0) needMatrix[i][j] = 0;
                if (needMatrix[i][j] > available[j]) {
                    flag = false;
                }
            }
            if (flag) {
                for (int j = 1; j < m; j++) {
                    available[j] += T[i][j];
                }
                int[] temp = new int[available.length];
                for(int j = 1; j < m; j++) temp[j] = available[j];
                availableResources.put(i, temp);
                safeSequence.add(i);
            }
            flag = true;
        }
        System.out.println("Following is the Need Matrix: ");
        for (int i = 0; i < T.length; i++) {
            for (int j = 1; j < m; j++) {
                System.out.print(needMatrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Following is the Available Resources Matrix: ");
        int[] row1 = availableResources.get(0);
        for(int j = 1; j < m; j++) System.out.print(row1[j] + " ");
        System.out.println();
        int[] row2 = availableResources.get(1);
        for(int j = 1; j < m; j++) System.out.print(row2[j] + " ");
        System.out.println();
        int[] row3 = availableResources.get(2);
        for(int j = 1; j < m; j++) System.out.print(row3[j] + " ");
        System.out.println();
        int[] row4 = availableResources.get(3);
        for(int j = 1; j < m; j++) System.out.print(row4[j] + " ");
        System.out.println();
        int[] row5 = availableResources.get(4);
        for(int j = 1; j < m; j++) System.out.print(row5[j] + " ");
        System.out.println();

        System.out.println("Following is the Safe Sequence: ");
        for (int x: safeSequence) {
            System.out.print("T" + x);
            if (x != m - 2) 
                System.out.print(" -> ");
        }
    }
}
