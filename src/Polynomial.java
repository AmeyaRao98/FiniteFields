import java.util.*;

//Degree 0 corresponds to index 0 of the polynomial of the ArrayList

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
		String result="";
		for(int i=0; i<coefficients.size();i++){
			coef="";
			if(coefficients.get(i).number!=0){
				if(coefficients.get(i).number!=1){
					coef = Integer.toString(coefficients.get(i).number);
				}
				result+= ("+ " +coef + xdisp(coefficients.size()-i-1)+" ");
			}
		}
		if(result.charAt(0)=='+'){
			result = result.substring(2);
		}
		System.out.println(result);
	}
	String xdisp(int x){
		if(x==0){
			return "";
		}
		if(x==1){
			return "X";
		}
		else{
			return ("X^" +x);
		}
		
	}
}