package edu.iastate.cs228.hw1;

/**
 *  
 * @author Caleb Hemmestad
 *
 */

/**
 * 
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "InsertionSort";
	}	

	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 */
	@Override 
	public void sort()
	{
		int j;
		for(int i = 0; i < points.length - 1; i++) {
			j = i + 1;
			while(j > 0 && pointComparator.compare(points[j], points[j - 1]) < 0) {
				swap(j, j - 1);
				j--;
			}
		}
	}		
}
