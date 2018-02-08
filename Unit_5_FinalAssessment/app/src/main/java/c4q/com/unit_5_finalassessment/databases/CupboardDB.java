package c4q.com.unit_5_finalassessment.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import c4q.com.unit_5_finalassessment.model.Articles;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by D on 2/6/18.
 */

public class CupboardDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sports.db";
    private static final int DATABASE_VERSION = 1;

    // make your database instance a singleton instance across the entire application's lifecycle.
    private static CupboardDB instance;
    // the static getInstance() method ensures that only one PostsDatabaseHelper will ever exist at any given time.
    // if the instance object has not been initialized, one will be created. If one has already been created then it
    // will simply be returned.

    public static synchronized CupboardDB getInstance (Context context){
        if (instance != null){
            instance = new CupboardDB(context.getApplicationContext());
        }
        return instance;
    }

    private CupboardDB (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    static {
        //Registers the model
        cupboard().register(Articles.class);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // this will ensure that all tables are created
        cupboard().withDatabase(db).createTables();
        // add indexes and other database tweaks in this method if you want
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this will upgrade tables, adding columns and new tables.
        // Note that existing columns will not be converted
        cupboard().withDatabase(db).upgradeTables();
        // do migration work if you have an alteration to make to your schema here

    }
}
