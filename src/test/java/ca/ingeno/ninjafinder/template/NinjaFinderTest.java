package ca.ingeno.ninjafinder.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import junit.framework.TestCase;

/*
 * Please provide your name and email...
 * 
 * Name : Jonathan Rochette
 * Email : rochette.jonathan@gmail.com
 * 
 * Please complete the methods below.
 * 
 * These methods will be called by a JUnit3/4 compatible test case suite to validate their correctness.
 * Use JUnit 4 artifacts if you prefer.
 * This file also provides a number of example test cases calling the method stubs.
 * 
 * You should leave your own unit tests in this file if you are using a TDD approach.
 * 
 * The goal here is not to absolutely get a good answer. We want to see your approach to problem solving.
 * We will ask you thereafter to explain to us why you choose to solve the problem the way you did.
 * So, make the code reflect how you actually write code in real life. Including naming, flow, structure, error handling, etc.
 * For instance, you can create as many sub methods as necessary. Also, your code should be able to handle just about
 * anything thrown at it without raising an Exception. If something is not stated in the problem, assume the worst and
 * code around it.
 * 
 * Please note that we have bundled everything in a single file for simplicity.
 * 
 * It is NOT permitted to use external libraries except hamcrest and assertj, which will be
 * included in the project. However, you can use anything else from the JDK.
 */
public class NinjaFinderTest extends TestCase {
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	// Questions

	/*
	 * Unique Values
	 * 
	 * Problem : Complete the isContainingOnlyUniqueValues method and return true if
	 * all the input values are distinct.
	 * 
	 * Input : An array of N items.
	 * 
	 * Output : true if all the input values are distinct or if the array is empty
	 * or if the array is null. False otherwise.
	 * 
	 * Example 1 : [1] returns true.
	 * 
	 * Example 2 : [1, 3, 1] returns false.
	 */

	public boolean isContainingOnlyUniqueValues(int[] values) {
		if (values == null || values.length == 0) {
			return true;
		}

		Set<Integer> distinctValues = new HashSet<>();
		for (int value : values) {
			if (!distinctValues.add(value)) {
				return false;
			}
		}
		return true;
	}

	public void testIsContainingOnlyUniqueValuesShouldReturnTrueWhenInputIsNull() {
		assertTrue(isContainingOnlyUniqueValues(null));
	}

	public void testIsContainingOnlyUniqueValuesShouldReturnTrueWhenInputIsEmpty() {
		assertTrue(isContainingOnlyUniqueValues(new int[] {}));
	}

	public void testIsContainingOnlyUniqueValueShouldReturnTrueWhenInputAsOneElement() {
		assertTrue(isContainingOnlyUniqueValues(new int[] { 1 }));
	}

	public void testUniqueTwoSame() {
		assertFalse(isContainingOnlyUniqueValues(new int[] { 1, 1 }));
	}

	public void testUniqueTwoDifferent() {
		assertTrue(isContainingOnlyUniqueValues(new int[] { 1, 2 }));
	}

	public void testUniqueManyDifferent() {
		assertTrue(isContainingOnlyUniqueValues(new int[] { 1, 2, 44, 58, 3, 10345 }));
	}

	public void testUniqueManyDuplicates() {
		assertFalse(isContainingOnlyUniqueValues(new int[] { 1, 2, 44, 2, 3, 2 }));
	}

	/*
	 * Permutations
	 * 
	 * Problem : Complete the permutations method, generating all the possible
	 * permutations from a set of integers provided as a String. For instance, given
	 * the String "1 3", the method should return "1 3" and "3 1" as a list of
	 * strings.
	 * 
	 * Input : A string of positive integers. The integers in the input strings will
	 * be separated by one or more spaces. The input may contain duplicate elements.
	 * 
	 * Output : An instance of java.util.List<String>, containing a distinct set of
	 * permutations. Each integer should be separated by a single space. The Strings
	 * should be trimmed.
	 * 
	 * Example : "1 3" will return ["1 3", "3 1"].
	 * 
	 */

	public List<String> permutations(String value) {
		if (value == null || value.isEmpty()) {
			return new ArrayList<>();
		}

		int[] integers = NinjaFinderStringUtils.convertStringToArrayOfInteger(value);

		if (integers.length == 0) {
			return new ArrayList<>();
		}

		Set<String> permutations = new HashSet<String>();
		computePermutations(0, integers, permutations);

		return new ArrayList<>(permutations);
	}

	private void computePermutations(int start, int[] input, Set<String> permutations) {
		if (start == input.length) {
			permutations.add(NinjaFinderStringUtils.convertArrayOfIntegersToFormattedString(input));
			return;
		}
		for (int i = start; i < input.length; i++) {
			swapValuesAtIndexes(input, start, i);

			computePermutations(start + 1, input, permutations);

			swapValuesAtIndexes(input, start, i);
		}
	}

	private void swapValuesAtIndexes(int[] input, int firstIndex, int secondIndex) {
		int temp = input[secondIndex];
		input[secondIndex] = input[firstIndex];
		input[firstIndex] = temp;
	}

