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

	public JavaSeparator(int line, int column, String separator) {
		super(line, column);
		this.separator = separator;
		this.tokenName = "UNKNOWN_SEPARATOR";
		switch(separator) {
			case ";":  this.tokenName = "SEMICOLON"; break;
			case ",":  this.tokenName = "COMMA"; break;
			case "{":  this.tokenName = "LEFT_BRACE"; break;
			case "}":  this.tokenName = "RIGHT_BRACE"; break;
			case "[":  this.tokenName = "LEFT_BRACK"; break;
			case "]":  this.tokenName = "RIGHT_BRACK"; break;
			case "(":  this.tokenName = "LEFT_PAR"; break;
			case ")":  this.tokenName = "RIGHT_PAR"; break;
			case ".":  this.tokenName = "PERIOD"; break;
		}
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

	public static boolean isSeparator(String separator) {
		boolean itIs = false;
		switch(separator) {
			case ";":  itIs = true; break;
			case ",":  itIs = true; break;
			case "{":  itIs = true; break;
			case "}":  itIs = true; break;
			case "[":  itIs = true; break;
			case "]":  itIs = true; break;
			case "(":  itIs = true; break;
			case ")":  itIs = true; break;
			case ".":  itIs = true; break;
		}
		return itIs;
	}
}












//END
