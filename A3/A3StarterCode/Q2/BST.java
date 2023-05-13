import java.util.ArrayList;

public class BST {

    public BSTNode root;

    public BST() {
        root = null;
    }

    public void insert(int num) {
        BSTNode nd=new BSTNode(num);
        int height=1;
        BSTNode temp=root;
        while(temp!=null){
            if(temp.value>num){temp=temp.left;}
            else{temp=temp.right;}
            height++;
        }
        nd.height=height;
        temp=nd;
        // TO be completed by students
    }

    public boolean delete(int num) {
        // TO be completed by students
        boolean ans=false;
        BSTNode temp=root;
        BSTNode prv=temp;
        while(temp!=null && temp.value!=num){
            prv=temp;
            if(temp.value>num){temp=temp.left;}
            else{temp=temp.right;}
        }
        if(temp.value==num){
            ans=true;
            BSTNode temp_2=temp;
            if(temp_2.left==null && temp_2.right==null){
                temp_2=null;
            }
            else if(temp_2.left==null && temp_2.right!=null){
                if(prv.left.value==num){prv.left=temp_2.right;}
                else{prv.right=temp_2.right;}
            } 
            else if(temp_2.left!=null && temp_2.right==null){
                if(prv.left.value==num){prv.left=temp_2.left;}
                else{prv.right=temp_2.left;}
            } 
            else{
                temp_2=temp_2.right;
                BSTNode prv2=temp_2;
                while(temp_2.left!=null){
                    prv2=temp_2;
                    temp_2=temp_2.left;
                }
                temp.value=temp_2.value;
                prv2.left=temp_2.right;
            }
        }
        return ans;
    }

    public boolean search(int num) {
        // TO be completed by students
        BSTNode temp=root;
        boolean ans=false;
        while(temp!=null){
            if(temp.value==num){ans=true;}
            else if(temp.value>num){temp=temp.left;}
            else{temp=temp.right;}
        }
        return ans;
    }
    //inorder start
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
        // TO be completed by students
        ArrayList<Integer> ans=new ArrayList<>();
        ans=temp_inorder(root);
        return ans;
    }
    //end

    //start preorder
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
        // TO be completed by students
        ArrayList<Integer> ans=new ArrayList<>();
        ans=temp_preorder(root);
        return ans;
    }
    //end preorder
    //start postorder
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
        // TO be completed by students
        ArrayList<Integer> ans=new ArrayList<>();
        ans=temp_postorder(root);
        return ans;
    }
    //end

    public static void main(String[] args) {
        BST obj=new BST();
        obj.insert(2);
        obj.insert(0);
        obj.insert(7);
        obj.insert(4);
        ArrayList<Integer> order_list=obj.inorder();
        int n=order_list.size();
        for(int i=0; i<n; ++i){
            System.out.println(order_list.get(i));
        }
    }
}