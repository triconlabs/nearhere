package com.tricon.labs.nearhere.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.reflect.TypeToken;
import com.tricon.labs.nearhere.R;
import com.tricon.labs.nearhere.adapters.PlaceTypeListAdapter;
import com.tricon.labs.nearhere.models.PlaceType;
import com.tricon.labs.nearhere.utils.GsonUtils;

import org.json.JSONArray;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private PlaceTypeListAdapter placeTypeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView rvPlaceTypes = (RecyclerView) findViewById(R.id.rv_place_types);

        placeTypeListAdapter = new PlaceTypeListAdapter(fetchPlaceTypes());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        rvPlaceTypes.setLayoutManager(gridLayoutManager);
        rvPlaceTypes.setAdapter(placeTypeListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<PlaceType> fetchPlaceTypes() {
        try {
            InputStream is = getAssets().open("place_types");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            JSONArray placeTypesJson = new JSONArray(new String(buffer, "UTF-8"));
            List<PlaceType> placeTypes = GsonUtils.fromJson(placeTypesJson.toString(), new TypeToken<ArrayList<PlaceType>>() {}.getType());
            return placeTypes;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void startPlaceListActivity(int position) {
        PlaceType placeType = placeTypeListAdapter.getItem(position);
        Intent intent = new Intent(this, PlacesActivity.class);
        intent.putExtra(PlacesActivity.KEY_PLACE_TYPE, placeType.type);
        startActivity(intent);
    }
}
