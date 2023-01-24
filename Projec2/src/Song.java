
public class Song {

	private String songName; // name or title of the song
	private String artist; // artist of the song
	private String duration; // duration of the song
	
	public Song (String songName, String artist, String duration) {
		if (songName == null|| artist == null || duration == null) 
			throw new IllegalArgumentException("One  or more of the fields is/are null");
		if (songName.isBlank()|| artist.isBlank() || duration.isBlank()) 
			throw new IllegalArgumentException("One  or more of the fields is/are blank");
		
		this.songName = songName;
		this.artist = artist;
		this.duration = duration;
	}
	
	public String getSongName() {
		return this.songName;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public String getDuration() {
		return this.duration;
	}
	
	public String toString() {
		String s = getSongName() + "---" + getArtist() + "---" + getDuration();
		return s; 
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Song)) return false;
		Song s = (Song) o;
		if (!(s.getSongName().equals(this.songName) || s.getArtist().equals(this.artist))) return false;
		return true;
	}
}
