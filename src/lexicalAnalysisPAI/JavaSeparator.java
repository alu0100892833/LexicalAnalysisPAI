//package lexicalAnalysisPAI;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Clase que representa los tokens separadores de Java.
 * @author oscardp96
 * @since 23-2-2017
 */
public class JavaSeparator extends JavaToken {

	private String tokenName;
	private String separator;

	public JavaSeparator(int line, int column, String tokenName, String separator) {
		super(line, column);
		this.tokenName = tokenName;
		this.separator = separator;
	}

	public String getTokenName() {
		return tokenName;
	}

	public String getSeparator() {
		return separator;
	}

	public String toString() {
		return super.toString() + this.getTokenName() + " " + this.getSeparator();
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

	public static JavaSeparator isSeparator(String separator, int line, int col) {
		JavaSeparator result = null;
		switch(separator) {
			case ";":  result = new JavaSeparator(line, col, "SEMICOLON", separator); break;
			case ",":  result = new JavaSeparator(line, col, "COMA", separator); break;
			case "{":  result = new JavaSeparator(line, col, "LEFT_BRACE", separator); break;
			case "}":  result = new JavaSeparator(line, col, "RIGHT_BRACE", separator); break;
			case "[":  result = new JavaSeparator(line, col, "LEFT_BRACK", separator); break;
			case "]":  result = new JavaSeparator(line, col, "RIGHT_BRACK", separator); break;
			case "(":  result = new JavaSeparator(line, col, "LEFT_PAR", separator); break;
			case ")":  result = new JavaSeparator(line, col, "RIGHT_PAR", separator); break;
			case ".":  result = new JavaSeparator(line, col, "DOT", separator); break;
		}
		return result;
	}
}












//END
