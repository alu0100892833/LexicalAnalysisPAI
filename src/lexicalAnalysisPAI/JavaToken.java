//package lexicalAnalysisPAI;

import java.io.BufferedWriter;
import java.io.IOException;


/**
 * Clase base para representar los tokens de Java.
 * @author oscardp96
 * @since 23-2-2017
 */
public class JavaToken {

	protected int line, col;

	public JavaToken(int line, int column) {
		this.line = line;
		this.col = column;
	}

	public int getLine() {
		return line;
	}

	public int getCol() {
		return col;
	}

	public String toString() {
		return this.getLine() + " " + this.getCol() + "  ";
	}

	public void toFile(BufferedWriter bw) {
		try {
			bw.write(this.getLine() + " " + this.getCol() + "  TOKEN_ERROR");
			bw.newLine();
		} catch(IOException e) {
			System.out.println("Error escribiendo token en el fichero de salida.");
			e.printStackTrace();
		}
	}
}
