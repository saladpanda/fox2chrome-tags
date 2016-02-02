fox2chrome-tags
===============

Converts a Firefox bookmarks.json to a Chrome bookmarks.html and appends the tags to bookmarks' names.
Firefox folder structure is ignored. Everything is thrown into one folder.

The generated bookmarks-HTML is designed for Chrome but should work with most other browsers or bookmark services as well. It is a `<!DOCTYPE NETSCAPE-Bookmark-file-1>` file

Syntax
======
Bookmarks are output to chrome with the following naming syntax:
`Bookmark Name [list, of, tags]`
So all the information is merged into the bookmarks name.

Usage
=====

1. Backup Firefox Bookmarks to json file
2. `javac FirefoxToChrome.java`
3. `java FirefoxToChrome infile.json outfile.html`
4. Import html file with Chrome.

Third party libraries
=====================
- [json-simple](https://code.google.com/p/json-simple/)

Many thanks to these libraries' authors and contributors.
