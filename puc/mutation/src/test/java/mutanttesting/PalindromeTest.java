package mutanttesting;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PalindromeTest {

	Palindrome palindrome;

	@Before
	public void setUp() {
		this.palindrome = new Palindrome();
	}
	
	@Test
	public void whenEmptyString_thanAccept() {
		assertTrue(palindrome.isPalindrome("noon"));
	}

	@Test
	public void whenPalindrom_thanAccept() {
		assertTrue(palindrome.isPalindrome("noon"));
	}

	@Test
	public void whenNotPalindrom_thanReject() {
		assertFalse(palindrome.isPalindrome("box"));
	}

	@Test
	public void whenNearPalindrom_thanReject() {
		assertFalse(palindrome.isPalindrome("neon"));
	}
	
}
