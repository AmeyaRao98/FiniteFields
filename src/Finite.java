import java.util.*;

//Degree 0 corresponds to index 0 of the polynomial of the ArrayList

public class Finite {


	Polynomial sum(Polynomial x, Polynomial y){
		Polynomial z = new Polynomial(null, x.getMod());//we assume that x,y and z(by extention) have the same mod
		int maxsize = Math.max(x.coefficients.size(), y.coefficients.size());
		for(int i=maxsize-1;i>=0;i--){
			if(i>x.coefficients.size()-1){
				z.add1Coefficient(y.coefficients.get(i).number);
			}
			else if(i>y.coefficients.size()-1){
				z.add1Coefficient(x.coefficients.get(i).number);
			}
			else{
				z.add1Coefficient(x.coefficients.get(i).number + y.coefficients.get(i).number);
			}
		}
		z.displayPoly();
		return z;

	}

	public static void main(String[] args) {
		int x[] = {5,5,1,6};
		int y[] = {1,1,3,3};
		Polynomial xx = new Polynomial(x, 8);
		Polynomial yy = new Polynomial(y, 8);
		new Finite().sum(xx,yy);

	}
}
