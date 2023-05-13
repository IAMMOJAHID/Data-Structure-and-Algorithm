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

        public boolean search(int num){
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

        public boolean delete(int num){
            boolean ans=false;
            int i=height-1;
            SkipListNode temp=this.head;
            while(i>-1){
                while(temp.next.get(i).value<num){
                    temp=temp.next.get(i);
                }
                if(temp.next.get(i).value==num){
                    if(head.next.get(i).next.get(i).value==POS_INF){this.height=height-1;}
                    temp.next.set(i, temp.next.get(i).next.get(i));
                    ans=true;
                }
                i--;
            }
            return ans;
        }

        public Integer upperBound(int num){           
            SkipListNode temp=this.head;
            int i=height-1;
            while(i>-1){
                while(temp.next.get(i).value<num){
                    temp=temp.next.get(i);
                } 
                i--;  
            }  
            while(temp.next.get(0).value==num){
                temp=temp.next.get(0);
            }
            return temp.next.get(0).value;
        }
        
        void insert_rec(int num, SkipListNode head,int size, SkipListNode adder){
                SkipListNode temp=head;
                while(temp.next.get(size-1).value<num){
                    temp=temp.next.get(size-1);
                }
                if(size!=1){insert_rec(num, temp, size-1,adder);}
                SkipListNode succsesor=temp.next.get(size-1);
                adder.height=size;
                temp.next.set(size-1,adder);
                adder.next.add(size-1,succsesor);
        }
        public void insert(int num){       
            int size=1;
            while(size<height+1 && randomizer.binaryRandomGen()){
                size++;
            }
            if(size>height){
                this.height=size;
                tail.height=size;
                head.height=size;
                head.next.add(size-1, tail);
                tail.next.add(size-1,null);
            }
            SkipListNode temp=head;
            int i=height;
            while(i>0){
                while(temp.next.get(i-1).value<num){temp=temp.next.get(i-1);}
                if(i==size){
                    SkipListNode adder=new SkipListNode(num, size);
                    insert_rec(num,temp,size, adder);
                    break;
                }
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