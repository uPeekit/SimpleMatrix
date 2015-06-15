# SimpleMatrix
Simple matrix Java API.

## Structure
simpleMatrix.matrix.* - Contains all the matrices classes.  
simpleMatrix.util.* - Contains util classes.  
simpleMatrix.exception.* - Contains exception classes.   

## Matrix
Is an abstract class containing most functionality similar to most matrices. Can hold double type values. Contains one abstract method checkElement() which is called when matrix element is assigned.   

If any of size arguments in a constructor is less than 1, IllegalArgumentException will be thrown.   
##### Matrix(int size)
Creates matrix with size*size dimensions, filled with zeros.   
##### Matrix(int columns, int rows)
Creates matrix with columns*rows dimensions, filled with zeros.   
##### Matrix(double[][] matrix, boolean arrayOfColumns)
Creates matrix from two-dimensional double array. Argument arrayOfColumns specifies, whether it is array of arrays which represent columns or vice versa. Changing it has the same effect as transposing.   
##### boolean setElementValue(int column, int row, double value)
Tries to assign value to matrix element and returns true if successful. Uses checkElement().   
##### int getElementsCount()
Returns number of elements in the matrix.   
##### int getColumnsCount()
Returns number of columns in the matrix.   
##### int getRowsCount()
Returns number of rows in the matrix.   
##### Element getElement(int column, int row)
Returns element in the specified position. Can throw IllegalArgumentException if any of arguments is less than 1.   
##### Element[] getColumn(int number)
Returns column with specified number. Can throw IllegalArgumentException if argument is less than 1.   
##### Element[] getRow(int number)
Returns row with specified number. Can throw IllegalArgumentException if argument is less than 1.   
##### Element[] getMainDiagonal()
Returns array of elements on the main diagonal.   
##### Matrix getMainDiagonalMatrix()
Returns square matrix of the same type (cast should be used), which have values only on the main diagonal and zeros everywhere else.   
##### Element[] getMinorDiagonal()
Returns array of elements on the minor diagonal.   
##### Matrix getMinorDiagonalMatrix()
Returns square matrix of the same type (cast should be used), which have values only on the minor diagonal and zeros everywhere else.   
##### boolean checkElement(Element e)
Checks if element can be assigned to the matrix. Can be used if matrix should have some bounds/restrictions, e.g for binary matrix
allowed values are only 1 and 0.   
##### String toString()
Returns string which shows matrix values and can be nicely printed out.   
##### Object clone()
Makes copy of the matrix. Cast should be used.   
##### boolean equals(Object obj)
Two matrices are equal if all the elements are equal.   
##### Iterator<Element> iterator()
Returns iterator to iterate through every element of matrix.   
##### Matrix multiplyByRestricted(double number)
Multiplies matrix by the number and returns matrix of the same type. Cast should be used. Can throw FailedOperationException if new value cannot be assigned to matrix of such type.   
##### CommonMatrix multiplyBy(double number)
Multiplies matrix by the number without any restrictions and returns CommonMatrix.   
##### CommonMatrix multiplyBy(Matrix matrix)
Multiplies two matrices and returns CommonMatrix. Can throw FailedOperationException if matrices cannot be multiplied.   
##### CommonMatrix sum(Matrix matrix)
Sums two matrices and returns CommonMatrix. Can throw FailedOperationException if matrices cannot be summed.   
##### Matrix transpose()
Transposes matrix and returns matrix of the same type. Cast should be used.   

## Matrix.Direction  
Is an enumeration which represents direction. Can take the values: UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT

## Matrix.Element
Is a read-only object which represents matrix element.
##### Element(int column, int row, double value)
Creates new element on the specified position with specified value.
##### int getColumn()
Returns the column of element.
##### int getRow()
Returns the row of element.
##### double getValue()
Returns the value of element.
##### Element getNeighbour(Direction direction)
Returns neighbour of element in the specified direction, null if nothing there.
##### Element getDiagonalOpposite()
Returns diagonal opposite element, null if no such.

## CommonMatrix
Is a simple implementation of matrix with no restrictions  
checkElement() returns true. 

##### void generateRandom(int min, int max, boolean fractional)
Generates random values from min to max and assignes it to the matrix. If argument fractional is false, fractional part of every value will be zero.

## BinaryMatrix
Is a matrix, which can hold only 1 and 0 values.

##### BinaryMatrix(boolean[][] matrix, boolean arrayOfColumns)
Same as similar constructor of Matrix, but takes boolean array as a source.
##### void generateRandom()
Generates random values and assignes it to the matrix.
##### boolean isOne(Element e)
Returns true if element value is 1.
##### int countContinents()
Counts continents which are represented by elements with value 1, standing next to each other
on the top, right, bottom and left, not diagonally.

##  MatrixUtil
Contains public static util methods to ease the work with matrices. Method names describe functionality pretty well.

##### double[] elementToDoubleArray(Element[] eArr)
Converts array of Element objects to array of double values.
##### double[][] elementToDouble2DArray(Element[][] eMatr)
Converts two-dimensional array Element objects to two-dimensional array of double values.
##### Element[][] doubleToElement2DArray(double[][] iMatr, Matrix matrix)
Converts two-dimensional array of double values to two-dimensional array of Element objects and assigns them to specified matrix.
##### Element[][] copyElement2DArray(Element[][] eMatr)
Copies two-dimensional array of Element objects. Copies still belong to same matrix.
##### double[][] generateRandom(int columns, int rows, int min, int max, boolean fractional)
Generates random two-dimensional array of double values with specified size and within specified bounds. If argument fractional is false, fractional part of every value will be zero.
##### Matrix getEmptyConcreteMatrix(Matrix m, int columns, int rows)
Returns empty (filled with zeros) matrix of the same type as argument matrix. Cast should be used after receiving.
