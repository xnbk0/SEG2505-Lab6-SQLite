package com.example.sqlitelab;

import android.provider.BaseColumns;

public final class FeedReaderContract {

    // Empêche la création accidentelle d'un objet FeedReaderContract
    private FeedReaderContract() {}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }
}