package in.msme.cic.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EventSQL extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "EventDB";

	public EventSQL(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create book table
		String CREATE_BOOK_TABLE = "CREATE TABLE SERVICE ( " + "Title TEXT, "
				+ "Des TEXT ,Date TEXT,Venue TEXT, Image_url TEXT )";

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
	private static final String KEY_TITLE = "Title";
	private static final String KEY_DES = "Des";
	private static final String KEY_IMAGE = "Image_url";
	private static final String KEY_VENUE = "Venue";
	private static final String KEY_DATE = "Date";
	private static final String[] COLUMNS = { KEY_TITLE, KEY_DES, KEY_IMAGE };

	// Get All Books
	public ArrayList<Event> getAllEvent() {
		ArrayList<Event> services = new ArrayList<Event>();

		// 1. build the query
		String query = "SELECT  * FROM " + TABLE_SUBJECT;

		// 2. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		// 3. go over each row, build book and add it to list
		Event service = null;
		if (cursor.moveToFirst()) {
			do {
				// book = new Subject_detail();
				// book.setId(Integer.parseInt(cursor.getString(0)));
				// book.setTitle(cursor.getString(1));
				// book.setAuthor(cursor.getString(2))
				service = new Event();
				service.setTitle(cursor.getString(0));
				service.setDes(cursor.getString(1));
				service.setDate(cursor.getString(2));
				service.setvenue(cursor.getString(3));
				service.setImage_url(cursor.getString(4));
				
				Log.e("postion in get all data", cursor.getPosition() + "");
				// Add book to books
				// books.add(book);
				services.add(service);
			} while (cursor.moveToNext());
		}

		// Log.d("getAllBooks()", books.toString());
		cursor.close();
		db.close();
		// return books
		return services;
	}

	// Deleting single book
	public void addService(Event service) {
		// for logging
		Log.e("where", "come inside add");
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(KEY_TITLE, service.getTitle()); // get title
			values.put(KEY_DES, service.getDes()); // get authorkey
			values.put(KEY_IMAGE, service.getImage_url());
			values.put(KEY_DATE, service.getDate());
			values.put(KEY_VENUE, service.getvenue());
			// 3. insert
			db.insert(TABLE_SUBJECT, // table
					null, // nullColumnHack
					values); // key/value -> keys = column names/ values =
								// column
								// values

		db.close();
	}
}
