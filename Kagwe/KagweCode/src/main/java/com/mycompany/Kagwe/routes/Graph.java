package com.mycompany.Kagwe.routes;

import java.io.*; 
import java.util.*; 
class Graph 
{ 
    static String log1 = "", log2 = "", log3 = "";
    public List bList, dList;
    public Iterator i, j;
    public ArrayList BFSlist, DFSlist;
public class Edge{
		int vertex,weight;
               
		public Edge(int v,int w){
			this.vertex=v; this.weight=w;
		}
		@Override
		public String toString(){
			return "("+vertex+","+weight+")";
		}
	}
    private int V;   // No. of vertices 
    private LinkedList<Edge> adj[]; //Adjacency Lists 
  
    Graph(int v) 
    { 
        V = v; 
        adj = new LinkedList[v]; 
        for (int i=0; i<v; ++i) 
            adj[i] = new LinkedList(); 
    } 
  
   
    void addEdge(int v,int dest,int weight) 
    { 
        Edge e=new Edge(dest,weight);
        adj[v].add(e);
    } 
  @Override
	public String toString(){
		String result="";
               
		for(int i=0;i<adj.length;i++)
                       
			result+=i+"=>"+adj[i]+"\n";
		return result;
	}
    void BFS(int s,int goal) 
    { 
        bList = new ArrayList(); //list of our path
        BFSlist = new ArrayList(); //list of our costs
        boolean visited[] = new boolean[V]; 
  
        LinkedList<Integer> queue = new LinkedList<Integer>(); //queue for our towns
        LinkedList<Integer> queue1 = new LinkedList<Integer>(); //queue for our costs
        queue1.addLast(0);
        visited[s]=true; //we initialize our source as visited
        queue.add(s); //we add the source to the queue
        int cost=0,c=0;// we initialize the cost to 0
        while (queue.size() != 0) //while q is not empty
        { 
            s = queue.poll(); //take 1st element of q and make it the source
            c= queue1.poll();
            bList.add(s);//source is added to the list
            cost+=c; //cost is incremented
            if(s==goal)break;
           
            Iterator<Edge> i = adj[s].listIterator(); 
            int n;
            
            while (i.hasNext())
            { 
                Edge e = i.next();
                n=e.vertex;
                
                if (!visited[n]) 
                { 
                    visited[n] = true; //if neighbor is not visited, it is visited and marked as visited
                    queue.add(n); //node is added to q
                    queue1.add(e.weight); //cost is added to q1
                } 
                
            } 
            
        } 
        i = bList.iterator();
        while(i.hasNext()){
            int k = (int) i.next();
            if(k==0){BFSlist.add("Nairobi");}
            else if(k==1){BFSlist.add("Kisumu");}
            else if(k==2){BFSlist.add("Meru");}
            else if(k==3){BFSlist.add("Garissa");}
            else if(k==4){BFSlist.add("Malindi");}
            else if(k==5){BFSlist.add("Lamu");}
            
        }
        log1 = BFSlist.toString();
        log2 = Integer.toString(cost);
        log3 = " OPTIMAL ROUTE: " +log1+"\n OPTIMAL DISTANCE: "+log2+" KM\n";
    } 
    void DFS(int s,int goal) 
        { 
            dList = new ArrayList(); //list of our path
            DFSlist = new ArrayList(); //list of costs
            Vector<Boolean> visited = new Vector<Boolean>(V); 
            for (int i = 0; i < V; i++) 
                visited.add(false); 
                  Stack<Integer> stack = new Stack<>(); 
             Stack<Integer> stack1 = new Stack<>();   
            
            stack.push(s); //we add our source to the stack
            stack1.push(0);
            int cost=0,c; 
            while(stack.empty() == false) 
            { 
                s = stack.peek(); //stack is lifo, so we take the last element as the source
                c= stack1.peek(); //the cost is checked
                stack.pop(); 
                stack1.pop();
                cost+=c;
                
                if(visited.get(s) == false) 
                { 
                    dList.add(s); // if node is not visited, it is visited, marked as visited and added to our list
                    visited.set(s, true); 
                } 
                if(s==goal)break; 
                
                Iterator<Edge> iterator = adj[s].iterator(); 
                  
                while (iterator.hasNext())  //this code checks its neighbors
                { 
                    
                    Edge e = iterator.next();
                    int v=e.vertex;
                    if(!visited.get(v)){ //checks if neighbor is not visited, it is added to stack and the cost  is added
                        stack.push(v); 
                        stack1.push(e.weight);
                    } 
                        
                } 
                  
            }
            j = dList.iterator();
            while(j.hasNext())
            {
                int k = (int) j.next();
                if(k==0){DFSlist.add("Nairobi");}
                else if(k==1){DFSlist.add("Kisumu");}
                else if(k==2){DFSlist.add("Meru");}
                else if(k==3){DFSlist.add("Garissa");}
                else if(k==4){DFSlist.add("Malindi");}
                else if(k==5){DFSlist.add("Lamu");}
            
            }
            log1 = DFSlist.toString();
            log2 = Integer.toString(cost);
            log3 = " OPTIMAL ROUTE: " +log1+"\n OPTIMAL DISTANCE: "+log2+" KM\n";
        } 
} 