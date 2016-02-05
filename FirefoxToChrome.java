import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class FirefoxToChrome {
	public static void main(String[] args) throws IOException,ParseException {
		if (args.length < 2) {
			System.out.println("USAGE:\njava FirefoxToChrome infile.json outfile.html");
			System.exit(0);
		}
		
		/*
		 * Read and parse file
		 */
		File inputFile = new File(args[0]);
		JSONObject placesRoot = (JSONObject) JSONValue.parseWithException(new FileReader(inputFile));
		ArrayList<Bookmark> bookmarksList = new ArrayList<Bookmark>();
		
		extractPlaces(placesRoot, bookmarksList);
		
		writeHTMLFile(args[1], bookmarksList);
	}
	
	public static void extractPlaces(JSONObject root, ArrayList<Bookmark> bookmarksList) {
		if (root.get("type").equals("text/x-moz-place-container")) {
			// recursion
			JSONArray rootChildren = (JSONArray) root.get("children");
			if (rootChildren != null) {
				for (Object child : rootChildren) {
					extractPlaces((JSONObject)child, bookmarksList);
				}
			}
		} else if (root.get("type").equals("text/x-moz-place")) {
			JSONObject place = root;
			
			String title = (String)place.get("title");
			String uri = (String)place.get("uri");
			String tags = (String)place.get("tags");
			
			// ignore firefox-specific folders etc.
			if (uri.startsWith("place:")) return;
			
			Bookmark currentBookmark = new Bookmark(title, uri);
			if (tags != null) {
				currentBookmark.tags.addAll(Arrays.asList(tags.split(",")));
			}

			if (! bookmarksList.contains(currentBookmark)) {
				// create a new Bookmark
				bookmarksList.add(currentBookmark);
			} else {
				// same bookmark. Merge tags into it.
				int index = bookmarksList.indexOf(currentBookmark);
				Bookmark oldBookmark = bookmarksList.get(index);
				oldBookmark.tags.addAll(currentBookmark.tags);
			}
		}
	}
	
	public static void writeHTMLFile(String filename, ArrayList<Bookmark> bookmarksList) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(new File(filename)));
		
		// header
		pw.println("<!DOCTYPE NETSCAPE-Bookmark-file-1>\n<META HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=UTF-8\">\n<TITLE>Bookmarks</TITLE>\n<H1>Bookmarks</H1>\n<DL><p>\n\t<DT><H3 ADD_DATE=\"0\" LAST_MODIFIED=\"0\" PERSONAL_TOOLBAR_FOLDER=\"true\">Bookmarks Bar</H3>\n\t<DL><p>\n\t</DL><p>");
		
		// bookmarks
		for (Bookmark b : bookmarksList) {
			pw.println("\t<DT><A HREF=\"" + b.uri + "\" ADD_DATE=\"0\" LAST_VISIT=\"0\" LAST_MODIFIED=\"0\">" + b.toString() + "</A>");
		}
		
		// footer
		pw.println("</DL><p>");
		
		pw.flush();
		pw.close();
	}
}
