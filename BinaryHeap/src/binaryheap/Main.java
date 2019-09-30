//Jared Huberman
//N03842534
package binaryheap;
import java.util.Scanner;
public class Main {
    
    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Adding 20 random integers to the heap:\n");
        for(int i = 0; i < 20; i++){
            pq.add((int)(Math.random() * 1000));
            pq.printBinaryTree(0, 0);
            System.out.println();
            System.out.println("Press <ENTER> to continue...");
            sc.nextLine();
        }
        
        System.out.println("Removing elements from the heap one by one:\n");
        do{
            pq.remove();
            pq.printBinaryTree(0, 0);
            System.out.println("Press <ENTER> to continue...");
            sc.nextLine();
        }while(!pq.isEmpty());
    }
}