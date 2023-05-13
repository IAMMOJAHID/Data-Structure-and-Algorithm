import java.util.LinkedList;

import Includes.DictionaryEntry;
import Includes.HashTableEntry;
import Includes.KeyAlreadyExistException;
import Includes.KeyNotFoundException;
import Includes.NullKeyException;

import java.lang.reflect.Array;

public class COL106Dictionary<K, V> {

    private LinkedList<DictionaryEntry<K, V>> dict;
    /*
     * dict is a Linked-List, where every node of linked-list is of type DictionaryEntry.
     * DictionaryEntry is a key-value pair, where the type of key and value is K and V respectively.
     */ 
    public LinkedList<HashTableEntry<K, V>>[] hashTable;
    /*
     * hashTable is an array of Linked-Lists which is initialized by the COL106Dictionary constructor.
     * Each index of hashTable stores a linked-list whose nodes are of type HashTableEntry
     * HashTableEntry is a key-address pair, where the type of key is K and the corresponding address is the address of the DictionaryEntry in the linked-list corresponding to the key of HashTableEntry
     */ 
    
    @SuppressWarnings("unchecked")
    COL106Dictionary(int hashTableSize) {
        dict = new LinkedList<DictionaryEntry<K, V>>();
        // This statement initiailizes a linked-list where each node is of type DictionaryEntry with key and value of type K and V respectively.
        hashTable = (LinkedList<HashTableEntry<K, V>>[]) Array.newInstance(LinkedList.class, hashTableSize);
        // This statement initiailizes the hashTable with an array of size hashTableSize where at each index the element is an instance of LinkedList class and
        // this array is type-casted to an array of LinkedList where the LinkedList contains nodes of type HashTableEntry with key of type K. 
    }

    public void insert(K key, V value) throws KeyAlreadyExistException, NullKeyException {
        //System.out.println(key);
        //System.out.println(value);
        if(key==null){throw new NullKeyException();}
        else if(hashTable[hash(key)]!=null){
            int i=0;
            Boolean check=false;
            while(i<hashTable[hash(key)].size()){
                if(hashTable[hash(key)].get(i).dictEntry.key.equals(key)){
                    check=true;
                }
                i++;
            }
            if(check==true){
                throw new KeyAlreadyExistException();
            }
            else{
                DictionaryEntry<K, V> temp=new DictionaryEntry<K,V>(key, value);
                dict.add(temp);
                HashTableEntry<K,V> temp2=new HashTableEntry<K,V>(key, temp);
                hashTable[hash(key)].add(temp2);
            }
        }
        else{
            DictionaryEntry<K, V> temp=new DictionaryEntry<K,V>(key, value);
            dict.add(temp);
            HashTableEntry<K,V> temp2=new HashTableEntry<K,V>(key, temp);
            hashTable[hash(key)]=new LinkedList<HashTableEntry<K, V>>();
            hashTable[hash(key)].add(temp2);
        }
        /*
         * To be filled in by the student
         * Input: A key of type K and it corresponding value of type V
         * Working: Inserts the argumented key-value pair in the Dictionary in O(1)
         */
    }

    public V delete(K key) throws NullKeyException, KeyNotFoundException{
        if(key==null){ throw new NullKeyException();}
        if(hashTable[hash(key)]!=null){
            V ans=null;
            int i=0;
            //HashTableEntry<K, V> temp=hashTable[hash(key)].get(i);
            while(hashTable[hash(key)].size()>i){
                HashTableEntry<K, V> temp=hashTable[hash(key)].get(i);
                if(temp.key.equals(key)){
                    ans=temp.dictEntry.value;
                    //deleting from dictionary
                    //DictionaryEntry<K, V> check=temp.dictEntry;
                    int j=0;
                    
                    while(dict.size()>j){
                        DictionaryEntry<K, V> travers_dict=dict.get(j);
                        if(travers_dict.key.equals(key)){
                            dict.remove(j);
                        }
                        j++;
                        //travers_dict=dict.get(i);
                    }
                    //end
                    hashTable[hash(key)].remove(0);
                }
                i++;
                //temp=hashTable[hash(key)].get(i);
            }
            if(ans==null){throw new KeyNotFoundException();}
            else{return ans;}
        }
        else{
            throw new KeyNotFoundException();
        }
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the associated value of type V with the argumented key
         * Working: Deletes the key-value pair from the Dictionary in O(1)
         */

        //return dict.getFirst().value;
    }

