import java.util.*;

public class Bakery {
    static int solve(ArrayList<Integer> cakes){
        SkipList obj=new SkipList();
        int n=cakes.size();
        int answer = 0;
        for(int i=0; i<n; ++i){
            Integer just=obj.upperBound(cakes.get(i));
            if(just!=null && just!=Integer.MAX_VALUE && just!=Integer.MIN_VALUE && just!=cakes.get(i)){
                obj.delete((int)just);
                obj.insert(cakes.get(i));
            }
            else{answer+=1; obj.insert(cakes.get(i));}
        }
        return answer;
    }
}
