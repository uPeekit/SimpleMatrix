package matrix;

import util.MatrixUtil;

public class BinaryMatrix extends Matrix{
	
	public BinaryMatrix(int size) {
		super(size);
	}
	
	public BinaryMatrix(int columns, int rows) {
		super(columns, rows);
	}
	
	public BinaryMatrix(double[][] matrix) {
		super(matrix);
	}
	
	public BinaryMatrix(boolean[][] matrix) {
		super(matrix.length, matrix[0].length);
		
		Element[][] m = new Element[ matrix.length ][ matrix[0].length ];
		
		for(int i = 0; i < matrix.length; ++i) {
			for(int j = 0; j < matrix[0].length; ++j) {
				double v = matrix[i][j] ? 1 : 0;
				m[i][j] = new Element( i, j, v );
			}
		}
		assignMatrix(m);
	}
	
	public int countContinents() {
		int c = 0;
		Element[][] mc = MatrixUtil.copyMatrix(matrix, this);
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
		matrix[i][j].setValue(0);
		
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
