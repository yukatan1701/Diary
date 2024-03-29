package database;

import java.io.*;
import java.util.Scanner;
import diary.Note;

public final class Query {
	private static String dir = "query/";
	
	private static String getQuery(String filename, Object... args)
				throws IOException {
		// FileReader file = new FileReader(dir + filename);
		ClassLoader cldr = Query.class.getClassLoader();
		InputStream file = cldr.getResourceAsStream(dir + filename);
		Scanner in = new Scanner(file);
		String text = "";
		while (in.hasNextLine()) {
			text += in.nextLine() + " ";
		}
		String query = String.format(text, args);
		in.close();
		file.close();
		return query;
	}
	
	public static String createTable(String dbname) throws IOException {
		return getQuery("create_table", dbname);
	}
	
	public static String selectAll(String dbname) throws IOException {
		return getQuery("select_all", dbname);
	}
    
    public static String selectPreview(String dbname) throws IOException {
		return getQuery("select_preview", dbname);
	}
	
	public static String selectById(String dbname, int id) throws IOException {
		return getQuery("select_id", dbname, id);
	}
	
	public static String deleteIndividually(String dbname, int[] rows)
			throws IOException {
		String suffix = "(" + String.valueOf(rows[0]);
		for (int i = 1; i < rows.length; i++)
			suffix += ", " + String.valueOf(rows[i]);
		suffix += ")";
		return getQuery("delete_individually", dbname, suffix);
	}
	
	public static String deleteBetween(String dbname, int[] rows)
			throws IOException {
		return getQuery("delete_range", dbname, rows[0], rows[1]);
	}
	
	private static String clear(String line) {
		String answer = line.replace("\'", "\'\'");
		return answer;
	}
	
	public static String insert(String dbname, Note note)
			throws IOException {
		return getQuery("insert_note", dbname, clear(note.getTitle()),
            clear(note.getText()), note.getMood(), note.getDesire(),
            note.getBlood(), note.getTears());
	}
    
	
	public static String editAll(String dbname, int id, String title, 
			String text) throws IOException {
		return getQuery("edit_all", dbname, clear(title), clear(text), id);
	}
	
	public static String editField(String dbname, int id, String field, 
			String text) throws IOException {
		return getQuery("edit_field", dbname, field, clear(text), id);
	}
    
    public static String editNote(String dbname, Note note) throws IOException {
        return getQuery("edit_note", dbname, note.getDate(),
            clear(note.getTitle()), clear(note.getText()),
            note.getMood(), note.getDesire(),
            note.getBlood(), note.getTears(),
            note.getId());
    }
}
