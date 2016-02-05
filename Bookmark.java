import java.util.*;

public class Bookmark {
	String title;
	String uri;
	HashSet<String> tags = new HashSet<String>();
	
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
		
		if (this.uri.equals(b.uri) && this.title.equals(b.title)) {
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
		StringBuilder s = new StringBuilder(title).append(" [");
		
		StringBuilder taglist = new StringBuilder();
		for (String tag : tags) {
			if (taglist.length() != 0) {
				taglist.append(", ");
			}
			taglist.append(tag);
		}

		s.append(taglist).append("]");
		return s.toString();
	}
}