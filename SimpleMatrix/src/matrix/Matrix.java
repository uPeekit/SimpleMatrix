package matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import util.MatrixUtil;
import exception.FailedOperationException;


public abstract class Matrix implements Cloneable, Iterable<Matrix.Element> {
	
	protected Element[][] matrix;
	protected int columns;
	protected int rows;
	
	protected Matrix(int size) {
		if(size <= 0)
			throw new IllegalArgumentException("Size cannot be equal to or less than 0");
		
		assignMatrix( new Element[size][size], true );
	}
	
	protected Matrix(int columns, int rows) {
		if(rows <= 0 || columns <= 0)
			throw new IllegalArgumentException("Row or column number cannot be equal to or less than 0");
		
		assignMatrix( new Element[columns][rows], true );
	}
	
	protected Matrix(double[][] matrix, boolean arrayOfColumns) {
		if(matrix.length == 0)
			throw new IllegalArgumentException("Matrix should have at least one row");
		
		Element[][] m = null;
		
		if(arrayOfColumns) {
			m = new Element[ matrix.length ][ matrix[0].length ];
			
			for(int i = 0; i < matrix.length; ++i) {
				for(int j = 0; j < matrix[0].length; ++j) {
					m[i][j] = new Element(i, j, matrix[i][j]);
				}
			}
		} else {
			m = new Element[ matrix[0].length ][ matrix.length ];
			
			for(int i = 0; i < matrix.length; ++i) {
				for(int j = 0; j < matrix[0].length; ++j) {
					m[j][i] = new Element(j, i, matrix[i][j]);
				}
			}
		}
		assignMatrix( m, false ); 
	}
		
	protected void assignMatrix(Element[][] matrix, boolean zeroOut) {
		this.matrix = matrix;
		this.columns = matrix.length;
		this.rows = matrix[0].length;
		
		if(zeroOut)
			for(int i = 0; i < columns; ++i)
				for(int j = 0; j < rows; ++j)
					this.matrix[i][j] = new Element(i, j, 0);
	}
		
	public boolean setElementValue(int column, int row, double value) {
		if(row < 0 || column < 0)
			return false;
		
		Element e = new Element(column, row, value);
		if( checkElement(e) ) {
			matrix[column][row] = e;
			return true;
		} else {
			return false;
		}
	}
	
	protected void setElement(int column, int row, Element element) {
		matrix[column][row] = element;
	}
	
	public int getElementsCount() {
		return columns * rows;
	}
	
	public int getColumnsCount() {
		return columns;
	}
	
	public int getRowsCount() {
		return rows;
	}
	
	public Element getElement(int column, int row) {
		if(row < 0 || column < 0)
			throw new IllegalArgumentException("Row or column number cannot be less than 0");
		if(row >= rows || column >= columns)
			throw new IllegalArgumentException("Max row number is " + (rows-1) + ", max column number is " + (columns-1));
		
		return matrix[column][row];
	}
	
	public Element[] getColumn(int number) {
		if(number < 0)
			throw new IllegalArgumentException("Column number cannot be less than 0");
		
		return matrix[number];
	}
	
	public Element[] getRow(int number) {
		if(number < 0)
			throw new IllegalArgumentException("Row number cannot be less than 0");
		
		Element[] row = new Element[ columns ];
		
		for(int i = 0; i < columns; ++i) {
			row[i] = matrix[i][number];
		}
		return row;
	}

	public Element[] getMainDiagonal() {
		int n = (columns < rows) ? columns : rows;
		
		Element[] diagonal = new Element[n];
		for(int i = 0; i < n; ++i)
			diagonal[i] = matrix[i][i];
			
		return diagonal;
	}
		
	public Matrix getMainDiagonalMatrix() {
		int n = (columns < rows) ? columns : rows;
		Matrix matrix = MatrixUtil.getEmptyConcreteMatrix(this, n, n);
		
		for(int i = 0; i < n; ++i)
			for(int j = 0; j < n; ++j)
				if(i == j)
					matrix.setElementValue( i, j, this.matrix[i][j].getValue() );
		
		return matrix;
	}
	
	public Element[] getMinorDiagonal() {
		int n = (columns < rows) ? columns : rows;

		Element[] diagonal = new Element[n];
		for(int i = 0; i < n; ++i)
			diagonal[i] = matrix[n-1-i][i];
			
		return diagonal;
	}
	
	public Matrix getMinorDiagonalMatrix() {
		int n = (columns < rows) ? columns : rows;
		Matrix matrix = MatrixUtil.getEmptyConcreteMatrix(this, n, n);
		
		for(int i = n-1; i >= 0; --i)
			for(int j = 0; j < n; ++j)
				if( (i + j) == (n - 1) )
					matrix.setElementValue( i, j, this.matrix[i][j].getValue() );
		
		return matrix;
	}
	
//	public Matrix getLowerTriangularMatrix() {
//		
//	}
//	
//	public Matrix getUpperTriangularMatrix() {
//		
//	}
	
	// TODO: submatrices by deleting row/column
	
	protected abstract boolean checkElement(Element e);
	
