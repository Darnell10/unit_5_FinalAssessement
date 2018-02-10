package c4q.com.unit_5_finalassessment.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import c4q.com.unit_5_finalassessment.model.Article;


/**
 * Created by D on 2/4/18.
 */

public class SQL_Database extends SQLiteOpenHelper {

    private static SQL_Database instance;

    private static final String DATABASE_NAME = "sport.db";
    private static final String TABLE_NAME = "sports";
    private static final int SCHEMA_VERSION = 1;


    public synchronized static SQL_Database getInstance(Context context) {
        if (instance == null) {
            instance = new SQL_Database(context.getApplicationContext());
        }
        return instance;
    }

    private SQL_Database(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " author TEXT, title TEXT, "
                + "description TEXT, url TEXT,urlToImage TEXT,publishedAt TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // leave blank unless I want to add to new version of Schema
        // something like the code below\
        /**if (newVersion > oldVersion) {
         db.execSQL(“ALTER TABLE student ADD COLUMN student_rollno INTEGER DEFAULT 0");
         }
         */
    }

    public void addArticle(Article article) {
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME
                        + " WHERE author = '" + article.getAuthor()
                        + "' AND title = '" + article.getTitle()
                        + "' AND description = '" + article.getDescription()
                        + "' AND url = '" + article.getUrl()
                        + "' AND urlToImage = '" + article.getUrlToImage()
                        + "' AND publishedAt = '" + article.getPublishedAt() +
                        "';", null
        );

        if (cursor.getCount() == 0) {
            getWritableDatabase().execSQL(" INSERT INTO " + TABLE_NAME +
                    "(author,title,description,url,urlToImage,publishedAt) VALUES("
                    + "'" + article.getAuthor() + "'"
                    + "," + "'" + article.getTitle() + "'"
                    + "," + "'" + article.getDescription() + "'"
                    + "," + "'" + article.getUrl() + "'"
                    + "," + "'" + article.getUrlToImage() + "'"
                    + "," + "'" + article.getPublishedAt()
                    + "';");
        }
        cursor.close();
    }

    public List<Article> getArticlesList() {
        List<Article> articleList = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery(
                " SELECT * FROM " + TABLE_NAME + ";", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Article article = new Article(
                            cursor.getString(cursor.getColumnIndex("author")),
                            cursor.getString(cursor.getColumnIndex("title")),
                            cursor.getString(cursor.getColumnIndex("description")),
                            cursor.getString(cursor.getColumnIndex("url")),
                            cursor.getString(cursor.getColumnIndex("urlToImage")),
                            cursor.getString(cursor.getColumnIndex("publishedAt")));
                    articleList.add(article);
                } while (cursor.moveToNext());
            }
        }
        return articleList;
    }
}
