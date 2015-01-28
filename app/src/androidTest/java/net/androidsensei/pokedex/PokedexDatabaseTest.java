package net.androidsensei.pokedex;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import net.androidsensei.pokedex.model.PokedexDbHelper;
import net.androidsensei.pokedex.model.PokedexContract.PokemonEntry;

public class PokedexDatabaseTest extends AndroidTestCase {

    public static final String LOG_TAG = PokedexDatabaseTest.class.getSimpleName();

    public void testCreateDb() throws Throwable {
        mContext.deleteDatabase(PokedexDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new PokedexDbHelper(this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }

    public void testInsertReadDb() {

        int id = 1;
        String name = "Charizard";
        String avatar = "http://img2.wikia.nocookie.net/__cb20140203022724/p__/protagonist/images/9/95/Charizard.png";
        String uuid = "a3667158-612a-47cf-8592-43e62b6f54";
        double height = 1.75;
        double weight = 220.25;

        ContentValues values = new ContentValues();
        values.put(PokemonEntry._ID, id);
        values.put(PokemonEntry.COLUMN_NAME, name);
        values.put(PokemonEntry.COLUMN_AVATAR, avatar);
        values.put(PokemonEntry.COLUMN_UUID, uuid);
        values.put(PokemonEntry.COLUMN_HEIGHT, height);
        values.put(PokemonEntry.COLUMN_WEIGHT, weight);

        PokedexDbHelper dbHelper = new PokedexDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long pokemonId;
        pokemonId = db.insert(PokemonEntry.TABLE_NAME, null, values);

        assertEquals(id,pokemonId);

        String[] columns = {
                PokemonEntry._ID,
                PokemonEntry.COLUMN_NAME,
                PokemonEntry.COLUMN_AVATAR,
                PokemonEntry.COLUMN_UUID,
                PokemonEntry.COLUMN_HEIGHT,
                PokemonEntry.COLUMN_WEIGHT,
        };

        Cursor cursor = db.query(
                PokemonEntry.TABLE_NAME,  // Table to Query
                columns,
                null, // Columns for the "where" clause
                null, // Values for the "where" clause
                null, // columns to group by
                null, // columns to filter by row groups
                null // sort order
        );

        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(PokemonEntry.COLUMN_NAME);
            String namePokemon = cursor.getString(nameIndex);

            assertEquals(name, namePokemon);
        }else {
            fail("Epic Fail :(");
        }

        cursor.close();
        dbHelper.close();
    }
}
