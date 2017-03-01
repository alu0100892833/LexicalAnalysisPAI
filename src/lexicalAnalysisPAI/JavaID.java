//package lexicalAnalysisPAI;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Clase que representa los tokens ID de Java
 * @author oscardp96
 * @since 23-2-2017
 */
public class JavaID extends JavaWord {

	private String lexeme;


	public JavaID(int line, int column, String lexeme) {
		super(line, column);
		this.lexeme = lexeme;
	}

	public String getLexeme() {
		return lexeme;
	}

	public String toString() {
		return super.toString() + "ID " + this.getLexeme();
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
