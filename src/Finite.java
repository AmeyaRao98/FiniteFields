import java.util.*;

public class Finite {


	Polynomial sum(Polynomial x, Polynomial y){
		Polynomial z = new Polynomial(null, x.getMod());//we assume that x,y and z(by extention) have the same mod
		int maxsize = Math.max(x.coefficients.size(), y.coefficients.size());
		for(int i=maxsize-1;i>=0;i--){
			if(i>x.coefficients.size()){
				z.add1Coefficient(y.coefficients.get(i));
			}
			else if(i>y.coefficients.size()){
				z.add1Coefficient(x.coefficients.get(i));
			}
			else{
				z.add1Coefficient(x.coefficients.get(i) + y.coefficients.get(i));
			}
		}
		return z;

	}
	public static void main(String[] args) {
	}
}
