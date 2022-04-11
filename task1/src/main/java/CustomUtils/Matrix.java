package CustomUtils;

/**
 * Class for comfortable using matrices
 */

public class Matrix {
	private int cols, rows;
	private double[][] matrix;

	/**
	 * Constructs a matrix in which all elements are 0
	 *
	 * @param matrixRows - a number of rows with which matrix to be constructed
	 * @param matrixCols - a number of columns with which matrix to be constructed
	 * @throws NegativeArraySizeException - if some of constructor's arguments a negative or null
	 */
	public Matrix(int matrixRows, int matrixCols) throws NegativeArraySizeException {
		if (matrixCols <= 0 || matrixRows <= 0) {
			throw new NegativeArraySizeException("Matrix size cannot be negative");
		}

		cols = matrixCols;
		rows = matrixRows;
		matrix = new double[rows][cols];
	}

	/**
	 * Constructs a matrix in which elements would be initialized with values from 2-dimensional array
	 *
	 * @param m - 2-dimensional array with which matrix to be filled
	 * @throws IllegalArgumentException - if 2-dimensional array is null
	 */
	public Matrix(double[][] m) throws IllegalArgumentException {
		if (m == null) {
			throw new NullPointerException("Matrix cannot be null");
		}

		rows = m.length;
		cols = m[0].length;
		matrix = m;
	}

	/**
	 * Constructs a matrix as a copy of another matrix
	 * @param another - matrix from which taking values
	 */
	public Matrix(Matrix another) {
		matrix = new double[another.rows][another.cols];
		rows = another.rows;
		cols = another.cols;

		for (var i = 0; i < rows; i++) {
			System.arraycopy(matrix[i], 0, another.matrix[i], 0, cols);
		}
	}

	/**
	 * Represents a string with matrix values
	 * @return string with values
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (var i = 0; i < rows; i++) {
			for (var j = 0; j < cols; j++) {
				result.append(matrix[i][j]);
				result.append(" ");
			}
			result.append("\n");
		}

		return result.toString();
	}

	/**
	 *
	 * @return number of raws of matrix
	 */
	public int getRows() {
		return rows;
	}

	/**
	 *
	 * @return number of columns of matrix
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * Gets a value from the specific column and row
	 * @param row row of particular value
	 * @param column column of particular value
	 * @return a value with the specific column and row
	 * @throws IndexOutOfBoundsException if specified column or row does not exist
	 */
	public double get(int row, int column) throws IndexOutOfBoundsException {
		if (row >= rows || column >= cols) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}

