package lw04.prelab;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt"));
        int N = sc.nextInt();
        int [] stabilityValues = new int[N];
    
        for (int i = 0; i < N; i++) {
            stabilityValues[i] = sc.nextInt();
        }

        int M = sc.nextInt();
        int[] targetStability = new int[M];
        for (int i = 0; i < M; i++) {
            targetStability[i] = sc.nextInt();
        }
        sc.close();

        for(int i=1; i <= 3; i++){
            int key = stabilityValues[i];
            int j = i - 1;

            while (j >= 0 && stabilityValues[j] < key) {
                stabilityValues[j + 1] = stabilityValues[j];
                j--;
            }
            stabilityValues[j + 1] = key;
        }
            for(int i = 0; i < N; i++){
                if (i > 0) {
                    System.out.print(" ");
                }
                System.out.print(stabilityValues[i]);
            }
            System.out.println();
          
            for(int i = 0; i < M; i++){
                int index = -1; 

                for(int j = 0; j < N; j++){
                    if(stabilityValues[j] == targetStability[i]){
                        index = j;
                        break;
                    }
                }
  
                if(index != -1){
                    System.out.println(targetStability[i] + ": FOUND at index " + index);
                } else {
                    System.out.println(targetStability[i] + ": NOT FOUND " );
                }
            } 
        
    }
}
