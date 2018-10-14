package com.example.kkchain.nanodegeree_project8_inventory_1_book.Data;

import android.provider.BaseColumns;

public class BookContract {

    private BookContract() {}

    /**
     * Inner class that defines constant values for the Book Store Inventory database table
     * Each entry in the table represents a single book title
     */

    public static final class BookEntry implements BaseColumns {

        /** Name of database table for Book Store */
        public final static String TABLE_NAME = "books";

        /**
         * Unique ID number for the book
         * Type INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Book Name
         * Type: TEXT
         */
        public final static String COLUMN_BOOK_NAME = "book_name";

        /**
         * Book Price
         * Type: INTEGER
         */
        public final static String COLUMN_BOOK_PRICE = "book_price";

        /**
         * Book Quantity
         * Type: INTEGER
         */
        public final static String COLUMN_BOOK_QUANTITY = "book_quantity";

        /**
         * Book Supplier Name
         * Type: TEXT
         */
        public final static String COLUMN_BOOK_SUPPLIER_NAME = "book_supplier_name";

        /**
         * Book Supplier Phone No
         * Type: INTEGER
         */
        public final static String COLUMN_BOOK_SUPPLIER_PHONE_NO = "book_supplier_phone_no";

    }

}
