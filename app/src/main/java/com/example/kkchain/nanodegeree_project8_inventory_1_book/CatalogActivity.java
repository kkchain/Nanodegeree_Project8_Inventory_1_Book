package com.example.kkchain.nanodegeree_project8_inventory_1_book;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kkchain.nanodegeree_project8_inventory_1_book.Data.BookContract.BookEntry;
import com.example.kkchain.nanodegeree_project8_inventory_1_book.Data.BookDbHelper;


/**
 * Display list of books that were entered and stored in the app
 */

public class CatalogActivity extends AppCompatActivity {

    /** Database helpter that will provide us access to the database */
    private BookDbHelper mDbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // To access our database, we represent our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity
        mDbHelper = new BookDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        insertBook();
        displayDatabaseInfo();
    }

    /**
     * Method to insert hardcoded data into database for debugging purposes
     */
    private void insertBook() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object
        ContentValues values = new ContentValues();
        values.put(BookEntry.COLUMN_BOOK_NAME, "Back to the Future");
        values.put(BookEntry.COLUMN_BOOK_PRICE, "25");
        values.put(BookEntry.COLUMN_BOOK_QUANTITY, 3);
        values.put(BookEntry.COLUMN_BOOK_SUPPLIER_NAME, "Motion Picture");
        values.put(BookEntry.COLUMN_BOOK_SUPPLIER_PHONE_NO, "123-456-7890");

        // insert a new row into the database
        long newRowId = db.insert(BookEntry.TABLE_NAME, null, values);

        // Toast message on the Book insert status to database
        if (newRowId == -1) {
            Toast.makeText(this, "Error when inserting the data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "New Book added with row id : " + newRowId, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Method to display information on TextView
     */
    private void displayDatabaseInfo() {
        // Create or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                BookEntry._ID,
                BookEntry.COLUMN_BOOK_NAME,
                BookEntry.COLUMN_BOOK_PRICE,
                BookEntry.COLUMN_BOOK_QUANTITY,
                BookEntry.COLUMN_BOOK_SUPPLIER_NAME,
                BookEntry.COLUMN_BOOK_SUPPLIER_PHONE_NO
        };

        Cursor cursor = db.query(
                BookEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.text_view_book);


        try {
            // Create a header in the TextView that looks like:
            // _id - book_name - book_price - book_quantity - book_supplier_name - book_supplier_name

            displayView.setText("The Inventory Table contains " + cursor.getCount() + "books. \n\n");
            displayView.append(BookEntry._ID + " - "
                                + BookEntry.COLUMN_BOOK_NAME + " - "
                                + BookEntry.COLUMN_BOOK_PRICE + " - "
                                + BookEntry.COLUMN_BOOK_QUANTITY + " - "
                                + BookEntry.COLUMN_BOOK_SUPPLIER_NAME + " - "
                                + BookEntry.COLUMN_BOOK_SUPPLIER_PHONE_NO + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(BookEntry._ID);
            int bookNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_NAME);
            int bookPriceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_PRICE);
            int bookQuantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_QUANTITY);
            int bookSupplierNameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_SUPPLIER_NAME);
            int bookSupplierPhoneNoColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_SUPPLIER_PHONE_NO);

            // Iterate through all the rows in the cursor
            while (cursor.moveToNext()) {
            int currentID = cursor.getInt(idColumnIndex);
            String currentBookName = cursor.getString(bookNameColumnIndex);
            int currentBookPrice = cursor.getInt(bookPriceColumnIndex);
            int currentBookQuantity = cursor.getInt(bookQuantityColumnIndex);
            String currentBookSupplierName = cursor.getString(bookSupplierNameColumnIndex);
            String currentBookSupplierPhoneNo = cursor.getString(bookSupplierPhoneNoColumnIndex);

            // Display the values from each column of the current from the cursor to TextView
            displayView.append(("\n" + currentID + " - "
                                        + currentBookName + " - "
                                        + currentBookPrice + " - "
                                        + currentBookQuantity + " - "
                                        + currentBookSupplierName + " - "
                                        + currentBookSupplierPhoneNo));
            }

        } finally {
            // Close the cursor when done reading from it
            cursor.close();
        }

    }
}
