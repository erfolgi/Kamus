package erfolgi.com.kamus.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import erfolgi.com.kamus.AppPreference;
import erfolgi.com.kamus.DetailActivity;
import erfolgi.com.kamus.ItemClickSupport;
import erfolgi.com.kamus.MainActivity;
import erfolgi.com.kamus.R;
import erfolgi.com.kamus.adapter.INDadapter;
import erfolgi.com.kamus.helper.INDhelper;
import erfolgi.com.kamus.model.INDmodel;

/**
 * A simple {@link Fragment} subclass.
 */
public class INDFragment extends Fragment {
    @BindView(R.id.recyclerview2) RecyclerView recyclerView;
    INDadapter indAdapter;
    INDhelper indHelper;
    Context con;
    public static ArrayList<INDmodel> stat;
    @BindView(R.id.SearchView2) SearchView SW;

    public INDFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity())
                .setActionBarTitle("IND-ENG");
        View view = inflater.inflate(R.layout.fragment_ind, container, false);
        ButterKnife.bind(this, view);
        con = getActivity();
        indHelper = new INDhelper(con);
        indAdapter = new INDadapter(con);



        recyclerView.setLayoutManager(new LinearLayoutManager(con));

        recyclerView.setAdapter(indAdapter);

        indHelper.open();

        final ArrayList<INDmodel> indModels;
        if(stat!=null){
            indModels = stat;
        }else{
            indModels = indHelper.getAllData();
        }

        indHelper.close();
        indAdapter.addItem(indModels);


        showRecyclerList(indModels);
        // Search View
        SW.setQueryHint(getResources().getString(R.string.insert_word));
        SW.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                indHelper.open();
                ArrayList<INDmodel> indModels = indHelper.getDataByName(query);
                indHelper.close();
                indAdapter.addItem(indModels);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                indHelper.open();
                ArrayList<INDmodel> indModels = indHelper.getDataByName(newText);
                indHelper.close();
                indAdapter.addItem(indModels);
                showRecyclerList(indModels);

                return false;
            }
        });

        return view;
    }
    private void showRecyclerList(final ArrayList<INDmodel> indModels) {
        recyclerView.setLayoutManager(new LinearLayoutManager(con));

        indAdapter.addItem(indModels);
        recyclerView.setAdapter(indAdapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                Intent moveWithDataIntent = new Intent(getActivity(), DetailActivity.class);
                moveWithDataIntent.putExtra(DetailActivity.EXTRA_WORD, indModels.get(position).getWord_ind());
                moveWithDataIntent.putExtra(DetailActivity.EXTRA_MEAN, indModels.get(position).getMean_ind());
                Toast.makeText(getActivity(), indModels.get(position).getWord_ind(), Toast.LENGTH_SHORT).show();
                getActivity().startActivity(moveWithDataIntent);
            }
        });
    }
}
