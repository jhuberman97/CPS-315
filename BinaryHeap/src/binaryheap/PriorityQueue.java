//Jared Huberman
//N03842534
package binaryheap;
import java.util.ArrayList;
public class PriorityQueue {
    
    ArrayList<Integer> tree = new ArrayList();
    
    public void add(int i){
        tree.add(i);
        int curr = tree.size() - 1;
        if(curr == 0) return;
        while(i > tree.get(curr - 1)){
            tree.set(curr, tree.get(curr - 1));
            tree.set((curr - 1), i);
            curr = curr - 1;
            if(curr == 0) break;
        }
    }
    
    public int remove(){
        return tree.remove(0);
    }
    
    public boolean isEmpty(){
        return tree.isEmpty();
    }
    
    public void printBinaryTree(int index, int level){
        if(tree.isEmpty() || index >= tree.size())
            return;
        printBinaryTree(2*index + 2, level+1);
        if(level != 0){
            for(int i = 0; i < level - 1; i++)
                System.out.print("|\t");
            System.out.println("|-------"+"(" + tree.get(index) + ")");
        }
        else System.out.println("(" + tree.get(index) + ")");
        printBinaryTree(2*index + 1, level+1);
    }    
}