	public void testPermutationsShouldReturnEmptyListIfInputIsNull() {
		List<String> result = permutations(null);
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	public void testPermutationsShouldReturnEmptyListIfInputIsEmpty() {
		List<String> result = permutations("");
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	public void testPermutationsShouldReturnEmptyListIfInputContainsOnlySpaces() {
		List<String> result = permutations("      ");
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	public void testPermutationsShouldReturnEmptyListIfInputContainsCharacterThatIsNotAnInteger() {
		List<String> result = permutations("  1  k ");
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	public void testPermutationsShouldReturnEmptyListIfInputIsNotAnInteger() {
		List<String> result = permutations(" 4.567");
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	public void testPermutationValueOne() {
		List<String> result = permutations("1");
		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.contains("1"));
	}

	public void testPermutationValueTwoDifferent() {
		List<String> result = permutations("1 2");
		assertNotNull(result);
		assertEquals(2, result.size());
		assertTrue(result.contains("1 2"));
		assertTrue(result.contains("2 1"));
	}

	public void testPermutationValueThreeDifferent() {
		List<String> result = permutations("1 2  3");
		assertNotNull(result);
		assertEquals(6, result.size());
		assertTrue(result.contains("1 2 3"));
		assertTrue(result.contains("1 3 2"));
		assertTrue(result.contains("2 3 1"));
		assertTrue(result.contains("2 1 3"));
		assertTrue(result.contains("3 2 1"));
		assertTrue(result.contains("3 1 2"));
	}

	public void testPermutationsOfThreeValuesWithTwoDuplicates() {
		List<String> result = permutations("1 2  2");
		assertNotNull(result);
		assertEquals(3, result.size());
		assertTrue(result.contains("1 2 2"));
		assertTrue(result.contains("2 1 2"));
		assertTrue(result.contains("2 2 1"));
	}

	/*
	 * Reverse
	 * 
	 * Problem : Complete the reverse method and return the provided integers in
	 * reverse order.
	 * 
	 * Input : A string of positive integers. The integers in the input string will
	 * be separated by one or more spaces. The input may contain duplicate elements.
	 * 
	 * Output : The reverse version of the input string. Each integer should be
	 * separated by a single space. The string should be trimmed.
	 * 
	 * Example : "1   2 3  " should return "3 2 1"
	 */
	public String reverse(String value) {
		if (value == null || value.isEmpty()) {
			return "";
		}

		List<Integer> reversedValue = NinjaFinderArrayUtils
				.convertArrayOfIntegersToList(NinjaFinderStringUtils.convertStringToArrayOfInteger(value));

		if (reversedValue.isEmpty()) {
			return "";
		}

		Collections.reverse(reversedValue);

		return NinjaFinderStringUtils.convertListOfIntegersToFormattedString(reversedValue);
	}

	public void testReverseShouldReturnEmptyStringIfInputIsNull() {
		String result = reverse(null);
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	public void testReverseShouldReturnEmptyStringIfInputIsEmpty() {
		String result = reverse("");
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	public void testReverseShouldReturnEmptyStringIfValueOnlyHasSpaces() {
		String result = reverse("               ");
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	public void testReverseShouldReturnEmptyStringIfInputContainsLetters() {
		String result = reverse("1 2 gg s");
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	public void testReverseShouldReturnEmptyStringIfDoesNotContainInteger() {
		String result = reverse("3.14159265359");
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	public void testReverseSimple() {
		assertEquals("3 2 1", reverse("1   2 3  "));
	}

	public void testReverseFourElements() {
		assertEquals("3 2 1 1", reverse("1       1  2 3  "));
	}

	public void testReverseManyElementsNotSorted() {
		assertEquals("1 5 77 4 5 3 2 1 1", reverse("1       1  2 3  5 4       77 5 1"));
	}

	/*
	 * Prime Palindrome
	 * 
	 * Problem : Complete the method primePalindrome to find the biggest prime
	 * number, which is also a palindrome, within the range of 1 - 1000. This being
	 * a constant, a good coder would just return the hard coded value. But, in the
	 * spirit of this test, please provide a complete solution to programmatically
	 * find the answer.
	 * 
	 * Input : none.
	 * 
	 * Output : An integer that meets the problem statement.
	 * 
	 * Example : No example.
	 */

	public int primePalindrome() {
		return findBiggestPrimePalindromeInRange(1, 1000);
	}

	private int findBiggestPrimePalindromeInRange(int inferiorLimit, int upperLimit) {
		for (int i = upperLimit; i >= inferiorLimit; i--) {
			if (isPrime(i) && isPalindrome(i)) {
				return i;
			}
		}
		return -1;
	}

	private boolean isPrime(int number) {
		for (int i = 2; 2 * i < number; i++) {
			if (number % i == 0)
				return false;
		}
		return true;
	}

	private boolean isPalindrome(int number) {
		return number == reverse(number);
	}

	private int reverse(int number) {
		int reverse = 0;
		while (number > 0) {
			int reminder = number % 10;
			reverse = reverse * 10 + reminder;
			number = number / 10;
		}
		return reverse;
	}

	public void testPrimeIsPositive() {
		assertTrue(primePalindrome() >= 1);
	}

	public void testLargestPrimePalindromeIs929() {
		assertTrue(primePalindrome() == 929);
	}

	public static class NinjaFinderStringUtils {
		public static int[] convertStringToArrayOfInteger(String stringValue) {
			try {
				return Arrays.asList(stringValue.split(" ")).stream()
						.filter(string -> string != null && !string.isEmpty()).mapToInt(Integer::parseInt).toArray();
			} catch (NumberFormatException e) {
				return new int[] {};
			}
		}

		public static String convertArrayOfIntegersToFormattedString(int[] integers) {
			return convertListOfIntegersToFormattedString(NinjaFinderArrayUtils.convertArrayOfIntegersToList(integers));
		}

		public static String convertListOfIntegersToFormattedString(List<Integer> integers) {
			String formattedString = "";
			for (Integer integer : integers) {
				formattedString = formattedString + integer + " ";
			}
			return formattedString.trim();
		}
	}

	public static class NinjaFinderArrayUtils {
		public static List<Integer> convertArrayOfIntegersToList(int[] integers) {
			return IntStream.of(integers).boxed().collect(Collectors.toList());
		}
	}
}
