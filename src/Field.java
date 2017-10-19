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

		//code to generate all the elements in the field
		//The field has p^n elements. 
		//we need to generate random elements 
		//once we obtain p^n unique elements, we stop the algorithm

	}
}
