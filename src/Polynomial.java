import java.util.*;

//Degree 0 corresponds to the highest power of the polynomial in the arraylist

public class Polynomial extends Finite {
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

	int size() {
		int size = coefficients.size();
		return size;
	}
	int degree(){
		for(int i = 1; i < size(); i++) {
			if(getCoefficient(i - 1) != 0) {
				return coefficients.size() - i;
			}
		}
		return 0;
	}

	int getCoefficient(int index) {
		return coefficients.get(index).number;
	}
	int leadingCoef(){
		for(int i = 0; i < size(); i++) {
			if(getCoefficient(i) != 0) {
				return getCoefficient(i);
			}
		}
		return 0;
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
	
	boolean irreducible(Polynomial f){//Algorithm 4.1.4
		//Finite fin = new Finite();
		
		int t=1;
		Polynomial one = new Polynomial(new int[]{1}, mod);
		while (sub(extEuclid(f, xpowminusx((int)Math.pow(mod, t),mod) ).gcd, one).leadingCoef() == 0 ){
			//Our programme may return the gcd as [0,0,0,1](for example), which is not equal to [1]
			//Therefore, we subtract one from the gcd and then check if the result's leading coefficient is zero
			//If it is, the gcd is 1.
			t++;
		}
		return(t==f.degree());
	}
	

	void displayPoly(){

		String coef="";
		String result="";
		if(coefficients.size() < 1 || leadingCoef() == 0){
			result = "0";
		}
		else{
			for(int i=0; i<coefficients.size();i++){
				coef="";
				if(coefficients.get(i).number!=0){
					if(coefficients.get(i).number!=1){
						coef = Integer.toString(coefficients.get(i).number);
					}
					result+= ("+ " +coef + xdisp(coefficients.size()-i-1,coefficients.get(i).number)+" ");
				}
				if(!result.equals("")){
					if(result.charAt(0)=='+'){
						result = result.substring(2);
					}
				}
			}
		}
		System.out.println(result);
	}
	String xdisp(int x,int i){
		if(x==0){
			if(i==1){
				return "1";
			}
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