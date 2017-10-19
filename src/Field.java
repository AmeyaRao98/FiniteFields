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

		for(int i = 0; i < p; i++){
			for(int j = 0; j < n ; j++){
				

			}

		}
	}
}
