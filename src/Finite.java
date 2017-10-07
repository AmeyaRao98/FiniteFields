import java.util.*;

//Degree 0 corresponds to the highest power of the polynomial in the arraylist

public class Finite {


	Polynomial sum(Polynomial x, Polynomial y){
		Polynomial z = new Polynomial(null, x.getMod());//we assume that x,y and z(by extention) have the same mod
		int maxsize = Math.max(x.coefficients.size(), y.coefficients.size());
		int xdiff = maxsize - x.coefficients.size();
		int ydiff = maxsize - y.coefficients.size();
		for(int i=0;i<maxsize;i++){
			if(x.coefficients.size()-y.coefficients.size()>i){
				z.add1Coefficient(x.coefficients.get(i).number);
			}
			else if(y.coefficients.size()-x.coefficients.size()>i){
				z.add1Coefficient(y.coefficients.get(i).number);
			}
			else{
				z.add1Coefficient(x.coefficients.get(i-xdiff).number + y.coefficients.get(i-ydiff).number);
			}
		}
		//z.displayPoly();
		return z;

	}

	Polynomial scalarmul(Polynomial x, int z){
		for(int i=0;i<x.coefficients.size();i++){

			x.set1Coefficient(i,x.coefficients.get(i).number*z);
		}
		//x.displayPoly();
		return x;
	}
	
	Polynomial sub(Polynomial x, Polynomial y){
		Polynomial z = new Polynomial(null, x.getMod());//we assume that x,y and z(by extention) have the same mod
		int maxsize = Math.max(x.coefficients.size(), y.coefficients.size());
		int xdiff = maxsize - x.coefficients.size();
		int ydiff = maxsize - y.coefficients.size();
		for(int i=0;i<maxsize;i++){
			if(x.coefficients.size()-y.coefficients.size()>i){
				z.add1Coefficient(x.coefficients.get(i).number);
			}
			else if(y.coefficients.size()-x.coefficients.size()>i){
				z.add1Coefficient(-y.coefficients.get(i).number);
			}
			else{
				z.add1Coefficient(x.coefficients.get(i-xdiff).number - y.coefficients.get(i-ydiff).number);
			}
		}
		//z.displayPoly();
		return z;

	}

	public static void main(String[] args) {
		int x[] = {5,2,3};
		int y[] = {1};
		Polynomial xx = new Polynomial(x, 8);
		Polynomial yy = new Polynomial(y, 8);
		new Finite().sum(xx, yy);

	}
}
