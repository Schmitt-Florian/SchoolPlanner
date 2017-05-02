package schmitt_florian.schoolplanner.activities;

import android.content.Intent;
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

import schmitt_florian.schoolplanner.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        ScheduleFragment.OnFragmentInteractionListener,
        SubjectsFragment.OnFragmentInteractionListener,
        HomeworkFragment.OnFragmentInteractionListener,
        ExamsFragment.OnFragmentInteractionListener,
        CreditsFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener {

    private static final String TAG = "MainActivity";
    FragmentManager fragmentManager;


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


        //  ----TESTING----
//        DatabaseHelperImpl testHelper = new DatabaseHelperImpl(this);
//        testHelper.resetDatabase();
//        testHelper.fillDatabaseWithExamples();
//
//        System.out.println(testHelper.toString());
        //  ----TESTING----


        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));

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

        if (id == R.id.nav_main) {
            // Goto Home
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.containerMain, new HomeFragment());
            ft.commit();
        } else if (id == R.id.nav_schedule) {
            // Goto Schedule
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.containerMain, new ScheduleFragment());
            ft.commit();
        } else if (id == R.id.nav_subjects) {
            // Goto Subjects
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.containerMain, new SubjectsFragment());
            ft.commit();
        } else if (id == R.id.nav_homework) {
            //Goto Homework
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.containerMain, new HomeworkFragment());
            ft.commit();
        } else if (id == R.id.nav_exams) {
            // Goto Exams
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.containerMain, new ExamsFragment());
            ft.commit();
        } else if (id == R.id.nav_credits) {
            //Goto Credits
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.containerMain, new CreditsFragment());
            ft.commit();
        } else if (id == R.id.nav_settings) {
            // Goto Settings
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.containerMain, new SettingsFragment());
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

    public void sendEmail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"projectschoolplanner@gmail.com"});
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Wählen Sie Ihren E-Maildienst aus"));
    }
}
