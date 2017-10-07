public class IntegerMod {
	int mod;
	int number;

	IntegerMod(int value, int m) {
		mod = m;
		set(value);
	}

	void set(int value) {
		if (value < 0) {
			number = value + mod * (int)(Math.abs(Math.floor( (double) value / (double) mod )));
		} else {
			number = value % mod;
		}
	}
}
