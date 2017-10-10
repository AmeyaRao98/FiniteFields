import java.util.ArrayList;

public class Field {
	int zmod;
	int pdegree;
	Polynomial pmod;
	ArrayList<Polynomial> elements = new ArrayList<Polynomial>();


	Field(int p, int n){
		zmod = p;
		pdegree = n;
		
		
	}

	int size(){
		return (int)Math.pow(zmod,pdegree);
	}

	void displayelements(){
		for(int i = 0; i <elements.size();i++){
			elements.get(i).displayPoly();
		}

		
	}
}
