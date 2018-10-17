package erfolgi.com.kamus;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import erfolgi.com.kamus.fragment.ENGFragment;
import erfolgi.com.kamus.fragment.INDFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ENGFragment mCategoryFragment= new ENGFragment();
        FragmentManager mFM= getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFM.beginTransaction();
        mFragmentTransaction.replace(R.id.main_container_wrapper, mCategoryFragment);
        mFragmentTransaction.commit();
        navigationView.setCheckedItem(R.id.nav_ENG);
    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ENG) {
            ENGFragment mCategoryFragment= new ENGFragment();
            FragmentManager mFM= getFragmentManager();
            FragmentTransaction mFragmentTransaction = mFM.beginTransaction();
            mFragmentTransaction.replace(R.id.main_container_wrapper, mCategoryFragment);
            mFragmentTransaction.commit();
        } else if (id == R.id.nav_IND) {
                INDFragment mCategoryFragment= new INDFragment();
                FragmentManager mFM= getFragmentManager();
                FragmentTransaction mFragmentTransaction = mFM.beginTransaction();
                mFragmentTransaction.replace(R.id.main_container_wrapper, mCategoryFragment);
                mFragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
