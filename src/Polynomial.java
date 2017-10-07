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
		String coef="";
		if(coefficients.get(0).number!=0){
			if(coefficients.get(0).number!=1){
				coef = Integer.toString(coefficients.get(0).number);
			}
			System.out.print(coef + "X^"+ (coefficients.size()));}

		for(int i=1; i<coefficients.size();i++){
			coef="";
			if(coefficients.get(i).number!=0){
				if(coefficients.get(i).number!=1){
					coef = Integer.toString(coefficients.get(i).number);
				}
				System.out.print(" + X^"+ (coefficients.size()-i));
			}
		}
	}
}