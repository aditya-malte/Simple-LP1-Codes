
public class MainClass 
{
	
	public static void main(String args[])
	{
		int visited[][] = {
							{0,0,0},
							{0,0,0},
							{0,0,0}
							};
		//pointer to blank
		int blank_i=1;
		int blank_j=0;
		int start[][] = {{1,8,2},
				  		 {-1,4,3},
				  		 {7,6,5}};
		
		
		/*
		int start[][] = {{-1,1,2},
		  	    		{3,4,5},
		  	    		{6,7,8}};
		*/
		//4+1+4+2+0+2+4+2+3
		int goal[][] = {{1,2,3},
				  	    {4,5,6},
				  	    {7,8,-1}};
		
		
		int up_cost = 1000;
		int down_cost = 1000;
		int left_cost = 1000;
		int right_cost = 1000;
		int min_cost = 1000;
		String decision = "none";	//up, down, left, right
		while(heuristicCost(start, goal)!=0)
		{
			up_cost = 10000;
			down_cost = 10000;
			left_cost = 10000;
			right_cost = 10000;
			min_cost = 10000;
			decision = "none";
			if(blank_i>0)	//then move up
			{
				if(visited[blank_i-1][blank_j]!=1)
					up_cost = heuristicCost(swapBlank(start, blank_i, blank_j, blank_i-1, blank_j), goal);
			}
			
			if(blank_i<2)	//then move down
			{
				if(visited[blank_i+1][blank_j]!=1)
					down_cost = heuristicCost(swapBlank(start, blank_i, blank_j, blank_i+1, blank_j), goal);				
			}
			
			if(blank_j>0)	//then move left
			{
				if(visited[blank_i][blank_j-1]!=1)
					left_cost = heuristicCost(swapBlank(start, blank_i, blank_j, blank_i, blank_j-1), goal);				
			}
			
			if(blank_j<2)	//then move right
			{
				if(visited[blank_i][blank_j+1]!=1)
					right_cost = heuristicCost(swapBlank(start, blank_i, blank_j, blank_i, blank_j+1), goal);				
			}
			
			if(left_cost<min_cost)
			{
				min_cost = left_cost;
				decision = "left";
			}
			
			if(right_cost<min_cost)
			{
				min_cost = right_cost;
				decision = "right";
			}
			
			if(up_cost<min_cost)
			{
				min_cost = up_cost;
				decision = "up";
			}
			
			if(down_cost<min_cost)
			{
				min_cost = down_cost;
				decision = "down";
			}
			
			if(decision.equals("none"))
			{
				System.out.println("ERROR");
				visited[4][4]=2;	//this will cause exception and stop program
			}
			
			
			if(decision.equals("up"))
			{
				start = swapBlank(start, blank_i, blank_j, blank_i-1, blank_j);
				blank_i--;
			}
			else if(decision.equals("down"))
			{
				start = swapBlank(start, blank_i, blank_j, blank_i+1, blank_j);	
				blank_i++;
			}
			else if(decision.equals("left"))
			{
				start = swapBlank(start, blank_i, blank_j, blank_i, blank_j-1);
				blank_j--;
			}
			else if(decision.equals("right"))
			{
				start = swapBlank(start, blank_i, blank_j, blank_i, blank_j+1);	
				blank_j++;
			}
			
			
			for(int i=0; i<3; i++)
			{
				for(int j=0; j<3; j++)
				{
					visited[i][j]=0;
				}
			}
			
			visited[blank_i][blank_j] = 1;
			System.out.println(decision);
			System.out.println(heuristicCost(start, goal));
			for(int i=0; i<3; i++)
			{
				for(int j=0; j<3; j++)
				{
					System.out.print(start[i][j]+",");
				}
				System.out.print("\n");
			}
			System.out.println("");
		}
	}
	
	private static int[][] swapBlank(int[][] start, int blank_i, int blank_j, int blank_i2, int blank_j2) 
	{
		int temp_array[][] = {{0,0,0},
						  {0,0,0},
						  {0,0,0}};
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				temp_array[i][j] = start[i][j];
		
		int temp = temp_array[blank_i][blank_j];
		temp_array[blank_i][blank_j] = temp_array[blank_i2][blank_j2];
		temp_array[blank_i2][blank_j2] = temp;
		return temp_array;
	}

	//find manhattan distance between each element in current and goal matrix
	private static int heuristicCost(int[][] current, int[][] goal) {
		int cost = 0;
		int current_element = -1;
		int goal_i = -1;
		int goal_j = -1;
		boolean found = false;
		for(int i=0; i<3; i++)
		{
			
			
			for(int j=0; j<3; j++)
			{
				found=false;
				current_element = current[i][j];
				//System.out.print(current_element+":"+i+","+j+"||");
				//find i, j of current element in goal matrix
				for(goal_i=0; goal_i<3; goal_i++)
				{
					for(goal_j=0; goal_j<3; goal_j++)
					{
						if(goal[goal_i][goal_j] ==  current_element)
						{
							found=true;
							break;
						}
					}
					
					if(found)
					{
						//System.out.println(goal_i+","+goal_j);
						break;
					}
				}
				if(current_element!=-1)
					cost = cost + Math.abs(goal_i - i) + Math.abs(goal_j - j);
			}
		}		
		return cost;
	}
}
