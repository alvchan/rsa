import java.math.BigInteger;

public final class RsaTester {
	public static boolean areAssertionsEnabled() {
		boolean assertEnabled = false;

		try {
			assert false;
		} catch (AssertionError e) {
			assertEnabled = true;
		}

		return assertEnabled;
	}

	public static void randTest(int testCount, int keySize, String m) {
		System.out.printf("[rand test %d]\n", testCount);
		Rsa rsa = new Rsa(keySize);
		BigInteger c = rsa.encrypt(new BigInteger(m));
		BigInteger m_d = rsa.decrypt(c);
		assert m.equals(m_d.toString());
		System.out.println("OK\n");
	}

	public static void main(String[] args) {
		if (!areAssertionsEnabled()) {
			System.out.println("you probably forgot to run with '-ea'");
			System.exit(0);
		}

		for (int i = 0; i < 100; i++) {
			randTest(i, 1024, String.valueOf(Rng.rand()));
		}
		System.out.println("No errors found. Life is good.");
	}
}