package com.example.sqlitelab;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                "Mon premier titre"
        );
        values.put(
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE,
                "Mon premier sous-titre"
        );

        long newRowId = db.insert(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                null,
                values
        );
        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE
        };

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {

            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(
                            FeedReaderContract.FeedEntry._ID
                    )
            );

            String title = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE
                    )
            );

            String subtitle = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE
                    )
            );
        }

        cursor.close();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}