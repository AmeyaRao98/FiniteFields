import java.util.*;

public class Polynomial {
	ArrayList<Integer> coeficients;
	int mod;
	
	Polynomial(int[] coef, int m) {
		mod = m;
		
		for (int i = 0; i < coef.length; i++) {
			coeficients.add(coef[1]);
		}
	}
	
	int getMod() {
		return mod;
	}
	
	ArrayList<Integer> getCoeficients() {
		return coeficients;
	}
	
	void setNumber(int n) {
		number = n;
	}
}
