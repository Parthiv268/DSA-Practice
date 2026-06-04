import java.util.ArrayList;
import java.util.Stack;

class main{
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
    // max through pep kcoding
    // uploading it on git with the nessecary info
    // height counting and 3 more problems
    public static void main(String[] args){
        // ArrayList<Node> n=new ArrayList<>();
        int[] arr={10,20,50,-1,60,-1,-1,70,-1,80,110,-1,120,-1,-1,40,100,-1,-1,-1};
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
    }
}