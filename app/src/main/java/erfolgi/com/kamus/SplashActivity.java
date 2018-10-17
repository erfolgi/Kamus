package erfolgi.com.kamus;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import erfolgi.com.kamus.fragment.INDFragment;
import erfolgi.com.kamus.helper.ENGhelper;
import erfolgi.com.kamus.helper.INDhelper;
import erfolgi.com.kamus.model.ENGmodel;
import erfolgi.com.kamus.model.INDmodel;

public class SplashActivity extends AppCompatActivity {
    static boolean ENG = false;
    static boolean IND = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //<< this
        setContentView(R.layout.activity_splash);
        new LoadDataENG().execute();
        new LoadDataIND().execute();
    }

    public void done(){
        if(ENG && IND){
            ENG=false;
            IND=false;

            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    private class LoadDataENG extends AsyncTask<Void, Integer, Void> {
        final String TAG = LoadDataENG.class.getSimpleName();
        ENGhelper engHelper;
        AppPreference appPreference;

        @Override
        protected void onPreExecute() {

            engHelper = new ENGhelper(SplashActivity.this);
            appPreference = new AppPreference(SplashActivity.this);
        }

        @Override
        protected Void doInBackground(Void... params) {

            Boolean firstRun = appPreference.getFirstRun();

            if (firstRun) {
                ArrayList<ENGmodel> engModels = preLoadRaw();
                engHelper.open();
                engHelper.drop();
                engHelper.beginTransaction();
                try {
                    for (ENGmodel model : engModels) {
                        engHelper.insertTransaction(model);
                    }
                    engHelper.setTransactionSuccess();
                } catch (Exception e) {
                    // Jika gagal maka do nothing
                    Log.e(TAG, "doInBackground: Exception");
                }
                engHelper.endTransaction();

                engHelper.close();

                appPreference.setFirstRun(false);

            } else {

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPostExecute(Void result) {
            SplashActivity.ENG=true;
            done();
        }

    }

    public ArrayList<ENGmodel> preLoadRaw() {
        ArrayList<ENGmodel> engModels = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.english_indonesia);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                ENGmodel engModel;

                engModel = new ENGmodel(splitstr[0], splitstr[1]);
                engModels.add(engModel);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return engModels;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //  //  //  //  //  //   //  //  //  //  //   //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //
    //  //  //  //  //  //   //  //  //  //  //   //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private class LoadDataIND extends AsyncTask<Void, Integer, Void> {
        final String TAG = LoadDataIND.class.getSimpleName();
        INDhelper indHelper;
        AppPreference appPreference;



        @Override
        protected void onPreExecute() {
            indHelper = new INDhelper(SplashActivity.this);
            appPreference = new AppPreference(SplashActivity.this);

        }

        @Override
        protected Void doInBackground(Void... params) {

            Boolean firstRun = appPreference.getFirstRun_ind();
            Log.d("indHelp Load: ", String.valueOf(firstRun));
            if (firstRun) {

                ArrayList<INDmodel> indModels = preLoadRawid();

                indHelper.open();
                indHelper.drop();
                indHelper.beginTransaction();
                try {
                    for (INDmodel model : indModels) {
                        Log.d(">->", String.valueOf(model));
                        indHelper.insertTransaction(model);
                    }
                    indHelper.setTransactionSuccess();
                } catch (Exception e) {
                    // Jika gagal maka do nothing
                    Log.e(TAG, "doInBackground: Exception");
                }
                indHelper.endTransaction();

                INDFragment.stat=indHelper.getAllData();
                indHelper.close();
                appPreference.setFirstRun_ind(false);


            } else {
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            SplashActivity.IND=true;

            done();
        }
    }

    public ArrayList<INDmodel> preLoadRawid() {
        ArrayList<INDmodel> indModels = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.indonesia_english);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                Log.d(">->", line);
                String[] splitstr = line.split("\t");

                INDmodel indModel;

                indModel = new INDmodel(splitstr[0], splitstr[1]);
                indModels.add(indModel);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return indModels;
    }
}
