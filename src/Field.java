import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Field {
	int zmod;
	int pdegree;
	Polynomial pmod;
	ArrayList<Polynomial> elements = new ArrayList<Polynomial>();


	Field(int p, int n){
		zmod = p;
		pdegree = n;
		Finite fin = new Finite();
		elements.add(new Polynomial(new int[]{0}, p));
		Polynomial elt = new Polynomial(null, p);
		Polynomial xp= new Polynomial(null, p);//x^k
		for(int k = 0; k < n; k++){
			xp = fin.xpow(k,p);
			int eltSize = elements.size();
			for(int i = 1; i < p; i++){
				elt = fin.scalarmul(xp, i);
				for(int j = 0; j < eltSize;j++){
					elements.add(fin.sum(elt, elements.get(j)));
				}
			}


		}
	}
	void displayElements(){
		for(int i = 0; i < elements.size(); i++){
			elements.get(i).displayPoly();
		}
	}
}