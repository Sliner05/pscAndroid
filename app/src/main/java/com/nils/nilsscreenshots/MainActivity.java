package com.nils.nilsscreenshots;

import android.os.Bundle;
import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.nils.nilsscreenshots.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public void startpsc (View view){
        // Settings.Secure.putInt(getContentResolver(), Settings.Secure.LOCK_TO_APP_EXIT, 1);
        showButton();
    }
        public void showButton (){
            /*
            Intent intent = new Intent(this, OverlayButtonService.class);
            startService(intent);

             */

            /*
                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_main);
            */

                    // Starte den Floating Widget Service
                    Intent intent = new Intent(this, FloatingWidgetService.class);
                    startService(intent);
                }





    public void stoppsc (View view){
       // Settings.Secure.putInt(getContentResolver(), Settings.Secure.LOCK_TO_APP_EXIT, 0);
        hideButton();
    }
        public void hideButton (){
            Intent intent = new Intent(this, FloatingWidgetService.class);
            stopService(intent);
        }
    public void choosePath (View view){
        // TODO open android Dialog and safe Path
        // unnötig da Pfad hardcoded wird für eigengebrauch
        Toast.makeText(getApplicationContext(),"/SD/Pictures/NilsScreenshots",Toast.LENGTH_SHORT).show();
    }
    File dir = new File(Environment.getExternalStorageDirectory() + "/Pictures/NilsScreenshots");

    public void safePicture () {
        // TODO take picture and safe to path
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, "img.png");

            // Nehme einen Screenshot auf
            Bitmap screenshot = takeScreenshot();

            // Speichere den Screenshot im externen Speicher
            saveScreenshot(screenshot);

    }

    private Bitmap takeScreenshot () {
        View rootView = getWindow().getDecorView().getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }


    // Convert bitmap to PNG
    private void saveScreenshot(Bitmap screenshot){
        try {
            FileOutputStream fos = new FileOutputStream(dir);
            screenshot.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }





    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}



