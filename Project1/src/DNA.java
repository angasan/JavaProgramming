////////////////////////////// FILE HEADER ////////////////////////////////////
//
// Author:   Angela Galindo Santos
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * 
 * Class that simulates the DNA
 *
 */
public class DNA {

	protected LinkedQueue<Character> DNA;
	protected static String[][] mRNAtoProteinMap = { { "UUU", "F" }, { "UUC", "F" }, { "UUA", "L" }, { "UUG", "L" },
			{ "UCU", "S" }, { "UCC", "S" }, { "UCA", "S" }, { "UCG", "S" }, { "UAU", "Y" }, { "UAC", "Y" },
			{ "UAA", "STOP" }, { "UAG", "STOP" }, { "UGU", "C" }, { "UGC", "C" }, { "UGA", "STOP" }, { "UGG", "W" },
			{ "CUU", "L" }, { "CUC", "L" }, { "CUA", "L" }, { "CUG", "L" }, { "CCU", "P" }, { "CCC", "P" },
			{ "CCA", "P" }, { "CCG", "P" }, { "CAU", "H" }, { "CAC", "H" }, { "CAA", "Q" }, { "CAG", "Q" },
			{ "CGU", "R" }, { "CGC", "R" }, { "CGA", "R" }, { "CGG", "R" }, { "AUU", "I" }, { "AUC", "I" },
			{ "AUA", "I" }, { "AUG", "M" }, { "ACU", "T" }, { "ACC", "T" }, { "ACA", "T" }, { "ACG", "T" },
			{ "AAU", "N" }, { "AAC", "N" }, { "AAA", "K" }, { "AAG", "K" }, { "AGU", "S" }, { "AGC", "S" },
			{ "AGA", "R" }, { "AGG", "R" }, { "GUU", "V" }, { "GUC", "V" }, { "GUA", "V" }, { "GUG", "V" },
			{ "GCU", "A" }, { "GCC", "A" }, { "GCA", "A" }, { "GCG", "A" }, { "GAU", "D" }, { "GAC", "D" },
			{ "GAA", "E" }, { "GAG", "E" }, { "GGU", "G" }, { "GGC", "G" }, { "GGA", "G" }, { "GGG", "G" } };

	// Constructors:
	/**
	 * Creates a DNA object from a reference sequence
	 * 
	 * @param sequence
	 */
	public DNA(String sequence) {
		this.DNA = new LinkedQueue<Character>();
		for (int i = 0; i < sequence.length(); i++) {
			this.DNA.enqueue(sequence.charAt(i));
		}
	}

	// ACCESSOR METHODS:
	/**
	 * Creates a mRNA sequence from the DNA sequence stored in this DNA object and
	 * returns it as an object
	 * 
	 * @return LinkedQueue<Character>
	 */
	public LinkedQueue<Character> transcribeDNA() {
		LinkedQueue<Character> mRNA = new LinkedQueue<Character>();
		for (int i = 0; i < DNA.size(); i++) {
			Character nucleoid = null;
			try {
				nucleoid = DNA.dequeue();
			} catch (NoSuchElementException nse) {
				return mRNA;
			}
			if (nucleoid.equals('A'))
				mRNA.enqueue('U');
			else if (nucleoid.equals('T'))
				mRNA.enqueue('A');
			else if (nucleoid.equals('G'))
				mRNA.enqueue('C');
			else if (nucleoid.equals('C'))
				mRNA.enqueue('G');

			DNA.enqueue(nucleoid);
		}
		return mRNA;
	}

	/**
	 * Creates a aminoacid queue from the mRNA sequence translated from the DNA
	 * 
	 * @param mRNA
	 * @return
	 */
	public LinkedQueue<String> mRNATranslate(LinkedQueue<Character> mRNA) {

		LinkedQueue<String> aminoacids = new LinkedQueue<String>();

		while (DNA.size() > 0) {
			Character n1 = null;
			Character n2 = null;
			Character n3 = null;
			try {
				n1 = mRNA.dequeue();
				n2 = mRNA.dequeue();
				n3 = mRNA.dequeue();
			} catch (NoSuchElementException nse) {
				return aminoacids;
			}
			String codon = n1.toString() + n2.toString() + n3.toString();
			String match = "";
			for (int j = 0; j < mRNAtoProteinMap.length; j++) {
				if (mRNAtoProteinMap[j][0].equals(codon)) {
					if (mRNAtoProteinMap[j][1].equals("STOP")) {
						return aminoacids;
					}
					match = mRNAtoProteinMap[j][1];
					break;
				}
			}
			aminoacids.enqueue(match);
		}
		return aminoacids;
	}

	/**
	 * Creates the sequence of aminoacids directly from this DNA object using the
	 * methods implemented above
	 * 
	 * @return LinkedQueue<String>
	 */
	public LinkedQueue<String> translateDNA() {
		LinkedQueue<Character> mRNA = transcribeDNA();
		LinkedQueue<String> aminoacids = mRNATranslate(mRNA);
		return aminoacids;
	}
}
