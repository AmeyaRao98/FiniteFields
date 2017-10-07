import java.util.*;

public class Finite {


	Polynomial sum(Polynomial x, Polynomial y){
		Polynomial z = new Polynomial(null, x.getMod());//we assume that x,y and z(by extention) have the same mod
		int maxsize = Math.max(x.coefficients.size(), y.coefficients.size());
		for(int i=0;i<maxsize;i++){
			if(i>x.coefficients.size()-1){
				z.add1Coefficient(y.coefficients.get(i));
			}
			else if(i>y.coefficients.size()-1){
				z.add1Coefficient(x.coefficients.get(i));
			}
			else{
				z.add1Coefficient(x.coefficients.get(i) + y.coefficients.get(i));
			}
		}
		z.displayPoly();
		return z;

	}
	
	public static void main(String[] args) {
		int x[] = {1};
		int y[] = {1,0};
		Polynomial xx = new Polynomial(x, 3);
		Polynomial yy = new Polynomial(y, 3);
		new Finite().sum(xx,yy);
		
	}
}
