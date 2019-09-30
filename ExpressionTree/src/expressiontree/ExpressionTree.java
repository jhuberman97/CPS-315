//Jared Huberman
//N03842534
package expressiontree;

import java.io.*;
import java.util.*;
public class ExpressionTree{
    public static void main(String[] args) throws FileNotFoundException{
        
        Node first, second;
        Scanner sc = new Scanner(new File("assign5-in.dat"));
        
        while(sc.hasNextLine()){
            Stack<Node> s = new Stack<Node>();
            String line = sc.nextLine();
            System.out.println("Postfix:\n" + line + "\n");
            StringTokenizer tokens = new StringTokenizer(line, " ");
            
            while (tokens.hasMoreTokens()){
                String token = tokens.nextToken();
                switch(token){
                    case "!":
                        Node n = s.pop();
                        s.push(new Node(token, null, n));
                        break;
                    case "^": case "*": case "/": case "%": case "+": case "-":
                        case "<": case "<=": case ">": case ">=": case "==": 
                        case "!=": case "&&": case "||":
                            second = s.pop();
                            first = s.pop();
                            s.push(new Node(token, first, second));
                            break;
                    case "$":
                        tokens.nextToken();
                        continue;
                    default:
                        s.push(new Node(token, null, null));
                        break;
                }
            }
            
            Node root = s.firstElement();
            System.out.println("Tree diagram:");
            Node.printBinaryTree(root, 0);
            
            System.out.print("\nFully parenthesized infix:\n");
            root.printInfix();
            
            System.out.println("\n\nExpression value: " + root.treeEval());
            
            if(sc.hasNext()){
                System.out.println("\nPress <Enter> to Continue...");
                new Scanner(System.in).nextLine();
            }
            System.out.println();
        }
    }   
}
