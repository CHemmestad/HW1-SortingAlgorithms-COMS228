package edu.iastate.cs228.hw1;

/**
 * 
 * @author Caleb Hemmestad
 *
 */

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		if(pts == null || pts.length == 0) {
            throw new IllegalArgumentException("pts cant be null or empty");
        }
		points = new Point[pts.length];
		for(int i = 0; i < pts.length; i++) {
			points[i] = pts[i];
		}
		sortingAlgorithm = algo;
	}

	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		File file = new File(inputFileName);
		Scanner scnr = new Scanner(file);

		int count = 0;
		while(scnr.hasNextInt()) {
			scnr.nextInt();
			count++;
		}
		scnr.close();
		if(count % 2 != 0) {
			throw new InputMismatchException("Odd number of integers");
		}
		
		scnr = new Scanner(file);
			
		sortingAlgorithm = algo;
		int numOfPoints = count / 2;
		Point[] pts = new Point[numOfPoints];
		for(int i = 0; i < numOfPoints; i++) {
			pts[i] = new Point(scnr.nextInt(), scnr.nextInt());
		}
		scnr.close();
		
		points = pts;
	}

	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 */
	public void scan()
	{
		AbstractSorter aSorter;
		
 		switch(sortingAlgorithm) {
			case SelectionSort:
				aSorter = new SelectionSorter(points);
				break;
			case InsertionSort:
				aSorter = new InsertionSorter(points);
				break;
			case MergeSort:
				aSorter = new MergeSorter(points);
				break;
			case QuickSort:
				aSorter = new QuickSorter(points);
				break;
			default:
				throw new IllegalArgumentException("Invalid sorting method");
		}
		
		int medianX;
		int medianY;
		
		long startTime = System.nanoTime();
		aSorter.setComparator(0);
		aSorter.sort();
		long endTime = System.nanoTime();
		long sortXTime = endTime - startTime;
		aSorter.printPoints(); //Just to see if its actually working
		medianX = aSorter.getMedian().getX();
		
		startTime = System.nanoTime();
		aSorter.setComparator(1);
		aSorter.sort();
		endTime = System.nanoTime();
		long sortYTime = endTime - startTime;
		aSorter.printPoints(); //Just to see if its actually working
		medianY = aSorter.getMedian().getY();
		
		medianCoordinatePoint = new Point(medianX, medianY);
		scanTime = (sortXTime + sortYTime);
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		String results = "";
		switch(sortingAlgorithm) {
			case InsertionSort:
				results += "InsertionSort ";
				break;
			case MergeSort:
				results += "MergeSort     ";
				break;
			case QuickSort:
				results += "QuickSort     ";
				break;
			case SelectionSort:
				results += "SelectionSort ";
				break;
			default:
				results += "Invalid Sort  ";
				break;
		}
		results += "%4d ".formatted(points.length);
		results += scanTime;
		results += "\n";
		return results;
	}
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		String mcp = "MCP: (%d, %d)\n".formatted(medianCoordinatePoint.getX(), medianCoordinatePoint.getY());
		return mcp;
	}

	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		FileWriter writer;
		
		try {
			writer = new FileWriter("MCP.txt", true);
			switch(sortingAlgorithm) {
				case InsertionSort:
					writer.write("InsertionSort - ");
					break;
				case MergeSort:
					writer.write("MergeSort - ");
					break;
				case QuickSort:
					writer.write("QuickSort - ");
					break;
				case SelectionSort:
					writer.write("SelectionSort - ");
					break;
				default:
					writer.write("Invalid Sort - ");
					break;
			}
			writer.write(this.toString());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * For testing
	 * @return points
	 */
	public Point[] getPoints() {
		return points;
	}

	

		
}
