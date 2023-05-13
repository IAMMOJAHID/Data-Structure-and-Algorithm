import java.util.ArrayList;

public class Tree {

    public TreeNode root;

    public Tree() {
        root = null;
    }
    //Insert started
    public void add_str(TreeNode temp,String s){
        if(temp.s.size()<3){
            int n=temp.s.size();
            int i=0;
            while(temp.s.get(i).compareTo(s)<0 && i<n){i++;}
            temp.s.add(i,s);
        }
        else{

        }
    }
    public void insert_dup(TreeNode temp,String s){
        if(temp.children==null){add_str(temp,s);}
        else{
            int n=temp.s.size();
            int i=0;
            while(temp.s.get(i).compareTo(s)<0 && i<n){i++;}
            insert_dup(temp.children.get(i), s);
        }
    }
    public void insert(String s) {
        if(root==null){
            TreeNode just=new TreeNode();
            just.s.add(s);
            root=just;
        }
        else{
            TreeNode temp=root;
            insert_dup(temp,s);
        }
        // TO be completed by students

    }
    //insert end

    public boolean delete(String s) {
        // TO be completed by students
        return false;
    }

    public boolean search(String s) {
        // TO be completed by students
        return false;
    }
    
    public int increment(String s) {
        // TO be completed by students
        return 0;
    }

    public int decrement(String s) {
        // TO be completed by students
        return 0;
    }

    public int getHeight() {
        // TO be completed by students
        return 0;
    }

    public int getVal(String s) {
        // TO be completed by students
        return 0;
    }
}
