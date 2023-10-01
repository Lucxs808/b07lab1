import java.io.*;

public class Polynomial {
	// Holds non zero coefficients
	double[] coefficients;
	// Corresponding exponent of coefficient
	int[] exponents;

	// Helper Functions
	public static boolean isInArray(int[] a, int val) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == val) {
				return true; // Int is in array
			}
		}
		return false; // Int not found in array
	}

	public static int findMax(int[] a) {
		// Set max to first value in array (default)
		int max = a[0];

		// Going through the array to find max val
		for (int i = 1; i < a.length; i++) {
			if (a[i] > max) {
				max = a[i];
			}
		}

		return max;
	}

	//

	public Polynomial() {
		coefficients = new double[] { 0 };
		exponents = new int[] { 0 };
	}

	public Polynomial(double[] inputarray) {
		int iter = inputarray.length; // This is how many times we iterate through a loop to set coefficients of
										// polynomial
		int newArrayLength = 0;
		for (int i = 0; i < iter; i++) {
			if (inputarray[i] != 0) {
				newArrayLength++;
			}
		}
		coefficients = new double[newArrayLength];
		exponents = new int[newArrayLength];

		int j = 0;
		int exp = 0;
		for (int i = 0; i < iter; i++) {
			if (inputarray[i] != 0) {
				exponents[j] = exp;
				coefficients[j] = inputarray[i];
				j++;
			}
			exp++;
		}
	}

	public Polynomial(double[] coef, int[] exp) {
		// From Piazza we can assume coef and exp arrays are the same
		int iter = coef.length;
		coefficients = new double[iter];
		exponents = new int[iter];
		for (int i = 0; i < iter; i++) {
			coefficients[i] = coef[i];
			exponents[i] = exp[i];
		}
	}

	public Polynomial(File p_file) throws Exception {
		BufferedReader b = new BufferedReader(new FileReader(p_file));
		String line = b.readLine();
		b.close();
		// System.out.println(line); This line here was for testing (prints out the text file polynomial)
		int xCount = 0;
		String[] terms = line.split("(?=[+-])");
		for (int i=0; i<terms.length;i++) {
			// Remove whitespace and split each term into coefficient and x exponent parts
			terms[i] = terms[i].trim();
			String[] parts = terms[i].split("x");

			if (parts.length == 1) {
				// Term has no "x", so it's a constant
				xCount++;

			} else {
				xCount++;
			}
		}
		coefficients = new double[xCount];
		exponents = new int[xCount];
		int counter = 0;
		for (int i=0; i<terms.length; i++) {
			// Remove whitespace and split each term into coefficient and x^exponent parts
			terms[i] = terms[i].trim();
			String[] parts = terms[i].split("x");

			if (parts.length == 1) {
				// Term has no "x", so it's a constant
				int constant = Integer.parseInt(parts[0]);
				exponents[counter] = 0;
				coefficients[counter] = constant;
				counter++;

			} else {
				// Term has "x" and possibly an exponent
				int coefficient = Integer.parseInt(parts[0]);
				int exponent = 1; // Default exponent is 1

				// Check if there's an exponent part
				if (parts[1].length() > 0) {
					exponent = Integer.parseInt(parts[1]);
				}
				exponents[counter] = exponent;
				coefficients[counter] = coefficient;
				counter++;
			}
		}
	}

	public Polynomial add(Polynomial p) {
		// How to access data member of object p?
		// get the length of polynomial
		int p1Length = coefficients.length;
		// get length of polynomial we are adding
		int p2Length = p.coefficients.length;
		// To make sure we add every coefficient, we want to iter the max amt (length)
		// New local array that holds the coefficients for this new array
		int newArrayIndex = 0;
		int[] temp = new int[p1Length + p2Length];
		for (int i = 0; i < p1Length; i++) {
			temp[i] = exponents[i];
			newArrayIndex++;
		}
		for (int i = 0; i < p2Length; i++) {
			if (isInArray(temp, p.exponents[i]) != true) {
				temp[newArrayIndex] = p.exponents[i];
				newArrayIndex++;
			}
		}

		double[] newCoef = new double[newArrayIndex]; // How can you do this?
		int[] newExp = new int[newArrayIndex];
		for (int i = 0; i < newArrayIndex; i++) {
			// These if statements are to avoid indexing out of scope depending on lengths
			// of each polynomial.
			newExp[i] = temp[i];
			for (int j = 0; j < exponents.length; j++) {
				if (exponents[j] == temp[i]) {
					newCoef[i] = newCoef[i] + coefficients[j];
				}
			}
			for (int j = 0; j < p.exponents.length; j++) {
				if (p.exponents[j] == temp[i]) {
					newCoef[i] = newCoef[i] + p.coefficients[j];
				}
			}
		}
		// Once iterations are done we create an object Polynomial to return called
		// added, we call the constructor to create this new polynomial
		Polynomial added = new Polynomial(newCoef, newExp);
//		 Return new added polynomial
		return added;
	}

	public double evaluate(double x) {
		// Begin by setting sum to the first value of the array of polynomials since it
		// is constant
		double sum = 0;
		// Loop through each coefficient (length of the array) and multiply it by x then
		// raise to power of i
		for (int i = 0; i < exponents.length; i++) {
			if (exponents[i] == 0) {
				sum = sum + coefficients[i];
			} else {
				// By BEDMAS we raise variable to power first
				double a = Math.pow(x, exponents[i]);
				// Then we multiple this value by the coefficient and add to our total.
				sum = sum + (coefficients[i] * a);

			}
		}
		return sum;
	}

	public boolean hasRoot(double root) {
		// we run the evaluate function on the inputed value and set that to a local
		// variable
		double value = evaluate(root);
		// If the evaluation is 0, by def of root, this value passed in the parameter is
		// called a root
		if (value == 0) {
			return true;
		} else {
			return false;
		}

	}

	public Polynomial multiply(Polynomial p) {
		int l1 = p.coefficients.length;
		int l2 = coefficients.length;
		int[] foilExp = new int[l1 * l2];
		double[] foilCoef = new double[l1 * l2];
		int counter = 0;
		for (int i = 0; i < l1; i++) {
			for (int j = 0; j < l2; j++) {
				foilExp[counter] = exponents[j] + p.exponents[i];
				foilCoef[counter] = coefficients[j] * p.coefficients[i];
				counter++;
			}
		}
		int[] tempExp = new int[l1 * l2];
		double[] tempCoef = new double[l1 * l2];
		int maxPowers = findMax(foilExp) + 1;
		counter = 0;
		// This is how many different powers there could be
		for (int i = 0; i < maxPowers; i++) {
			// This loops through the foiled exponent array
			for (int j = 0; j < foilExp.length; j++) {
				if (foilExp[j] == i) {

					if (!(isInArray(tempExp, i))
							|| (i == 0 && (isInArray(p.exponents, i) || isInArray(exponents, i)))) {
						tempExp[counter] = i;
						counter++;
					}
					tempCoef[counter - 1] = tempCoef[counter - 1] + foilCoef[j];

				}
			}
		}
		int[] finalExp = new int[counter];
		double[] finalCoef = new double[counter];
		for (int i = 0; i < counter; i++) {
			finalExp[i] = tempExp[i];
			finalCoef[i] = tempCoef[i];
		}
		Polynomial multiplied = new Polynomial(finalCoef, finalExp);
		return multiplied;
	}

	public void saveToFile(String p) throws Exception {
		// Assisted through lec 2 code.
		BufferedWriter w = new BufferedWriter(new FileWriter(p));
		for (int i = 0; i < exponents.length; i++) {
			if (i == 0 && coefficients[i] > 0 && exponents[i] == 0) {
				w.write(Double.toString((coefficients[i])));
			} else if (i == 0 && coefficients[i] > 0 && exponents[i] != 0) {
				w.write(Double.toString((coefficients[i])));
			} else if (coefficients[i] > 0) {
				w.write("+" + Double.toString((coefficients[i])));
			} else {
				w.write(Double.toString((coefficients[i])));
			}
			if (exponents[i] != 0) {
				w.write("x" + Integer.toString((exponents[i])));
			}
		}
		w.close();
	}

}
