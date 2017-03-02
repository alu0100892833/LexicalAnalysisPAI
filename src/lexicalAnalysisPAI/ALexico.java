//package lexicalAnalysisPAI;

public class ALexico {

	public static void main(String[] args) throws IllegalArgumentException {
		Analyzer javaLexicAnalisis = null;
		boolean error = false;
		try {
			javaLexicAnalisis = new Analyzer(args[0], args[1]);
			javaLexicAnalisis.run();
		}
		catch(Exception e) {
			System.out.println("ALGO HA IDO MAL");
			error = true;
			e.printStackTrace();
		}
		finally {
			javaLexicAnalisis.close(error);
		}
	}
}
