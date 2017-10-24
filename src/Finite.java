import java.util.*;

//Degree 0 corresponds to the highest power of the polynomial in the arrayList

public class Finite {

	Polynomial sum(Polynomial a, Polynomial b){
		Polynomial z = new Polynomial(null, a.mod);//we assume that a,y and z(by extension) have the same mod

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
		return z;

	}

	Polynomial scalarmul(Polynomial a, int mul){
		Polynomial z = new Polynomial(null, a.mod);
		for(int i = 0; i < a.size(); i++){//loop through the list of coefficients

			z.add1Coefficient(a.getCoefficient(i) * mul);//adds the scalar multiple to the new array
		}
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

	Euclid extEuclid(Polynomial a, Polynomial b){// Algorithm 1.2.10 + 1.2.11
		Euclid e = new Euclid(new Polynomial(null, a.mod), new Polynomial(new int[]{1}, a.mod), new Polynomial(new int[]{0}, a.mod));
		// the algorithm requires x=1 and y=0.
		
		Polynomial big = biggerPoly(a,b);
		Polynomial small = smallerPoly(a,b);
		a = big;
		b = small;
		//the algorithm doesn't work consistently unless a>b
		
		

		Polynomial r = new Polynomial(null, a.mod);//q

		Polynomial ac = a;//copy of a for 1.2.11
		Polynomial bc = b;//copy of b for 1.2.11
		Polynomial c = b; // copy of b, c is going to be the temporary storage for b
		Polynomial u = new Polynomial(new int[]{0}, a.mod);//u=0
		Polynomial v = new Polynomial(new int[]{1}, a.mod);//v=1
		Polynomial xp = new Polynomial(null, a.mod);//x'
		Polynomial yp = new Polynomial(null, a.mod);//y'
		Polynomial q = new Polynomial(null, a.mod);//q


		while(b.leadingCoef()!=0){//finding the gcd: 1.2.10
			r = division(a,b).remainder;
			a = b;
			b = r;
		/*}
		while(bc.leadingCoef()!=0){//finding x and y: 1.2.11*/
			q = division(ac,bc).quotient;
			c = bc;
			bc = division(ac,bc).remainder;
			ac = c;
			xp = e.x;
			yp = e.y;
			e.x = u;
			e.y = v;
			u = sub(xp, product(q,u));
			v = sub(yp, product(q,v));
		}

		e.gcd = a;
		//e.gcd.displayPoly();
		//e.x.displayPoly();
		//e.y.displayPoly();
		return e;
	}
	
	Polynomial biggerPoly(Polynomial a, Polynomial b){//returns the bigger polynomial

		int max = Math.max(a.size(), b.size());
		int adiff = max - a.size();
		int bdiff = max - b.size();
		//elements of the polynomial that have the same degree have the same distance from the end of the ArrayList

		for(int i = 0; i < max; i++){
			if(a.size() - b.size() > i){ //if b's degree is less than a's degree
				if(a.getCoefficient(i)>0){
					return a;
				}
			}
			else if(b.size() - a.size()>i){//if a's degree is less than b's degree
				if(b.getCoefficient(i)>0){
					return b;
				}
			}
			else{//if we reach values for which a and b have equal degree
				if(a.getCoefficient( i - adiff) > b.getCoefficient( i - bdiff)){
					return a;
				}
				if(a.getCoefficient( i - adiff) < b.getCoefficient( i - bdiff)){
					return b;
				}
			}
		}
		return a;

	}
	
	Polynomial smallerPoly(Polynomial a, Polynomial b){//returns the smaller polynomial

		int max = Math.max(a.size(), b.size());
		int adiff = max - a.size();
		int bdiff = max - b.size();
		//elements of the polynomial that have the same degree have the same distance from the end of the ArrayList

		for(int i = 0; i < max; i++){
			if(a.size() - b.size() > i){ //if b's degree is less than a's degree
				if(a.getCoefficient(i)>0){
					return b;
				}
			}
			else if(b.size() - a.size()>i){//if a's degree is less than b's degree
				if(b.getCoefficient(i)>0){
					return a;
				}
			}
			else{//if we reach values for which a and b have equal degree
				if(a.getCoefficient( i - adiff) > b.getCoefficient( i - bdiff)){
					return b;
				}
				if(a.getCoefficient( i - adiff) < b.getCoefficient( i - bdiff)){
					return a;
				}
			}
		}
		return a;

	}

	boolean equalModP(Polynomial a, Polynomial b, Polynomial p){//checks whether two polynomials are equal modulo a third poylonmial
		return(division(a,p).remainder == division(b,p).remainder);
	}


	Polynomial xpow(int pow,int mod){//return x^pow
		Polynomial z = new Polynomial(null, mod);
		z.add1Coefficient(1);
		for(int i = 0; i < pow; i++){
			z.add1Coefficient(0);
		}
		return z;
	}

	Polynomial xpowminusx(int pow,int mod){//returns x^pow - x
		return sub(xpow(pow,mod), new Polynomial(new int[]{1,0},mod));
	}
	
	
	int findDiv(int r,int b,int m){//finds the quotient of b that gives r during division modulo m
		int i=0;
		for( i=0 ; i<m ; i++){
			if(( b * i) % m == r){
				return i;
			}
		}
		return -1;
	}

	void addTable(Polynomial a){//creates the addition table
		Field f = new Field(a.mod, a.degree());
		int maxLen = maxLength(f);
		Polynomial pdisp = new Polynomial(null, a.mod);

		System.out.print(padding(maxLen)+ "|");
		for(int k = 0; k < f.elements.size(); k++){
			System.out.print(f.elements.get(k).adisplayPoly() + padding(maxLength(f) - f.elements.get(k).adisplayPoly().length()));
		}
		System.out.println("");
		for(int l = 0; l < (maxLength(f)+2)*(f.elements.size()+1)+1;l++){
			System.out.print("-");
		}
		System.out.println("");



		for(int i = 0; i < f.elements.size(); i++){
			System.out.print(f.elements.get(i).adisplayPoly() + padding(maxLength(f) - f.elements.get(i).adisplayPoly().length())+"|");
			for(int j = 0; j <  f.elements.size(); j++){
				pdisp = division(sum(f.elements.get(i), f.elements.get(j)), a).remainder;
				System.out.print(pdisp.adisplayPoly() + padding(maxLen - pdisp.adisplayPoly().length()) );
			}
			System.out.println("");

		}

	}

	void mulTable(Polynomial a){//creates the multiplication table
		Field f = new Field(a.mod, a.degree());
		int maxLen = maxLength(f);
		Polynomial pdisp = new Polynomial(null, a.mod);

		System.out.print(padding(maxLen)+ "|");
		for(int k = 0; k < f.elements.size(); k++){
			System.out.print(f.elements.get(k).adisplayPoly() + padding(maxLength(f) - f.elements.get(k).adisplayPoly().length()));
		}
		System.out.println("");
		for(int l = 0; l < (maxLength(f)+2)*(f.elements.size()+1)+1;l++){
			System.out.print("-");
		}
		System.out.println("");



		for(int i = 0; i < f.elements.size(); i++){
			System.out.print(f.elements.get(i).adisplayPoly() + padding(maxLength(f) - f.elements.get(i).adisplayPoly().length())+"|");
			for(int j = 0; j <  f.elements.size(); j++){
				pdisp = division(product(f.elements.get(i), f.elements.get(j)), a).remainder;
				System.out.print(pdisp.adisplayPoly() + padding(maxLen - pdisp.adisplayPoly().length()) );
			}
			System.out.println("");

		}

	}
	
	
	ArrayList<Polynomial> primitives(int degree, int mod){//returns all primitive elements in a field
		ArrayList<Polynomial> primis = new ArrayList<Polynomial>();
		Field f = new Field(mod, degree);
	
		for(int i = 0; i < f.elements.size(); i++ ){
				if(primitive(f, f.elements.get(i))){
					primis.add(f.elements.get(i));
				}
		}
		return primis;

	}
	
	boolean primitive(Field f, Polynomial a){
		ArrayList<Integer> primdivs = primeDivisors(f.pdegree-1);
		int i = 1;
		Polynomial one = new Polynomial(new int[]{1}, a.mod);
		while(!(polyPow(a, (f.pdegree-1)/primdivs.get(i))== one) && i <= f.zmod){
			i++;
		}
		return(i <= f.zmod);
	}
	
	
	
	Polynomial polyPow(Polynomial a,int pow){//returns a^pow
		Polynomial z = new Polynomial(new int[]{1},a.mod);
		
		for(int i=0;i<pow;i++){
			z = product(z, a);
		}
		return z;
	}

	
	ArrayList<Integer> primeDivisors(int n){//creates an arraylist with the prime divisors of n
		ArrayList<Integer> primdivs = new ArrayList<Integer>();
		if(n%2==0){
			primdivs.add(2);
		}
		n = repeatedDiv(n,2);
		
		for (int i = 3; i <= Math.sqrt(n); i+= 2) {
            while (n%i == 0)
            {
                primdivs.add(i);
                n = repeatedDiv(n,i);
            }
        }
		return primdivs;
	}
	int repeatedDiv(int n, int d){
		while(n%d==0){
			n/=d;
		}
		return n;
	}


	int maxLength(Field f){//returns the length of the element with the most characters in a field. Used for spacing in the tables
		if(f.pdegree==1){
			return (f.zmod-1)/10 + 1;
		}
		return (((f.zmod-1)/10 + 1) + 5) * (f.pdegree-2) + 5 + 2*((f.zmod-1)/10 + 1);
	}
	String padding(int n){//returns spaces equal to the input plus a bar at the end
		String thing = "";
		for(int i = 0; i <= n; i++){
			thing+=" ";
		}
		return thing + "|";
	}

	void fieldOps(Polynomial a, Polynomial b, Polynomial irr){//performs addition, multiplication and multiplication a with the inverse of b
		Polynomial one = new Polynomial(new int[]{1}, a.mod);
		division(sum(a,b), irr).remainder.displayPoly();
		division(product(a,b), irr).remainder.displayPoly();
		if(sub(extEuclid(b, irr).gcd, one).leadingCoef() == 0){
			division(product(a,extEuclid(b,irr).x), irr).remainder.displayPoly();;
		} 
		else {
			System.out.println("b does not an inverse");
		}

	}

	ArrayList<Polynomial> irreducibles(int degree, int mod){//returns all irreducible polynomials of the given degree
		ArrayList<Polynomial> irrs = new ArrayList<Polynomial>();
		Field f = new Field(mod, degree+1);
	
		for(int i = 0; i < f.elements.size(); i++ ){
			if(f.elements.get(i).degree() == degree){
				if(f.elements.get(i).irreducible()){
					irrs.add(f.elements.get(i));
				}
			}
		}
		return irrs;

	}


	public static void main(String[] args) {
		int mod = 3;
		int x[] = {1,1};
		int y[] = {0};
		int ir[] = {1,1,1};
		Polynomial xx = new Polynomial(x, mod);
		Polynomial yy = new Polynomial(y, mod);
		Polynomial irrr = new Polynomial(ir, mod);
		new Finite().mulTable(irrr);
	}
}
