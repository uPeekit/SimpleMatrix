package simpleMatrix.matrix;

import simpleMatrix.util.MatrixUtil;

public class CommonMatrix extends Matrix implements Cloneable {

	public CommonMatrix(double[][] matrix, boolean arrayOfColumns) {
		super(matrix, arrayOfColumns);
	}

	public CommonMatrix(int columns, int rows) {
		super(columns, rows);
	}

	public CommonMatrix(int size) {
		super(size);
	}

	public void generateRandom(int min, int max, boolean fractional) {
		matrix = MatrixUtil.doubleToElement2DArray( MatrixUtil.generateRandom(columns, rows, min, max, fractional), this );
	}

	@Override
	protected boolean checkElement(Element e) {
		return true;
	}

}
