package net.androidsensei.pokedex.model;

import android.provider.BaseColumns;

/**
 * Created by hkadejo on 12-10-14.
 */
public class PokedexContract implements BaseColumns {

    public static final class PokemonEntry implements BaseColumns {

        public static final String TABLE_NAME = "pokemons";

        public static final String COLUMN_UUID = "uuid";

        public static final String COLUMN_NAME = "name";

        public static final String COLUMN_AVATAR = "avatar";

        public static final String COLUMN_HEIGHT = "height";

        public static final String COLUMN_WEIGHT = "weight";

    }

    public static final class LocationEntry implements BaseColumns {

        public static final String TABLE_NAME = "locations";

        public static final String COLUMN_POKEMON_ID = "pokemon_id";

        public static final String COLUMN_LATITUDE = "latitude";

        public static final String COLUMN_LONGITUDE = "longitude";

    }

}
