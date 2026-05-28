public class main{
   static public class Node{
     int data;
     Node next;
   } 
   static public class LinkedList{
     Node head;
     Node tail;
     int size;
     void addLast(int val){
        Node temp=new Node(); 
        temp.data=val;
        temp.next=null;
        if(head==null){
            head=temp;
            tail=temp;
        }
        else{
            // you just need to passon tail
            tail.next=temp;
            tail=temp;
        }
        size++;
     }
     public int getSize(){
        return size;
     }
     void display(){
        Node temp=head;
        if(temp==null){
            System.out.println("The list is empty");
        }
        else{
            while(temp!=null){
                System.out.println("The value is "+temp.data);
                temp=temp.next;
            }
        }
     }
     void removeFirst(){
        if(head==null){
            System.out.println("The list is empty");
        }
        else if(head.next==null){
            Node temp=head;
            head=tail=null;
            temp=null;
            // is writing temp usefule here?
            size--;
        }
        else{
            Node temp=head;
            head=temp.next;
            temp.next=null;
            temp=null;
            size--;
        }
     }
     public int getFirst(){
        if(size==0){
            System.out.println("The list is empty");
            return -1;
        }
        else{
            return head.data;
        }
     }
     public int getLast(){
        if(size==0){
            System.out.println("The list is empty");
            return -1;
        }
        else{
            return tail.data;
        }
     }
    public int getAt(int idx){
        if(size==0){
            System.out.println("The list is empty");
            return -1;
        }
        else if(idx<0||idx>=size){
            System.out.println("Invalid index");
            return -1;
        }
        else{
            int count=0;
            Node temp=head;
            while(count<idx){
                temp=temp.next;
                count++;
            }
            return temp.data;
        }
    }
    
    public void addFirst(int val){
        Node temp=new Node();
        temp.data=val;
        if(head==null){
            head=tail=temp;
        }
        else{
            temp.next=head;
            head=temp;
             
        }
        size++;
    }
    public void addAt(int idx,int val){
        if(idx<0||idx>size){
            System.out.println("Invalid argument");
        }
        else{
            Node temp=new Node();
            temp.data=val;
            size++;
            if(idx==0){
                temp.next=head;
                head=temp;
            }
            else if(idx==size){
                tail.next=temp;
                tail=temp;
            }
            else{
                int count=0;
                Node temp1=head;
                while(count<idx-1){
                    temp1=temp1.next;
                    count++;
                }
                temp.next=temp1.next;
                temp1.next=temp;
            }
        }
    }
    public void removeLast(){
        if(size==0){
            System.out.println("List is already empty");
        }
        else if(size==1){
            head=tail=null;
            size--;
        }
        else{
            Node temp=head;
            while(temp.next!=tail){
                temp=temp.next;
            }
            temp.next=null;
            tail=null;
            tail=temp;
            size--;
        }
    }
    public void removeAt(int idx){
        if(size==0||idx>size-1){
            System.out.println("The list is empty");
        }
        else if(idx==0){
            removeFirst();
            size--;
        }
        else if(idx==size-1){
            removeLast();
            size--;
        }
        else{
            Node temp=head;
            int count=0;
            while(count<idx-1){
                temp=temp.next;
                count++;
            }
            Node del=temp.next;
            // representing the node to be deleted
            temp.next=del.next;
            del.next=null;
            del=null;
            size--;
        }
    }
    public LinkedList reverseData(LinkedList l1){
        if(size==0){
            System.out.println("The linked list is empty");
            return null;
        }
        else{
            int start=0;
            int end=size-1;
            Node startNode=head;
            Node endNode=head;
            while(start<end){
                endNode=head;
                // for reset after every inner iteration
                int count=0;
                while(count!=end){
                    endNode=endNode.next;
                    count++;
                }
                // now exchanging the two values between start and end
                int temp=startNode.data;
                startNode.data=endNode.data;
                endNode.data=temp;
                startNode=startNode.next;
                start++;
                end--;
            }
            return l1;
        }
    }
    public LinkedList reversePointer(LinkedList l1){
        if(size==0){
            System.out.println("The list is empty");
            return null;
        }
        else{
            Node prev=null;
            Node current=head;
            tail=head;
            Node temp=null;
            // I also have to set head and tail here
            do {
                temp=current.next;
                if(temp==null){
                    head=current;
                }
                current.next=prev;
                prev=current;
                current=temp;
            } while (temp!=null);
            return l1;
        }
    }
    public int KElementFromTheend(int k){
        // if we want to do it without using size and in single itertions then we need to reverse it 
        LinkedList l1=reversePointer(this);
        int val=-1;
        if(l1!=null){
            if(k==0||k<0){
                System.out.println("Enter a valid k");
                return -1;
            }
            Node temp=l1.head;
            for(int i=0;i<k-1;i++){
                temp=temp.next;
            }
            val=temp.data;
        }
        else{
            System.out.println("List is empty");
            val=-1;
        }
        return val;
    }
    public int KElementFromTheend1(int k){
        // if we want to do it without using size and in single itertions then we need to reverse it 
        if(k==0||k>size){
            System.out.println("Enter a valid index");
            return -1;
        }
        int val=-1;
        Node slow=head;
        Node fast=head;
        for(int i=0;i<k-1;i++){
            fast=fast.next;
        }
        while(fast.next!=null){
            slow=slow.next;
            fast=fast.next;
        }
        val=slow.data;
        return val;
        // 10,20,30,40,50,60,70
    }
    public LinkedList mergeSortTwoLL(LinkedList L1,LinkedList L2){
        LinkedList L3=new LinkedList();
        Node temp1=L1.head;
        Node temp2=L2.head;
        while(temp1!=null&&temp2!=null){
            if(temp1.data<temp2.data){
                L3.addLast(temp1.data);
                temp1=temp1.next;
            }
            else{
                L3.addLast(temp2.data);
                temp2=temp2.next;
            }
        }
        while(temp1!=null){
            L3.addLast(temp1.data);
            temp1=temp1.next;
        }
        while(temp2!=null){
            L3.addLast(temp2.data);
            temp2=temp2.next;
        }
        return L3;
    }
    public static Node middleLL(Node Head,Node Tail){
        Node slow=Head;
        Node fast=Head;
        while(fast!=Tail&&fast.next!=Tail){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }
    public static LinkedList mergeSort(Node Head,Node Tail){
        // using recursion as we haave two methods already with us that promote it.
        if(Head==Tail){
            LinkedList l1=new LinkedList();
            l1.addLast(Head.data);
            return l1;
        }
        Node middleNode=middleLL(Head, Tail);
        LinkedList leftList=mergeSort(Head,middleNode);
        LinkedList rightList=mergeSort(middleNode.next,Tail);
        leftList=leftList.mergeSortTwoLL(leftList, rightList);
        return leftList;
        // one of the best questions out there include the recursion in notes as well as the iterations
    }
    public void removeDuplicateSorted(){
        // 1->1->2->2->2->3->3->3->4
        if(size==1){
            System.out.println("Size is 1 so no duplicates in the LL");
        }
        Node temp=head;
        int count=0;
        while(temp.next!=null){
            if(temp.data==temp.next.data){
                temp=temp.next;
                // System.out.println("Hello I removed the following indexes - "+count);
                removeAt(count);
                // but this approach is O(n2)

            }
            else{
            temp=temp.next;
            count++;
            }
            // count++;
        }
    }
    public void removeDuplicateSorted2(){
        Node one=head;
        while(one.next!=null){
           Node two=one.next;
           if(one.data==two.data){
            two.next=null;
            two=one.next.next;
            one.next=two;
           }
           else{
            one=one.next;
           }
        //    if the conditions include O(n) time complexity and constant space complexity it si mostly the pointer approach in linked list
        // also especially in the questions where we need to at specific place in certai linked lists.
        // or you can create a new linked list and do the same but remeber while using this and new linked list as res
        // then you cannot write this=res; as this is a read only function and so for the linked list to get its value from res you need to do the following - 
        // this.Head=res.Head;
        // this.Tail=res.Tail;
        // this.size=res.size;
        }
        
    }
    public void oddEven(){
        // method of creating two lists
        LinkedList odd=new LinkedList();
        LinkedList even=new LinkedList();
        Node temp=head;
        while(temp!=null){
            int val=temp.data;
            Node del=temp;
            temp=temp.next;
            del.next=null;
            del=null;
            if(val%2==0){
                even.addLast(val);
            }
            else{
                odd.addLast(val);
            }
            
        }
        // as odd should be the starting point of the linked list.
        if(odd.head!=null&&even.head!=null){
            odd.tail.next=even.head;
            this.head=odd.head;
            this.tail=even.tail;
            this.size=odd.size+even.size;
        }
        else if(odd.head!=null){
            this.head=odd.head;
            this.tail=even.tail;
            this.size=odd.size+even.size;
        }
        else if(even.head!=null){
            this.head=even.head;
            this.tail=even.tail;
            this.size=odd.size+even.size;
        }
        else{
            System.out.println("The list is empty");
        }
        
    }
    public void KreverseLL(int k){
        Node current=head;
        Node prev=null;
        Node dummy=new Node();
        dummy.next=head;
        head=dummy;
        // size++;
        int x=(size%k);
        // x gives us the noed thaat we do not need to arrange
        int countTillLRev=size-x;
        // these represents the nodes that needs to be reversed and therefore even though we are adding a dummy node the size should remain the same.
        Node prevTail=head;
        Node currHead=dummy.next;
        int val=k;
        while(countTillLRev>0){
            Node temp=current.next;
            current.next=prev;
            prev=current;
            current=temp;
            countTillLRev--;
            k--;
            if(k==0){
                prevTail.next=prev;
                prevTail=currHead;
                currHead.next=current;
                currHead=current;
                prev=null;
                k=val;
            }
        }
        // now setting up head and tail for that
        head=dummy.next;
        // removing the dummy node
        dummy.next=null;
        dummy=null;
        if(currHead==null){
            tail=prevTail;
        }
    }
    // displaying reverse using recursion
    private void displayReverseHelper(Node node){
        //
        if(node==null){
            return;
        }
        displayReverseHelper(node.next);
        System.out.println(node.data+"  "); 
    }
    public void displayReverse(){
        // the way you want , backtracking should happen
        displayReverseHelper(head);
        // System.out.println(head.data);
        System.out.println("Printing the Linked List in rverse using recursion.");
    }
    private void reversePRHelper(Node node){
        if(node.next==null){
            return;
        }
        reversePRHelper(node.next);
        node.next.next=node;
    }
    public void reversePR(){
        reversePRHelper(head);
        head.next=null;
        // this is the most importannt one here as we need to do head.next=null to break an infinite chain between the first and second node 
        // caused by the reverseHelper , run the backtracking and you will see it becomes 10<->20<-30<-40<-50<-60
        Node temp=tail;
        tail=head;
        head=temp;
        // System.out.println(head.next.next.data);
        // this proves reverse to ho rha hai
        // as this Linked list can be used in further methods so head and tail should be nmaintained clearly
    }
    public boolean chkPallindrome(){
        Node one=head;
        // Node two=middleLL(head, tail);
        boolean chk=true;
        if(one==null){
            System.out.println("The Linked List is empty");
            return false;
        }
        else if(one.next==null){
            return true;
        }
        else{
            Node slow=head;
            Node fast=head;
            while(fast!=null&&fast.next!=null){
                fast=fast.next.next;
                slow=slow.next;
            }
            // slow represents the head of the new nod enow reversing it
            // reversing the linked list
            Node current=slow;
            Node prev=null;
            while(current!=null){
                Node temp=current.next;
                current.next=prev;
                prev=current;
                current=temp;
            }
            Node left=head;
            Node right=tail;
            while(right!=null){
                if(left.data!=right.data){
                    return false;
                }
                left=left.next;
                right=right.next;
            }
        }
        return chk;   
     }
     private void pallindromeHelper(Node right){
        if(right==null){
            return;
        }
        pallindromeHelper(right.next);
        if(right.data!=pleft.data){
            pcheck=false;
        }
        pleft=pleft.next;
        // if(left==right||left.next==right){
        //     pflag=false;
        //     // so that it stops checking midway
        // }

     }
     Node pleft;
     boolean pcheck;
     boolean pflag;
     public boolean chkPallindrome2(){
        pflag=true;
        pcheck=true;
        Node pright=head;
        pleft=head;
        pallindromeHelper(pright);
        return pcheck;
     }
     public LinkedList foldLinkedList(){
        if(size>2){
        LinkedList n=new LinkedList();
        n.head=head;
        n.tail=tail;
        Node slow=n.head;
        Node fast=n.head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        // Now we will reverse the second half portion of the linked list
        Node prev=null;
        Node current=slow;
        while(current!=null){
            Node temp=current.next;
            current.next=prev;
            prev=current;
            current=temp;
        }
        Node left=n.head;
        Node right=n.tail;
        while(right!=null){
            Node nextLeft=left.next;
            Node nextRight=right.next;
            left.next=right;
            if (right!=nextLeft){
            right.next=nextLeft;
            // otherwise it forms an self iterating loop at the end which is never corrected
            }

            left=nextLeft;
            if(left!=null){
                n.tail=left;
            }
            right=nextRight;
        }
        return n;
      }
      else{
        System.out.println("Fold not possible for this linked list.So retuening the original one.");
      }
      return this;
     }
     private void foldHelper(Node n){

        if(n==null){
            return;
        }

        foldHelper(n.next);

        // STOP further processing
        if(fflag){
            return;
        }

        // midpoint reached
        if(n==left || left.next==n){
            tail=n;
            tail.next=null;

            fflag=true;   // stop all upper calls
            return;
        }

        Node temp=left.next;
        left.next=n;
        n.next=temp;
        left=temp;
    }
     Node left;
     boolean fflag;
     public LinkedList foldLinkedList2(){
        if(size<=2){
            System.out.println("LinkedList cannot be folded");
        }
        else{
            LinkedList x=this;
            Node right=x.head;
            left=x.head;
            fflag=false;
            foldHelper(right);
            return x;
        }
        return this;
     }
     int sumCarry=0;
     int s=0;
     LinkedList sum;
     private void sumHelper(Node temp1,Node temp2,int diff){
        if(temp1==null){
            return;
        }
        if(diff>0){
            diff--;
            sumHelper(temp1.next,temp2,diff);    
        }
        // if we just use if it shows error as then for diff==0 is shows(7,null.next,0) which
        // is invalid for temp2
        else if(diff==0){
            sumHelper(temp1.next,temp2.next,diff);
        }
        // here else if is necessary as 
        // all calss completed now the process after backtracking
        if(diff==0){
            s=temp1.data+temp2.data+sumCarry;
            sum.addFirst(s%10);
            sumCarry=s/10;
        }
        else{
            s=temp1.data+sumCarry;
            sum.addFirst(s%10);
            sumCarry=s/10;
        }

     }
     public LinkedList sumThroughRecursion(LinkedList l1,LinkedList l2){
        sum=new LinkedList();
        Node dummy=new Node();
        dummy.data=0;
        if(l1.size>l2.size){
            dummy.next=l2.head;
            sumHelper(l1.head,dummy,l1.size-l2.size-1);
        }   
        else if(l2.size>l1.size){
            dummy.next=l1.head;
            sumHelper(l2.head,dummy,l2.size-l1.size-1);
        }
        else{
            sumHelper(l2.head,l1.head,l2.size-l1.size);

        }
        return sum;
     }
     public LinkedList sumThroughReversal(LinkedList l1,LinkedList l2){
        LinkedList sum=new LinkedList();
        int s=0;
        int carry=0;
        // now reversing both the linked list first
        LinkedList rl1=l1.reversePointer(l1);
        // only reversePointer(l1) will not work as 'this' will not refer to anything but the linked list object 
        // in general being treated to so if you do not want to writ l1.reversePointer(l1) then use current=l1.head
        LinkedList rl2=l2.reversePointer(l2);
        // now simpleaaddition
        Node temp1=rl1.head;
        Node temp2=rl2.head;
        while(temp1!=null&&temp2!=null){
            s=temp1.data+temp2.data+carry;
            // System.out.println("The value of s "+s);
            sum.addFirst(s%10);
            carry=s/10;
            temp1=temp1.next;
            temp2=temp2.next;
        }
        while(temp1!=null){
            s=temp1.data+carry;
            sum.addFirst(s%10);
            carry=s/10;
            temp1=temp1.next;
        }
        while(temp2!=null){
            s=temp2.data+carry;
            sum.addFirst(s%10);
            carry=s/10;
            temp2=temp2.next;
        }
        return sum;
     }
   }
   public static class LLtoStackAdapter{
    LinkedList list;
    public LLtoStackAdapter(){
        list=new LinkedList();
    }
    int size(){
        return list.size;
    }
    void push(int val){
        list.addLast(val);
    }
    int pop(){
       int value=-1;
       if(list.size==0){
            System.out.println("List is already empty");
            value=-1;
        }
        else if(list.size==1){
            value=list.head.data;
            list.head=list.tail=null;
            list.size--;
        }
        else{
            Node temp=list.head;
            while(temp.next!=list.tail){
                temp=temp.next;
            }
            value=list.tail.data;
            temp.next=null;
            list.tail=null;
            list.tail=temp;
            list.size--;
        }
        return value;
    }
    int top(){
        return list.tail.data;
    }

   }
   public static void main(String[] args) {
    LinkedList ll=new LinkedList();
    // // ll.addLast(10);
    // // ll.addLast(20);
    // // ll.addLast(30);
    // // ll.addLast(40);
    // // ll.addLast(50);
    // // System.out.println(ll.getFirst());
    // // System.out.println(ll.getLast());
    // // System.out.println(ll.getAt(19));
    // // System.out.println(ll.getAt(2));
    // // ll.addFirst(0);
    // // ll.addFirst(20);
    // // // ll.display();
    // // ll.addAt(0,15);
    // // ll.display();
    // ll.addLast(0);
    // ll.addLast(10);
    // ll.addLast(20);
    // ll.addLast(30);
    // ll.addLast(40);
    // ll.addLast(50);
    // // ll.removeLast();
    // // ll.removeLast();
    // // ll.display();
    // // System.out.println(ll.size);
    // // ll.removeLast();
    // // ll.removeLast();
    // // System.out.println(ll.size);
    // // ll.removeAt(2);
    // // ll.display();
    // // System.out.println(ll.size);
    // // ll.removeAt(1);
    // // ll.display();
    // // System.out.println(ll.size);
    // // LinkedList l1=ll.reverseData(ll);
    // LinkedList l1=ll.reverseData(ll);
    // if(l1==null){
    //     System.out.println("----");
    // }
    // // l1.display();
    // else{l1.display();}
    // LLtoStackAdapter stack=new LLtoStackAdapter();
    // stack.push(0);
    // stack.push(10);
    // stack.push(20);
    // stack.push(30);
    // System.out.println(stack.size());
    // System.out.println(stack.top());
    // System.out.println(stack.pop());
    // System.out.println(stack.top());
    // System.out.println(stack.size());
    // ll.addLast(0);
    // ll.addLast(10);
    // ll.addLast(20);
    // ll.addLast(30);
    // ll.addLast(40);
    // ll.addLast(50);
    // LinkedList l2=new LinkedList();
    // l2.addLast(-1);
    // l2.addLast(15);
    // l2.addLast(25);

    // System.out.println(ll.KElementFromTheend(2));
    // dont run both of them simultaneously as the the first one reverses the oringinal linked list.
    // System.out.println(ll.KElementFromTheend1(0));

    // LinkedList L3=ll.mergeSortTwoLL(ll, l2);
    // L3.display();
    LinkedList l1=new LinkedList();
    // l1.addLast(2);
    // l1.addLast(7);
    // l1.addLast(1);
    // l1.addLast(6);
    // l1.addLast(5);
    // l1.addLast(3);
    // l1.addLast(4);
    // Node x=l1.middleLL(l1.head,l1.tail);
    // System.out.println(x.data);
    // LinkedList x=l1.mergeSort(l1.head, l1.tail);
    // x.display();
    // l1.addLast(0);
    // l1.addLast(0);
    // l1.addLast(2);
    // l1.addLast(2);
    // l1.addLast(2);
    // l1.addLast(2);
    // l1.addLast(2);
    // l1.addLast(6);
    // l1.addLast(6);
    // l1.addLast(6);
    // l1.addLast(-2);
    // l1.removeDuplicateSorted();
    // l1.oddEven();
    // l1.display();
    // l1.addLast(0);
    // l1.addLast(10);
    // l1.addLast(20);
    // l1.addLast(30);
    // l1.addLast(40);
    // l1.addLast(50);
    // l1.addLast(60);
    // l1.addLast(70);
    // l1.KreverseLL(3);
    // l1.addFirst(-10);
    // l1.addLast(80);
    // l1.display();
    // l1.displayReverse();
    // l1.reversePR();
    // l1.display();
    // l1.addLast(1);
    // l1.addLast(2);
    // l1.addLast(3);
    // l1.addLast(4);
    // l1.addLast(3);
    // l1.addLast(2);
    // l1.addLast(1);
    // l1.addLast(5);
    // System.out.println(l1.chkPallindrome());
    // do mind that check pallindrome also alters the original linked list.
    // Now folding the linked list.
    // System.out.println(l1.chkPallindrome2());
    // LinkedList x=l1.foldLinkedList2();
    // x.display();
    // apply this recursion on pallindrome and 
    // in recursion if something is going in an infinite loop simply means there is a problem in link when 
    // backtracking
    // performing addition through recursion is atualy quite interrssting 
    // let use see if our sum problem through recursion runs
    LinkedList l2=new LinkedList();
    l1.addLast(1);
    l1.addLast(2);
    l1.addLast(3);
    l1.addLast(7);
    l2.addLast(4);
    l2.addLast(5);
    l2.addLast(0);
    // l2.addLast(0);
    // l2.addLast(0);
    // l2.addLast(0);
    // LinkedList s=l1.sumThroughRecursion(l1, l2);
    // s.display();
    // all the cases workk herre so its good 
    LinkedList s=l1.sumThroughReversal(l1, l2);
    s.display();
   }
}
