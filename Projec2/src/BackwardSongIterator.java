import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardSongIterator implements Iterator<Song> {
	private LinkedNode<Song> next; // reference to the next linked node in a list of nodes
	
	// Constructor
		public BackwardSongIterator(LinkedNode<Song> last) {
			this.next = last;
		}
		
		public boolean hasNext() {
			return next != null;
		}
		
		public Song next() { 
			if (this.hasNext() == false) throw new NoSuchElementException("No such element");
			Song s = next.getData();
			this.next = next.getPrev();
			return s;
		}
}
