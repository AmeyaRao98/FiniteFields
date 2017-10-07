import java.util.*;

//Degree 0 corresponds to the highest power of the polynomial in the arrayList

public class Finite {

	Polynomial sum(Polynomial x, Polynomial y){
		Polynomial z = new Polynomial(null, x.getMod());//we assume that x,y and z(by extension) have the same mod

		int max = Math.max(x.size(), y.size());
		int xdiff = max - x.size();
		int ydiff = max - y.size();
		//elements of the polynomial that have the same degree have the same distance from the end of the ArrayList

		for(int i = 0; i < max; i++){
			if(x.size() - y.size() > i){ //if y's degree is less than x's degree
				z.add1Coefficient( x.getCoefficient(i));//directly add the values of x for which we don't have a value of y with the same degree
			}
			else if(y.size() - x.size()>i){//if x's degree is less than y's degree
				z.add1Coefficient( y.getCoefficient( i));//directly add the values of y for which we don't have a value of x with the same degree
			}
			else{//if we reach values for which x and y have equal degree
				z.add1Coefficient( x.getCoefficient( i - xdiff) + y.getCoefficient( i - ydiff));//we add the values and put in z
			}
		}
		//z.displayPoly();
		return z;

	}

	Polynomial scalarmul(Polynomial x, int z){
		for(int i = 0; i < x.size(); i++){//loop through the list of coefficients

			x.set1Coefficient(i, x.getCoefficient(i) * z);//replace the element by its product with the scalar
		}
		//x.displayPoly();
		return x;
	}

	Polynomial sub(Polynomial x, Polynomial y){
		Polynomial z = new Polynomial(null, x.getMod());//we assume that x,y and z(by extension) have the same mod

		int max = Math.max(x.size(), y.size());
		int xdiff = max - x.size();
		int ydiff = max - y.size();
		//elements of the polynomial that have the same degree have the same distance from the end of the ArrayList

		for(int i = 0; i < max; i++){
			if( x.size() - y.size() > i){//if y's degree is less than x's degree
				z.add1Coefficient( x.getCoefficient( i));//directly add the values of x for which we don't have a value of y with the same degree
			}
			else if( y.size() - x.size() > i){//if x's degree is less than y's degree
				z.add1Coefficient( -y.getCoefficient( i));//directly add the values of y(with a minus) for which we don't have a value of x with the same degree
			}
			else{//if we reach values for which x and y have equal degree
				z.add1Coefficient( x.getCoefficient( i - xdiff) - y.getCoefficient( i - ydiff));//we subtract y from x and the values and put in z
			}
		}
		//z.displayPoly();
		return z;

	}

	Polynomial product(Polynomial x, Polynomial y) {
		Polynomial z = new Polynomial(null, x.getMod());//we assume that x,y and z(by extension) have the same mod

		for (int i = 0; i < x.size() + y.size() - 1; i++) {
			z.add1Coefficient(0);
		}

		for (int i = 0; i < x.size(); i++) {
			for (int j = 0; j < y.size(); j++) {
				z.set1Coefficient(i + j, z.getCoefficient( i + j)+ (x.getCoefficient( i) * y.getCoefficient( j)));
			}
		}
		z.displayPoly();
		return z;
	}

	Division division(Polynomial x, Polynomial y) {
		Division d = new Division(new Polynomial(null, x.getMod()), x);//This objects contains the quotient and remainder and will be returned at the end
		int lc=0;//stores lc(r)/lc(b)
		while(d.remainder.degree()>=y.degree()){
			d.quotient = d.quotient;

		}



		return d;
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
