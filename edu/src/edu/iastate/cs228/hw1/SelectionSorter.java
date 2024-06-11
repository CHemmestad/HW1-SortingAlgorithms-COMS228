package edu.iastate.cs228.hw1;

/**
 *  
 * @author Caleb Hemmestad
 *
 */

/**
 * 
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		algorithm = "SelectionSort";
	}	
	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		int min;
		for(int i = 0; i < points.length - 1; i++) {
			min = i;
			for(int j = i + 1; j < points.length; j++) {
				if(pointComparator.compare(points[j], points[min]) < 0) {
					min = j;
				}
			}
			swap(i, min);
		}
	}	
}



