    public V update(K key, V value) throws NullKeyException, KeyNotFoundException{
        if(key==null){throw new NullKeyException();}
        else if(hashTable[hash(key)]!=null){
            int i=0;
            V ans=null;
            while(hashTable[hash(key)].size()>i){
                if(hashTable[hash(key)].get(i).dictEntry.key.equals(key)){
                    ans=hashTable[hash(key)].get(i).dictEntry.value;
                    hashTable[hash(key)].get(i).dictEntry.value=value;
                }
                i++;
            }
            if(ans==null){throw new KeyNotFoundException();}
            else{return ans;}
        }
        else{
            throw new KeyNotFoundException();
        }
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the previously associated value of type V with the argumented key
         * Working: Updates the value associated with argumented key with the argumented value in O(1)
         */

        //return dict.getFirst().value;
    }

    public V get(K key) throws NullKeyException, KeyNotFoundException {
        if(key==null){throw new NullKeyException();}
        else if(hashTable[hash(key)]!=null){
            int i=0;
            V ans=null;
            while(hashTable[hash(key)].size()>i){
                if(hashTable[hash(key)].get(i).dictEntry.key.equals(key)){
                    ans=hashTable[hash(key)].get(i).dictEntry.value;
                }
                i++;
            }
            if(ans==null){throw new KeyNotFoundException();}
            else{return ans;}
        }
        else{
            throw new KeyNotFoundException();
        }
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the associated value of type V with the argumented key in O(1)
         */

        //return dict.getFirst().value;
    }
    
    public int size() {
        int i=0;
        while(i<dict.size()){
            i++;
        }
        /*
         * To be filled in by the student
         * Return: Returns the size of the Dictionary in O(1)
         */
        return i;
    }

    @SuppressWarnings("unchecked")
    public K[] keys(Class<K> cls) {
        int n=size();
        K[] ans=(K[]) Array.newInstance(cls, dict.size());
        int i=0;
        while(i<n){
            ans[i]=dict.get(i).key;
            i++;
        }
        /*
         * To be filled in by the student
         * Return: Returns array of keys stored in dictionary.
         */
        return ans;
        //return (K[]) Array.newInstance(cls, dict.size());
    }

    @SuppressWarnings("unchecked")
    public V[] values(Class<V> cls) {
        int n=size();
        V[] ans=(V[]) Array.newInstance(cls, dict.size());
        int i=0;
        while(i<n){
            ans[i]=dict.get(i).value;
            i++;
        }
        /*
         * To be filled in by the student
         * Return: Returns array of keys stored in dictionary.
         */
        return ans;
        //return (V[]) Array.newInstance(cls, dict.size());
    }

    public int hash(K key) {
        String s=(String)key;
        //System.out.println(s);
        int n=s.length();
        int x=hashTable.length;
        int sum=0;
        long p=1;
        //System.out.println(key);
        for(int i=0; i<n; ++i){
            p=p%x;
            sum+=((((int)s.charAt(i)+1)%x)*(p)%x);
            sum=sum%x;
            p*=131;
        }
        // int ans= (int)(sum % vv);
        //System.out.println(vv);
        //System.out.println(ans);
        return sum%x;//%(hashTable.length);
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the hash of the argumented key using the Polynomial Rolling
         * Hash Function.
         */

        //return 0;
    } 
    
    // public static void main(String[] args) throws KeyAlreadyExistException, NullKeyException, KeyNotFoundException {
    //     COL106Dictionary<String,Integer> obj=new COL106Dictionary<>(503);
    //     obj.insert("iiitd", 10);
    //     obj.insert("world", 5);
    //     obj.insert("Hello", 7);
    //     //System.out.println(obj.hash("!!!"));
    //     // System.out.println(obj.update(null, 20));
    //     //System.out.println(obj.delete(null));
    //     //System.out.println(obj.get("t"));
    //     // Integer[] ans=obj.values(Integer.class);
    //     // for(int i=0; i<ans.length; ++i){
    //     //     System.out.println(ans[i]);
    //     // }
    //     //System.out.println(obj.size());
    //     // System.out.println(obj.hash("turbine"));
    //     // System.out.println(obj.hash("performance"));
    // }
}
