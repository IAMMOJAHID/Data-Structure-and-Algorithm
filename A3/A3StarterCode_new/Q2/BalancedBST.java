public class BalancedBST extends BST {
     
    public int max(int a, int b){
        if(a>b){return a;}
        else{return b;}
    }
    BSTNode rotate_LL(BSTNode a) {
        BSTNode b=a.left;
        BSTNode temp=b.right;
        b.right=a;
        a.left=temp;

        if(a.left!=null && a.right!=null){a.height=1+max(a.left.height, a.right.height);}
        else if(a.left!=null && a.right==null){a.height=1+a.left.height;}
        else if(a.left==null && a.right!=null){a.height=1+a.right.height;}
        else{a.height=1;}

        if(b.left!=null && b.right!=null){b.height=1+max(b.left.height, b.right.height);}
        else if(b.left!=null && b.right==null){b.height=1+b.left.height;}
        else if(b.left==null && b.right!=null){b.height=1+b.right.height;}
        else{b.height=1;}
        return b;
    }
    BSTNode rotate_RR(BSTNode a) {
        BSTNode b=a.right;
        BSTNode temp=b.left;
        b.left=a;
        a.right=temp;

        if(a.left!=null && a.right!=null){a.height=1+max(a.left.height, a.right.height);}
        else if(a.left!=null && a.right==null){a.height=1+a.left.height;}
        else if(a.left==null && a.right!=null){a.height=1+a.right.height;}
        else{a.height=1;}

        if(b.left!=null && b.right!=null){b.height=1+max(b.left.height, b.right.height);}
        else if(b.left!=null && b.right==null){b.height=1+b.left.height;}
        else if(b.left==null && b.right!=null){b.height=1+b.right.height;}
        else{b.height=1;}
        
        return b;
    }
    public BSTNode rotate_LR(BSTNode a){
        a.left = rotate_LL(a.left);
        return rotate_RR(a);
    }
    public BSTNode rotate_RL(BSTNode a){
        a.right = rotate_RR(a.right);
        return rotate_LL(a);
    }
    public BSTNode check11(BSTNode a){
        BSTNode ans=this.head.next.get(9);
        
    }
    public BSTNode insert_dup(int num,BSTNode root, BSTNode ancs){
        if(root==null){
            BSTNode temp=new BSTNode(num);
            return temp;
        }
        else if(num<root.value){ root.left=insert_dup(num, root.left, root);}
        else{root.right=insert_dup(num, root.right, root);}

        if(root.left!=null && root.right!=null){root.height=1+max(root.left.height, root.right.height);}
        else if(root.left!=null && root.right==null){root.height=1+root.left.height;}
        else if(root.left==null && root.right!=null){root.height=1+root.right.height;}
        else{root.height=1;}

        int check_baln;
        if(root.left!=null && root.right!=null){check_baln=root.right.height-root.left.height;}
        else if(root.left==null && root.right!=null){check_baln=root.right.height;}
        else if(root.left!=null && root.right==null){check_baln=0-root.left.height;}
        else{check_baln=0;}

        if(check_baln>1){
            if(root.right.value<num){root=rotate_RR(root);}
            else{root=rotate_RL(root);}
        }
        else if(check_baln<-1){
            if(root.left.value<num){ root=rotate_LR(root);}
            else{root=rotate_LL(root);}
        }
        return root;
    }
    public void insert(int key){
        BSTNode temp=root;
        root=insert_dup(key, temp, null);
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
        if(rut.left!=null && rut.right!=null){rut.height=1+max(rut.left.height, rut.right.height);}
        else if(rut.left!=null && rut.right==null){rut.height=1+rut.left.height;}
        else if(rut.left==null && rut.right!=null){rut.height=1+rut.right.height;}
        else{rut.height=1;}

        int check_baln;
        if(rut.left!=null && rut.right!=null){check_baln=rut.right.height-rut.left.height;}
        else if(rut.left==null && rut.right!=null){check_baln=rut.right.height;}
        else if(rut.left!=null && rut.right==null){check_baln=0-rut.left.height;}
        else{check_baln=0;}

        if(check_baln>1){
            if(rut.right.value<num){rut=rotate_RR(rut);}
            else{rut=rotate_RL(rut);}
        }
        else if(check_baln<-1){
            if(rut.left.value<num){ rut= rotate_LR(rut);}
            else{rut=rotate_LL(rut);}
        }
        return rut;
    }
    public boolean delete(int num) {
        this.check_delete=false;
        root=delete_recursion( num, root, root);
        return this.check_delete;
    }
}
