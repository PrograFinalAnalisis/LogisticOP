package com.logisticop.logisticop.logisticop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Programador on 05/03/2015.
 */
public class version extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.version);


    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.Version) {
            Intent intent = null;
            intent = new Intent(version.this, version.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
