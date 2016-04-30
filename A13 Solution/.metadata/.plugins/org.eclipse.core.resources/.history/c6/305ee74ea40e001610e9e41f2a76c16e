/**
 * 
 */
package assignment13;

import java.util.ArrayList;

/**
 * This class is what we will test your code on. If your BestPath
 * objects equal ours (using the .equals method given) then you will
 * pass the tests. Do no modify anything that is given (use it of
 * course but don't change names etc.)
 * 
 * @author David Reeves
 * @author Naser Abu-Rmaileh
 */
public class BestPath {
	
	public ArrayList<String> getPath ()
    {
        return path;
    }

    public void setPath (ArrayList<Airport> path)
    {
        this.path = new ArrayList<String>();
        for(int i = 0; i<path.size(); i++)
        {
            this.path.add(path.get(i).getAirport());
        }
    }

    public double getPathLength ()
    {
        return pathLength;
    }

    public void setPathLength (double pathLength)
    {
        this.pathLength = pathLength;
    }

    /**
	 * This should contain the nodes between the origin and destination
	 * inclusive. For example if I want the path between SLC and MEM it
	 * should have SLC (index 0), MEM (index 1). If there are lay overs
	 * it should include them in between (turns out you can fly to Memphis
	 * from here directly).
	 */
	private ArrayList<String> path;
	
	/**
	 * Since some path costs are going to be doubles sometimes use a double
	 * when costs are integers cast to a double.
	 */
	private double pathLength;

	@Override
	public boolean equals(Object o) {
		if (o instanceof BestPath) {
			BestPath other = (BestPath) o;
			return this.pathLength == other.pathLength && this.path.equals(other.path);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Path Length: " + pathLength + "\nPath: "+ this.path;
	}
	
	/**
	 * A simple method used to set the path list to the input list.
	 * This method is a helper method which is used when dealing with any "garbage input" that the 
	 * user may enter, such as null, or an empty string, or an airport origin/destination name which doesn't exist.
	 * 
	 * @param garbageInput -- The ArrayList used to deal with bad input.
	 */
    public void setStringArray (ArrayList<String> garbageInput)
    {
       this.path = garbageInput;
    }
    
    /**Helper method for testing. Returns an ArrayList version of the BestPath object.
     * 
     * @return -- The BestPath as an ArrayList
     */
    public String [] toArray()
    {
    	String [] pathList = new String [path.size()];
    	for (int i = 0; i <  path.size(); i ++)
    	{
    		pathList[i] = path.get(i);
    	}
    	
    	return pathList;
    }
    
    
}