	@Override
	public String toString() {
		String m = "";
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < columns; ++j) {
				if(matrix[j][i] != null)
					m += " " + matrix[j][i].getValue() + " ";
				else
					m += " - ";
			}
			m += "\n";
		}
		return m;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
        Matrix m = MatrixUtil.getEmptyConcreteMatrix(this, columns, rows);
        for(int i = 0; i < columns; ++i)
			for(int j = 0; j < rows; ++j)
				m.matrix[i][j] = (Element)matrix[i][j].clone();
        return m;
    }
	
	@Override
	public boolean equals(Object obj) {
		if( !(obj instanceof Matrix) )
			return false;
		
		Matrix m = (Matrix)obj;
		if(m == this)
			return true;
		
		if(m.getColumnsCount() != columns || m.getRowsCount() != rows)
			return false;
		
		for(int i = 0; i < columns; ++i)
			for(int j = 0; j < rows; ++j)
				if( m.getElement(i, j).getValue() != matrix[i][j].getValue() )
					return false;
		return true;
	}
	
	@Override
	public Iterator<Element> iterator() {
		ArrayList<Element> ar = new ArrayList<>( this.getElementsCount() );
		for(int i = 0; i < this.columns; ++i)
			ar.addAll( Arrays.asList( matrix[i] ) );

		return ar.iterator();
	}
	
	public Matrix multiplyByRestricted(double number) throws FailedOperationException {
		Matrix result = null;
		try {
			result = this.getClass().cast( this.clone() );
		} catch (CloneNotSupportedException e) {}
		
		for(int i = 0; i < columns; ++i)
			for(int j = 0; j < rows; ++j)
				if( !result.setElementValue(i, j, matrix[i][j].getValue() * number) )
					throw new FailedOperationException("One or more multiplied values cannot be assigned to this matrix");
		
		return result;
	}
	
	public CommonMatrix multiplyBy(double number) {
		CommonMatrix result = new CommonMatrix(columns, rows);
		
		for(int i = 0; i < columns; ++i)
			for(int j = 0; j < rows; ++j)
				result.setElementValue(i, j, matrix[i][j].getValue() * number);
		
		return result;
	}
	
	public CommonMatrix multiplyBy(Matrix matrix) throws FailedOperationException {
		if( this.columns != matrix.rows )
			throw new FailedOperationException("Number of columns in the first matrix must be equal "
											 + "to number of rows in the second matrix");
		CommonMatrix result = new CommonMatrix(matrix.columns, this.rows);
		
		int multiplyQ = this.columns;
		double newVal;
		for(int i = 0; i < matrix.columns; ++i) {
			for(int j = 0; j < this.rows; ++j) {
				newVal = 0;
				for(int n = 0; n < multiplyQ; ++n)
					newVal += this.matrix[n][j].getValue() * matrix.getElement(i, n).getValue();
						
				result.setElementValue(i, j, newVal);
			}
		}	
		return result;
	}
	
	public CommonMatrix sum(Matrix matrix) throws FailedOperationException {
		if( this.columns != matrix.columns || this.rows != matrix.rows )
			throw new FailedOperationException("Number of columns and rows in matrices must be equal");
		
		CommonMatrix result = new CommonMatrix(columns, rows);
		
		for(int i = 0; i < columns; ++i)
			for(int j = 0; j < rows; ++j)
				result.setElementValue(i, j, this.matrix[i][j].getValue() + matrix.getElement(i, j).getValue());
		
		return result;
	}
	
	public Matrix transpose() {
		Matrix result = MatrixUtil.getEmptyConcreteMatrix(this, rows, columns);
		
		for(int i = 0; i < columns; ++i)
			for(int j = 0; j < rows; ++j)
				result.setElementValue(j, i, matrix[i][j].getValue());
		
		return result;
	}
	
	
	public enum Direction {
		UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT
	}

	
	public class Element implements Cloneable {
		
		private int column;
		private int row;
		private double value;
		
		public Element(int column, int row, double value) {
			if(row < 0 || column < 0)
				throw new IllegalArgumentException("Row or column number cannot be less than 0");
				
			this.row = row;
			this.column = column;
			this.value = value;
		}
		
		public int getColumn() {
			return column;
		}
		
		public int getRow() {
			return row;
		}
		
		public double getValue() {
			return value;
		}
				
		public Element getNeighbour(Direction direction) {
			Element el = null;
			
			switch(direction) {
				case UP: el = (row > 0) ? Matrix.this.matrix[column][row-1] : null;
						 break;
				case DOWN: el = (row < Matrix.this.rows-1) ? Matrix.this.matrix[column][row+1] : null;
						 break;
				case LEFT: el = (column > 0) ? Matrix.this.matrix[column-1][row] : null;
						 break;
				case RIGHT: el = (column < Matrix.this.columns-1) ? Matrix.this.matrix[column+1][row] : null;
						 break;
				case UP_LEFT: el = (row > 0 && column > 0) ? Matrix.this.matrix[column-1][row-1] : null;
						 break;
				case DOWN_RIGHT: el = (row < Matrix.this.rows-1 && column < Matrix.this.columns-1) ? Matrix.this.matrix[column+1][row+1] : null;
						 break;
				case UP_RIGHT: el = (row > 0 && column < Matrix.this.columns-1) ? Matrix.this.matrix[column+1][row-1] : null;
						 break;
				case DOWN_LEFT: el = (row < Matrix.this.rows-1 && column > 0) ? Matrix.this.matrix[column-1][row+1] : null;
						 break;
			}
			return el;
		}
		
		public Element getDiagonalOpposite() {
			return (row >= columns || column >= rows) ? null : Matrix.this.matrix[row][column];
		}
		
		@Override
		public boolean equals(Object obj) {
			if( !(obj instanceof Element) )
				return false;
			
			Element e = (Element)obj;
			if(e == this)
				return true;
			
			if(e.getColumn() != column)
				return false;
			if(e.getRow() != row)
				return false;
			if(e.getValue() != value)
				return false;
			
			return true;
		}
		
		@Override
		public String toString() {
			return "[" + column + "," + row + "]: " + value;
		}
		
		@Override
		public Object clone() throws CloneNotSupportedException {
	        return super.clone();
	    }
	}
}
