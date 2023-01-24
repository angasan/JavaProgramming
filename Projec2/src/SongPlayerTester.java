import java.util.NoSuchElementException;

/**
 * This class implements unit test methods to check the correctness of Song,
 * LinkedNode, SongPlayer ForwardSongIterator and BackwardSongIterator classes
 * in P07 Iterable Song Player assignment.
 *
 */
public class SongPlayerTester {
	/**
	 * This method test and make use of the Song constructor, an accessor (getter)
	 * method, overridden method toString() and equal() method defined in the Song
	 * class.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testSong() {

		/**
		 * Checks that the constructor throws an exception when needed
		 */

		try {
			Song s = new Song(null, "hola", "D");
			System.out.println("25");
			return false;
		} catch (IllegalArgumentException iae) {

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		try {
			Song s = new Song("Roar", "Katy Perry", "");
			System.out.println("36");
			return false;
		} catch (IllegalArgumentException iae) {

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		try {
			Song s = new Song("La Fama", " ", "3:59");
			System.out.println("46");
			return false;
		} catch (IllegalArgumentException iae) {

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		/**
		 * Check that the constructor doesn't throw an exception when it shouldn't
		 */
		try {
			Song s = new Song("La Fama", "Rosalia", "3:08");
			if (!s.getArtist().equals("Rosalia"))
				return false;
			if (!s.getDuration().equals("3:08"))
				return false;
			if (!s.getSongName().toLowerCase().equals("la fama"))
				return false;
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * This method test and make use of the LinkedNode constructor, an accessor
	 * (getter) method, and a mutator (setter) method defined in the LinkedCart
	 * class.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testLinkedNode() {

		// Check that the constructor throws the correct exception when needed
		try {
			LinkedNode<String> ln = new LinkedNode<String>(null, null, null);
			return false;
		} catch (IllegalArgumentException iae) {

		} catch (Exception e) {
			return false;
		}

		// Check that no exception is thrown when correctly created
		try {
			LinkedNode<String> ln = new LinkedNode<String>(null, "head", null);
			if (!ln.getData().equals("head"))
				return false;
			if (!(ln.getNext() == null))
				return false;
			if (!(ln.getPrev() == null))
				return false;

			// Try creating a new node and make it the next one
			LinkedNode<String> ln2 = new LinkedNode<String>(ln, "head", null);
			ln.setNext(ln2);
			if (!ln.getNext().equals(ln2))
				return false;

			LinkedNode<String> ln3 = new LinkedNode<String>(null, "head", ln);
			ln.setPrev(ln3);
			if (!(ln.getPrev().equals(ln3)))
				return false;

		} catch (IllegalArgumentException iae) {
			return false;
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * This method checks for the correctness of addFirst(), addLast() and add(int
	 * index) method in SongPlayer class
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testSongPlayerAdd() {
		SongPlayer sp = new SongPlayer();
		if (sp.size() != 0)
			return false;

		/**
		 * TEST ADD FIRST:
		 */

		// Check that the required exception is thrown when passed an invalid song
		try {
			sp.addFirst​(null);
			return false;
		} catch (NullPointerException npe) {

		} catch (Exception e) {
			return false;
		}

		// Check that no exception is thrown when passed a valid song
		// Check that the size is updated
		// Check that the head and tail are the same
		// Check that the head and tail store s
		Song s = new Song("La Fama", "Rosalia", "3:08");
		try {
			sp.addFirst​(s);
			if (sp.size() != 1)
				return false;
			if (sp.getFirst() != sp.getLast())
				return false;
			if (sp.getFirst() != s)
				return false;
		} catch (NullPointerException npe) {
			return false;
		} catch (Exception e) {
			return false;
		}

		/**
		 * TEST ADD LAST:
		 */

		// Check that the required exception is thrown when passed an invalid song
		try {
			sp.addLast​(null);
			return false;
		} catch (NullPointerException npe) {

		} catch (Exception e) {
			return false;
		}

		// Check that no exception is thrown when passed a valid song
		// Check that the size is updated
		// Check that the head and tail are the same
		// Check that the head and tail store s
		SongPlayer sp2 = new SongPlayer();
		try {
			sp2.addLast​(s);
			if (sp2.size() != 1)
				return false;
			if (sp2.getFirst() != sp2.getLast())
				return false;
			if (sp2.getFirst() != s)
				return false;
		} catch (NullPointerException npe) {
			return false;
		} catch (Exception e) {
			return false;
		}

		Song s2 = new Song("Nunca Estoy", "C Tangana", "2:20");
		sp2.addFirst​(s2);
		if (sp2.size() != 2)
			return false;
		if (sp2.getFirst() != s2)
			return false;
		if (sp2.getFirst() == sp2.getLast())
			return false;

		sp.addLast​(s2);
		if (sp.size() != 2)
			return false;
		if (sp.getFirst() != s)
			return false;
		if (sp.getLast() != s2)
			return false;
		if (sp.getFirst() == sp.getLast())
			return false;

		/**
		 * TEST ADD:
		 */
		Song s3 = new Song("Mojito", "Jay Chou", "3:05");

		// Check that the method throws the right exception when out of indexes
		try {
			sp.add​(15, s3);
			return false;
		} catch (IndexOutOfBoundsException iob) {

		} catch (Exception e) {
			return false;
		}

		// Check that the method throws the right exception when the song passed is
		// invalid
		try {
			sp.add​(1, null);
			return false;
		} catch (NullPointerException npe) {

		} catch (Exception e) {
			return false;
		}

		// Check that the method doesn't throw any exception when valid parameters are
		// passed
		try {
			sp.add​(1, s3);
			if (sp.size() != 3)
				return false;
			if (sp.getFirst() != s)
				return false;
			if (sp.getLast() != s2)
				return false;
			if (sp.get​(1) != s3)
				return false;
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * This method checks for the correctness of getFirst(), getLast() and get(int
	 * index) method in SongPlayer class
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testSongPlayerGet() {
		SongPlayer sp = new SongPlayer();

		/**
		 * TEST GET FIRST:
		 */

		// Check that the method throws the apporpiate exception when the list is empty
		try {
			sp.getFirst();
			System.out.println("291");
			return false;
		} catch (NoSuchElementException nse) {

		} catch (Exception e) {
			System.out.println("296");
			return false;
		}
		Song s = new Song("Clear Day", "Jay Chou", "4:59");
		sp.addFirst​(s);

		// Check that no exceptions are thrown when the list isnt empty
		Song s2 = new Song("Superman Can’t Fly", "Jay Chou", "4:59");
		try {
			if (s != sp.getFirst()) {
				System.out.println("306");
				return false;
			}
		} catch (Exception e) {
			System.out.println("307");
			return false;
		}
		Song s3 = new Song("StarBoy", "The Weeknd", "3:50");
		sp.addFirst​(s3);
		if (s3 != sp.getFirst()) {
			System.out.println("316");
			return false;
		}

		/**
		 * TEST GET FIRST:
		 */
		SongPlayer sp2 = new SongPlayer();
		// Check that the method throws the apporpiate exception when the list is empty
		try {
			sp2.getLast();
			System.out.println("326");
			return false;
		} catch (NoSuchElementException nse) {

		} catch (Exception e) {
			System.out.println("331");
			return false;
		}
		sp2.addLast​(s);

		// Check that no exceptions are thrown when the list isnt empty
		try {
			if (s != sp2.getLast()) {
				System.out.println("339");
				return false;
			}
		} catch (Exception e) {
			System.out.println("343");
			return false;
		}
		sp2.addLast​(s3);
		if (s3 != sp2.getLast()) {
			System.out.println("348");
			return false;
		}

		/**
		 * TEST GET:
		 */
		sp = new SongPlayer();

		// Try that the method throws the correct exception when the index is out of
		// bounds
		try {
			sp.get​(0);
			return false;
		} catch (IndexOutOfBoundsException iob) {

		} catch (Exception e) {
			return false;
		}
		sp.addFirst​(s);
		sp.addLast​(s2);
		sp.addLast​(s3);

		// Try that the method throws the correct exception when the index is out of
		// bounds
		try {
			sp.get​(5);
			return false;
		} catch (IndexOutOfBoundsException iob) {

		} catch (Exception e) {
			return false;
		}

		// Test that the method doesn't throw any exception when parameter is valid
		try {
			if (sp.get​(1) != s2)
				return false; // Check that it works
			if (sp.get​(0) != s)
				return false;
			if (sp.get​(2) != s3)
				return false;
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * This method checks for the correctness of removeFirst(), removeLast() and
	 * remove(int index) method in SongPlayer class
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testSongPlayerRemove() {
		SongPlayer sp = new SongPlayer();
		Song s = new Song("Clear Day", "Jay Chou", "4:59");
		Song s2 = new Song("Superman Can’t Fly", "Jay Chou", "4:59");
		Song s3 = new Song("StarBoy", "The Weeknd", "3:50");

		/**
		 * TEST REMOVE FIRST:
		 */

		// Check that the required exception is thrown when empty list
		try {
			sp.removeFirst();
			return false;
		} catch (NoSuchElementException npe) {

		} catch (Exception e) {
			return false;
		}

		sp.addFirst​(s);
		sp.addLast​(s2);
		sp.addLast​(s3);

		// Check that no exception is thrown when valid list
		try {
			// Check that the size is reduced, the first node is updated and the song
			// returned is the expected
			Song s4 = sp.removeFirst();
			if (sp.size() != 2)
				return false;
			if (sp.getFirst() != s2)
				return false;
			if (!s4.equals(s))
				return false;
		} catch (Exception e) {
			return false;
		}

		/**
		 * TEST REMOVE LAST:
		 */
		// Check that the required exception is thrown when passed an invalid list
		sp = new SongPlayer();
		try {
			sp.removeLast();
			return false;
		} catch (NoSuchElementException npe) {

		} catch (Exception e) {
			return false;
		}

		sp.addFirst​(s);
		sp.addLast​(s2);
		sp.addLast​(s3);
		
		// Check that the NO exception is thrown when passed a valid list
		try {
			Song s4 = sp.removeLast();
			if (sp.size() != 2) // Size reduced by one
				return false;
			if (sp.getFirst() != s) // First element stays the same
				return false;
			if (sp.getLast() != s2) // Last element updated
				return false;
			if (!s4.equals(s3)) // returned song is the expected
				return false;
		} catch (Exception e) {
			return false;
		}

		/**
		 * TEST REMOVE:
		 */
		sp = new SongPlayer();

		// Check that the method throws the right exception when invalid index
		try {
			sp.remove(0);
			return false;
		} catch (IndexOutOfBoundsException iob) {

		} catch (Exception e) {
			return false;
		}

		sp.addFirst​(s);
		sp.addLast​(s2);
		sp.addLast​(s3);

		try {
			sp.remove(5); // 5 is still out of bounds
			return false;
		} catch (IndexOutOfBoundsException iob) {

		} catch (Exception e) {
			return false;
		}

		// Check that the NO exception is thrown when passed a valid list and index
		try {
			Song s4 = sp.remove(1); 
			if (sp.size() != 2) return false;
			if (sp.getFirst() != s) return false;
			if (sp.getLast() != s3) return false;
			if (!s4.equals(s2)) return false;
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * This method checks for the correctness of iterator(),
	 * switchPlayingDirection() and String play() method in SongPlayer class
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testSongPlayerIterator() {
		
		SongPlayer songList = new SongPlayer();
		songList.addFirst​(new Song("Mojito", "Jay Chou", "3:05"));
		songList.addFirst​(new Song("Secret", "Jay Chou", "4:56"));
		songList.addFirst​(new Song("Clear Day", "Jay Chou", "4:59"));
		songList.addFirst​(new Song("Dragon Fist", "Jay Chou", "4:32"));
		songList.addFirst​(new Song("Out of Time", "The Weeknd", "3:34"));
		songList.addLast​(new Song("StarBoy", "The Weeknd", "3:50"));
		songList.addLast​(new Song("Save Your Tears", "The Weeknd", "3:35"));
		songList.add​(1, new Song("Simple Love", "Jay Chou", "4:30"));
		songList.add​(2, new Song("Superman Can’t Fly", "Jay Chou", "4:59"));
		songList.addLast​(new Song("Oh My God", "Adele", "3:45"));
		songList.addLast​(new Song("Levitating", "Dua Lipa", "3:23"));
		songList.add​(6, new Song("Be Kind", "Marshmello & Halsey", "2:53"));
		
		String exp = "Out of Time---The Weeknd---3:34\n"
				+ "Simple Love---Jay Chou---4:30\n"
				+ "Superman Can’t Fly---Jay Chou---4:59\n"
				+ "Dragon Fist---Jay Chou---4:32\n"
				+ "Clear Day---Jay Chou---4:59\n"
				+ "Secret---Jay Chou---4:56\n"
				+ "Be Kind---Marshmello & Halsey---2:53\n"
				+ "Mojito---Jay Chou---3:05\n"
				+ "StarBoy---The Weeknd---3:50\n"
				+ "Save Your Tears---The Weeknd---3:35\n"
				+ "Oh My God---Adele---3:45\n"
				+ "Levitating---Dua Lipa---3:23\n";
		
		String exp2 = "Simple Love---Jay Chou---4:30\n"
				+ "Superman Can’t Fly---Jay Chou---4:59\n"
				+ "Dragon Fist---Jay Chou---4:32\n"
				+ "Clear Day---Jay Chou---4:59\n"
				+ "Secret---Jay Chou---4:56\n"
				+ "Mojito---Jay Chou---3:05\n"
				+ "StarBoy---The Weeknd---3:50\n"
				+ "Save Your Tears---The Weeknd---3:35\n"
				+ "Oh My God---Adele---3:45\n";
		
		String exp3 = "Oh My God---Adele---3:45\n"
				+ "Save Your Tears---The Weeknd---3:35\n"
				+ "StarBoy---The Weeknd---3:50\n"
				+ "Mojito---Jay Chou---3:05\n"
				+ "Secret---Jay Chou---4:56\n"
				+ "Clear Day---Jay Chou---4:59\n"
				+ "Dragon Fist---Jay Chou---4:32\n"
				+ "Superman Can’t Fly---Jay Chou---4:59\n"
				+ "Simple Love---Jay Chou---4:30\n";
		
		String res = songList.play();
		if (!res.equals(exp)) return false;
		
		songList.remove(6);
		songList.removeFirst();
		songList.removeLast();
		
		String res2 = songList.play();
		if (!res2.equals(exp2)) return false;
		
		songList.switchPlayingDirection();
		res = songList.play();
		if (!res.equals(exp3)) return false;
		
		
		return true;
	}

	/**
	 * This method checks for the correctness of contains(Object song), clear(),
	 * isEmpty()and size() method in SongPlayer class
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testSongPlayerCommonMethod() {
		
		SongPlayer songList = new SongPlayer();
		if (!songList.isEmpty()) return false;
		
		songList.addFirst​(new Song("Mojito", "Jay Chou", "3:05"));
		songList.addFirst​(new Song("Secret", "Jay Chou", "4:56"));
		songList.addFirst​(new Song("Clear Day", "Jay Chou", "4:59"));
		songList.addFirst​(new Song("Dragon Fist", "Jay Chou", "4:32"));
		songList.addFirst​(new Song("Out of Time", "The Weeknd", "3:34"));
		songList.addLast​(new Song("StarBoy", "The Weeknd", "3:50"));
		songList.addLast​(new Song("Save Your Tears", "The Weeknd", "3:35"));
		songList.add​(1, new Song("Simple Love", "Jay Chou", "4:30"));
		songList.add​(2, new Song("Superman Can’t Fly", "Jay Chou", "4:59"));
		songList.addLast​(new Song("Oh My God", "Adele", "3:45"));
		songList.addLast​(new Song("Levitating", "Dua Lipa", "3:23"));
		songList.add​(6, new Song("Be Kind", "Marshmello & Halsey", "2:53"));
		songList.remove(6);
		songList.removeFirst();
		songList.removeLast();
		Song oneSong = new Song("Mojito", "Jay Chou", "3:05");
		
		if (!songList.contains​(oneSong)) return false;
		if (songList.size() != 9) return false;
		
		oneSong = new Song("Be Kind", "Marshmello & Halsey", "2:53");
		if (songList.contains​(oneSong)) return false;
		if (songList.isEmpty()) return false;
		
		songList.clear();
		if (!songList.isEmpty()) return false;
		
		return true;
	}

	/**
	 * This method checks for the correctness of ForwardSongIterator class
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testForwardSongIterator() {
		/**
		 * Create a chain of 3 nodes: 1. Create 3 songs to go with each node 2. Create
		 * the nodes 3. Link the nodes
		 */

		Song s = new Song("La Fama", "Rosalia", "3:08");
		Song s1 = new Song("Grenade", "Bruno Mars", "3:50");
		Song s2 = new Song("Nunca Estoy", "C Tangana", "2:20");
		LinkedNode<Song> ln = new LinkedNode<Song>(null, s, null);
		LinkedNode<Song> ln1 = new LinkedNode<Song>(ln, s1, null);
		ln.setNext(ln1);
		LinkedNode<Song> ln2 = new LinkedNode<Song>(ln1, s2, null);
		ln1.setNext(ln2);

		/**
		 * Create a ForwardSongIterator object with a reference to the head of the list.
		 * 1. Check that the iterator has a head (first next) object to look at 2. Get
		 * the head (next) object and check that it is what we expected 3. Check that
		 * this method (next) iterates to the next one and both methods keep working
		 */
		ForwardSongIterator f = new ForwardSongIterator(ln);
		if (!f.hasNext())
			return false;
		try {
			if (!f.next().equals(s))
				return false;
			if (!f.hasNext())
				return false;
			if (!f.next().equals(s1))
				return false;
			if (!f.hasNext())
				return false;
			if (!f.next().equals(s2))
				return false;
			if (f.hasNext())
				return false;
		} catch (NoSuchElementException nse) {
			return false;
		} catch (Exception e) {
			return false;
		}
		try {
			// Try getting the next node in the list when there is not one
			f.next();
			return false;
		} catch (NoSuchElementException nse) {

		} catch (Exception e) {
			return false;
		}

		/**
		 * Check that the methods work as expected for an empty Linked List
		 */
		ForwardSongIterator f1 = new ForwardSongIterator(null);
		if (f1.hasNext())
			return false;
		try {
			f1.next();
			return false;
		} catch (NoSuchElementException nse) {

		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * This method checks for the correctness of BackwardSongIterator class
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testBackwardSongIterator() {
		/**
		 * Create a chain of 3 nodes: 1. Create 3 songs to go with each node 2. Create
		 * the nodes 3. Link the nodes
		 */

		Song s = new Song("La Fama", "Rosalia", "3:08");
		Song s1 = new Song("Grenade", "Bruno Mars", "3:50");
		Song s2 = new Song("Nunca Estoy", "C Tangana", "2:20");
		LinkedNode<Song> ln = new LinkedNode<Song>(null, s, null);
		LinkedNode<Song> ln1 = new LinkedNode<Song>(ln, s1, null);
		ln.setNext(ln1);
		LinkedNode<Song> ln2 = new LinkedNode<Song>(ln1, s2, null);
		ln1.setNext(ln2);

		/**
		 * Create a ForwardSongIterator object with a reference to the head of the list.
		 * 1. Check that the iterator has a head (first next) object to look at 2. Get
		 * the head (next) object and check that it is what we expected 3. Check that
		 * this method (next) iterates to the next one and both methods keep working
		 */
		BackwardSongIterator b = new BackwardSongIterator(ln2);
		if (!b.hasNext())
			return false;
		try {
			if (!b.next().equals(s2))
				return false;
			if (!b.hasNext())
				return false;
			if (!b.next().equals(s1))
				return false;
			if (!b.hasNext())
				return false;
			if (!b.next().equals(s))
				return false;
			if (b.hasNext())
				return false;
		} catch (NoSuchElementException nse) {
			return false;
		} catch (Exception e) {
			return false;
		}
		try {
			// Try getting the next node in the list when there is not one
			b.next();
			return false;
		} catch (NoSuchElementException nse) {

		} catch (Exception e) {
			return false;
		}

		/**
		 * Check that the methods work as expected for an empty Linked List
		 */
		BackwardSongIterator b1 = new BackwardSongIterator(null);
		if (b1.hasNext())
			return false;
		try {
			b1.next();
			return false;
		} catch (NoSuchElementException nse) {

		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * This method calls all the test methods defined and implemented in your
	 * SongPlayerTester class.
	 * 
	 * @return true if all the test methods defined in this class pass, and false
	 *         otherwise.
	 */
	public static boolean runAllTests() {
		return false;
	}

	/**
	 * Driver method defined in this SongPlayerTester class
	 * 
	 * @param args input arguments if any.
	 */
	public static void main(String[] args) {

		boolean tSong = testSong();
		boolean tLinkedNode = testLinkedNode();
		boolean tForw = testForwardSongIterator();
		boolean tBack = testBackwardSongIterator();
		boolean tAdd = testSongPlayerAdd();
		boolean tGet = testSongPlayerGet();
		boolean tIter = testSongPlayerIterator();
		boolean tComm = testSongPlayerCommonMethod();

		System.out.println("Test Song: " + tSong);
		System.out.println("Test LinkedNode: " + tLinkedNode);
		System.out.println("Test Forward Iterator: " + tForw);
		System.out.println("Test Backward Iterator: " + tBack);
		System.out.println("Test Song Player Add: " + tAdd);
		System.out.println("Test Song Player Get: " + tGet);
		System.out.println("Test Song Iterator: " + tIter);
		System.out.println("Test Common Method: " + tComm);
	}
}