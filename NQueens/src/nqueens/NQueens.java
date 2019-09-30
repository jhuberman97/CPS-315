//Jared Huberman
//CSC 315 HW 2
//N03842534
package nqueens;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
public class NQueens {

    int filled = 0;
    Stack<Point> s = new Stack();
    ArrayList<Point[]> solutions = new ArrayList();
        
    private boolean isSafe(Point check, int n) {
        for(Point p : s){
            //row
            if(check.x == p.x && check.y != p.y)
                return false;
            //col
            if(check.y == p.y && check.x != p.x)
                return false;
            int i,j;
            //lower left diag
            for(i = check.x, j = check.y; i >= 0 && j >= 0; i--, j--){
                if(p.x == i && p.y == j && p.x != check.x)
                    return false;
            }
            //upper left diag
            for(i = check.x, j = check.y; i >= 0 && j < n; i--, j++){
                if(p.x == i && p.y == j && p.x != check.x)
                    return false;
            }
            //lower right diag
            for(i = check.x, j = check.y; i < n && j >= 0; i++, j--){
                if(p.x == i && p.y == j && p.x != check.x)
                    return false;
            }
            //upper right diag
            for(i = check.x, j = check.y; i < n && j < n; i++, j++){
                if(p.x == i && p.y == j && p.x != check.x)
                    return false;
            }
        }
        return true;
    }
    
    public void solve(int n){
        outerloop:
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                Point newPoint = new Point(i,j);
                s.push(newPoint);
                filled++;
                if(!isSafe(newPoint, n)){
                    s.pop();
                    filled--;
                }
                if(filled == n){
                    solutions.add(s.toArray(new Point[n]));
                    s.pop();
                    filled--;
                    continue;
                }
            }
            boolean rowUsed = false;
            for(Point p : s){
                if(p.x == i) rowUsed = true;
            }
            while(filled < i + 1 && !rowUsed){
                if(s.isEmpty()) break outerloop;
                Point old = s.pop();
                filled--;
                Point backtrack = new Point(old.x, old.y + 1);
                if(backtrack.y >= n){
                    i--;
                    continue;
                }
                while(!isSafe(backtrack, n) && backtrack.y < n - 1){
                    backtrack = new Point(backtrack.x, backtrack.y + 1);
                }
                if(isSafe(backtrack, n)){
                    s.push(backtrack);
                    filled++;
                }
                i--;
            }
        }
        
        int count = 1;
        for(Point[] solution : solutions){
            System.out.println("Solution " + count);
            for(Point p : solution){
                System.out.println("Row " + (p.x) + ", Column " + (p.y));
            }
            System.out.println();
            count++;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Enter a value n for n-queens:");
        Scanner sc = new Scanner(System.in);
        new NQueens().solve(sc.nextInt());
    }

    
}
