package schmitt_florian.schoolplanner.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
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
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private boolean MENUSTATE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = this.getSupportFragmentManager();

        //----TESTING----
        DatabaseHelperImpl testHelper = new DatabaseHelperImpl(this);
        testHelper.resetDatabase();
        testHelper.fillDatabaseWithExamples();

        System.out.println(testHelper.toString());
        //----TESTING----
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        initDrawer();
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setTitle("Main Page");
//        }
//        toolbar.setSubtitle("Test Subtitle");


    }

    /**
     * Method called when starting or resuming the activity to reload the fragment to take care of may occurred changes.
     * Method also preselects the last loaded Fragment at app start
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
            SharedPreferences preferences = this.getSharedPreferences(this.getApplicationContext().toString(), Context.MODE_PRIVATE);
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

            int lastFragmentMenuItemId = preferences.getInt("lastFragment", 0);
            MenuItem lastFragmentMenuItem = navigationView.getMenu().getItem(lastFragmentMenuItemId);

            lastFragmentMenuItem.setChecked(true);
            onNavigationItemSelected(lastFragmentMenuItem);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (loadedFragment instanceof HomeFragment) {
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
                MENUSTATE = false;
                break;
            }
            case R.id.nav_schedule: {
                // Goto Schedule
                loadedFragment = new ScheduleFragment();
                MENUSTATE = true;
                break;
            }
            case R.id.nav_homework: {
                //Goto Homework
                loadedFragment = new HomeworkFragment();
                MENUSTATE = false;
                break;
            }
            case R.id.nav_exams: {
                // Goto Exams
                loadedFragment = new ExamsFragment();
                MENUSTATE = false;
                break;
            }
            case R.id.nav_grades: {
                // Goto Subjects
                loadedFragment = new GradesFragment();
                hideMenu();
                break;
            }
            case R.id.nav_teachers: {
                // Goto Subjects
                loadedFragment = new TeachersFragment();
                MENUSTATE = false;
                break;
            }
            case R.id.nav_subjects: {
                // Goto Subjects
                loadedFragment = new SubjectsFragment();
                MENUSTATE = false;
                break;
            }
            case R.id.nav_credits: {
                //Goto Credits
                loadedFragment = new CreditsFragment();
                MENUSTATE = false;
                break;
            }
            case R.id.nav_settings: {
                // Goto Settings
                loadedFragment = new SettingsFragment();
                MENUSTATE = false;
                break;
            }
            default: {
                MENUSTATE = false;
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

    /**
     * calls {@link AppCompatActivity#onDestroy()} and saves loaded fragment's menu item id into {@link SharedPreferences}
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences preferences = this.getSharedPreferences(this.getApplicationContext().toString(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (!(reloadFragment() < 0)) {
            editor.putInt("lastFragment", reloadFragment());
            editor.apply();
        }
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
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, (Toolbar) findViewById(R.id.toolbar), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * method to reload the loaded fragment
     * @return return id of the loaded fragment in menu_main_drawer.xml file, or -1 if loaded fragment is not in
     * in list
     */
    private int reloadFragment() {
        int temp = -1;
        if (loadedFragment instanceof CreditsFragment) {
            loadedFragment = new CreditsFragment();
            temp = 7;

        } else if (loadedFragment instanceof ExamsFragment) {
            loadedFragment = new ExamsFragment();
            temp = 3;

        } else if (loadedFragment instanceof GradesFragment) {
            loadedFragment = new GradesFragment();
            temp = 4;

        } else if (loadedFragment instanceof HomeFragment) {
            loadedFragment = new HomeFragment();
            temp = 0;

        } else if (loadedFragment instanceof HomeworkFragment) {
            loadedFragment = new HomeworkFragment();
            temp = 2;

        } else if (loadedFragment instanceof ScheduleFragment) {
            loadedFragment = new ScheduleFragment();
            temp = 1;

        } else if (loadedFragment instanceof SettingsFragment) {
            loadedFragment = new SettingsFragment();
            temp = 8;

        } else if (loadedFragment instanceof SubjectsFragment) {
            loadedFragment = new SubjectsFragment();
            temp = 6;

        } else if (loadedFragment instanceof TeachersFragment) {
            loadedFragment = new TeachersFragment();
            temp = 5;

        }

        return temp;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        Log.d(TAG, "onCreateOptionsMenu");
        if (MENUSTATE)
            showMenu();
        else
            hideMenu();

        return true;
    }

    private void showMenu() {
        try {
            toolbar.getMenu().getItem(0).setVisible(true);

        } catch (IndexOutOfBoundsException ex) {
        }
    }

    private void hideMenu() {
        try {
            toolbar.getMenu().getItem(0).setVisible(false);
            Log.d(TAG, "hideMenu: false");
        } catch (IndexOutOfBoundsException ex) {
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);  // OPEN DRAWER
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion
}
