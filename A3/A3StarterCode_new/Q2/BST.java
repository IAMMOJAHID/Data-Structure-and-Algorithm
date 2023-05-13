import java.util.*;
public class BST {

    public BSTNode root;

    public BST() {
        root = null;
    }
    public int max(int a, int b){
        if(a>b){return a;}
        else{return b;}
    }
    public BSTNode insert_dup1(int num,BSTNode root, BSTNode ancs){
        if(root==null){
            BSTNode temp=new BSTNode(num);
            root=temp;
        }
        else if(num<root.value){ root.left=insert_dup1(num, root.left, root);}
        else{root.right=insert_dup1(num, root.right, root);}
        return root;
    }

    public void insert(int num) {
        BSTNode temp=root;
        root=insert_dup1(num, temp, null);
    }

    public boolean check_delete=false;
    BSTNode delete_recursion(int num, BSTNode rut, BSTNode kk){
        if(check_delete==true && rut.right==null){
                kk.value=rut.value;
                return rut.left;
        }
        else if (rut==null){return rut;}
        else if (num<rut.value){rut.left=delete_recursion( num, rut.left, kk);}    
        else if (rut.value<num){rut.right=delete_recursion( num, rut.right, kk);}
        else {
            this.check_delete=true;
            if (rut.left==null){return rut.right;}
            else if (rut.right==null){return rut.left;}
            else{
                rut.left=delete_recursion(num, rut.left, rut);
            }     
        }
        return rut;
    }
    public boolean delete(int num) {
        this.check_delete=false;
        root=delete_recursion( num, root, root);
        return this.check_delete;
    }

    public boolean search(int num) {
        BSTNode temp=root;
        boolean ans=false;
        while(temp!=null){
            if(temp.value==num){ans=true; break;}
            else if(temp.value>num){temp=temp.left;}
            else{temp=temp.right;}
        }
        return ans;
    }
    public ArrayList<Integer> temp_inorder(BSTNode sampl){
        ArrayList<Integer> ans=new ArrayList<>();
        if(sampl!=null){
            BSTNode temp1=sampl;
            BSTNode temp2=sampl;
            ans.addAll(temp_inorder(temp1.left));
            ans.add(sampl.value);
            ans.addAll(temp_inorder(temp2.right));
        }
        return ans;
    }
    public  ArrayList<Integer> inorder() {
        BSTNode tem=root;
        ArrayList<Integer> ans=temp_inorder(tem);
        return ans;
    }
    public ArrayList<Integer> temp_preorder(BSTNode sampl){
        ArrayList<Integer> ans=new ArrayList<>();
        if(sampl!=null){
            BSTNode temp1=sampl;
            BSTNode temp2=sampl;
            ans.add(sampl.value);
            ans.addAll(temp_preorder(temp1.left));
            ans.addAll(temp_preorder(temp2.right));
        }
        return ans;
    }
    public  ArrayList<Integer> preorder() {
        ArrayList<Integer> ans=new ArrayList<>();
        ans=temp_preorder(root);
        return ans;
    }
    public ArrayList<Integer> temp_postorder(BSTNode sampl){
        ArrayList<Integer> ans=new ArrayList<>();
        if(sampl!=null){
            BSTNode temp1=sampl;
            BSTNode temp2=sampl;
            ans.addAll(temp_postorder(temp1.left));
            ans.addAll(temp_postorder(temp2.right));
            ans.add(sampl.value);
        }
        return ans;
    }
    public  ArrayList<Integer> postorder() {
        ArrayList<Integer> ans=new ArrayList<>();
        ans=temp_postorder(root);
        return ans;
    }
}