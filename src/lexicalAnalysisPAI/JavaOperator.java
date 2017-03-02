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
		put("+", "ADD"); put("-", "SUB"); put("*", "MULT"); put("/", "DIV"); put("%", "MOD"); put("&&", "AND");
		put("||", "OR"); put("&", "BIT_AND"); put("|", "BIT_OR"); put("^", "BIT_XOR"); put("++", "INCREMENT");
		put("--", "DECREMENT"); put("==", "EQUAL_TO"); put("=", "EQUAL"); put("!=", "NOT_EQUAL_TO");
		put(">", "GREATER_THAN"); put("<", "LESS_THAN"); put(">=", "GREATER_EQUAL"); put("<=", "LESS_EQUAL");
		put("~", "BIT_COMPLIMENT"); put("<<", "LEFT_SHIFT"); put(">>", "RIGHT_SHIFT"); put(">>>", "ZERO_FILL_RIGHT_SHIFT");
		put("!", "NOT"); put("+=", "DIR_ADD"); put("-=", "DIR_SUB"); put("*=", "DIR_MULT"); put("/=", "DIR_DIV");
		put("%=", "DIR_MOD"); put(">>=", "DIR_RIGHT_SHIFT"); put("<<=", "DIR_LEFT_SHIFT"); put("&=", "DIR_AND");
		put("^=", "DIR_XOR"); put("|", "DIR_OR");
	}};

	public JavaOperator(int line, int column, String operator) {
		super(line, column);
		this.operator = operator;

		if (operators.containsKey(operator))
			this.tokenName = operators.get(operator);
		else
			this.tokenName = "UNKOWN_OPERATOR";
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

	public static boolean isOperator(String sequence) {
		if (operators.containsKey(sequence))
			return true;
		return false;
	}

}










//END