		return matrix[row][column];
	}

	/**
	 * Summarizing the matrices
	 * @param another matrix to add
	 * @return summarized matrix
	 * @throws UnsupportedOperationException if a matrices have a different sizes
	 */
	public Matrix add(Matrix another) throws UnsupportedOperationException {
		if (cols != another.cols || rows != another.rows) {
			throw new UnsupportedOperationException("Matrices should have equal sizes to sum it");
		}

		var result = new Matrix(cols, rows);

		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				result.matrix[i][j] = matrix[i][j] + another.matrix[i][j];
			}
		}

		return result;
	}

	/**
	 * Subtraction of matrices
	 * @param another matrices to subtract
	 * @return matrix after subtration
	 * @throws UnsupportedOperationException if a matrices have a different sizes
	 */
	public Matrix sub(Matrix another) throws UnsupportedOperationException {
		if (cols != another.cols || rows != another.rows) {
			throw new UnsupportedOperationException("Matrices should have equal sizes to subtract it");
		}

		var result = new Matrix(cols, rows);

		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				result.matrix[i][j] = matrix[i][j] - another.matrix[i][j];
			}
		}

		return result;
	}

	/**
	 * Multiplication of matrices
	 * @param another matrix to multiplicate
	 * @return matrix after multiplication
	 * @throws UnsupportedOperationException if first matrix number of columns is not equals number of rows of seconds matrix
	 */
	public Matrix mult(Matrix another) throws UnsupportedOperationException {
		if (cols != another.rows) {
			throw new UnsupportedOperationException("Wrong sized matrices in multiplication");
		}
		
		var result = new Matrix(cols, another.rows);

		for (var i = 0; i < result.cols; i++) {
			for (var j = 0; j < result.rows; j++) {
				double value = 0;
				for (var k = 0; k < cols; k++) {
					value += matrix[i][k] * another.matrix[k][j];
				}
				result.matrix[i][j] = value;
			}
		}

		return result;
	}

	/**
	 * Multiplication by integer value
	 * @param multiplier integer value by which all elements are going to be multiplied
	 * @return a matrix with multiplied elements
	 */
	public Matrix mult(int multiplier) {
		var result = new Matrix(rows, cols);

		for (var i = 0; i < result.rows; i++) {
			for (var j = 0; j < result.cols; j++) {
				result.matrix[i][j] = matrix[i][j] * multiplier;
			}
		}

		return result;
	}

	/**
	 * Multiplication by double value
	 * @param multiplier double value by which all elements are going to be multiplied
	 * @return a matrix with multiplied elements
	 */
	public Matrix mult(double multiplier) {
		var result = new Matrix(rows, cols);

		for (var i = 0; i < result.rows; i++) {
			for (var j = 0; j < result.cols; j++) {
				result.matrix[i][j] = matrix[i][j] * multiplier;
			}
		}

		return result;
	}

	/**
	 * Set elements with particular row and column to specified value
	 * @param value value to set
	 * @param col column of element which to be set
	 * @param row row of element which to be set
	 * @throws IndexOutOfBoundsException if specified column or row do not exist
	 */
	public void set(double value, int col, int row) throws IndexOutOfBoundsException {
		if (col >= cols || row >= rows) {
			throw new IndexOutOfBoundsException("Invalid index in set operation");
		}

		matrix[col][row] = value;
	}

	/**
	 * Transposing a matrix
	 */
	public void transpose() {
		var result = new double[cols][rows];

		for (var i = 0; i < rows; i++) {
			for (var j = 0; j < cols; j++) {
				result[j][i] = matrix[i][j];
			}
		}

		rows = result.length;
		cols = result[0].length;
		matrix = result;
	}

	/**
	 * Generates an identity matrix with particular size
	 * @param size size of matrix which to be constructed
	 * @return an identity matrix
	 */
	public static Matrix getIdentityMatrix(int size) {
		var result = new Matrix(size,size);

		for (var i = 0; i < size; i++) {
			result.matrix[i][i] = 1;
		}

		return result;
	}

	/**
	 * Find a reversed matrix
	 * @return reversed matrix
	 */
	public Matrix reverse() throws UnsupportedOperationException {
		if (getDeter() == 0) {
			throw new UnsupportedOperationException("Deter of matrix = 0");
		}

		var copy = new Matrix(rows, cols);

		for (var i = 0; i < rows; i++) {
			if (cols >= 0) System.arraycopy(matrix[i], 0, copy.matrix[i], 0, cols);
		}

		var iden = getIdentityMatrix(cols);

		for (var i = 0; i < cols; i++) {
			var mult = copy.matrix[i][i];

			for (var j = 0; j < cols; j++) { // Делим строку на множитель
				copy.matrix[i][j] /= mult;
				iden.matrix[i][j] /= mult;
			}
			for (var j = 0; j < rows; j++) {
				if (j != i) {
					var localmult = copy.matrix[j][i];
					for (var k = 0; k < cols; k++) {
						copy.matrix[j][k] -= localmult * copy.matrix[i][k];
						iden.matrix[j][k] -= localmult * iden.matrix[i][k];
					}
				}
			}
		}

		return iden;
	}

	/**
	 * Find a determinant of matrix
	 * @return determinant
	 */
	public double getDeter() {
		double result = 0;
		if (rows == 3 && cols == 3) {
			result = matrix[0][0] * matrix[1][1] * matrix[2][2] + matrix[2][0] * matrix[0][1] * matrix[1][2] +
				matrix[0][2] * matrix[1][0] * matrix[2][1] - matrix[2][0] * matrix[1][1] * matrix[0][2] -
				matrix[1][0] * matrix[0][1] * matrix[2][2] - matrix[2][1] * matrix[1][2] * matrix[0][0];
			return result;
		}

		if (rows == 2 && cols == 2) {
			result = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
			return result;
		}

		for (var i = 0; i < cols; i++) {
			var j = 0;
			var tmp = new Matrix(rows - 1, cols - 1);
			while (j < i) {
				var k = 1;
				while (k < rows) {
					tmp.matrix[k - 1][j] = matrix[k][j];
					k++;
				}
				j++;
			}

			j = i + 1;

			while (j < cols) {
				var k = 1;
				while (k < rows) {
					tmp.matrix[k - 1][j - 1] = matrix[k][j];
					k++;
				}
				j++;
			}

			if (i % 2 == 0) {
				result += matrix[0][i] * tmp.getDeter();
			} else {
				result -= matrix[0][i] * tmp.getDeter();
			}
		}

		return result;
	}

	/**
	 * Gets an algebraic complement of matrix with particular row and column
	 * @param row row of algebraic complement to be computed
	 * @param column column of algebraic complement to be computed
	 * @return an algebraic complement
	 */
	public double getAlgebraicComplement(int row, int column) {
		double result = 0;

		var tmp = new Matrix(rows - 1, cols - 1);

		var i = 0;

		while (i < row) {
			var j = 0;
			while (j < column) {
				tmp.matrix[i][j] = matrix[i][j];
				j++;
			}

			j = column + 1;

			while (j < cols) {
				tmp.matrix[i][j - 1] = matrix[i][j];
				j++;
			}
			i++;
		}

		i = row + 1;

		while (i < rows) {
			var j = 0;
			while (j < column) {
				tmp.matrix[i - 1][j] = matrix[i][j];
				j++;
			}

			j = column + 1;

			while (j < cols) {
				tmp.matrix[i - 1][j - 1] = matrix[i][j];
				j++;
			}
			i++;
		}

		result = tmp.getDeter();

		if ((row + column) % 2 != 0) {
			result = -result;
		}

		return result;
	}

	/**
	 * Constructs a 2-dimensional array from matrix
	 * @return 2-dimensional array which represents matrix
	 */
	public double[][] getRawMatrix() {
		var result = new double[rows][cols];

		for (var i = 0; i < rows; i++) {
			System.arraycopy(matrix[i], 0, result[i], 0, cols);
		}

		return result;
	}

	/**
	 * Computes an allied matrix
	 * @return allied matrix
	 */
	public Matrix getAlliedMatrix() {
		var result = new Matrix(rows, cols);

		for (var i = 0; i < rows; i++) {
			for (var j = 0; j < cols; j++) {
				result.matrix[i][j] = getAlgebraicComplement(i, j);
			}
		}

		return result;
	}
}
