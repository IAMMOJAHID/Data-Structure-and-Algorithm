package com.gradescope.assignment1;

import com.gradescope.assignment1.AbstractFreqOfLetters;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FreqOfLetters extends AbstractFreqOfLetters {
    public Integer[] count_letters(String fname) throws FileNotFoundException, IOException {
        FileReader pg=new FileReader(fname);
        try {
            Integer[] result = new Integer[26];
            for(int i=0; i<26; ++i){
                result[i]=0;
            }
            
            BufferedReader messi = new BufferedReader(pg);
            String str;
            /*for(int i=0; i<str.length(); ++i){
                int m=str.charAt(i)-'a';
                result[m]+=1;
            }*/
            while((str=messi.readLine())!= null){
                for(int i=0; i<str.length(); ++i){
                    if(str.charAt(i)!=' '){
                        int m=str.charAt(i)-'a';
                        result[m]+=1;
                    }
                }
            }
            return result;
        } 
        catch ( FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        catch ( IOException e) {
            throw new FileNotFoundException();
        }
        /*
         * To be filled in by the student
         * Input: File name
         * Return: Array of 26 length containing freq of characters present in the file
         * Note: Ignore ' ' and EOF characters from input file
         */
        //Integer[] result = new Integer[26];
    }
    /* 
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FreqOfLetters obj=new FreqOfLetters();
        int[] ans=obj.count_letters("dumm.txt");
        int i=0;
        for(char c='a'; c<='z'; ++c){
            System.out.print(ans[i]+",");
            i++;
        }
    }*/
}
