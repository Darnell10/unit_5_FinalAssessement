package c4q.com.unit_5_finalassessment.databases;

import android.content.ContentValues;
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

public class SQLDatabase extends SQLiteOpenHelper {

  //private static SQLDatabase sqLiteDatabase;

  private static SQLDatabase instance;
  private SQLiteDatabase myDb;

  private static final String DATABASE_NAME = "sport.db";
  private static final String TABLE_NAME = "sports";
  private static final int SCHEMA_VERSION = 1;


  public static SQLDatabase getInstance(Context context) {
    if (instance == null) {
      instance = new SQLDatabase(context.getApplicationContext());
    }
    return instance;
  }

  public SQLDatabase(Context context) {
    super(context, DATABASE_NAME, null, SCHEMA_VERSION);
  }


  @Override
  public void onCreate(SQLiteDatabase db) {
    myDb = db;
    db.execSQL("CREATE TABLE " + TABLE_NAME
        + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + " author TEXT, title TEXT, "
        + "description TEXT, url TEXT,urlToImage TEXT,published TEXT);");
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

  // This is the original code!!
//    public void addStory(Article articles) {
//        Cursor cursor = getReadableDatabase().rawQuery(
//                " SELECT * FROM " + TABLE_NAME
//                + "WHERE author " + articles.getAuthor()
//                + " AND title " + articles.getTitle()
//                + " AND  description  " + articles.getDescription()
//                + " AND url " + articles.getUrl()
//                + " AND  urlToImage " + articles.getUrlToImage()
//                + " AND published  " + articles.getPublishedAt()
//                + "';'", null);

//  public void addArticle(Article article) {
//    getWritableDatabase().execSQL(" INSERT INTO " + TABLE_NAME +
//        "(author,title,description,url,urlToImage,published) VALUES("
//        + "'" + article.getAuthor() + "'"
//        + "," + "'" + article.getTitle() + "'"
//        + "," + "'" + article.getDescription() + "'"
//        + "," + "'" + article.getUrl() + "'"
//        + "," + "'" + article.getUrlToImage() + "'"
//        + "," + "'" + article.getPublishedAt()
//        + "';");
//  }


  // related to original code do not delete till issue is solved.
//        if (cursor.getCount()==0){
//            getWritableDatabase().execSQL( " INSERT INTO " + TABLE_NAME
//                    +"(author,title,description,url ,urlToImage ,published) VALUES (‘" +
//                    articles.getAuthor()+"‘,’" +
//                    articles.getTitle()+"‘,’" +
//                    articles.getDescription()+"‘,’" +
//                    articles.getUrl()+"‘,’" +
//                    articles.getUrlToImage()+"‘,’" +
//                    articles.getPublishedAt()+"‘);’");
//        }
//        cursor.close();
//    }

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
              cursor.getString(cursor.getColumnIndex("published")));
          articleList.add(article);
        } while (cursor.moveToNext());
      }
    }
    return articleList;
  }


}
