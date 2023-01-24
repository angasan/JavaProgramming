////////////////////////////// FILE HEADER ////////////////////////////////////
//
// Author:   Angela Galindo Santos
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * Test methods to verify your implementation of the methods for P08.
 */
public class DNATester {

	// TO DO: verify your LinkedQueue implementation directly

	/**
	 * Tester method to verify the correctness of the implementation of the method
	 * size()
	 * 
	 * @return boolean
	 */
	public static boolean testQueueSize() {
		LinkedQueue<Character> t = new LinkedQueue<Character>();
		try {
			if (t.size() != 0)
				return false;
			t.enqueue('T');
			if (t.size() != 1)
				return false;
			t.enqueue('C');
			if (t.size() != 2)
				return false;
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Test the implementation of the enqueue and dequeue methods
	 * @return boolean
	 */
	public static boolean testEnqueueDequeue() {
		LinkedQueue<Character> t = new LinkedQueue<Character>();
		if (t.front != null)
			return false;
		if (t.back != null || t.back != t.front)
			return false;
		if (t.size() != 0)
			return false;

		/**
		 * Test enque
		 */
		try {
			t.enqueue('T');
			if (!t.front.getData().equals('T'))
				return false;
			if (!t.back.getData().equals('T') || t.back != t.front)
				return false;
			if (t.size() != 1)
				return false;
			t.enqueue('C');
			if (!t.front.getData().equals('T'))
				return false;
			if (!t.back.getData().equals('C') || t.back == t.front)
				return false;
			if (t.size() != 2)
				return false;
		} catch (Exception e) {
			return false;
		}

		/**
		 * Test deque
		 */
		Character s;
		try {
			s = t.dequeue();
			if (t.size() != 1)
				return false;
			if (!s.equals('T'))
				return false;
			if (!t.front.getData().equals('C'))
				return false;
			s = t.dequeue();
			if (t.size() != 0)
				return false;
		} catch (Exception e) {
			return false;
		}

		try {
			s = t.dequeue();
			return false;
		} catch (NoSuchElementException nse) {

		} catch (Exception e) {
			return false;
		}

		if (!testQueueSize())
			return false;
		return true;
	}

	/**
	 * Tests the transcribeDNA() method
	 * 
	 * @return true if and only if the method works correctly
	 */
	public static boolean testTranscribeDNA() {
		DNA testDNA = new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
		String mRNA = "CCUCAGUCAAUUCGCUGGCCCUGUAUGACAGAACCAUUAGAGGCUCGAUCUUUCAGAGAC";
		System.out.println(testDNA.transcribeDNA().toString());
		if (testDNA.transcribeDNA().toString().replaceAll(" ", "").equals(mRNA)) {
			return true;
		}
		return false;
	}

	/**
	 * Tests the translateDNA() method
	 * 
	 * @return true if and only if the method works correctly
	 */
	public static boolean testTranslateDNA() {
		DNA testDNA = new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
		System.out.println(testDNA.translateDNA().toString());
		if (testDNA.translateDNA().toString().replaceAll(" ", "").equals("PQSIRWPCMTEPLEARSFRD")) {
			return true;
		}
		return false;
	}

	// TODO: verify the mRNATranslate() method
	public static boolean testMRNATranslate() {
		DNA testDNA = new DNA("GGA");
		LinkedQueue<Character> mRNA = new LinkedQueue<Character>();
		mRNA.enqueue('C');
		mRNA.enqueue('C');
		mRNA.enqueue('U');

		LinkedQueue<String> amino = testDNA.mRNATranslate(mRNA);
		if (!amino.front.getData().equals("P"))
			return false;

		mRNA = new LinkedQueue<Character>();
		mRNA.enqueue('C');
		mRNA.enqueue('U');
		mRNA.enqueue('A');
		mRNA.enqueue('A');
		mRNA.enqueue('U');
		mRNA.enqueue('G');
		mRNA.enqueue('U');
		amino = testDNA.mRNATranslate(mRNA);

		String expected = "L M ";
		if (!amino.toString().equals(expected))
			return false;
		if (!amino.front.getData().equals("L"))
			return false;
		amino.dequeue();
		if (!amino.front.getData().equals("M"))
			return false;

		mRNA = new LinkedQueue<Character>();
		mRNA.enqueue('G');
		mRNA.enqueue('G');
		mRNA.enqueue('C');
		mRNA.enqueue('C');
		mRNA.enqueue('G');
		mRNA.enqueue('G');
		mRNA.enqueue('G');
		mRNA.enqueue('A');
		mRNA.enqueue('G');
		mRNA.enqueue('G');
		mRNA.enqueue('C');
		mRNA.enqueue('C');
		mRNA.enqueue('A');
		mRNA.enqueue('C');
		mRNA.enqueue('C');
		mRNA.enqueue('U');
		mRNA.enqueue('A');
		mRNA.enqueue('G');
		mRNA.enqueue('G');
		mRNA.enqueue('U');
		mRNA.enqueue('U');
		System.out.println(mRNA.toString());
		amino = testDNA.mRNATranslate(mRNA);

		expected = "G R E A T ";
		System.out.println(amino.toString());
		if (!amino.toString().equals(expected))
			return false;

		return true;
	}

	/**
	 * Main method - use this to run your test methods and output the results
	 * (ungraded)
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {
		System.out.println("testEnqueueDequeue: " + testEnqueueDequeue());
		System.out.println("testQueueSize: " + testQueueSize());
		System.out.println("transcribeDNA: " + testTranscribeDNA());
		System.out.println("testMRNATranslate: " + testMRNATranslate());
		System.out.println("translateDNA: " + testTranslateDNA());

	}

}
