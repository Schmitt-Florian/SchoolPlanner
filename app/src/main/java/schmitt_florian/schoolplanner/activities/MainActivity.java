package schmitt_florian.schoolplanner.activities;

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
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import schmitt_florian.schoolplanner.R;
import schmitt_florian.schoolplanner.logic.DatabaseHelperImpl;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        ScheduleFragment.OnFragmentInteractionListener,
        SubjectsFragment.OnFragmentInteractionListener,
        HomeworkFragment.OnFragmentInteractionListener,
        ExamsFragment.OnFragmentInteractionListener,
        CreditsFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener {

    public int plusButtonViewId1 = 2131558571;
    public int plusButtonViewId2 = 2131558564;


    public int cancelButtonViewId1 = 2131558573;
    public int cancelButtonViewId2 = 2131558566;



    private static final String TAG = "MainActivity";
    FragmentManager fragmentManager;


    public void onPlusClick (View view){

        FrameLayout frameLayout = (FrameLayout) view.getParent();
        frameLayout.setVisibility(view.INVISIBLE);

        int viewId = view.getId();

        if (viewId == plusButtonViewId1){
            LinearLayout layout = (LinearLayout) findViewById(R.id.subjects_detailsLayout);
            layout.setVisibility(view.VISIBLE);
        }else if(viewId == plusButtonViewId2){
            LinearLayout layout = (LinearLayout) findViewById(R.id.homework_detailsLayout);
            layout.setVisibility(view.VISIBLE);
        }else {
            LinearLayout layout = (LinearLayout) findViewById(R.id.exams_detailsLayout);
            layout.setVisibility(view.VISIBLE);
        }

    }

    public void onCancelClick (View view){

        LinearLayout linearLayout = (LinearLayout) view.getParent();
        linearLayout.setVisibility(view.INVISIBLE);

        int viewId = view.getId();

        if (viewId == cancelButtonViewId1){
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.subjects_listLayout);
            frameLayout.setVisibility(view.VISIBLE);
        }else if(viewId == cancelButtonViewId2){
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.homework_listLayout);
            frameLayout.setVisibility(view.VISIBLE);
        }else {
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.exams_listLayout);
            frameLayout.setVisibility(view.VISIBLE);
        }

    }

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


//          ----TESTING----
        DatabaseHelperImpl testHelper = new DatabaseHelperImpl(this);
        testHelper.resetDatabase();
        testHelper.fillDatabaseWithExamples();

        System.out.println(testHelper.toString());
//        ----TESTING----


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
}
