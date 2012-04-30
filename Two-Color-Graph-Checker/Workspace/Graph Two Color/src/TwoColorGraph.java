import java.io.*;
import java.util.*;

public class TwoColorGraph {
	public static void main(String[] args) throws FileNotFoundException
	{
		
		//Initialize the Graph
		try {
			File f = new File(args[0]); //Find File
			Scanner in = new Scanner(f); //Open File
			Vertex[] nodes = new Vertex[Integer.parseInt(in.nextLine())+1]; //Initialize graph array size with first line in file
			for(int i=1; i<nodes.length; i++)
			{
				nodes[i] = new Vertex(i); //Create all them vertices.
			}
			while(in.hasNextLine()) //Go through entire file
			{
				String[] line = in.nextLine().split("\\,"); //Split current line at comma to extract two edges
				int edge1 = Integer.parseInt(line[0]); //First edge connects vertex
				int edge2 = Integer.parseInt(line[1]); //To the second edge
				nodes[edge1].addEdge(edge2); //Create edge from node to other node
				nodes[edge2].addEdge(edge1); //Create return edge since its a undirected graph
			}
			in.close(); //Close the file. For fun really. 
			depthFirstSearch(1, nodes);
		} catch(Exception g) { g.printStackTrace(); } //Throw Error, break, crash burn, run command "rm -rf /"
		System.out.println("Hi, your program didn't crash. Good for you dog.");
		
	}//
	
	public static void depthFirstSearch(int pointer, Vertex[] nodes) //Recursive depth first search part of the program
	{
		nodes[pointer].setColor("gray"); //Set current node to gray
		for(int i=0; i<nodes[pointer].edges.length; i++) //Go through each edge in current node
		{
			int next = nodes[pointer].edges[i];
			System.out.println("Checking: " + pointer +  " -> " + next);
			if(nodes[next].color.equals("white"))
			{
				System.out.println("Going Deeper on: " + pointer +  " -> " + next);
				depthFirstSearch(next, nodes);
			} else { System.out.println("Skipping: " + pointer +  " -> " + next);}
		}
		System.out.println("Marking: " + pointer +  " as black");
		nodes[pointer].setColor("black");
	}//
	
	


}

class Vertex{
	//0 = White
	//1 = Grey
	//2 = Black
	String color = "white";
	int[] edges = new int[0];
	int number = 0; //This is simply the number of the node in the array -1. 
	public Vertex(int num)
	{
		number = num; //Give node a name for easier outputs and stuff
	}
	public void addEdge(int edge)
	{
		//Add an edge to vertex. Make a larger array each time. 
		int[] temp = new int[edges.length + 1];
		for(int i=0; i<edges.length; i++)
		{
			temp[i] = edges[i]; //Add all old edges to temp
		}
		temp[edges.length] = edge; //Add final edge
		edges = temp; //Replace edges with updated array
		//for(int i=0; i<edges.length; i++){ System.out.println(edges[i]); System.out.println("...");}
	}
	public void setColor(String val)
	{
		color = val;
	}
}

class Puppy{
	   public Puppy(String name){
	      System.out.println("Passed Name is :" + name ); 
	   }
	   public static void main(String []args){
	      Puppy myPuppy = new Puppy( "Goober" );
	   }
	}