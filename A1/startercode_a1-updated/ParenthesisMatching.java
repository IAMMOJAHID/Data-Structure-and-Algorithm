package com.gradescope.assignment1;

import com.gradescope.assignment1.AbstractParenthesisMatching;
import com.gradescope.assignment1.DemoStack;

public class ParenthesisMatching extends AbstractParenthesisMatching {
    DemoStack store=new DemoStack();
    public Boolean match_parenthesis(String s){
        /*
         * To be filled in by the student
         * Input: String made up of characters ‘(’, ‘{’, ‘[’, ‘)’, ‘}’, and ‘]’ 
         * Return: Whether input string is a matching parenthesis
         */
        //ParenthesisMatching obj=new ParenthesisMatching();
        for(int i=0; i<s.length(); ++i){
            if(s.charAt(i)=='(' || s.charAt(i)=='{' || s.charAt(i)=='['){
                store.push(s.charAt(i));
            }
            else{
                if(store.size()>0){
                    if(s.charAt(i)==')' && (char)store.pop()!='('){return false;}
                    else if(s.charAt(i)=='}' && (char)store.pop()!='{'){return false;}
                    else if(s.charAt(i)==']' && (char)store.pop()!='['){return false;}
                    else{continue;}
                }
                else{return false;}
            }
        }
        if(store.size==0){return true;}
        return false;
    } 
    public static void main(String[] args) {
        ParenthesisMatching obj=new ParenthesisMatching();
        System.out.println(obj.match_parenthesis("{[()][]()}"));
        System.out.println(obj.match_parenthesis("{[(])}"));
    }
}
