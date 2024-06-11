package edu.iastate.cs228.hw1;

/**
 *  
 * @author Caleb Hemmestad
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "MergeSort"; 
	}

	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		mergeSortRec(points);
	}
	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		if(pts.length <= 1) {
			return;
		}
		int median = pts.length / 2;
		Point[] left = new Point[median];
		Point[] right = new Point[pts.length - median];
		left = newArray(pts, 0, median);
		right = newArray(pts, median, pts.length);
		
		mergeSortRec(left);
		mergeSortRec(right);
		
		merge(pts, left, right);
	}

	/**
	 * merges all the arrays after splitting them
	 * @param pts
	 * @param left
	 * @param right
	 */
	private void merge(Point[] pts, Point[] left, Point[] right) {
		int i = 0;
		int j = 0;
		int k = 0;
		
		while(i < left.length && j < right.length) {
			if(pointComparator.compare(left[i], right[j]) <= 0) {
				pts[k] = left[i];
				k++;
				i++;
			} else {
				pts[k] = right[j];
				k++;
				j++;
			}
		}
		for(; j < right.length; j++, k++) {
			pts[k] = right[j];
		}
		for(; i < left.length; i++, k++) {
			pts[k] = left[i];
		}
	}
	
	/**
	 * creates a new array to split the array into left and right
	 * @param pts
	 * @param min
	 * @param max
	 * @return a new array with the specified elements from old array
	 */
	private Point[] newArray(Point[] pts, int min, int max) {
		Point[] result = new Point[max - min];
		for(int i = min, j = 0; i < max; i++, j++) {
			result[j] = pts[i];
		}
		return result;
	}

}
