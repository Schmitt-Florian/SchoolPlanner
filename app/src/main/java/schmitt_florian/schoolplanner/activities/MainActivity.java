package schmitt_florian.schoolplanner.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import schmitt_florian.schoolplanner.R;
import schmitt_florian.schoolplanner.logic.DatabaseHelperImpl;

public class MainActivity extends AppCompatActivity implements

        NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        ScheduleFragment.OnFragmentInteractionListener,
        HomeworkFragment.OnFragmentInteractionListener,
        ExamsFragment.OnFragmentInteractionListener,
        GradesFragment.OnFragmentInteractionListener,
        SubjectsFragment.OnFragmentInteractionListener,
        CreditsFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener,
        AdapterView.OnItemSelectedListener{

    private static final String TAG = "MainActivity";
    FragmentManager fragmentManager;
    private int lastFragment = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Spinner spinner = (Spinner) findViewById(R.id.spinnerTest);
        spinner.setOnItemSelectedListener(this);
        List<String> subjects = new ArrayList<String>();
        subjects.add("Maths");
        subjects.add("Swag");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subjects);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        super.onCreate(savedInstanceState);
        SharedPreferences preferences = this.getSharedPreferences(this.getApplicationContext().toString(), Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = this.getSupportFragmentManager();


        //----TESTING----
        DatabaseHelperImpl testHelper = new DatabaseHelperImpl(this);
        testHelper.resetDatabase();
        testHelper.fillDatabaseWithExamples();

        System.out.println(testHelper.toString());
        //----TESTING----

        lastFragment = preferences.getInt("lastFragment", 0);
        navigationView.getMenu().getItem(lastFragment).setChecked(true);

        onNavigationItemSelected(navigationView.getMenu().getItem(lastFragment));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        SharedPreferences preferences = this.getSharedPreferences(this.getApplicationContext().toString(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        switch (id) {

            case R.id.nav_main: {
                // Goto Home
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.containerMain, new HomeFragment());
                ft.commit();
                editor.putInt("lastFragment", 0);
                break;
            }
            case R.id.nav_schedule: {
                // Goto Schedule
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.containerMain, new ScheduleFragment());
                ft.commit();
                editor.putInt("lastFragment", 1);
                break;
            }
            case R.id.nav_homework: {
                //Goto Homework
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.containerMain, new HomeworkFragment());
                ft.commit();
                editor.putInt("lastFragment", 2);
                break;
            }
            case R.id.nav_exams: {
                // Goto Exams
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.containerMain, new ExamsFragment());
                ft.commit();
                editor.putInt("lastFragment", 3);
                break;
            }
            case R.id.nav_grades: {
                // Goto Subjects
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.containerMain, new GradesFragment());
                ft.commit();
                editor.putInt("lastFragment", 4);
                break;
            }
            case R.id.nav_subjects: {
                // Goto Subjects
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.containerMain, new SubjectsFragment());
                ft.commit();
                editor.putInt("lastFragment", 5);
                break;
            }
            case R.id.nav_credits: {
                //Goto Credits
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.containerMain, new CreditsFragment());
                ft.commit();
                editor.putInt("lastFragment", 6);
                break;
            }
            case R.id.nav_settings: {
                // Goto Settings
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.containerMain, new SettingsFragment());
                ft.commit();
                editor.putInt("lastFragment", 7);
                break;
            }

        }
        editor.apply();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d(TAG, "onFragmentInteraction");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
