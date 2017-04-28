package schmitt_florian.schoolplanner.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import schmitt_florian.schoolplanner.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        ScheduleFragment.OnFragmentInteractionListener,
        SubjectsFragment.OnFragmentInteractionListener,
        HomeworkFragment.OnFragmentInteractionListener,
        ExamsFragment.OnFragmentInteractionListener,
        CreditsFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener
         {

    private static final String TAG = "MainActivity";
    FragmentManager fragmentManager;
    Fragment f;
    boolean a = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            // Goto Home
            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.containerMain, homeFragment);
            ft.commit();

        } else if (id == R.id.nav_schedule) {
            // Goto Schedule
            ScheduleFragment scheduleFragment = new ScheduleFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.containerMain, scheduleFragment);
            ft.commit();

        } else if (id == R.id.nav_subjects) {
            // Goto Subjects
            SubjectsFragment subjectsFragment = new SubjectsFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.containerMain, subjectsFragment);
            ft.commit();

        } else if (id == R.id.nav_homework) {
            //Goto Homework
            HomeworkFragment homeworkFragment = new HomeworkFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.containerMain, homeworkFragment);
            ft.commit();

        } else if (id == R.id.nav_exams) {
            // Goto Exams
            ExamsFragment examsFragment = new ExamsFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.containerMain, examsFragment);
            ft.commit();

        } else if (id == R.id.nav_credits) {
            //Goto Credits
            CreditsFragment creditsFragment = new CreditsFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.containerMain, creditsFragment);
            ft.commit();

        } else if (id == R.id.nav_settings) {
            // Goto Settings
            SettingsFragment settingsFragment = new SettingsFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.containerMain, settingsFragment);
            ft.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d(TAG, "onFragmentInteraction");
    }
}
