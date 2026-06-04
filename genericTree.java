import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;

public class genericTree{
    private static class Node{
        int data;
        ArrayList<Node> children=new ArrayList<>();

        
    }
    public static void Display(Node parent){
        // first we display the element sand its childern
        String str=parent.data+" --> ";
        for(Node x:parent.children){
            str+=x.data+" , "; 
        }
        str+=" .";
        System.out.println(str);
        for(Node node:parent.children){
            Display(node);
        }
    }
    public static Node construct(int[] arr){
        Node root=null;
        Stack<Node> st=new Stack<>();
        for(int i=0;i<arr.length;i++){
            if(st.empty()){
                Node t=new Node();
                t.data=arr[i];
                root=t;
                st.push(t);
            }
            else{
                if(arr[i]==-1){
                    st.pop();
                    
                }
                else{
                    Node t=new Node();
                    t.data=arr[i];
                    st.peek().children.add(t);
                    st.push(t);
                }
            }
        }
        return root;
    }
    // static int does not set istself 0 when the method is called the second time , it still has the old intial value whihc you need to set at 0 through flag;
    static int sizeOfGenericTree=0;
    public static int size1(Node root,boolean reset){
        if(reset){
            sizeOfGenericTree=0;
            reset=false;
        }
        sizeOfGenericTree++;
        for(Node node:root.children){
            size1(node,reset);
        }
        return sizeOfGenericTree;
    }
    public static int size2(Node n){
        int s=0;
        for(Node node:n.children){
            int cs=size2(node);
            // cs represents every childrens s
            s=s+cs;
        }
        s=s+1;
        return s;
    }
    public static int max1(Node n){
         int max=n.data;
         for(Node node:n.children){
            int cs=max1(node);
            // System.out.println("Values of cs - "+cs);
            // cs represents every childrens s
            if(cs>max){
                max=cs;
            }
        }
        return max;
    }
    public static int height(Node n){
        // the logic here is very interesting
        // we are counting height from edges
        int ht=-1;
        for(Node node:n.children){
            int ch=height(node);
            ht=Math.max(ht,ch);
        }
        ht++;
        // ht++ is very important here as it makes the the change in height for the current node adding 1 to it.
        // therefore making the height of that node from the deepest leaf that it has to offer accurate as
        // this +1 considers the edge between the parent node at that instance to the child node at that instance.
        return ht;
    }
    // max through pep kcoding
    // uploading it on git with the nessecary info
    // height counting and 3 more problems
    public static void traversals(Node node){
        System.out.println("Node Pre "+node.data);
        int parentData=node.data;
        for(Node n:node.children){
            System.out.println("Edge Pre "+parentData+" --> "+n.data);
            // System.out.println("Pre Node "+n.data);
            traversals(n);
            System.out.println("Edge Post "+parentData+" --> "+n.data);
        }
        // now the post part of it
        System.out.println("Node Post "+node.data);
    }
    public static void levelOrderTraversal(Node node){
        Queue<Node> q=new ArrayDeque<>();
        q.add(node);
        // adding the root node
        // System.out.println();
        while(!q.isEmpty()){
            Node val=q.remove();
            System.out.print(val.data+" ");
            for(Node x:val.children){
                q.add(x);
            }
        }
        System.out.print(".");
    }
    public static void levelOrderTraversalLine(Node node){
        Queue<Node> parent=new ArrayDeque<>();
        Queue<Node> child=new ArrayDeque<>();
        parent.add(node);
        while(!parent.isEmpty()||!child.isEmpty()){
            Node val=parent.remove();
            System.out.print(val.data+" ");
            for(Node x:val.children){
                child.add(x);
            }
            if(parent.isEmpty()){
                System.out.println();
                // parent.addAll(child);
                // child.clear();
                // or 
                parent=child;
                child=new ArrayDeque<>();
                // the one above or is comparatively worse option as addAll has time compleixity of O(k)
                // whereas parent=child makes it just address allocation which makes the complexity as O(1)
            }
        }
        // System.out.print(".");
    }
    public static void main(String[] args){
        // ArrayList<Node> n=new ArrayList<>();
        int[] arr={10,20,-1,30,50,-1,60,-1,-1,40,-1,-1};
        Node temp= construct(arr);
        Display(temp);
        System.out.println(size1(temp,true));
        System.out.println(size2(temp));
        // int[] arr1={10,20,50,-1,60,-1,-1,70,-1,80,110,-1,120,-1,-1,40,100,-1,-1,-1};
        //   Node temp1= construct(arr);
        // // Display(temp1);
        // System.out.println(size1(temp1,true));
        // now calculating size without using boolean;
        System.out.println(max1(temp));
        System.out.println(height(temp));
        // traversals(temp);
        levelOrderTraversal(temp);
        System.out.println();
        levelOrderTraversalLine(temp);
    }
}
// IMP - the only cases where these methods dont work is wheere you have not been provided any tree and for that
// you have to include an if statment of a null tee in every method.