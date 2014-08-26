package in.msme.cic.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Gallerysql extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "GalleryDB";

	public Gallerysql(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create book table
		String CREATE_BOOK_TABLE = "CREATE TABLE SERVICE ( " + "Title TEXT, "
				+ "Des TEXT , Image_url TEXT )";

		// create books table
		db.execSQL(CREATE_BOOK_TABLE);
		Log.e("where", "oncreate");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older books table if existed
		db.execSQL("DROP TABLE IF EXISTS books");

		// create fresh books table
		this.onCreate(db);
	}

	// ---------------------------------------------------------------------

	/**
	 * CRUD operations (create "add", read "get", update, delete) book + get all
	 * books + delete all books
	 */

	// Books table name
	private static final String TABLE_SUBJECT = "SERVICE";
	// Books Table Columns names
	private static final String KEY_imageurl = "Title";


	

	// Get All Books
	public ArrayList<String> getAllSevices() {
		ArrayList<String> services = new ArrayList<String>();

		// 1. build the query
		String query = "SELECT  * FROM " + TABLE_SUBJECT;

		// 2. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		// 3. go over each row, build book and add it to list
		
		if (cursor.moveToFirst()) {
			do {
				String m1;
				// book = new Subject_detail();
				// book.setId(Integer.parseInt(cursor.getString(0)));
				// book.setTitle(cursor.getString(1));
				// book.setAuthor(cursor.getString(2))
				m1=cursor.getString(0);
				
				Log.e("postion in get all data", cursor.getPosition() + "");
				// Add book to books
				// books.add(book);
				services.add(m1);
			} while (cursor.moveToNext());
		}

		// Log.d("getAllBooks()", books.toString());
		cursor.close();
		db.close();
		// return books
		return services;
	}

	// Deleting single book
	public void addService(String service) {
		// for logging
		Log.e("where", "come inside add");
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(KEY_imageurl, service); // get title
			
			// 3. insert
			db.insert(TABLE_SUBJECT, // table
					null, // nullColumnHack
					values); // key/value -> keys = column names/ values =
								// column
								// values

		db.close();
	}
}
