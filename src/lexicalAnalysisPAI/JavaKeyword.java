//package lexicalAnalysisPAI;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Clase para representar tokens de las palabras reservadas de Java.
 * @author oscardp96
 * @since 23-2-2017
 */
public class JavaKeyword extends JavaWord {

	private String tokenName, lexeme;

	public JavaKeyword(int line, int column, String lexeme) {
		super(line, column);
		this.tokenName = "KW" + lexeme.toUpperCase();
		this.lexeme = lexeme;
	}

	public String getTokenName() {
		return tokenName;
	}

	public String getLexeme() {
		return lexeme;
	}

	public String toString() {
		return super.toString() + this.getTokenName() + " " + this.getLexeme();
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
}
