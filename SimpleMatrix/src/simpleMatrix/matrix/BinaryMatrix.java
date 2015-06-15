package simpleMatrix.matrix;

import simpleMatrix.util.MatrixUtil;

public class BinaryMatrix extends Matrix {
	
	public BinaryMatrix(int size) {
		super(size);
	}
	
	public BinaryMatrix(int columns, int rows) {
		super(columns, rows);
	}
	
	public BinaryMatrix(double[][] matrix, boolean arrayOfColumns) {
		super(matrix, arrayOfColumns);
	}
	
	public BinaryMatrix(boolean[][] matrix, boolean arrayOfColumns) {
		super(matrix.length, matrix[0].length);
		
		Element[][] m = null;
		
		if(arrayOfColumns) {
			m = new Element[ matrix.length ][ matrix[0].length ];
			
			for(int i = 0; i < matrix.length; ++i) {
				for(int j = 0; j < matrix[0].length; ++j) {
					double v = matrix[i][j] ? 1 : 0;
					m[i][j] = new Element( i, j, v );
				}
			}
		} else {
			m = new Element[ matrix[0].length ][ matrix.length ];
			
			for(int i = 0; i < matrix.length; ++i) {
				for(int j = 0; j < matrix[0].length; ++j) {
					double v = matrix[i][j] ? 1 : 0;
					m[j][i] = new Element( j, i, v );
				}
			}
		}
		assignMatrix( m, false );
	}
	
	public int countContinents() {
		int c = 0;
		Element[][] mc = MatrixUtil.copyMatrix(matrix);
		for(int i = 0; i < columns; ++i) {
			for(int j = 0; j < rows; ++j) {
				if( mc[i][j].getValue() == 1) {
					mc = floodAround(mc, i, j);
					++c;
				}
			}
		}
		return c;
	}
	
	private Element[][] floodAround(Element[][] matrix, int i, int j) {
		matrix[i][j] = new Element(i, j, 0);
		
		if( j > 0 && isOne( matrix[i][j-1] ) ) {
			floodAround(matrix, i, j-1);
		}
		if( i > 0 && isOne( matrix[i-1][j] ) ) {
			floodAround(matrix, i-1, j);
		}
		if( j < rows-1 && isOne( matrix[i][j+1] ) ) {
			floodAround(matrix, i, j+1);
		}
		if( i < columns-1 && isOne( matrix[i+1][j] ) ) {
			floodAround(matrix, i+1, j);
		}
		return matrix;
	}
	
	private boolean isOne(Element e) {
		return (e.getValue() == 1) ? true : false;
	}

	public void generateRandom() {
		matrix = MatrixUtil.doubleToElementMatrix( MatrixUtil.generateRandom(columns, rows, 0, 1, false), this );
	}

	@Override
	protected boolean checkElement(Element e) {
		return (e.getValue() == 0 || e.getValue() == 1) ? true : false;
	}
	
}
