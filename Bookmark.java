import java.util.*;

public class Bookmark {
	String title;
	String uri;
	ArrayList<String> tags = new ArrayList<String>();
	
	public Bookmark(String title, String uri) {
		this.title = title;
		this.uri = uri;
	}
	
	/*
	 * Assume:
	 * Same URI = Same Bookmark
	 */
	@Override
	public boolean equals(Object o) {
		if (! (o instanceof Bookmark)) return false;
		Bookmark b = (Bookmark) o;
		
		if (this.uri.equals(b.uri)) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Print the bookmark in form:
	 * Title [list, of, tags]
	 */
	@Override
	public String toString() {
		String s = title;
		s += " [";
		for (int i=0; i<this.tags.size(); i++) {
			s += this.tags.get(i);
			if (i < this.tags.size()-1) {
				s += ", ";
			}
		}
		s += "]";
		
		return s;
	}
}