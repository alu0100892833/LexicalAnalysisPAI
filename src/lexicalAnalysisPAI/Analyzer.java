//package lexicalAnalysisPAI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Esta clase se encarga de analizar léxicamente un fichero de entrada en formato .java.
 * Imprime el resultado del análisis en el fichero especificado como fichero de salida.
 * @author oscardp96
 * @since 23-2-2017
 */
public class Analyzer {

	private BufferedWriter output;
	private BufferedReader input;
	private SymbolTable symbolTable;

	/**
	 * Inicializa los búffers de lectura y escritura con los que se trabaja.
	 * @param inFile Nombre del fichero de entrada.
	 * @param outFile Nombre del fichero de salida.
	 * @throws IllegalArgumentException En el caso de que el fichero de entrada no esté en formato .java
	 */
	public Analyzer(String inFile, String outFile) throws IllegalArgumentException {
		output = null;
		input = null;
		try {
			if (!inFile.endsWith(".java"))
				throw new IllegalArgumentException("El fichero de entrada debe ser un fichero en formato .java");
			if (!outFile.endsWith(".txt"))
				outFile += ".txt";
			output = new BufferedWriter(new FileWriter(outFile));
			input = new BufferedReader(new FileReader(inFile));
			symbolTable = new SymbolTable();
		} catch (IOException e) {
			System.out.println("Error en la apertura de los ficheros.");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este método se encarga de llevar a cabo un correcto cierre de los búffers de entrada y salida.
	 * Debe ser llamado al finalizar cualquier análisis para evitar problemas con los ficheros.
	 */
	public void close(boolean error) {
		try {
			if (error) {
				output.newLine();
				output.write("IMPORTANTE: ALGO HA IDO MAL");
			}
			if (output != null)
				output.close();
			if (input != null)
				input.close();
		}
		catch (IOException e) {
			System.out.println("Error en el cierre de los ficheros.");
			e.printStackTrace();
		}
	}

	/**
	 * Esta función ejecuta el proceso de análisis sobre el fichero de entrada, escribiendo los resultados en el fichero de salida.
	 */
	public void run() {
		boolean error = false;
		String object;
		int line = 1;
		try {
			while ((object = input.readLine()) != null) {
				this.analizeLine(object, line);
				line++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			error = true;
		} finally {
			this.close(error);
		}
	}

	/**
	* Este método analiza el contenido de una determinada línea de código.
	* La divide en palabras utilizando los principales separadores de Java.
	* @param object La línea que vamos a analizar.
	* @param line El número de línea que estamos analizando del fichero de código fuente.
	*/
	private void analizeLine(String object, int line) {
		int col = 0;
		String trimmedObject = object.trim();
		String delim = " (){}[];,\"\'.";
		StringTokenizer stringTokens = new StringTokenizer(trimmedObject, delim, true);

		while (stringTokens.hasMoreElements()) {
			String word = stringTokens.nextElement().toString();
			col = object.indexOf(word, col);

			//Para el caso de las cadenas de caracteres
			if (word.equals("\"")) {
				String stringConst = "";
				word = stringTokens.nextElement().toString();
				while (word != "\"") {
					stringConst += word;
					word = stringTokens.nextElement().toString();
				}
				JavaCharacterString constant = new JavaCharacterString(line, object.indexOf(stringConst, col), stringConst);
				constant.toFile(output);
			}

			//Para el resto de casos
			else {
				this.analizeWord(word, line, col);
			}
			col++;
		}
	}

	/**
	 * Este método analiza el contenido de una determinada palabra dentro de una línea de código.
	 * Se considera que no hay espacios y por tanto analiza caracter a caracter.
	 * @param word Palabra que queremos analizar.
	 * @param line Línea del fichero de código fuente en el que se encuentra la palabra.
	 * @param col Columna en la que empieza la palabra dentro de su línea.
	 */
	private void analizeWord(String word, int line, int col) {
		JavaToken token = new JavaToken(line, col);
		if ((token = JavaSeparator.isSeparator(word, line, col)) == null) {
			//Si no es un separador, hay que estudiarlo más a fondo
			//Dividimos de nuevo en tokens y metemos en un arrray para que sea más sencillo de manejar
			StringTokenizer tokenGroup = new StringTokenizer(word, "+-*/%&^|!<>~=", true);
			String allTokens = "";
			while (tokenGroup.hasMoreElements())
				allTokens += tokenGroup.nextElement().toString() + " ";
			String[] tokens = allTokens.split("\\s+");
			int iterator = 0;

			while (iterator < tokens.length) {
				String operator = "";
				if (JavaOperator.isOperator(tokens[iterator])) {
					while (JavaOperator.isOperator(tokens[iterator])) {
						operator += tokens[iterator];
						iterator++;
					}
					token = JavaOperator.isOperator(operator, line, word.indexOf(operator, col));
					token.toFile(output);
					continue;
				} else {
					int number; double floating;
					if (tokens[iterator].contains(".")) {
						try {
							floating = Double.parseDouble(tokens[iterator]);
							token = new JavaFloating(line, col, floating);
							token.toFile(output);
							col += tokens[iterator].length();
							iterator++;
							continue;
						} catch(NumberFormatException e) {
							//Excepcion controlada
						}
					} else {
						try {
							number = Integer.parseInt(tokens[iterator]);
							token = new JavaInt(line, col, number);
							token.toFile(output);
							col += tokens[iterator].length();
							iterator++;
							continue;
						} catch(NumberFormatException e2) {
							//Excepcion controlada
						}
					}
				}

				//El caso restante es que el token esté formado por uno o varios identificadores
				//Aquí ya podemos tokenizar también con el separador punto
				StringTokenizer lastTokenization = new StringTokenizer(tokens[iterator], ".", true);
				while (lastTokenization.hasMoreElements()) {
					String element = lastTokenization.nextElement().toString();
					//El primer caso es que el token sea el separador en sí
					//El segundo caso es que el identificador ya esté en la tabla de símbolos y sea una palabra reservada
					//En el tercer caso ya no es un separador ni una palabra reservada. Es un identificador
					if (element.equals(".")) {
						JavaSeparator dot = JavaSeparator.isSeparator(element, line, col);
						dot.toFile(output);
						col += element.length();
					} else if (symbolTable.existsKeyword(element)) {
						JavaWord identifier = symbolTable.get(tokens[iterator]);
						identifier.toFile(output);
						col += element.length();
					} else {
						JavaID newid = new JavaID(line, col, tokens[iterator]);
						newid.toFile(output);
						symbolTable.put(tokens[iterator], newid);
						col += element.length();
					}
				}
				iterator++;
			}
		}

	}
}












//END
