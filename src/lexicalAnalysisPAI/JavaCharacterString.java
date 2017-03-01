//package lexicalAnalysisPAI;

import java.io.BufferedWriter;
import java.io.IOException;

public class JavaCharacterString extends JavaToken {

	private String value;

	public JavaCharacterString(int line, int column, String value) {
		super(line, column);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		return super.toString() + "CHAR " + this.getValue();
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
