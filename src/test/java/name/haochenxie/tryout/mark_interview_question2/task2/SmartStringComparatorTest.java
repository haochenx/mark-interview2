package name.haochenxie.tryout.mark_interview_question2.task2;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class SmartStringComparatorTest {

	@Test
	public void testCompare() {
		
		String[] sample = new String[] { "Py14-2", "Py14-1", "Py130-1a",
				"ABC3", "ABC1", "ABC100", "ABC30", "Py14-12" };
		String[] expected = new String[] { "ABC1", "ABC3", "ABC30", "ABC100",
				"Py14-1", "Py14-2", "Py14-12", "Py130-1a" };
		
		Arrays.sort(sample, new SmartStringComparator());
		
		assertArrayEquals(expected, sample);
	}

	@Test
	public void testTokenize() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompareToken() {
		fail("Not yet implemented");
	}

}
