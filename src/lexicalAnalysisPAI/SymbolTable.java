//package lexicalAnalysisPAI;
import java.util.Hashtable;

public class SymbolTable {
	private Hashtable<String, JavaWord> symbols;

	public SymbolTable() {
		symbols = new Hashtable<String, JavaWord>();
		this.fill();
	}

	public JavaWord get(String lexeme) {
		JavaWord result = symbols.get(lexeme);
		return result;
	}

	public void put(String lexeme, JavaWord word) {
		if (!symbols.containsKey(lexeme))
			symbols.put(lexeme, word);
	}

	public boolean existsKeyword(String lexeme) {
		if (symbols.containsKey(lexeme))
			if (symbols.get(lexeme) instanceof JavaKeyword)
				return true;

		return false;
	}


	public void fill() {
		symbols.put("abstract", new JavaKeyword(0, 0, "abstract"));
		symbols.put("assert", new JavaKeyword(0, 0, "assert"));
		symbols.put("boolean", new JavaKeyword(0, 0, "boolean"));
		symbols.put("break", new JavaKeyword(0, 0, "break"));
		symbols.put("byte", new JavaKeyword(0, 0, "byte"));
		symbols.put("case", new JavaKeyword(0, 0, "case"));
		symbols.put("catch", new JavaKeyword(0, 0, "catch"));
		symbols.put("char", new JavaKeyword(0, 0, "char"));
		symbols.put("class", new JavaKeyword(0, 0, "class"));
		symbols.put("const", new JavaKeyword(0, 0, "const"));
		symbols.put("continue", new JavaKeyword(0, 0, "continue"));
		symbols.put("default", new JavaKeyword(0, 0, "default"));
		symbols.put("do", new JavaKeyword(0, 0, "do"));
		symbols.put("double", new JavaKeyword(0, 0, "double"));
		symbols.put("else", new JavaKeyword(0, 0, "else"));
		symbols.put("enum", new JavaKeyword(0, 0, "enum"));
		symbols.put("extends", new JavaKeyword(0, 0, "extends"));
		symbols.put("final", new JavaKeyword(0, 0, "final"));
		symbols.put("finally", new JavaKeyword(0, 0, "finally"));
		symbols.put("float", new JavaKeyword(0, 0, "float"));
		symbols.put("for", new JavaKeyword(0, 0, "for"));
		symbols.put("goto", new JavaKeyword(0, 0, "goto"));
		symbols.put("if", new JavaKeyword(0, 0, "if"));
		symbols.put("implements", new JavaKeyword(0, 0, "implements"));
		symbols.put("import", new JavaKeyword(0, 0, "import"));
		symbols.put("instanceof", new JavaKeyword(0, 0, "instanceof"));
		symbols.put("int", new JavaKeyword(0, 0, "int"));
		symbols.put("interface", new JavaKeyword(0, 0, "interface"));
		symbols.put("long", new JavaKeyword(0, 0, "long"));
		symbols.put("native", new JavaKeyword(0, 0, "native"));
		symbols.put("new", new JavaKeyword(0, 0, "new"));
		symbols.put("package", new JavaKeyword(0, 0, "package"));
		symbols.put("private", new JavaKeyword(0, 0, "private"));
		symbols.put("protected", new JavaKeyword(0, 0, "protected"));
		symbols.put("public", new JavaKeyword(0, 0, "public"));
		symbols.put("return", new JavaKeyword(0, 0, "return"));
		symbols.put("short", new JavaKeyword(0, 0, "short"));
		symbols.put("static", new JavaKeyword(0, 0, "static"));
		symbols.put("strictfp", new JavaKeyword(0, 0, "strictfp"));
		symbols.put("super", new JavaKeyword(0, 0, "super"));
		symbols.put("switch", new JavaKeyword(0, 0, "switch"));
		symbols.put("synchronized", new JavaKeyword(0, 0, "synchronized"));
		symbols.put("this", new JavaKeyword(0, 0, "this"));
		symbols.put("throw", new JavaKeyword(0, 0, "throw"));
		symbols.put("throws", new JavaKeyword(0, 0, "throws"));
		symbols.put("transient", new JavaKeyword(0, 0, "transient"));
		symbols.put("try", new JavaKeyword(0, 0, "try"));
		symbols.put("void", new JavaKeyword(0, 0, "void"));
		symbols.put("volatile", new JavaKeyword(0, 0, "volatile"));
		symbols.put("while", new JavaKeyword(0, 0, "while"));
	}

}
