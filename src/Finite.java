import java.util.*;

//Degree 0 corresponds to the highest power of the polynomial in the arrayList

public class Finite {

	Polynomial sum(Polynomial x, Polynomial y){
		Polynomial z = new Polynomial(null, x.mod);//we assume that x,y and z(by extension) have the same mod

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

	Polynomial scalarmul(Polynomial x, int a){
		Polynomial z = new Polynomial(null, x.mod);
		for(int i = 0; i < x.size(); i++){//loop through the list of coefficients

			z.add1Coefficient(x.getCoefficient(i) * a);//adds the scalar multiple to the new array
		}
		//z.displayPoly();
		return z;
	}

	Polynomial sub(Polynomial x, Polynomial y){
		Polynomial z = new Polynomial(null, x.mod);//we assume that x,y and z(by extension) have the same mod

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
		Polynomial z = new Polynomial(null, x.mod);//we assume that x,y and z(by extension) have the same mod

		for (int i = 0; i < x.size() + y.size() - 1; i++) {
			z.add1Coefficient(0);
		}

		for (int i = 0; i < x.size(); i++) {
			for (int j = 0; j < y.size(); j++) {
				z.set1Coefficient(i + j, z.getCoefficient( i + j)+ (x.getCoefficient( i) * y.getCoefficient( j)));
			}
		}
		//z.displayPoly();
		return z;
	}

	Division division(Polynomial x, Polynomial y) {//Algorithm 1.2.6 [Long Division]
		Division d = new Division(new Polynomial(null, x.mod), x);//This objects contains the quotient and remainder and will be returned at the end

		int lc=0;//stores lc(d.remainder)/lc(y)
		Polynomial xx = new Polynomial(null,x.mod);//stores x^(deg(d.remainder)-deg(y)

		//while(d.remainder.degree() >= y.degree()){
		for(int i = 0; i <= (x.degree() - y.degree()) + 1; i++){
			lc = findDiv(d.remainder.leadingCoef(), y.getCoefficient(0), x.mod);
			xx = xpow(d.remainder.degree() - y.degree(),x.mod);
			
			d.quotient = sum(d.quotient, scalarmul(xx,lc));
			d.remainder = sub(d.remainder, scalarmul(product(xx,y),lc));

		}
		//d.quotient.displayPoly();
		//d.remainder.displayPoly();
		return d;
	}

	Polynomial xpow(int pow,int mod){
		Polynomial z = new Polynomial(null, mod);
		z.add1Coefficient(1);
		for(int i = 0; i < pow; i++){
			z.add1Coefficient(0);
		}
		return z;
	}
	
	int findDiv(int r,int b,int m){
		int i=0;
		for( i=1 ; i<=m ; i++){
			if(( b * i) % m == r){
				return i;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		int x[] = { 3, 4, 3};
		int y[] = { 4, 5};
		Polynomial xx = new Polynomial(x, 5);
		Polynomial yy = new Polynomial(y, 5);
		new Finite().division(xx, yy);
	}
}
