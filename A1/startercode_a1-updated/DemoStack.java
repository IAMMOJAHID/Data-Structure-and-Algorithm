package com.gradescope.assignment1;

import java.util.EmptyStackException;

import com.gradescope.assignment1.AbstractDemoStack;

public class DemoStack extends AbstractDemoStack {
    public int N;
    public int size;
    Character[] base;
    public DemoStack(){
        super();
        this.N=10;
        base=new Character[N];
        base = new Character[N];
        this.size=0;
    }
    public void push(Character i){
        if(size == N){
            Character[] temp = base;
            N = 2*N;
            base = new Character[N];
            for(int k=0; k<size; ++k){
                base[k]=temp[k];
            }
        }
        base[size]=i;
        size=size+1;
        /*
         * To be filled in by the student
         * Input: Character to be inserted onto top of stack
         */
    }

    public Character pop() throws EmptyStackException{ 
        if(size!=0){
            Character value = base[size-1];
            size = size-1;
            if(size < N/4 && N>40){
                Character[] temp = base;
                N = N/2;
                base = new Character[N];
                for(int i=0; i<size; ++i)
                base[i]=temp[i];
            }
            return value;
        }
        else{
            throw new EmptyStackException();
        }
        /*
         * To be filled in by the student
         * Return: Character present at the top of the stack
         */
    }
    
    public Boolean is_empty(){
        if(size==0){return true;}
        else{return false;}
        /*
         * To be filled in by the student
         * Return: Stack is empty or not
         */
    }
    
    public Integer size(){
        return size;
        /*
         * To be filled in by the student
         * Return: Number of elements which are present in stack
         */
    }
    
    public Character top() throws EmptyStackException{
        if(size>0){
            return base[size-1];
        }
        else{
            throw new EmptyStackException();
        }
        /*
         * To be filled in by the student
         * Return: Character present at the top of the stack
         */
    }

    public Character[] return_base_array(){
       /*
        * To be filled in the by the student
        * Return: Return reference to the base array storing the elements of stack
        */
        return base;
    }
    /* 
    public static void main(String[] args) {
        DemoStack obj=new  DemoStack();
        /*obj.push('[');
        obj.push('{');
        obj.push('(');
        System.out.println(obj.pop());
        obj.push(']');
        System.out.println(obj.pop());
        System.out.println(obj.size());
        System.out.println(obj.top());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.is_empty());*/
        /*obj.push('[');
        obj.push('{');
        System.out.println(obj.size());
        System.out.println(obj.top());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        //System.out.println(obj.size());
        //System.out.println(obj.top());
        //System.out.println(obj.pop());
        //System.out.println(obj.pop());
        //System.out.println(obj.is_empty());
    }*/
}
