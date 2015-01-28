package net.androidsensei.pokedex.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import net.androidsensei.pokedex.model.PokedexContract.PokemonEntry;
import net.androidsensei.pokedex.model.PokedexContract.LocationEntry;

/**
 * Created by hkadejo on 12-10-14.
 */
public class PokedexDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "pokedex.db";
    public static final int DATABASE_VERSION = 1;

    public PokedexDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_POKEMON_TABLE = "CREATE TABLE " + PokemonEntry.TABLE_NAME + " (" +
                PokemonEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PokemonEntry.COLUMN_AVATAR + " TEXT NOT NULL, " +
                PokemonEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                PokemonEntry.COLUMN_UUID + " TEXT NOT NULL, " +
                PokemonEntry.COLUMN_HEIGHT + " REAL NOT NULL, " +
                PokemonEntry.COLUMN_WEIGHT + " REAL NOT NULL );";

        final String SQL_CREATE_LOCATION_TABLE = "CREATE TABLE " + LocationEntry.TABLE_NAME + " (" +
                LocationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                LocationEntry.COLUMN_LATITUDE + " REAL NOT NULL, " +
                LocationEntry.COLUMN_POKEMON_ID + " INTEGER NOT NULL, " +
                LocationEntry.COLUMN_LONGITUDE + " REAL NOT NULL," +
                // Set up the location column as a foreign key to location table.
                " FOREIGN KEY (" + LocationEntry.COLUMN_POKEMON_ID + ") REFERENCES " +
                PokemonEntry.TABLE_NAME + " (" + PokemonEntry._ID + "));";

        sqLiteDatabase.execSQL(SQL_CREATE_POKEMON_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_LOCATION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PokemonEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LocationEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
