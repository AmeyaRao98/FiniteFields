import java.util.*;

//Degree 0 corresponds to the highest power of the polynomial in the arrayList

public class Finite {


	Polynomial sum(Polynomial x, Polynomial y){
		Polynomial z = new Polynomial(null, x.getMod());//we assume that x,y and z(by extension) have the same mod
		int maxsize = Math.max(x.coefficients.size(), y.coefficients.size());
		int xdiff = maxsize - x.coefficients.size();
		int ydiff = maxsize - y.coefficients.size();
		for(int i = 0; i < maxsize; i++){
			if(x.coefficients.size() - y.coefficients.size() > i){
				z.add1Coefficient( x.getCoefficient(i));
			}
			else if(y.coefficients.size() - x.coefficients.size()>i){
				z.add1Coefficient( y.getCoefficient( i));
			}
			else{
				z.add1Coefficient( x.getCoefficient( i - xdiff) + y.getCoefficient( i - ydiff));
			}
		}
		//z.displayPoly();
		return z;

	}

	Polynomial scalarmul(Polynomial x, int z){
		for(int i = 0; i < x.coefficients.size(); i++){

			x.set1Coefficient(i, x.getCoefficient(i) * z);
		}
		//x.displayPoly();
		return x;
	}
	
	Polynomial sub(Polynomial x, Polynomial y){
		Polynomial z = new Polynomial(null, x.getMod());//we assume that x,y and z(by extension) have the same mod
		int maxsize = Math.max(x.coefficients.size(), y.coefficients.size());
		int xdiff = maxsize - x.coefficients.size();
		int ydiff = maxsize - y.coefficients.size();
		for(int i = 0; i < maxsize; i++){
			if( x.coefficients.size() - y.coefficients.size() > i){
				z.add1Coefficient( x.getCoefficient( i));
			}
			else if( y.coefficients.size() - x.coefficients.size() > i){
				z.add1Coefficient( -y.getCoefficient( i));
			}
			else{
				z.add1Coefficient( x.getCoefficient( i - xdiff) - y.getCoefficient( i - ydiff));
			}
		}
		//z.displayPoly();
		return z;

	}
	
	Polynomial product(Polynomial x, Polynomial y) {
		Polynomial z = new Polynomial(null, x.getMod());//we assume that x,y and z(by extension) have the same mod
		int xSize = x.coefficients.size();
		int ySize = y.coefficients.size();
		
		for (int i = 0; i < xSize + ySize - 1; i++) {
			  z.add1Coefficient(0);
		}
		
		for (int i = 0; i < xSize; i++) {
			for (int j = 0; j < ySize; j++) {
				z.set1Coefficient(i + j, z.getCoefficient( i + j)+ (x.getCoefficient( i) * y.getCoefficient( j)));
			}
		}
		z.displayPoly();
		return z;
	}
	
	Polynomial division(Polynomial x, Polynomial y) {
		Polynomial z = new Polynomial(null, x.getMod());//we assume that x,y and z(by extension) have the same mod
		return z;
	}

	public static void main(String[] args) {
		int x[] = { 5, 2, 3};
		int y[] = { 4, 3, 2};
		Polynomial xx = new Polynomial(x, 8);
		Polynomial yy = new Polynomial(y, 8);
		new Finite().product(xx, yy);
		new Finite().sum(xx, yy);
	}
}
