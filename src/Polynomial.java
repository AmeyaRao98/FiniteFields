import java.util.*;

public class Polynomial {
	ArrayList<Integer> coefficients = new ArrayList<Integer>();
	int mod;

	Polynomial(int[] coef, int m) {

		mod = m;
		if(coef != null){
			for (int i = 0; i < coef.length; i++) {
				if (coef[i] < 0) {
					coef[i] = coef[i] + mod*(int)(Math.abs(Math.floor((double)coef[i] / (double)mod)));
				} else {
					coef[i] = coef[i] % mod;
				}
				coefficients.add(coef[i]);
			}
		}
	}

	int getMod() {
		return mod;
	}

	ArrayList<Integer> getCoefficients() {
		return coefficients;
	}

	int get1Coefficient(int index) {
		return coefficients.get(index);
	}

	void add1Coefficient(int element) {
		if (element < 0) {
			element = element + mod * (int)(Math.abs(Math.floor( (double) element / (double) mod )));
		} else {
			element = element % mod;
		}
		coefficients.add(element);
	}

	void set1Coefficient(int index,int element) {
		if (element < 0) {
			element = element + mod * (int)(Math.abs(Math.floor( (double) element / (double) mod )));
		} else {
			element = element % mod;
		}
		coefficients.set(index, element);
	}

	void setCoefficients(int[] coef) {
		for (int i = 0; i < coef.length; i++) {
			if (coef[i] < 0) {
				coef[i] = coef[i] + mod*(int)(Math.abs(Math.floor((double)coef[i] / (double)mod)));
			} else {
				coef[i] = coef[i] % mod;
			}
			coefficients.add(coef[i]);
		}
	}

	void displayPoly(){
		System.out.println("X^"+ (coefficients.size()));
		for(int i=1; i<coefficients.size();i++){
			System.out.println(" + X^"+ (coefficients.size()-i));
		}
		
	}
}