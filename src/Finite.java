import java.util.*;

//Degree 0 corresponds to the highest power of the polynomial in the arrayList

public class Finite {

<<<<<<< HEAD
	Polynomial sum(Polynomial a, Polynomial b){
		Polynomial z = new Polynomial(null, a.mod);//we assume that a,y and z(by extension) have the same mod
=======
	Polynomial sum(Polynomial x, Polynomial y){
		Polynomial z = new Polynomial(null, x.mod);// we assume that x,y and z(by extension) have the same mod
>>>>>>> 845d73b12d52004ce7740b92c916173146d5df72

		int max = Math.max(a.size(), b.size());
		int adiff = max - a.size();
		int bdiff = max - b.size();
		//elements of the polynomial that have the same degree have the same distance from the end of the ArrayList

		for(int i = 0; i < max; i++){
			if(a.size() - b.size() > i){ //if b's degree is less than a's degree
				z.add1Coefficient( a.getCoefficient(i));//directly add the values of a for which we don't have a value of b with the same degree
			}
			else if(b.size() - a.size()>i){//if a's degree is less than b's degree
				z.add1Coefficient( b.getCoefficient( i));//directly add the values of b for which we don't have a value of a with the same degree
			}
			else{//if we reach values for which a and b have equal degree
				z.add1Coefficient( a.getCoefficient( i - adiff) + b.getCoefficient( i - bdiff));//we add the values and put in z
			}
		}
		//z.displayPoly();
		return z;

	}

	Polynomial scalarmul(Polynomial a, int mul){
		Polynomial z = new Polynomial(null, a.mod);
		for(int i = 0; i < a.size(); i++){//loop through the list of coefficients

			z.add1Coefficient(a.getCoefficient(i) * mul);//adds the scalar multiple to the new array
		}
		//z.displayPoly();
		return z;
	}

	Polynomial sub(Polynomial a, Polynomial b){
		Polynomial z = new Polynomial(null, a.mod);//we assume that a,y and z(by extension) have the same mod

		int max = Math.max(a.size(), b.size());
		int adiff = max - a.size();
		int bdiff = max - b.size();
		//elements of the polynomial that have the same degree have the same distance from the end of the ArrayList

		for(int i = 0; i < max; i++){
			if( a.size() - b.size() > i){//if b's degree is less than a's degree
				z.add1Coefficient( a.getCoefficient( i));//directly add the values of a for which we don't have a value of b with the same degree
			}
			else if( b.size() - a.size() > i){//if a's degree is less than b's degree
				z.add1Coefficient( -b.getCoefficient( i));//directly add the values of b(with a minus) for which we don't have a value of a with the same degree
			}
			else{//if we reach values for which a and b have equal degree
				z.add1Coefficient( a.getCoefficient( i - adiff) - b.getCoefficient( i - bdiff));//we subtract b from a and the values and put in z
			}
		}
		//z.displayPoly();
		return z;

	}

	Polynomial product(Polynomial a, Polynomial b) {
		Polynomial z = new Polynomial(null, a.mod);//we assume that a,y and z(by extension) have the same mod

		for (int i = 0; i < a.size() + b.size() - 1; i++) {
			z.add1Coefficient(0);
		}

		for (int i = 0; i < a.size(); i++) {
			for (int j = 0; j < b.size(); j++) {
				z.set1Coefficient(i + j, z.getCoefficient( i + j)+ (a.getCoefficient( i) * b.getCoefficient( j)));
			}
		}
		//z.displayPoly();
		return z;
	}

	Division division(Polynomial a, Polynomial b) {//Algorithm 1.2.6 [Long Division]
		Division d = new Division(new Polynomial(null, a.mod), a);//This object contains the quotient and remainder and will be returned at the end
		int lc = 0;//stores lc(d.remainder)/lc(b)
		Polynomial xx = new Polynomial(null,a.mod);//stores a^(deg(d.remainder)-deg(b)
		while(d.remainder.degree() >= b.degree() && d.remainder.leadingCoef()!=0){
			lc = findDiv(d.remainder.leadingCoef(), b.leadingCoef(), a.mod);
			xx = xpow(d.remainder.degree() - b.degree(), a.mod);

			d.quotient = sum(d.quotient, scalarmul(xx, lc));
			d.remainder = sub(d.remainder, scalarmul(product(xx, b), lc));

		}
		//d.quotient.displayPoly();
		//d.remainder.displayPoly();
		return d;
	}

	Euclid exteuclid(Polynomial a, Polynomial b){// Algorithm 1.2.10 + 1.2.11
		Euclid e = new Euclid(new Polynomial(null, a.mod), new Polynomial(null, a.mod), new Polynomial(null, a.mod));
		Polynomial r = new Polynomial(null, a.mod);//q

		Polynomial ac = a;//copy of a for 1.2.11
		Polynomial bc = b;//copy of b for 1.2.11
		e.x.add1Coefficient(1);//x=1
		e.y.add1Coefficient(0);//y=0
		Polynomial u = new Polynomial(new int[]{0}, a.mod);//u=0
		Polynomial v = new Polynomial(new int[]{1}, a.mod);//v=1
		Polynomial xp = new Polynomial(null, a.mod);//x'
		Polynomial yp = new Polynomial(null, a.mod);//y'
		Polynomial q = new Polynomial(null, a.mod);//q


		while(b.leadingCoef()!=0){//finding the gcd: 1.2.10
			r = division(a,b).remainder;
			a = b;
			b = r;
		}
		while(bc.leadingCoef()!=0){//finding x and y: 1.2.11
			q = division(ac,bc).quotient;
			System.out.print("q: ");
			q.displayPoly();
			ac = bc;
			System.out.print("ac: ");
			
			bc = division(ac,bc).remainder;
			System.out.println("bc: " + bc);
			xp = e.x;
			System.out.println("xp: " + xp);
			yp = e.y;
			System.out.println("yp: " + yp);
			e.x = u;
			System.out.println("e.x " +e.x);
			e.y = v;
			System.out.println("e.y " + e.y);
			u = sub(xp, product(q,u));
			v = sub(yp, product(q,v));
		}

		e.gcd = a;
		e.gcd.displayPoly();
		e.x.displayPoly();
		e.y.displayPoly();
		return e;
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
		for( i=0 ; i<m ; i++){
			if(( b * i) % m == r){
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int mod = 7;
		int x[] = {2, 1};
		int y[] = {2, 2};
		Polynomial xx = new Polynomial(x, mod);
		Polynomial yy = new Polynomial(y, mod);
		new Finite().exteuclid(xx, yy);
	}
}
