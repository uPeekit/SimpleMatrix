package org.peekit.simplematrix.util;

import java.lang.reflect.InvocationTargetException;

import org.peekit.simplematrix.matrix.Matrix;
import org.peekit.simplematrix.matrix.Matrix.Element;

public class MatrixUtil {
	
	public static double[] elementToDoubleArray(Element[] eArr) {
		double[] arr = new double[eArr.length];
		
		for(int i = 0; i < eArr.length; ++i) {
			arr[i] = eArr[i].getValue();
		}
		return arr;
	} 
	
	public static double[][] elementToDouble2DArray(Element[][] eMatr) {
		if(eMatr.length == 0)
			throw new IllegalArgumentException("Matrix should have at least one row");
		
		double[][] matr = new double[eMatr.length][eMatr[0].length];
		
		for(int i = 0; i < eMatr.length; ++i) {
			for(int j = 0; j < eMatr[0].length; ++j) {
				matr[i][j] = eMatr[i][j].getValue();
			}
		}
		return matr;
	}
	
	public static Element[][] doubleToElement2DArray(double[][] iMatr, Matrix matrix) {
		if(iMatr.length == 0)
			throw new IllegalArgumentException("Matrix should have at least one row");
		
		Element[][] matr = new Element[iMatr.length][iMatr[0].length];
		
		for(int i = 0; i < iMatr.length; ++i) {
			for(int j = 0; j < iMatr[0].length; ++j) {
				Element e = matrix.new Element(i, j, iMatr[i][j]);
				matr[i][j] = e;
			}
		}
		return matr;
	}
	
	public static Element[][] copyElement2DArray(Element[][] eMatr) {
		if(eMatr.length == 0)
			throw new IllegalArgumentException("Matrix should have at least one row");
		
		Element[][] copy = new Element[ eMatr.length ][ eMatr[0].length ];
		
		for(int i = 0; i < eMatr.length; ++i) {
			for(int j = 0; j < eMatr[0].length; ++j) {
				try {
					copy[i][j] = (Element)eMatr[i][j].clone();
				} catch (CloneNotSupportedException e) {}
			}
		}
		return copy;
	}
	
	public static double[][] generateRandom(int columns, int rows, int min, int max, boolean fractional) {
		double[][] matrix = new double[columns][rows];
		
		for(int i = 0; i < columns; ++i) {
			for(int j = 0; j < rows; ++j) {
				double r = min + (Math.random() * ((max - min) + 1));
				if(!fractional)
					r = Math.floor(r);
				
				matrix[i][j] = r;
			}
		}
		return matrix;
	}
	
	public static Matrix getEmptyConcreteMatrix(Matrix m, int columns, int rows) {
		Matrix matrix = null;
		try {
			matrix = m.getClass().getConstructor(int.class, int.class).newInstance(columns, rows);
		} catch (NoSuchMethodException e) {
			System.out.println(e);
		} catch (SecurityException e) {
			System.out.println(e);
		} catch (InstantiationException e) {
			System.out.println(e);
		} catch (IllegalAccessException e) {
			System.out.println(e);
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		} catch (InvocationTargetException e) {
			System.out.println(e);
		}
		return matrix;
	}
	
}
