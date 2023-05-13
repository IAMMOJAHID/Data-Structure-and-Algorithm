
import Includes.*;

public class FrequencyAnalysis {
    //sizes of hash-tables are updated
    static final int[] hashTableSizes = {173, 6733, 100003};
    COL106Dictionary<String, Integer> dict1 = new COL106Dictionary<String, Integer>(hashTableSizes[0]);
    COL106Dictionary<String, Integer> dict2 = new COL106Dictionary<String, Integer>(hashTableSizes[1]);
    COL106Dictionary<String, Integer> dict3 = new COL106Dictionary<String, Integer>(hashTableSizes[2]);

    void fillDictionaries(String inputString) throws NullKeyException, KeyAlreadyExistException, KeyNotFoundException {
        String[] splited_Arr = inputString.split("\\s+");
        int n=splited_Arr.length;
        if(n==0){throw new NullKeyException();}
        else{ 
            for(int i=0; i<n; ++i){
                String str=splited_Arr[i];
                str=str.toLowerCase();
                String tt="";
                for(int k=0; k<str.length(); ++k){
                    if((int)str.charAt(k)<=122 && (int)str.charAt(k)>=97){tt+=str.charAt(k);}
                }
                str=tt;
                if(str!=""){
                    if(dict1.hashTable[dict1.hash(str)]!=null){
                        Boolean check=false;
                        int k=0;
                        while(k<dict1.hashTable[dict1.hash(str)].size()){
                            if(dict1.hashTable[dict1.hash(str)].get(k).dictEntry.key.equals(str)){
                                check=true;
                            }
                            k++;
                        }
                        if(check==true){
                            dict1.update(str, dict1.get(str)+1);
                            dict2.update(str, dict2.get(str)+1);
                            dict3.update(str, dict3.get(str)+1);
                        }
                        else{
                            dict1.insert(str,1);
                            dict2.insert(str,1);
                            dict3.insert(str,1);
                        }
                    }
                    else{
                        dict1.insert(str,1);
                        dict2.insert(str,1);
                        dict3.insert(str,1);
                    }
                }
            }
        }
        /*
         * To be filled in by the student
         */
    }
    // long[] profile() {
    //     /*
    //      * To be filled in by the student
    //      */
    //     return new long[4];
    // }

    int[] noOfCollisions() {
        int[] ans=new int[3];
        for(int i=0; i<173; ++i){
            if(dict1.hashTable[i]!=null){
                ans[0]+=dict1.hashTable[i].size()-1;
            }
        }
        for(int i=0; i<6733; ++i){
            if(dict2.hashTable[i]!=null){
                ans[1]+=dict2.hashTable[i].size()-1;
            }
        }
        for(int i=0; i<100003; ++i){
            if(dict3.hashTable[i]!=null){
                ans[2]+=dict3.hashTable[i].size()-1;
            }
        }
        /*
         * To be filled in by the student
         */
        return ans;
    }

    String[] hashTableHexaDecimalSignature() {
        //first
        String[] ans=new String[3];
        String count="";
        for(int i=0; i<173; ++i){
            if(dict1.hashTable[i]!=null){count+='1';}
            else{count+='0';}   
        }
        int number11 = Integer.parseInt(count, 2); 
        String hexa11 = Integer.toHexString(number11); 
        ans[0]=hexa11.toUpperCase();
        //second
        String count1="";
        for(int i=0; i<6733; ++i){
            if(dict2.hashTable[i]!=null){count1+='1';}
            else{count1+='0';}   
        }
        int binaryString1 = Integer.parseInt(count1, 2);
        String hexString1 = Integer.toHexString(binaryString1);
        hexString1=hexString1.toUpperCase();
        ans[1]=hexString1;

        //third
        String count2="";
        for(int i=0; i<100003; ++i){
            if(dict3.hashTable[i]!=null){count2+='1';}
            else{count2+='0';}   
        }
        int binaryString2 = Integer.parseInt(count2, 2);
        String hexString2 = Integer.toHexString(binaryString2);
        hexString2=hexString2.toUpperCase();
        ans[2]=hexString2;
        /*
         * To be filled in by the student
         */
        return ans;
    }
    
    String[] distinctWords() {
        
        /*
         * To be filled in by the student
         */
        return dict1.keys(String.class);
    }

    Integer[] distinctWordsFrequencies() {
        /*
         * To be filled in by the student
         */
        return dict1.values(Integer.class);
    }

    // public static void main(String[] args) throws NullKeyException, KeyAlreadyExistException, KeyNotFoundException {
    //     FrequencyAnalysis obj=new FrequencyAnalysis();
    //     obj.fillDictionaries("The Indian Institute of Technology Delhi (IIT Delhi) is a public institute of technology located in New Delhi, India. It is one of the twenty-three Indian Institutes of Technology created to be Centres of Excellence for India’s training, research and development in science, engineering and technology.");
    //     String[] ans=obj.distinctWords();
    //     for(int i=0; i<ans.length; ++i){
    //         System.out.print(ans[i]+",");
    //     }
        
    // }
}
