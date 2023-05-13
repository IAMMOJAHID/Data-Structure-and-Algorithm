public class PointQuadtree {

    enum Quad {
        NW,
        NE,
        SW,
        SE
    }

    public PointQuadtreeNode root;

    public PointQuadtree() {
        this.root = null;
    }

    public boolean insert(CellTower a) {
        boolean ans=false;
        PointQuadtreeNode inst=new PointQuadtreeNode(a);
        if(root==null){root=inst; ans=true;}
        else{
            PointQuadtreeNode temp=root;
            while(temp!=null){
                if(a.x==temp.celltower.x && a.y==temp.celltower.y){break;}
                else if(a.x<temp.celltower.x && a.y>=temp.celltower.y){
                    if(temp.quadrants[0]==null){temp.quadrants[0]=inst; ans=true;}
                    temp=temp.quadrants[0];
                } 
                else if(a.x>=temp.celltower.x && a.y>temp.celltower.y){
                    if(temp.quadrants[1]==null){temp.quadrants[1]=inst; ans=true;}
                    temp=temp.quadrants[1];
                }
                else if(a.x<=temp.celltower.x && a.y<temp.celltower.y){
                    if(temp.quadrants[2]==null){temp.quadrants[2]=inst; ans=true;}
                    temp=temp.quadrants[2];
                }
                else if(a.x>temp.celltower.x && a.y<=temp.celltower.y){
                    if(temp.quadrants[3]==null){temp.quadrants[3]=inst; ans=true;}
                    temp=temp.quadrants[3];
                }
            }
        }
        return ans;
    }

    public boolean cellTowerAt(int x, int y) {
        boolean ans=false;
        PointQuadtreeNode temp=root;
        while(temp!=null){
            if(x==temp.celltower.x && y==temp.celltower.y){ans=true; break;}
            else if(x<temp.celltower.x && y>=temp.celltower.y){
                if(temp.quadrants[0]==null){break;}
                temp=temp.quadrants[0];
            } 
            else if(x>=temp.celltower.x && y>temp.celltower.y){
                if(temp.quadrants[1]==null){break;}
                temp=temp.quadrants[1];
            }
            else if(x<=temp.celltower.x && y<temp.celltower.y){
                if(temp.quadrants[2]==null){break;}
                temp=temp.quadrants[2];
            }
            else if(x>temp.celltower.x && y<=temp.celltower.y){
                if(temp.quadrants[3]==null){break;}
                temp=temp.quadrants[3];
            }
        }
        return ans;
    }

    private CellTower ans=null;
    private int cost=-1;
    public void check(int x, int y, int r, PointQuadtreeNode temp){
        if(temp!=null){
            double dist=temp.celltower.distance(x,y);
            if(dist<r){
                if(cost<0 || temp.celltower.cost<cost){
                    ans=temp.celltower;
                    cost=temp.celltower.cost;
                }
            }
            check(x,y,r,temp.quadrants[0]);
            check(x,y,r,temp.quadrants[1]);
            check(x,y,r,temp.quadrants[2]);
            check(x,y,r,temp.quadrants[3]);
        }
    }
    public CellTower chooseCellTower(int x, int y, int r) {
        ans=null;
        cost=-1;
        PointQuadtreeNode temp=root;
        check(x,y,r,temp);
        return ans;
    }
    
}
