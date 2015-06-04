package matrix;


public abstract class Matrix {
	
	protected Element[][] matrix;
	
	protected int columns;
	protected int rows;
	
	protected Matrix(int size) {
		if(size <= 0)
			throw new IllegalArgumentException("Size cannot be equal to or less than 0");
		
		assignMatrix( new Element[size][size] );
	}
	
	protected Matrix(int columns, int rows) {
		if(rows <= 0 || columns <= 0)
			throw new IllegalArgumentException("Row or column number cannot be equal to or less than 0");
		
		assignMatrix( new Element[columns][rows] );
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
		assignMatrix(m); 
	}
	
	protected void assignMatrix(Element[][] matrix) {
		this.matrix = matrix;
		this.columns = matrix.length;
		this.rows = matrix[0].length;
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
		
		public void setValue(double value) {
			if( !Matrix.this.checkElement(this) )
				throw new IllegalArgumentException("Bad value");
			
			this.value = value;
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
