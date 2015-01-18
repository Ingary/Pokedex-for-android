package net.androidsensei.pokedex.api;

import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import net.androidsensei.pokedex.PokedexApplication;
import net.androidsensei.pokedex.model.Pokemon;
import net.androidsensei.pokedex.model.PokemonList;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rafael on 1/18/15.
 */
public class PokedexApi {

    static String MI_POKEDEX_URL = "http://mi-pokedex.herokuapp.com/api/";
    static String POKEMONS_LIST = MI_POKEDEX_URL + "pokemons";

    public static void getPokemons(Response.Listener response, Response.ErrorListener error) {
        GsonRequest<PokemonList> getPersons =
                new GsonRequest<PokemonList>(POKEMONS_LIST, PokemonList.class, response, error);

        PokedexApplication.getInstance().addToRequestQueue(getPersons);

    }
}
