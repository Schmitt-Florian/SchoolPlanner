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
import schmitt_florian.schoolplanner.logic.DatabaseHelperImpl;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        ScheduleFragment.OnFragmentInteractionListener,
        HomeworkFragment.OnFragmentInteractionListener,
        ExamsFragment.OnFragmentInteractionListener,
        GradesFragment.OnFragmentInteractionListener,
        TeachersFragment.OnFragmentInteractionListener,
        SubjectsFragment.OnFragmentInteractionListener,
        CreditsFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener {

    private static final String TAG = "MainActivity";
    private Fragment loadedFragment;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawer();
        fragmentManager = this.getSupportFragmentManager();

        //----TESTING----
        DatabaseHelperImpl testHelper = new DatabaseHelperImpl(this);
        testHelper.resetDatabase();
        testHelper.fillDatabaseWithExamples();

        System.out.println(testHelper.toString());
        //----TESTING----
    }

    /**
     * Method called when starting or resuming the activity to reload the fragment to take care of may occurred changes.
     * Method also preselects the {@link HomeFragment} at app start
     */
    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (loadedFragment != null) {
            reloadFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.containerMain, loadedFragment);
            ft.commit();
        } else {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.getMenu().getItem(0).setChecked(true);

            loadedFragment = new HomeFragment();
            onResumeFragments();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(loadedFragment.equals(new HomeFragment())){
            super.onBackPressed();
            } else {
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.getMenu().getItem(0).setChecked(true);

                loadedFragment = new HomeFragment();
                onResumeFragments();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_main: {
                // Goto Home
                loadedFragment = new HomeFragment();
                break;
            }
            case R.id.nav_schedule: {
                // Goto Schedule
                loadedFragment = new ScheduleFragment();
                break;
            }
            case R.id.nav_homework: {
                //Goto Homework
                loadedFragment = new HomeworkFragment();
                break;
            }
            case R.id.nav_exams: {
                // Goto Exams
                loadedFragment = new ExamsFragment();
                break;
            }
            case R.id.nav_grades: {
                // Goto Subjects
                loadedFragment = new GradesFragment();
                break;
            }
            case R.id.nav_teachers: {
                // Goto Subjects
                loadedFragment = new TeachersFragment();
                break;
            }
            case R.id.nav_subjects: {
                // Goto Subjects
                loadedFragment = new SubjectsFragment();
                break;
            }
            case R.id.nav_credits: {
                //Goto Credits
                loadedFragment = new CreditsFragment();
                break;
            }
            case R.id.nav_settings: {
                // Goto Settings
                loadedFragment = new SettingsFragment();
                break;
            }
            default: {
                return false;
            }
        }

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.containerMain, loadedFragment);
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d(TAG, "onFragmentInteraction");
    }

    //region private methods

    /**
     * method to configure the navigation drawer
     */
    private void initDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, (Toolbar) findViewById(R.id.toolbar), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * method to reload the loaded fragment
     */
    private void reloadFragment() {
        if (loadedFragment instanceof CreditsFragment) {
            loadedFragment = new CreditsFragment();
        } else if (loadedFragment instanceof ExamsFragment) {
            loadedFragment = new ExamsFragment();
        } else if (loadedFragment instanceof GradesFragment) {
            loadedFragment = new GradesFragment();
        } else if (loadedFragment instanceof HomeFragment) {
            loadedFragment = new HomeFragment();
        } else if (loadedFragment instanceof HomeworkFragment) {
            loadedFragment = new HomeworkFragment();
        } else if (loadedFragment instanceof ScheduleFragment) {
            loadedFragment = new ScheduleFragment();
        } else if (loadedFragment instanceof SettingsFragment) {
            loadedFragment = new SettingsFragment();
        } else if (loadedFragment instanceof SubjectsFragment) {
            loadedFragment = new SubjectsFragment();
        } else if (loadedFragment instanceof TeachersFragment) {
            loadedFragment = new TeachersFragment();
        }
    }
    //endregion
}
