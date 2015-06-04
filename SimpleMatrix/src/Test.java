import java.util.Arrays;

import matrix.*;
import matrix.Matrix.Direction;


public class Test {
	
	public static void main(String args[]) {	// TODO: junit tests
		BinaryMatrix matrix = new BinaryMatrix(5, 3);
		
		matrix.generateRandom();
		System.out.println( matrix );

		System.out.println( "Row 2: " + Arrays.toString( matrix.getRow(2) ) );
		System.out.println( "Column 2: " + Arrays.toString( matrix.getColumn(2) ) );
		
		System.out.println( "Islands count: " + matrix.countContinents() + "\n");
		
		System.out.println( "Set [1,1] to 3.14159265: " + matrix.setElementValue(1, 1, 3.14159265) );
		System.out.println( "Set [1,1] to 0: " + matrix.setElementValue(1, 1, 0) + "\n");
		
		CommonMatrix matrix1 = new CommonMatrix(new double[][] {{0, 2}, {1, 5}, {22, 44}}, true);
		System.out.println( matrix1 );
		
		CommonMatrix matrix2 = new CommonMatrix(new double[][] {{0, 2}, {1, 5}, {22, 44}}, false);
		System.out.println( matrix2 );
		
		matrix1.generateRandom(11, 37, true);
		System.out.println( matrix1 );
		
		Matrix.Element el = matrix1.getElement(2, 1);
		System.out.println(el);
		System.out.println( "[2,1] up left neighbour: " + el.getNeighbour(Direction.UP_LEFT) );
		System.out.println( "[2,1] right neighbour: " + el.getNeighbour(Direction.RIGHT) + "\n" );
		
		BinaryMatrix bm = new BinaryMatrix( new boolean[][] {{true, false}, {true, true}, {false, false}}, true );
		System.out.println(bm);
		
	}

}
