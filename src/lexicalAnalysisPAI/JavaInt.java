//package lexicalAnalysisPAI;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class JavaInt extends JavaNumericToken {

	private Integer value;

	public JavaInt(int line, int column, int value) {
		super(line, column);
		this.value = new Integer(value);
	}

	public String toString() {
		return super.toString() + "INT " + this.getValue();
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
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
