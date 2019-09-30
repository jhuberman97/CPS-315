//Jared Huberman
//N03842534
package expressiontree;

public class Node {
    
    Node left;
    Node right;
    String val;
    
    public Node(String v, Node l, Node r){
        val = v;
        left = l;
        right = r;
    }
    
    public void printInfix(){
        String data = val;
        if(!isNumeric(data)) 
            System.out.print("(");
        if(left != null) left.printInfix();
        System.out.print(val);
        if(right != null) right.printInfix();
        if(!isNumeric(data)) 
            System.out.print(")");
    }
    
    public int treeEval(){
        String data = val;
            switch(data){
                case "!":
                    if(right.treeEval() == 1)
                        return 0;
                    else if(right.treeEval() == 0)
                        return 1;
                    //break;
                case "^":
                    return (int)Math.pow(left.treeEval(), right.treeEval());
                case "*": 
                    return left.treeEval() * right.treeEval();
                case "/": 
                    return left.treeEval() / right.treeEval();
                case "%":
                    return left.treeEval() % right.treeEval();
                case "+": 
                    return left.treeEval() + right.treeEval();
                case "-":
                    return left.treeEval() - right.treeEval();
                case "<": 
                    if(left.treeEval() < right.treeEval())
                        return 1;
                    else return 0;
                case "<=": 
                    if(left.treeEval() <= right.treeEval())
                        return 1;
                    else return 0;
                case ">": 
                    if(left.treeEval() > right.treeEval())
                        return 1;
                    else return 0;
                case ">=":
                    if(left.treeEval() >= right.treeEval())
                        return 1;
                    else return 0;
                case "==": 
                    if(left.treeEval() == right.treeEval())
                        return 1;
                    else return 0;
                case "!=":
                    if(left.treeEval() != right.treeEval())
                        return 1;
                    else return 0;
                case "&&":
                    if(left.treeEval() == 1 && right.treeEval() == 1)
                        return 1;
                    else return 0;
                case "||":
                    if(left.treeEval() == 1 || right.treeEval() == 1)
                        return 1;
                    else return 0;
                default:
                    return Integer.parseInt(val);
            }
    }
    
    public static void printBinaryTree(Node root, int level){
        if(root == null)
            return;
        printBinaryTree(root.right, level+1);
        if(level != 0){
            for(int i = 0; i < level - 1; i++)
                System.out.print("|\t");
            System.out.println("|-------"+"(" + root.val + ")");
        }
        else System.out.println("(" + root.val + ")");
        printBinaryTree(root.left, level+1);
}    
    
    public static boolean isNumeric(String s){  
        try{  
            int i = Integer.parseInt(s);  
        }  
        catch(NumberFormatException nfe){  
            return false;  
        }  
        return true;  
    }
}
