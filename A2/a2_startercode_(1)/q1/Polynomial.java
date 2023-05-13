public class Polynomial extends LinkedList{
    
    public Polynomial add(Polynomial p){
        if(this.head==null){
            Node temp=p.head;
            this.head=temp;
        }
        else{
            int size_add=this.len();
            int size_p=p.len();
            if(size_add>size_p){
                Node add_node=this.head;
                Node p_node=p.head;
                while(size_add!=size_p){add_node=add_node.next; size_add--;}
                while(add_node!=null && p_node!=null){
                    add_node.data=add_node.data + p_node.data;
                    p_node=p_node.next;
                    add_node=add_node.next;
                }
            }
            else if(size_p>size_add){
                Node add_node=this.head;
                Node p_node=p.head;
                while(size_add!=size_p){p_node=p_node.next; size_p--;}
                while(add_node!=null && p_node!=null){
                    p_node.data=p_node.data + add_node.data;
                    add_node=add_node.next;
                    p_node=p_node.next;
                }
            }
            else{
                Node add_node=this.head;
                Node p_node=p.head;
                //while(size_add!=size_p){sec=sec.next; size_p--;}
                while(add_node!=null && p_node!=null){
                    p_node.data=add_node.data + p_node.data;
                    add_node=add_node.next;
                    p_node=p_node.next;
                }
            }
        }
        //this.head=p.head;
        if(this.len()>p.len()){
            while(this.head.data==0 && this.head.next!=null){
                this.head=this.head.next;
            }
            return this;
        }
        else{
            while(p.head.data==0 && p.head.next!=null){
                p.head=p.head.next;
            }
            return p;
        }
    }

    public Polynomial mult(Polynomial p){
        if(this.head==null){
            this.head=p.head;
        }
        else{
            int m=p.len();
            int n=this.len();
            //new
            int[] change_it=new int[m+n-1];
            for (int i=0; i<m+n-1; i++){
                change_it[i]=0;
            }
            Node s=p.head;
            for (int i=0; i<m; i++){
                Node a=this.head;
                for(int j=0; j<n; j++){
                    change_it[i+j]+=s.data*a.data;
                    a=a.next;
                }
                s=s.next;
            }
            //end
            LinkedList ans=new LinkedList();
            for (int i = 0; i < m + n - 1; i++){
                ans.insert(change_it[i]);
            }
            p.head=ans.head;
        }
        //this.head=p.head;
        //Node check=p.head;
        while(p.head.data==0 && p.head.next!=null){
            p.head=p.head.next;
        }
        return p;
    } 
    // public static void main(String[] args){
    //     Polynomial obj1=new Polynomial();
    //     obj1.insert(8);
    //     obj1.insert(0);
    //     obj1.insert(7);
    //     obj1.insert(0);
    //     obj1.insert(10);
    //     obj1.insert(0);
    //     Polynomial obj2=new Polynomial();
    //     obj2.insert(3);
    //     obj2.insert(0);
    //     obj2.insert(0);
    //     obj2.insert(9);

    //     Polynomial ans2=obj1.add(obj2);

    //     Node an=ans2.head;
    //     while(an!=null){
    //         System.out.println(an.data);
    //         an=an.next;
    //     }
    // }
}