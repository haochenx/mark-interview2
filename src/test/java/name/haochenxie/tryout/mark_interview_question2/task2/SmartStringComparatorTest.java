package name.haochenxie.tryout.mark_interview_question2.task2;

import static org.junit.Assert.*;

import java.util.Arrays;

import name.haochenxie.tryout.mark_interview_question2.task2.SmartStringComparator.Token;
import name.haochenxie.tryout.mark_interview_question2.task2.SmartStringComparator.TokenType;

import org.junit.Test;

public class SmartStringComparatorTest {
	
	protected SmartStringComparator comp = new SmartStringComparator();

	@Test
	public void testCompare() {
		
		String[] sample = new String[] { "Py14-2", "Py14-1", "Py130-1a",
				"ABC3", "ABC1", "ABC100", "ABC30", "Py14-12" };
		String[] expected = new String[] { "ABC1", "ABC3", "ABC30", "ABC100",
				"Py14-1", "Py14-2", "Py14-12", "Py130-1a" };
		
		Arrays.sort(sample, comp);
		
		assertArrayEquals(expected, sample);
	}

	@Test
	public void testTokenize() {
		
		String sample = "Py13-25ac-bd007acb2";
		
		Token[] expected = new Token[] {
				new Token(TokenType.STRING, "Py"),
				new Token(TokenType.NUMBER, "13"),
				new Token(TokenType.STRING, "-"),
				new Token(TokenType.NUMBER, "25"),
				new Token(TokenType.STRING, "ac-bd"),
				new Token(TokenType.NUMBER, "007"),
				new Token(TokenType.STRING, "acb"),
				new Token(TokenType.NUMBER, "2"),
		};
		
		assertArrayEquals(expected, SmartStringComparator.tokenize(sample));
	}

	@Test
	public void testCompareTokenStrStr() {
		Token[] groupA = new Token[] { 
				new Token(TokenType.STRING, "abc"), 
				new Token(TokenType.STRING, "abc"), 
				new Token(TokenType.STRING, "acb"), 
		};
		Token[] groupB = new Token[] {
				new Token(TokenType.STRING, "abc"), 
				new Token(TokenType.STRING, "acb"), 
				new Token(TokenType.STRING, "abc"), 
		};
		
		int[] expected = new int[] {
				0, -1, 1
		};
		
		for (int i = 0; i < groupA.length; ++i) {
			int result = SmartStringComparator.compareToken(groupA[i], groupB[i]);
			
			if (expected[i] == 0)
				assertEquals(0, result);
			else
				assertTrue(expected[i] * result > 0);
		}
	}

	@Test
	public void testCompareTokenNumNum() {
		Token[] groupA = new Token[] { 
				new Token(TokenType.NUMBER, "123"), 
				new Token(TokenType.NUMBER, "003"), 
				new Token(TokenType.NUMBER, "123"),
				new Token(TokenType.NUMBER, "321"), 
				new Token(TokenType.NUMBER, "12"),
		};
		Token[] groupB = new Token[] {
				new Token(TokenType.NUMBER, "123"), 
				new Token(TokenType.NUMBER, "003"), 
				new Token(TokenType.NUMBER, "321"), 
				new Token(TokenType.NUMBER, "123"), 
				new Token(TokenType.NUMBER, "100"),
		};
		
		int[] expected = new int[] {
				0, 0, -1, 1, -1
		};
		
		for (int i = 0; i < groupA.length; ++i) {
			int result = SmartStringComparator.compareToken(groupA[i], groupB[i]);
			
			if (expected[i] == 0)
				assertEquals(0, result);
			else
				assertTrue(expected[i] * result > 0);
		}
	}
	
	@Test
	public void testCompareTokenStrNum() {
		Token[] groupA = new Token[] { 
				new Token(TokenType.NUMBER, "123"), 
				new Token(TokenType.STRING, "ABC"), 
				new Token(TokenType.STRING, "ABCD"), 
		};
		Token[] groupB = new Token[] {
				new Token(TokenType.STRING, "ABC"), 
				new Token(TokenType.NUMBER, "123"), 
				new Token(TokenType.NUMBER, "02"), 
		};
		int[] expected = new int[] {
				-1, 1, 1
		};
		
		for (int i = 0; i < groupA.length; ++i) {
			int result = SmartStringComparator.compareToken(groupA[i], groupB[i]);
			
			if (expected[i] == 0)
				assertEquals(0, result);
			else
				assertTrue(expected[i] * result > 0);
		}
	}

}
