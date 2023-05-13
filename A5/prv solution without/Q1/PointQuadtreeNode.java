class CellTower {
    int x;
    int y;
    int cost;

    public CellTower(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    public double distance(int x1, int y1) {
        double ans=Math.sqrt( (this.x-x1)*(this.x-x1) + (this.y-y1)*(this.y-y1) );
        return ans;
    }
}

public class PointQuadtreeNode {

    public CellTower celltower;
    public PointQuadtreeNode[] quadrants;

    public PointQuadtreeNode(CellTower a) {
        this.celltower = a;
        this.quadrants = new PointQuadtreeNode[4];
    }  

}
