import java.io.*;
import java.util.*;

public class SnakesLadder /*extends AbstractSnakeLadders*/ {
	int N, M;
	int snakes[];
	int ladders[];

	public SnakesLadder(String name)throws Exception{
		File file = new File(name);
		BufferedReader br = new BufferedReader(new FileReader(file));
		N = Integer.parseInt(br.readLine());
        
        M = Integer.parseInt(br.readLine());

	    snakes = new int[N];
		ladders = new int[N];
	    for (int i = 0; i < N; i++){
			snakes[i] = -1;
			ladders[i] = -1;
		}

		for(int i=0;i<M;i++){
            String e = br.readLine();
            StringTokenizer st = new StringTokenizer(e);
            int source = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());

			if(source<destination){
				ladders[source] = destination;
			}
			else{
				snakes[source] = destination;
			}
        }
	}

    private int check[];
	private int check_back[];
	private int optml_ans;

	public int OptimalMoves(){
		int ans=N;
		check=new int[N+1];
		for (int i=0; i<N+1; ++i){check[i]=N;}
		Queue<Integer> que_ins= new LinkedList<>(); 
		Queue<Integer> que_ans= new LinkedList<>();
		que_ins.add(0);
		que_ans.add(0); check[0]=1;
		while(que_ins.isEmpty()==false){
			Integer tem=que_ins.poll();
			Integer ans1=que_ans.poll();
			if(tem==N && ans1<ans){
				ans=ans1;
			}
			if(tem<N && ladders[tem]!=-1 && check[ladders[tem]]>ans1){
				que_ins.add(ladders[tem]);
				que_ans.add(ans1);
				check[ladders[tem]]=ans1;
			}
			if(tem<N && snakes[tem]!=-1 && check[snakes[tem]]>ans1){
				que_ins.add(snakes[tem]);
				que_ans.add(ans1);
				check[snakes[tem]]=ans1;
			}
			if(tem+6<=N && check[tem+6]>ans1+1 && ladders[tem]==-1 && snakes[tem]==-1){
				que_ins.add(tem+6);
				check[tem+6]=ans1+1;
				que_ans.add(ans1+1);
			}
			if(tem+5<=N && check[tem+5]>ans1+1 && ladders[tem]==-1 && snakes[tem]==-1){
				que_ins.add(tem+5);
				check[tem+5]=ans1+1;
				que_ans.add(ans1+1);
			}
			if(tem+4<=N && check[tem+4]>ans1+1 && ladders[tem]==-1 && snakes[tem]==-1){
				que_ins.add(tem+4);
				check[tem+4]=ans1+1;
				que_ans.add(ans1+1);
			}
			if( tem+3<=N && check[tem+3]>ans1+1 && ladders[tem]==-1 && snakes[tem]==-1){
				que_ins.add(tem+3);
				check[tem+3]=ans1+1;
				que_ans.add(ans1+1);
			}
			if(tem+2<=N && check[tem+2]>ans1+1 && ladders[tem]==-1 && snakes[tem]==-1){
				que_ins.add(tem+2);
				check[tem+2]=ans1+1;
				que_ans.add(ans1+1);
			}
			if( tem+1<=N && check[tem+1]>ans1+1 && ladders[tem]==-1 && snakes[tem]==-1){
				que_ins.add(tem+1);
				check[tem+1]=ans+1;
				que_ans.add(ans1+1);
			}
		}
		check_back=new int[N+1];
		for(int i=0; i<N+1; ++i){check_back[i]=0;}
		for(int i=N-1; i>0; --i){
			if(snakes[i]==-1){
				int answer=N;
				if(ladders[i]!=-1){
					if(answer>check_back[ladders[i]]){answer=check_back[ladders[i]];}
				}
				if(i+6<=N && answer>check_back[i+6]+1){answer=check_back[i+6]+1;}
				if(i+5<=N && answer>check_back[i+5]+1){answer=check_back[i+5]+1;}
				if(i+4<=N && answer>check_back[i+4]+1){answer=check_back[i+4]+1;}
				if(i+3<=N && answer>check_back[i+3]+1){answer=check_back[i+3]+1;}
				if(i+2<=N && answer>check_back[i+2]+1){answer=check_back[i+2]+1;}
				if(i+1<=N && answer>check_back[i+1]+1){answer=check_back[i+1]+1;}
				check_back[i]=answer;
			}
			else{
				check_back[i]=N;
			}
		}
		optml_ans=ans;
		return ans;
	}

	public int Query(int x, int y){
		OptimalMoves();
		int now=check[x]+check_back[y];
		if(now<optml_ans){return 1;}
		else{return -1;}
	}

	private ArrayList<Integer> arr1=new ArrayList<Integer>();
	private ArrayList<Integer> arr2=new ArrayList<Integer>();

	public int[] FindBestNewSnake(){
		OptimalMoves();
		int p=ladders.length;
		for(int i=0; i<p; ++i){
			if(ladders[i]!=-1){arr1.add(i); arr2.add(ladders[i]);}
		}
		int n=arr1.size();
		System.out.println("n:"+n);
		int result[] = {-1, -1};
		int prv=optml_ans;
		for(int i=0; i<n; ++i){
				for(int j=i+1; j<n; ++j){
						if(arr2.get(i)>arr1.get(j)){
							int now=check[arr2.get(i)] + check_back[arr1.get(j)];
							if(now<prv){result[0]=arr2.get(i); result[1]=arr1.get(j); prv=now;}
						}
						if(arr2.get(j)>arr1.get(i)){
							int now=check[arr2.get(j)] + check_back[arr1.get(i)];
							if(now<prv){result[0]=arr2.get(j); result[1]=arr1.get(i); prv=now;}
						}
				}
		}
		return result;
	}
	public static void main(String[] args) throws Exception{
        SnakesLadder obj = new SnakesLadder("input.txt");
		int a = obj.OptimalMoves();
		int c[] = new int[2];
		c = obj.FindBestNewSnake();
		System.out.println(a);
		System.out.println(obj.Query(54, 50));
		System.out.println(obj.Query(54, 95));
		System.out.println(obj.Query(54, 10));
		System.out.println(c[0]+","+ c[1]);
	}
}