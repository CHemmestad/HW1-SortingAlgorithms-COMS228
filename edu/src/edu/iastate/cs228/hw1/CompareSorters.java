package edu.iastate.cs228.hw1;

/**
 *  
 * @author Caleb hemmestad
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;

import java.util.Scanner; 
import java.util.Random; 

//Output example
/*
algorithm size time (ns)
----------------------------------
SelectionSort 1000 49631547
InsertionSort 1000 22604220
MergeSort     1000 2057874
QuickSort     1000 1537183
----------------------------------
*/


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		Random rand = new Random();
		Scanner scnr = new Scanner(System.in);
		PointScanner[] scanners = new PointScanner[4]; 
		
		int counter = 1;
		boolean done = false;
		int selection;
		
		System.out.println("keys: 1 (random integers) 2 (file input) 3 (exit)");
		
		while(!done) {
			System.out.printf("Trial %d: ", counter);
			selection = scnr.nextInt();
			
			switch(selection) {
				case 1:
					System.out.print("Enter number of random points: ");
					int numCount = scnr.nextInt();
					System.out.println();
					
					Point[] points = generateRandomPoints(numCount, rand);
					
					System.out.println("algorithm     size time (ns)");
					System.out.println("-----------------------------");
					for(int i = 0; i < 4; i++) {
						scanners[i] = new PointScanner(points, Algorithm.values()[i]);
						scanners[i].scan();
						System.out.print(scanners[i].stats());
					}
					System.out.println("-----------------------------\n");
					break;
				case 2:
					System.out.println("Points from a file");
					System.out.print("File name: ");
					String name = scnr.next();
					System.out.println();
					
					System.out.println("algorithm     size time (ns)");
					System.out.println("-----------------------------");
					for(int i = 0; i < 4; i++) {
						scanners[i] = new PointScanner(name, Algorithm.values()[i]);
						scanners[i].scan();
						System.out.print(scanners[i].stats());
					}
					System.out.println("-----------------------------\n");
					break;
				case 3:
					done = true;
					System.out.println("Exited");
					break;
				default:
					System.out.println("Invalid option");
					counter--;
					break;
			}
			counter++;
		}
		scnr.close();
		
	}
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		if(numPts < 1) {
			throw new IllegalArgumentException("Number of points needs to be at least one");
		}
		
		Point[] points = new Point[numPts];
		
		int x;
		int y;
		
		for(int i = 0; i < numPts; i++) {
			x = rand.nextInt(101) - 50;
			y = rand.nextInt(101) - 50;
			points[i] = new Point(x, y);
		}
		
		return points;
	}
	
}
