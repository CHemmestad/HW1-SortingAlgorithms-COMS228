package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import org.junit.jupiter.api.Test;

class Tester {

	@Test
	void pointTest() {
		Point point = new Point(4, 2);
		assertEquals(4, point.getX());
		assertEquals(2, point.getY());
		
		Point point2 = new Point();
		assertEquals(0, point2.getX());
		assertEquals(0, point2.getY());
		
		Point point3 = new Point(point);
		assertEquals(4, point3.getX());
		assertEquals(2, point3.getY());
		
		assertEquals(false, Point.xORy);
		Point.setXorY(true);
		assertEquals(true, Point.xORy);
		
		Point point5 = new Point(8, 1);
		Point point6 = new Point(4, 4);
		Point point7 = new Point(1, 8);
		Point point8 = new Point(4, 2);
		Point point9 = new Point(4, 6);
		Point point10 = new Point(2, 4);
		Point point11 = new Point(6, 4);
		
		assertEquals(true, point6.equals(point6));
		assertEquals(false, point6.equals(point8));
		assertEquals(false, point6.equals(point10));
		assertEquals(false, point6.equals(null));
		assertEquals(false, point6.equals(new Object()));
		
		Point.setXorY(true);
		assertEquals(0, point5.compareTo(point5));
		assertEquals(1, point5.compareTo(point6));
		assertEquals(1, point5.compareTo(point7));
		assertEquals(-1, point6.compareTo(point5));
		assertEquals(0, point6.compareTo(point6));
		assertEquals(1, point6.compareTo(point7));
		assertEquals(-1, point7.compareTo(point5));
		assertEquals(-1, point7.compareTo(point6));
		assertEquals(0, point7.compareTo(point7));
		assertEquals(1, point6.compareTo(point8));
		assertEquals(-1, point6.compareTo(point9));
		
		Point.setXorY(false);
		assertEquals(0, point5.compareTo(point5));
		assertEquals(-1, point5.compareTo(point6));
		assertEquals(-1, point5.compareTo(point7));
		assertEquals(1, point6.compareTo(point5));
		assertEquals(0, point6.compareTo(point6));
		assertEquals(-1, point6.compareTo(point7));
		assertEquals(1, point7.compareTo(point5));
		assertEquals(1, point7.compareTo(point6));
		assertEquals(0, point7.compareTo(point7));
		assertEquals(1, point6.compareTo(point10));
		assertEquals(-1, point6.compareTo(point11));
		
		assertEquals("(8, 1)", point5.toString());
	}
	
	@Test
	void algorithmTest() {
		@SuppressWarnings("unused")
		Algorithm alg = Algorithm.SelectionSort;
	}
	
	@Test
	void PointScannerTest() throws InputMismatchException, FileNotFoundException {
		Point[] pts = {new Point(2, 0), new Point(5, 9), new Point(3, 6), new Point(2, 2), new Point(7, 8)};
		PointScanner ptScan = new PointScanner(pts, Algorithm.SelectionSort);
		for(int i = 0; i < pts.length; i++) {
			assertEquals(pts[i].getX(), ptScan.getPoints()[i].getX());
			assertEquals(pts[i].getY(), ptScan.getPoints()[i].getY());
		}
		ptScan = new PointScanner("Points", Algorithm.SelectionSort);
		for(int i = 0; i < pts.length; i++) {
			assertEquals(pts[i].getX(), ptScan.getPoints()[i].getX());
			assertEquals(pts[i].getY(), ptScan.getPoints()[i].getY());
		}
		
		ptScan.scan();
		System.out.print(ptScan.stats());
		System.out.print(ptScan.toString());
		ptScan.writeMCPToFile();
		
		ptScan = new PointScanner("Points", Algorithm.InsertionSort);
		ptScan.scan();
		System.out.print(ptScan.stats());
		System.out.print(ptScan.toString());
		ptScan.writeMCPToFile();
		
		ptScan = new PointScanner("Points", Algorithm.MergeSort);
		ptScan.scan();
		System.out.print(ptScan.stats());
		System.out.print(ptScan.toString());
		ptScan.writeMCPToFile();
		
		ptScan = new PointScanner("Points", Algorithm.QuickSort);
		ptScan.scan();
		System.out.print(ptScan.stats());
		System.out.print(ptScan.toString());
		ptScan.writeMCPToFile();
	}
}
