package name.haochenxie.tryout.mark_interview_question2.task2;

import java.util.Comparator;

public class SmartStringComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static Token[] tokenize(String str) {
		// TODO not implemented
		return null;
	}
	
	public static int compareToken(Token a, Token b) {
		// TODO not implemented
		return 0;
	}

	public static class Token {

		public TokenType type;
		public String value;
		
		public Token(TokenType type, String value) {
			this.type = type;
			this.value = value;
		}
		
	}

	public static enum TokenType {
		STRING, NUMBER
	}

}
