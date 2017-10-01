package org.peekit.simplematrix;
import java.util.Arrays;
import java.util.Iterator;

import org.peekit.simplematrix.exception.FailedOperationException;
import org.peekit.simplematrix.matrix.*;
import org.peekit.simplematrix.matrix.Matrix.Direction;


public class Test {
	
	public static void main(String args[]) {	// TODO: junit tests
		BinaryMatrix matrix = new BinaryMatrix(5, 3);
		
		matrix.generateRandom();
		System.out.println( matrix );

		System.out.println( "Row 2: " + Arrays.toString( matrix.getRow(2) ) );
		System.out.println( "Column 2: " + Arrays.toString( matrix.getColumn(2) ) );
		System.out.println( "Main diagonal: " + Arrays.toString( matrix.getMainDiagonal() ) );
		System.out.println( "Minor diagonal: " + Arrays.toString( matrix.getMinorDiagonal() ) );
		System.out.println( "Main diagonal matrix:\n" + matrix.getMainDiagonalMatrix() );
		System.out.println( "Minor diagonal matrix:\n" + matrix.getMinorDiagonalMatrix() );
		
		System.out.println( "Islands count: " + matrix.countContinents() + "\n");
		
		System.out.println( "Set [1,1] to 3.14159265: " + matrix.setElementValue(1, 1, 3.14159265) );
		System.out.println( "Set [1,1] to 0: " + matrix.setElementValue(1, 1, 0) + "\n");
		
		CommonMatrix matrix1 = new CommonMatrix(new double[][] {{0, 2}, {1, 5}, {22, 44}}, true);
		System.out.println( "matrix1:\n" + matrix1 );
		
		CommonMatrix matrix2 = new CommonMatrix(new double[][] {{0, 2}, {1, 5}, {22, 44}}, false);
		System.out.println( "matrix2:\n" + matrix2 );
		
		matrix1.generateRandom(11, 37, true);
		System.out.println( "Random matrix between 11 and 37:\n" + matrix1 );
		
		Matrix.Element el = matrix1.getElement(2, 1);
		System.out.println(el);
		System.out.println( "[2,1] up left neighbour: " + el.getNeighbour(Direction.UP_LEFT) );
		System.out.println( "[2,1] right neighbour: " + el.getNeighbour(Direction.RIGHT) + "\n" );
		
		BinaryMatrix bm = new BinaryMatrix( new boolean[][] {{true, false}, {true, true}, {false, false}}, false );
		BinaryMatrix bmEq = new BinaryMatrix( new boolean[][] {{true, false}, {true, true}, {false, false}}, false );
		System.out.println("bm matrix:\n" + bm);
		
		System.out.println("Equality works: " + bm.equals(bmEq));
		try {
			Matrix bmClone = (BinaryMatrix)bm.clone();
			System.out.println("Clone same reference: " + (bm == bmClone));
			System.out.println("Clone equality: " + (bm.equals(bmClone)));
			System.out.println("Same reference to elements: " + (bm.getElement(1, 1) == bmClone.getElement(1, 1)));
			System.out.println();
		} catch (CloneNotSupportedException e) { }
		
		System.out.println("bm [0,2] opposite: " + bm.getElement(0, 2).getDiagonalOpposite());
		System.out.println("bm [0,1] opposite: " + bm.getElement(0, 1).getDiagonalOpposite());
		System.out.println();
		
		try {
			System.out.println("Multiplying binary matrix by 2 with binary restrictions:");
			bm.multiplyByRestricted(2);
		} catch (FailedOperationException e) {
			System.out.println(e);
		}
		System.out.println("Without:");
		System.out.println(bm.multiplyBy(2));
		try {
			System.out.println( "matrix2 multiplyed by 2.2:\n" + matrix2.multiplyByRestricted(2.2) );
		} catch (FailedOperationException e) {
			System.out.println(e);
		}
		try {
			Matrix multiplied = new CommonMatrix(new double[][] {{1, 0}, {3, 4}, {2, -1}}, true).multiplyBy(
									new CommonMatrix(new double[][] {{2, 3, 0}, {0, -2, 1}, {-1, 1, 2}, {1, 2, 3}}, true)
								);
			System.out.println("Matrices multiplication works: " + 
				multiplied.equals( new CommonMatrix(new double[][] {{11, 12}, {-4, -9}, {6, 2}, {13, 5}}, true) ) );
		} catch (FailedOperationException e) {
			System.out.println(e);
		}
		try {
			System.out.println("Summing works: " + new CommonMatrix(new double[][] {{3, 7}, {8, 5}}, true).sum(
									new CommonMatrix(new double[][] {{8, 4}, {3, 6}}, true)).equals(
										new CommonMatrix(new double[][] {{11, 11}, {11, 11}}, true)
								));
		} catch (FailedOperationException e) {
			System.out.println(e);
		}
		System.out.println("bm transposed:\n" + bm.transpose());
		
		System.out.println("Using iterator for matrix2:");
		Iterator<Matrix.Element> iter = matrix2.iterator();
		while(iter.hasNext())
			System.out.println(iter.next());
	}

}
