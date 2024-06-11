package edu.iastate.cs228.hw1;

/**
 *  
 * @author Caleb Hemmestad
 *
 */

/**
 * 
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		super(pts);
		algorithm = "QuickSort";
	}

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 */
	@Override 
	public void sort()
	{
		quickSortRec(0, points.length - 1);
	}
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		if(first >= last) {
			return;
		}
		int split = partition(first, last);
		
		quickSortRec(first, split);
		quickSortRec(split + 1, last);
	}
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		int midIndex = (first + last) / 2;
		Point pivot = points[midIndex];
		
		int left = first;
		int right = last;
		
		while(true) {
			while(pointComparator.compare(points[left], pivot) < 0) {
				left++;
			}
			while(pointComparator.compare(pivot, points[right]) < 0) { 
				right--;
			}
			if(left >= right) {
				break;
			} else{
				swap(left, right);
				left++;
				right--;
			}
		}
		return right; 
	}
}
