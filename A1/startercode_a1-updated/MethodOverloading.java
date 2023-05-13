package com.gradescope.assignment1;

import com.gradescope.assignment1.AbstractMethodOverloading;
import java.lang.Math;

public class MethodOverloading extends AbstractMethodOverloading {
    /*
     * To be filled in by the student
     * Implement all the three overloaded methods here:
     *      Method name : "calculate"
     *      Return type : "double"
     *      And method should be "public" member of the class.
     */
    public double calculate(int a){
        return a*a;
    }
    public double calculate(int a,int b){
        return a*b;
    }
    public double calculate(int a,int b,int c){
        double s=(a+b+c)/2;
        double ans=Math.sqrt(s*(s-a)*(s-b)*(s-c));
        return ans;
    }
    /* 
    public static void main(String[] args) {
        MethodOverloading obj=new MethodOverloading();
        System.out.println(obj.calculate(2));
        System.out.println(obj.calculate(2,3));
        System.out.println(obj.calculate(3,4,5));
    }*/
}
