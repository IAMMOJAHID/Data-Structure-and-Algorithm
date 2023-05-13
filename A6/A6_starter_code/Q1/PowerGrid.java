import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class PowerLine {
    String cityA;
    String cityB;

    public PowerLine(String cityA, String cityB) {
        this.cityA = cityA;
        this.cityB = cityB;
    }
}

// Students can define new classes here
class Vertex{
    String V;
    ArrayList<Vertex> child;
    int label;
    int HP;
    public Vertex(String V){
        this.V=V;
        this.child=new ArrayList<>();
        this.label=-1;
        this.HP=-1;
    }
}

public class PowerGrid {
    int numCities;
    int numLines;
    String[] cityNames;
    PowerLine[] powerLines;

    // Students can define private variables here
    private Vertex V;
    Map<String, Vertex> store_vertex=new HashMap<>();

    public PowerGrid(String filename) throws Exception {
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        numCities = Integer.parseInt(br.readLine());
        numLines = Integer.parseInt(br.readLine());
        cityNames = new String[numCities];
        for (int i = 0; i < numCities; i++) {
            cityNames[i] = br.readLine();
        }
        powerLines = new PowerLine[numLines];
        for (int i = 0; i < numLines; i++) {
            String[] line = br.readLine().split(" ");
            powerLines[i] = new PowerLine(line[0], line[1]);
        }
        for(int i=0; i<numCities; ++i){
            Vertex pp=new Vertex(cityNames[i]);
            store_vertex.put(cityNames[i], pp);
        }
        this.V=store_vertex.get(cityNames[0]);

        for(int i=0; i<numLines; ++i){
            Vertex C1= store_vertex.get(powerLines[i].cityA);
            Vertex C2= store_vertex.get(powerLines[i].cityB);

            store_vertex.get(powerLines[i].cityA).child.add(C2);
            store_vertex.get(powerLines[i].cityB).child.add(C1);
        }

        Map<String,Boolean> is_visit=new HashMap<>();
        Stack<Vertex> DFS=new Stack<>();
        V.label=0;
        DFS.push(V);
        is_visit.put(V.V, true);
        while(!DFS.empty()){
            Vertex temp=DFS.peek();
            Boolean check=true;
            int s=temp.child.size();
            for(int i=0; i<s; ++i){
                if( is_visit.get(temp.child.get(i).V)==null){
                    is_visit.put(temp.child.get(i).V, true);
                    temp.child.get(i).label=temp.label+1;
                    DFS.push(temp.child.get(i));
                    check=false;
                    break;
                }
            }
            if(check==true){
                int minm=temp.label;
                for(int i=0; i<s; ++i){
                    int z=temp.child.get(i).label;
                    int zz=temp.child.get(i).HP;
                    if(zz>=0 && zz<minm){minm=zz;}
                    if(z<minm-1){minm=z;}
                }
                temp.HP=minm;
                DFS.pop();
            }
        }

    }

    public ArrayList<PowerLine> criticalLines() {
        Vertex temp=V;
        Map<Vertex,Boolean> is_visit=new HashMap<>();
        is_visit.put(temp, true);

        ArrayList<PowerLine> ans=new ArrayList<>();
        Queue<Vertex> DF= new LinkedList<>();
        DF.add(V);
        while(!DF.isEmpty()){
            Vertex temp2=DF.poll();
            int s=temp2.child.size();
            for(int i=0; i<s; ++i){
                //System.out.println(temp2.child.get(i).HP);
                if( is_visit.get(temp2.child.get(i))==null ){
                    if(temp2.child.get(i).HP>=temp2.child.get(i).label){
                        PowerLine xx=new PowerLine(temp2.V, temp2.child.get(i).V);
                        ans.add(xx);
                    }
                    is_visit.put(temp2.child.get(i), true);
                    DF.add(temp2.child.get(i));
                }
            }
        }
        return ans;
    }

    private ArrayList<PowerLine> ans;
    public void preprocessImportantLines() {
        ans=new ArrayList<>();
        Vertex temp=V;
        Map<Vertex,Boolean> is_visit=new HashMap<>();
        is_visit.put(temp, true);

        Queue<Vertex> DF= new LinkedList<>();
        DF.add(V);
        while(!DF.isEmpty()){
            Vertex temp2=DF.poll();
            int s=temp2.child.size();
            for(int i=0; i<s; ++i){
                //System.out.println(temp2.child.get(i).HP);
                if( is_visit.get(temp2.child.get(i))==null ){
                    if(temp2.child.get(i).HP>=temp2.child.get(i).label){
                        PowerLine xx=new PowerLine(temp2.V, temp2.child.get(i).V);
                        ans.add(xx);
                    }
                    is_visit.put(temp2.child.get(i), true);
                    DF.add(temp2.child.get(i));
                }
            }
        } 
    }

    public int numImportantLines(String cityA, String cityB) {
        
        Vertex start=store_vertex.get(cityA);
        //Vertex dest=store_vertex.get(cityB);

        Stack<Vertex> store_DFS=new Stack<>();
        Map<Vertex, Boolean> visit=new HashMap<>();
        store_DFS.push(start);
        visit.put(start, true);
        while(!store_DFS.isEmpty()){
            Vertex temp=store_DFS.peek();
            if(temp.V.equals(cityB)){break;}
            int n=temp.child.size();
            boolean move_available=false;
            for(int i=0; i<n; ++i){
                if(visit.get(temp.child.get(i))==null){
                    move_available=true;
                    visit.put(temp.child.get(i), true);
                    store_DFS.push(temp.child.get(i));
                    break;
                }
            }
            if(move_available==false){store_DFS.pop();}
        }
    
        int no_bridge=0;
        while(store_DFS.size()>1){
            Vertex V1=store_DFS.pop();
            Vertex V2=store_DFS.peek();

            int n=ans.size();
            for(int i=0; i<n; ++i){
                if( (ans.get(i).cityA.equals(V1.V) && ans.get(i).cityB.equals(V2.V)) || (ans.get(i).cityB.equals(V1.V) && ans.get(i).cityA.equals(V2.V))){no_bridge+=1;}
            }
        }
        return no_bridge;
    }
}