package name.haochenxie.tryout.mark_interview_question2.task2;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Comparator;

public class SmartStringComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static Token[] tokenize(String str) {
		try (PushbackReader reader = new PushbackReader(new StringReader(str))) {
			ArrayList<Token> tokens = new ArrayList<>();
			char[] buff = new char[str.length()];
			
			while (readerHasNext(reader)) {
				int len = 0;
	
				TokenType type = TokenType.whichType(buff[len++] = (char) reader.read());
				
				for (int ch = reader.read(); ch > 0; ch = reader.read()) {
					if (TokenType.whichType(ch) != type) {
						reader.unread(ch);
						break;
					}

					buff[len++] = (char) ch;
				}
				
				if (buff[0] < 0)
					break;
				else
					tokens.add(new Token(type, new String (buff, 0, len)));
			}
				
			return tokens.toArray(new Token[tokens.size()]);
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
	
	private static boolean readerHasNext(PushbackReader reader) throws IOException {
		int ch = reader.read();
		reader.unread(ch);
		return ch >= 0;
	}

	public static int compareToken(Token a, Token b) {
		if (a.type == TokenType.NUMBER && b.type == TokenType.NUMBER)
			return Long.decode(a.value).compareTo(Long.decode(b.value));
		else
			return a.value.compareTo(b.value);
	}

	public static class Token {

		public TokenType type;
		public String value;
		
		public Token(TokenType type, String value) {
			this.type = type;
			this.value = value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Token other = (Token) obj;
			if (type != other.type)
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return String.format("Token(%s,%s)", type, value);
		}
		
	}

	public static enum TokenType {
		STRING, NUMBER;
		
		public static TokenType whichType(int ch) {
			if (Character.isDigit(ch))
				return NUMBER;
			else
				return STRING;
		}

	}

}
