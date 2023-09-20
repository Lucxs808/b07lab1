
public class Polynomial {

	double[] polynomial;

	public Polynomial() {
		polynomial = new double[] { 0 };
	}

	public Polynomial(double[] inputarray) {
		int iter = inputarray.length; // This is how many times we iterate through a loop to set coefficients of
										// polynomial
		polynomial = new double[iter];
		for (int i = 0; i < iter; i++) {
			polynomial[i] = inputarray[i]; // set the polynomial coefficients according to inputed array of values
		}
	}

	public Polynomial add(Polynomial p) {
		// How to access data member of object p?
		// get the length of polynomial
		int p1Length = polynomial.length;
		// get length of polynomial we are adding
		int p2Length = p.polynomial.length;
		// To make sure we add every coefficient, we want to iter the max amt (length)
		// for the length of the 2 polynomials
		int iter = Math.max(this.polynomial.length, p.polynomial.length);
		// New local array that holds the coefcients for this new array
		double[] newPoly = new double[Math.max(this.polynomial.length, p.polynomial.length)]; // How can you do this?
		for (int i = 0; i < iter; i++) {
			// These if statements are to avoid indexing out of scope depending on lengths
			// of each polynomial.
			if (i > p1Length - 1) {
				newPoly[i] = p.polynomial[i];
			} else if (i > p2Length - 1) {
				newPoly[i] = polynomial[i];
			} else {
				newPoly[i] = polynomial[i] + p.polynomial[i];
			}
		}
		// Once iterations are done we create an object Polynomial to return called
		// added, we call the constructor to create this new polynomial
		Polynomial added = new Polynomial(newPoly);
		// Return new added polynomial
		return added;
	}

	public double evaluate(double x) {
		// Begin by setting sum to the first value of the array of polynomials since it
		// is constant
		double sum = polynomial[0];
		// Loop through each coefficient (length of the array) and multiply it by x then
		// raise to power of i
		for (int i = 1; i < polynomial.length; i++) {
			// By BEDMAS we raise variable to power first
			double a = Math.pow(x, i);
			// Then we multiple this value by the coefficient and add to our total.
			sum = sum + (polynomial[i] * a);
		}
		// return total
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

}
