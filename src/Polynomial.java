import java.util.*;

public class Polynomial {
	ArrayList<IntegerMod> coefficients = new ArrayList<IntegerMod>();
	int mod;

	Polynomial(int[] coef, int m) {
		mod = m;
		if(coef != null){
			for (int i = 0; i < coef.length; i++) {
				coefficients.add(new IntegerMod(coef[i], mod));
			}
		}
	}

	int getMod() {
		return mod;
	}

	ArrayList<IntegerMod> getCoefficients() {
		return coefficients;
	}

	void add1Coefficient(int element) {
		coefficients.add(new IntegerMod(element, mod));
	}

	void set1Coefficient(int index,int element) {
		coefficients.get(index).set(element);
	}

	void setCoefficients(int[] coef) {
		for (int i = 0; i < coef.length; i++) {
			coefficients.get(i).set(coef[i]);
		}
	}

	void displayPoly(){
		System.out.println("X^"+ (coefficients.size()));
		for(int i=1; i<coefficients.size();i++){
			System.out.println(" + X^"+ (coefficients.size()-i));
		}
		
	}
}