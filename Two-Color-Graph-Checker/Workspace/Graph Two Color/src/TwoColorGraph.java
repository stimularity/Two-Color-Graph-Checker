import java.io.*;
import java.util.*;

public class TwoColorGraph {
	//Global Variables
	static int numnodes = 0;
	static int edge = 0;
	static int type = 0; //0 = red, 1 = blue.
	static boolean twocolor = true;
	static String output = ""; //Output of the entire program.
	
	public static void main(String[] args) throws FileNotFoundException
	{
		
		//Initialize the Graph
		try {
			File f = new File(args[0]); //Find File
			Scanner in = new Scanner(f); //Open File
			int numnodes = Integer.parseInt(in.nextLine()); //Get total number of nodes from the text file
			Vertex[] nodes = new Vertex[numnodes+1]; //Initialize graph array size with first line in file
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
				edge++; //Count number of edges
			}
			in.close(); //Close the file. For fun really. 
			depthFirstSearch(1, "red", nodes); //Run the bloody search. 
			addLineToOutput("Graph with " + numnodes + " verticies and " + edge + " edges."); //Add to output file
			
			//Output Diagnostics
			if(twocolor){ //This graph is two colorable
				addLineToOutput("Yes: Two-Colorable (The graph is two colorable.) \n");
				for(int i=1; i<nodes.length; i++)
				{
					addLineToOutput("Node: " + i + " (" + nodes[i].type + ")");
					for(int j=0; j<nodes[i].edges.length; j++)
					{
						addLineToOutput("\t Connects to node: " + nodes[i].edges[j] + " (" + nodes[ nodes[i].edges[j] ].type + ")");
					}
				}
			} else { //This graph is not two colorable
				addLineToOutput("No: Two-Colorable (The graph is not two colorable) \n");
				String[] odd = loop.split("\\,");
				String badloop = ""; boolean go = false;
				for(int i=0; i<odd.length; i++)
				{
					if(Integer.parseInt(odd[i]) == bad2){go = true;}
					if(go){ badloop += odd[i] + ", "; }
					if(Integer.parseInt(odd[i]) == bad1){go = false;}
				}
				addLineToOutput(badloop);
				//Print out offending nodes
				addLineToOutput("Bad edges between vertex " + bad1);
				for(int i=0; i<nodes[bad1].edges.length; i++)
				{
					addLineToOutput("\t Connects to node: " + nodes[bad1].edges[i] + " (" + nodes[ nodes[bad1].edges[i] ].type + ")");
				}
				addLineToOutput("and edges between vertex " + bad2);
				for(int i=0; i<nodes[bad2].edges.length; i++)
				{
					addLineToOutput("\t Connects to node: " + nodes[bad2].edges[i] + " (" + nodes[ nodes[bad2].edges[i] ].type + ")");
				}
				
			}
			
			//50 or more edges gets written to file else, output to terminal. 
			if(edge >= 50){ 
				System.out.println("Output can be found in output.txt");
				try {
					FileWriter outFile = new FileWriter("output.txt");
					PrintWriter out = new PrintWriter(outFile);
					// Write text to file
					out.println(output);
					out.close(); //Close output file
				} catch (IOException e){ e.printStackTrace(); } //Catching all errors like a boss.
			} else {
				System.out.println(output); //Output is acceptable for terminal. Enjoy. 
			}
			
		} catch(Exception g) { g.printStackTrace(); } //Throw Error, break, crash burn, run command "rm -rf /"	
	}//
	
	public static void addLineToOutput(String in)
	{
		output += (in + "\n");
	}
	static int bad1 = 0;
	static int bad2 = 0;
	static String loop = ""; //The loop of bad loopies
	public static void depthFirstSearch(int pointer, String type, Vertex[] nodes) //Recursive depth first search part of the program
	{
		
		nodes[pointer].setColor("gray"); //Set current node to gray
		nodes[pointer].setType(type); //Set the type of the node 0 or 1, red or blue
		loop += pointer + ",";
		for(int i=0; i<nodes[pointer].edges.length; i++) //Go through each edge in current node
		{
			int next = nodes[pointer].edges[i];
			if(nodes[next].type.equals(nodes[pointer].type)){ //Check if any surrounding nodes are already the same color.
				twocolor = false; //The graph is not two colorable.
				//Save bad areas of graph.
				bad1 = next;
				bad2 = pointer;
				//System.out.println("Node " + next + " is the same color as " + pointer); 
			}
			if(nodes[next].color.equals("white")) //If the node is white and unexplored
			{
				//System.out.println("Move: " + pointer +  " -> " + next);
				depthFirstSearch(next,nextType(type), nodes); //Search unexplored nodes. 
			} //else { System.out.println("Skipping: " + pointer +  " -> " + next);}
		}
		//System.out.println("Marking: " + pointer + " black " + nodes[pointer].type);
		nodes[pointer].setColor("black"); //Sets node to black, we know its done now. 
	}//
	
	static public String nextType(String t) //Simply alternates between red and blue so I dont have to.
	{
		if(t.equals("red"))
		{
			return "blue";
		} else {
			return "red";
		}
	}//
	


}//End Main

class Vertex{
	String color = "white";
	int[] edges = new int[0]; //Number of edges connected to the node
	int number = 0; //This is simply the number of the node in the array -1.
	String type = ""; //Type of node, red or blue. 
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
	public void setColor(String val) //Set the color of the node while searching. 
	{
		color = val;
	}
	public void setType(String t) //Type of node, red or blue. 
	{
		if(type.equals(""))
		{
			type = t;
		}
	}
}//End Vertex

class Puppy{ //An Essential substructure to the graph.
	   public Puppy(String name){
	      System.out.println("Passed Name is :" + name ); 
	   }
	   public static void main(String []args){
	      Puppy myPuppy = new Puppy( "Goober" );
	   }
}//