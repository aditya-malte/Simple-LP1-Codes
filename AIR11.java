import java.util.LinkedList;
import java.util.Scanner;

class Node
{
	String id;
	int path_cost;	//cost from previous node to this node
	int net_path_cost;	//cost from start node to this node
	int heuristic_cost; //cost from node to goal
	Node left;
	Node right;
	
	Node()
	{
		id="Empty";
		net_path_cost=-1;
		path_cost= -1;
		heuristic_cost = -1;
		left=null;
		right=null;
	}
	
	Node(String id, int path_cost, int heuristic_cost)
	{
		this.id = id;
		this.net_path_cost = -1;
		this.path_cost = path_cost;
		this.heuristic_cost = heuristic_cost;
	}
}

class Tree
{
	Node head;
	Scanner sc;
	Tree()
	{
		sc = new Scanner(System.in);
		System.out.println("Enter id, path_cost and heuristic cost");
		String id = sc.next();
		int heuristic_cost = sc.nextInt();
		head.id = id;
		head.path_cost = 0;
		head.net_path_cost = 0;
		head.heuristic_cost = heuristic_cost;
	}
	
	void createTree()
	{
		sc = new Scanner(System.in);
		String choice;
		System.out.println("id G is reserved for goal node");
		System.out.println("Left node to head? y/n");
		choice = sc.next();
		if(choice.equals("y"))
		{
			System.out.println("Enter id, path_cost and heuristic cost");
			String id = sc.next();
			int path_cost = sc.nextInt();
			int heuristic_cost = sc.nextInt();
			Node temp = new Node();
			temp.id = id;
			temp.path_cost = path_cost;
			temp.net_path_cost = path_cost;
			temp.heuristic_cost = heuristic_cost;
			head.left = temp;
			createTree(head.left);
		}
		
		System.out.println("Right node to head? y/n");
		choice = sc.next();
		if(choice.equals("y"))
		{
			System.out.println("Enter id, path_cost and heuristic cost");
			String id = sc.next();
			int path_cost = sc.nextInt();
			int heuristic_cost = sc.nextInt();
			Node temp = new Node();
			temp.id = id;
			temp.path_cost = path_cost;
			temp.net_path_cost = path_cost;
			temp.heuristic_cost = heuristic_cost;
			head.right = temp;
			createTree(head.right);
		}	
	}
	void createTree(Node node)
	{
		if(node.id.equals("G"))
			return;
		sc = new Scanner(System.in);
		String choice;
		
		System.out.println("Left node to "+node.id+"? y/n");
		choice = sc.next();
		if(choice.equals("y"))
		{
			System.out.println("Enter id, path_cost and heuristic cost");
			String id = sc.next();
			int path_cost = sc.nextInt();
			int heuristic_cost = sc.nextInt();
			Node temp = new Node();
			temp.id = id;
			temp.path_cost = path_cost;
			temp.net_path_cost = temp.path_cost + node.net_path_cost;
			temp.heuristic_cost = heuristic_cost;
			node.left = temp;
			createTree(node.left);
		}
		
		System.out.println("Right node to "+node.id+"? y/n");
		choice = sc.next();
		if(choice.equals("y"))
		{
			System.out.println("Enter id, path_cost and heuristic cost");
			String id = sc.next();
			int path_cost = sc.nextInt();
			int heuristic_cost = sc.nextInt();
			Node temp = new Node();
			temp.id = id;
			temp.path_cost = path_cost;
			temp.net_path_cost = temp.path_cost + node.net_path_cost;
			temp.heuristic_cost = heuristic_cost;
			node.right = temp;
			createTree(node.right);
		}	
		
	}

	void bestFirstSearch() 
	{
		LinkedList<Node> open_list = new LinkedList<Node>();
		System.out.println("Visited: "+head.id);
		if(head.left!=null)
			open_list.add(head.left);
		if(head.right!=null)
			open_list.add(head.right);
		int min_cost;
		int min_cost_pos;
		Node min_cost_node;
		while(!open_list.isEmpty())
		{
			
			//find element with minimum cost
			min_cost = 10000;
			min_cost_pos = -1;
			for(int i=0; i<open_list.size();i++)
			{
				if(min_cost>open_list.get(i).heuristic_cost)
				{
					min_cost = open_list.get(i).heuristic_cost;
					min_cost_pos = i;
				}
			}
			min_cost_node = open_list.remove(min_cost_pos);
			System.out.println("Visited: "+ min_cost_node.id);
			if(min_cost_node.id.equals("G"))	//reached goal node
				return;
			if(min_cost_node.left!=null)
			{
				open_list.add(min_cost_node.left);
			}
			if(min_cost_node.right!=null)
			{
				open_list.add(min_cost_node.right);
			}
		}
	}
	void aStarSearch()
	{
		LinkedList<Node> open_list = new LinkedList<Node>();
		System.out.println("Visited: "+head.id);
		if(head.left!=null)
			open_list.add(head.left);
		if(head.right!=null)
			open_list.add(head.right);
		int min_cost;
		int min_cost_pos;
		Node min_cost_node;
		while(!open_list.isEmpty())
		{
			
			//find element with minimum cost
			min_cost = 10000;
			min_cost_pos = -1;
			for(int i=0; i<open_list.size();i++)
			{
				if(min_cost>open_list.get(i).heuristic_cost)
				{
					min_cost = open_list.get(i).heuristic_cost + open_list.get(i).net_path_cost;
					min_cost_pos = i;
				}
			}
			min_cost_node = open_list.remove(min_cost_pos);
			System.out.println("Visited: "+ min_cost_node.id);
			if(min_cost_node.id.equals("G"))	//reached goal node
				return;
			if(min_cost_node.left!=null)
			{
				open_list.add(min_cost_node.left);
			}
			if(min_cost_node.right!=null)
			{
				open_list.add(min_cost_node.right);
			}
		}
		
	}
	
}
public class MainClass 
{
	public static void main(String args[])
	{
		
		Tree tree = new Tree();
		tree.createTree();
		//tree.bestFirstSearch();
		tree.aStarSearch();
	}
}
