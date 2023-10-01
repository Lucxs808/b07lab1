import java.io.*;

public class Driver {
	//These printArray methods are only for testing purposes (Makes it easier for me to read/understand output)
	public static void printArray(int[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i < a.length - 1) {
                System.out.print(", "); // Add a comma and space for elements except the last one
            }
        }
        System.out.println("]");
    }
	public static void printArray(double[] a) {
        System.out.print("[");
        for (int i = 0;  i < a.length; i++) {
            System.out.print(a[i]);
            if (i < a.length - 1) {
                System.out.print(", "); // Add a comma and space for elements except the last one
            }
        }
        System.out.println("]");
    }
	public static void main(String[] args) throws Exception {
		double [] coef1 = { 1, -3, 3 };
		int [] exp1 = { 0, 1, 7 };
		double [] coef2 = { 5, 2, 1 };
		int [] exp2 = { 7, 2, 0 };
		double [] coef3 = { 2, 2, 2, 1, 4 };
		int [] exp3 = { 0, 1, 2, 7, 4 };
		double [] coef4 = { 1, 1, 1, 1, 1 };
		int [] exp4 = { 0, 1, 3, 4, 6 };
		double [] coef5 = { 1, 2};
		int [] exp5 = { 0, 1};
		double [] coef7 = { -11, 2, 5, -3};
		int [] exp7 = { 0, 1, 3, 2};
		double [] coef8 = { 108, 1, -12, -9};
		int [] exp8 = { 0, 3, 2, 1};
		
		//Polynomial p = new Polynomial();
		//System.out.println(p.evaluate(1));
		Polynomial p1 = new Polynomial(coef1,exp1);
		Polynomial p2 = new Polynomial(coef2,exp2);
		Polynomial p3 = new Polynomial(coef3,exp3);
		Polynomial p4 = new Polynomial(coef4,exp4);
		Polynomial p5 = new Polynomial(coef5,exp5);
		Polynomial p7 = new Polynomial(coef7,exp7);
		Polynomial p8 = new Polynomial(coef8,exp8);
		//Polynomial p2 = new Polynomial(c2);
		//Polynomial p3 = new Polynomial(c3);
		//System.out.println("Evaluation of p3 at -1 is: "+p3.evaluate(0));
		Polynomial s = p3.add(p4);
		System.out.println("The Coefficient Array of p3+p4 is: ");
		printArray(s.coefficients);
		System.out.println("The Exponent Array of p3+p4 is: ");
		printArray(s.exponents);
		System.out.println("\n");
		Polynomial z = p1.add(p3);
		System.out.println("The Coefficient Array of p1+p3 is: ");
		printArray(z.coefficients);
		System.out.println("The Exponent Array of p1+p3 is: ");
		printArray(z.exponents);
		
		System.out.println("\n");
		System.out.println("p1(1) = " + p1.evaluate(1));
		System.out.println("p2(0.2) = " + p2.evaluate(0.2));
		System.out.println("\n");
		if (p5.hasRoot(-0.5)) {
			System.out.println("-0.5 is a root of p5");
		} else {
			System.out.println("-0.5 is not a root of p5");
		}
		if (p5.hasRoot(1)) {
			System.out.println("1 is a root of p5");
		} else {
			System.out.println("1 is not a root of p5");
		}
		int v = 12;
		if (p8.hasRoot(v)) {
			System.out.println(v+" is a root of p8");
		} else {
			System.out.println(v+" is not a root of p8");
		}
		System.out.println("\n");
		Polynomial t = p1.multiply(p5);
		System.out.println("The Coefficient Array of p1*p5 is: ");
		printArray(t.coefficients);
		System.out.println("The Exponent Array of p1*p5 is: ");
		printArray(t.exponents);
		System.out.println("\n");
		Polynomial u = p2.multiply(p3);
		System.out.println("The Coefficient Array of p2*p3 is: ");
		printArray(u.coefficients);
		System.out.println("The Exponent Array of p2*p3 is: ");
		printArray(u.exponents);
		System.out.println("\n");
		
		File file = new File("/Users/mynameislucus/Desktop/Lucus_CompSci/B07Lab1Test/src/test.txt");
		Polynomial p6 = new Polynomial(file);
		System.out.println("The Coefficient Array is: ");
		printArray(p6.coefficients);
		System.out.println("The Exponent Array is: ");
		printArray(p6.exponents);
		
		String save1 = "/Users/mynameislucus/Desktop/Lucus_CompSci/B07Lab1Test/src/test1.txt";
		p1.saveToFile(save1);
		
		String save2 = "/Users/mynameislucus/Desktop/Lucus_CompSci/B07Lab1Test/src/test2.txt";
		p2.saveToFile(save2);
		
		String save3 = "/Users/mynameislucus/Desktop/Lucus_CompSci/B07Lab1Test/src/test3.txt";
		p7.saveToFile(save3);
		
		String save4 = "/Users/mynameislucus/Desktop/Lucus_CompSci/B07Lab1Test/src/test4.txt";
		p8.saveToFile(save4);
	}

}
