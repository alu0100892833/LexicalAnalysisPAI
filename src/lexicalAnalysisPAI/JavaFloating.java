//package lexicalAnalysisPAI;

import java.io.BufferedWriter;
import java.io.IOException;

public class JavaFloating extends JavaNumericToken {

	private Double value;

	public JavaFloating(int line, int column, double value) {
		super(line, column);
		this.value = value;
	}

	public String toString() {
		return super.toString() + "FLOATING " + this.getValue();
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
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
