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

        //my edit
        for(int i=0; i<numCities; ++i){
            Vertex pp=new Vertex(cityNames[i]);
            store_vertex.put(cityNames[i], pp);
        }
        this.V=store_vertex.get(cityNames[0]);
        // System.out.println(powerLines[0].cityB);
        // System.out.println(powerLines[0].cityB);

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

        // TO be completed by students
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
        /*
         * Implement an efficient algorithm to compute the critical transmission lines
         * in the power grid.
         
         * Expected running time: O(m + n), where n is the number of cities and m is the
         * number of transmission lines.
         */

        // return new ArrayList<PowerLine>();
    }

    ArrayList<PowerLine> ans;
    public void preprocessImportantLines() {
        /*
         * Implement an efficient algorithm to preprocess the power grid and build
         * required data structures which you will use for the numImportantLines()
         * method. This function is called once before calling the numImportantLines()
         * method. You might want to define new classes and data structures for this
         * method.
         
         * Expected running time: O(n * logn), where n is the number of cities.
         */
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

        //numImprt code
        
    }

    public int numImportantLines(String cityA, String cityB) {
        /*
         * Implement an efficient algorithm to compute the number of important
         * transmission lines between two cities. Calls to numImportantLines will be
         * made only after calling the preprocessImportantLines() method once.
         
         * Expected running time: O(logn), where n is the number of cities.
         */
        preprocessImportantLines();
        Vertex start=store_vertex.get(cityA);

        Stack<Vertex> store_DFS=new Stack<>();
        Map<Vertex, Boolean> visit=new HashMap<>();
        store_DFS.push(start);
        visit.put(start, true);
        while(!store_DFS.isEmpty()){
            Vertex tempp=store_DFS.peek();
            if(tempp.V.equals(cityB)){break;}
            int n=tempp.child.size();
            boolean move_available=false;
            for(int i=0; i<n; ++i){
                if(visit.get(tempp.child.get(i))==null){
                    move_available=true;
                    visit.put(tempp.child.get(i), true);
                    store_DFS.push(tempp.child.get(i));
                    break;
                }
            }
            if(move_available==false){store_DFS.pop();}
        }
        //root from A to B stored
        //counting bridge in between root
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
    public static void main(String[] args) throws Exception {
        PowerGrid obj=new PowerGrid("input.txt");
        ArrayList<PowerLine> temp=obj.criticalLines();
        int n=temp.size();
        for(int i=0; i<n; ++i){
            System.out.println(temp.get(i).cityA+":"+temp.get(i).cityB);
        }
        obj.preprocessImportantLines();
        // System.out.println(obj.numImportantLines("Delhi","Chennai"));
        // System.out.println(obj.numImportantLines("Mumbai","Kolkata"));
        System.out.println(obj.numImportantLines("D","E"));
        System.out.println(obj.numImportantLines("K","N"));
        System.out.println(obj.numImportantLines("H","O"));
        System.out.println(obj.numImportantLines("G","J"));
    }
}