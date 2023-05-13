public class LinkedList{ 
    
    public Node head;

    public LinkedList(){
        head = null;
    }
    
    public void insert(int c){
        Node a=new Node(c);
        //size+=1;
        if(head==null){head=a;}
        else{
            Node t=head;
            while(t.next!=null){t=t.next;}
            t.next=a;
        }
        //to be completed by the student
    }

    public int len(){
        int size=0;
        Node xx=head;
        while(xx!=null){
            size+=1;
            xx=xx.next;
        }
        return size;
    }
}


