import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
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
        System.out.println(sizeOfGenericTree);
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
    public static void zigzagTraversal(Node node){
        Stack<Node> main=new Stack<>();
        Stack<Node> child=new Stack<>();
        main.push(node);
        while(main.size()!=0||child.size()!=0){
            Node temp=main.pop();
            System.out.print(temp.data+" , ");
            for(Node x:temp.children){
                child.push(x);
            }
            if(main.size()==0){
                System.out.println();
                main=child;
                child=new Stack<>();
            }
        }
    } 
    // check this once
    public static void mirrorNode1(Node root){
        Stack<Node> temp=new Stack<>();
        for(Node x:root.children){
            temp.push(x);
        }
        ArrayList<Node> child1=new ArrayList<>();
        while(temp.size()!=0){
            child1.add(temp.pop());
        }
        root.children=child1;
        for(Node x:root.children){
            mirrorNode1(x);
        }
    }
    public static void mirrorNode2(Node root){
        for(Node x:root.children){
            mirrorNode2(x);
        }
        Collections.reverse(root.children);
        // in arraylist you could directly apply the reversal from Collections
    }
    public static boolean removeLeafNode1(Node root){
        boolean inLoop=false;
        ArrayList<Node> temp=(ArrayList)root.children.clone();
        // so before this we tried two apporaches first one was directly making chnages in the root.children node 
        // the mistake in that was if leaf node was removed then the iteration for the next root would be skipped as it would move a index ahead.
        // then we tried ArrayList temp=root.children;
        // the same issue as here temp just refers to the same address as root.children
        // so we use clone so that temp refers to a new arraylist (copy of the original one) but the changes in the original does not affect it
        // although i think here the time and space complexity will be messed up as 
        // time complexity - O(N2) but in ideal case it should be O(N)
        for(Node x:temp){
            inLoop=true;
            boolean cLoop=removeLeafNode1(x);
            // System.out.println("For the value "+x.data+" the value of cloop is "+cLoop);
            if(cLoop==false){
                // System.out.println("Hello --> "+x.data);
                root.children.remove(x);

            }
        }
        return inLoop;
    }
    // The better apporach is the nect one 
    public static void removeLeafNode2(Node root){
        // we order the parent of each node to remove the childs 
        for(int i=root.children.size()-1;i>=0;i--){
            // from the last to qaavoid skipping any nodes here
            Node child=root.children.get(i);
            if(child.children.size()==0){
                // that means it is the leaf node
                root.children.remove(child);
            }
        }
        //the remove child part should come beofre otherwise it will also remove extra nodes which were not leaf earlier.. 
        for(Node x:root.children){
            removeLeafNode2(x);
        }
        // for(int i=root.children.size()-1;i>=0;i--){
        //     // from the last to avoid skipping any nodes here
        //     Node child=root.children.get(i);
        //     if(child.children.size()==0){
        //         // that means it is the leaf node
        //         root.children.remove(child);
        //     }
        // }
        // this is incorrect as it also removes the nodes that bacome leaf in the future
    }
    public static void linearize1(Node root){
       for(Node x:root.children){
         linearize1(x);
       }
    //    System.out.println(root.data);
        // now it reaches the leaf nodes first 
        while(root.children.size()>1){
            Node last=root.children.remove(root.children.size()-1);
            Node secondLast=root.children.get(root.children.size()-1);
            // secondLast.children.add(last);
            // System.out.println("1. -->"+secondLast.data);
            Node toBeConnected=getTail(secondLast);
            toBeConnected.children.add(last);
            // System.out.println(secondLast.children.get(0).data);

            // this will linearize all the nodes except the root node where it wont give us the structure we want.
        }
        // now everything before the root node has been linearized but to get the structure we want we have to connect every tail of second level height 
        // nodes to the first node of the second linearized branch
        //the only thing that has not been balanced here is rooot node so it can have size>1
    }
    private static Node getTail(Node root){
        while(root.children.size()==1){
            root=root.children.get(0);
        }
        return root;
        // System.out.println(root.data);
        // there is some fundamentma; mistake in the recursion happening here clear it and add to notes
    }
    public static void main(String[] args){
        // ArrayList<Node> n=new ArrayList<>();
        int[] arr={10,20,50,-1,60,-1,-1,30,70,-1,80,110,-1,120,-1,-1,90,-1,-1,40,100,-1,-1,-1};
        Node temp= construct(arr);
        // zigzagTraversal(temp);
        // Display(temp);
        // System.out.println(size1(temp,true));
        // System.out.println(size1(temp,true));
        // System.out.println(size2(temp));
        // int[] arr1={10,20,50,-1,60,-1,-1,70,-1,80,110,-1,120,-1,-1,40,100,-1,-1,-1};
        //   Node temp1= construct(arr);
        // // Display(temp1);
        // System.out.println(size1(temp1,true));
        // now calculating size without using boolean;
        // System.out.println(max1(temp));
        // System.out.println(height(temp));
        // traversals(temp);
        // levelOrderTraversal(temp);
        // System.out.println();
        // levelOrderTraversalLine(temp);
        // mirrorNode1(temp);
        // Display(temp);
        // boolean x=removeLeafNode1(temp);
        // removeLeafNode2(temp);
        linearize1(temp);
        Display(temp);
    }
}
// IMP - the only cases where these methods dont work is wheere you have not been provided any tree and for that
// you have to include an if statment of a null tee in every method.