public class Subset {

	public static void main(String args[]) {

		int k = Integer.parseInt(args[0]);

		String[] inputArray = new String[100];
		int ct = 0;
		while (!StdIn.isEmpty()) {
			String inputString = StdIn.readString();
			/*System.out.println("Input : " + inputString);*/
			inputArray[ct++] = inputString;

		}

		int n = ct;
		while (k-- > 0) {
			int random = StdRandom.uniform(n);
			if (!inputArray[random].equals("")) {
				System.out.println(inputArray[random]);
				inputArray[random] = "";
			} else {
				k++;
			}

		}

	}

}
