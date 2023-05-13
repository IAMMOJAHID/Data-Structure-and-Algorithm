import java.util.ArrayList;
import java.util.Collections;

public class SkipList {

        public SkipListNode head;
        public SkipListNode tail;
        public int height;
        public Randomizer randomizer;
        private final int NEG_INF = Integer.MIN_VALUE;
        private final int POS_INF = Integer.MAX_VALUE;

        SkipList(){
            /*
            * DO NOT EDIT THIS FUNCTION
            */
            this.head = new SkipListNode(NEG_INF,1);
            this.tail = new SkipListNode(POS_INF,1);
            this.head.next.add(0,this.tail);
            this.tail.next.add(0,null);
            this.height = 1;
            this.randomizer = new Randomizer();
        }

        public boolean delete(int num){
            // TO be completed by students
            boolean ans=false;
            int i=height-1;
            SkipListNode temp=this.head;
            while(i>-1){
                while(temp.next.get(i).value<num){
                    temp=temp.next.get(i);
                }
                if(temp.next.get(i).value==num){
                    while(i>-1){
                        temp.next.remove(i);
                    }
                    i--;
                    ans=true;
                }
                i--;
            }
            return ans;
        }

        public boolean search(int num){
            // TO be completed by students
            boolean ans=false;
            int i=height-1;
            while(i>-1){
                SkipListNode temp=this.head;
                while(temp.next.get(i).value<num){
                    temp=temp.next.get(i);
                }
                if(temp.next.get(i).value==num){
                    ans=true;
                    break;
                }
                i--;
            }
            return ans;
        }

        public Integer upperBound(int num){ 
            // TO be completed by students  
            SkipListNode temp=this.head;
            int i=height-1;
            while(i>-1){
                while(temp.next.get(i).value<num){
                    temp=temp.next.get(i);
                } 
                i--;  
            }  
            while(temp.next.get(i).value==num){
                temp=temp.next.get(i);
            }
            return temp.next.get(i).value;
        }

        public void insert(int num){
            Randomizer check=new Randomizer();
            boolean tt=check.binaryRandomGen();
            
            int i=0;
            while( tt && i<height){
                i++;
                tt=check.binaryRandomGen();
            }
            int up=i;
            SkipListNode temp=head;
            SkipListNode prv=new SkipListNode(num, i);
            while(i>-1){
                SkipListNode node_new=new SkipListNode(num, i);
                while(temp.next.get(i).value<num){
                    temp=temp.next.get(i);
                }
                SkipListNode test=temp.next.get(i);
                temp.next.set(i,node_new);
                node_new.next.set(i,test);
                if(i!=up){
                    node_new.next.set(i+1,prv);
                    prv.next.set(i,node_new);
                }
                prv=node_new;
                i--;
            }
            
        }

        public void print(){
            /*
            * DO NOT EDIT THIS FUNCTION
            */
            for(int i = this.height ; i>=1; --i){
                SkipListNode it = this.head;
                while(it!=null){
                    if(it.height >= i){
                        System.out.print(it.value + "\t");
                    }
                    else{
                        System.out.print("\t");
                    }
                    it = it.next.get(0);
                }
                System.out.println("null");
            }
        }
}