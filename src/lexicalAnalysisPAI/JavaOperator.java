//package lexicalAnalysisPAI;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Clase que representa los tokens operadores de Java.
 * @author oscardp96
 * @since 23-2-2017
 */
public class JavaOperator extends JavaToken {

	private String operator;
	private String tokenName;
	private static final HashMap<String, String> operators = new HashMap<String, String>() {{
		put("+", "ADD"); put("-", "SUB"); put("*", "MULT"); put("/", "DIV"); put("%", "MOD");
	}};

	public JavaOperator(int line, int column, String tokenName, String operator) {
		super(line, column);
		this.operator = operator;
		this.tokenName = tokenName;
	}

	public String getOperator() {
		return operator;
	}

	public String getTokenName() {
		return tokenName;
	}

	public String toString() {
		return super.toString() + this.getTokenName() + " " + this.getOperator();
	}

	public void toFile(BufferedWriter bw) {
		try {
			bw.write(this.toString());
			bw.newLine();
		}
		catch (IOException e) {
			System.out.println("Error escribiendo token en el fichero de salida. ");
			e.printStackTrace();
		}
	}

	public static JavaOperator isOperator(String sequence, int line, int col) {
		JavaOperator result = null;
		if (operators.get(sequence) != null)
			result = new JavaOperator(line, col, operators.get(sequence), sequence);
		return result;
	}

	public static boolean isOperator(String sequence) {
		if (operators.get(sequence) != null)
			return true;
		return false;
	}

}










//END
