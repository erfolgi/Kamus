package erfolgi.com.kamus.fragment;


import android.content.Context;
import android.content.Intent;
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
import erfolgi.com.kamus.DetailActivity;
import erfolgi.com.kamus.ItemClickSupport;
import erfolgi.com.kamus.MainActivity;
import erfolgi.com.kamus.R;
import erfolgi.com.kamus.adapter.ENGadapter;
import erfolgi.com.kamus.helper.ENGhelper;
import erfolgi.com.kamus.model.ENGmodel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ENGFragment extends Fragment {
    @BindView(R.id.recyclerview)RecyclerView recyclerView;
    ENGadapter engAdapter;
    ENGhelper engHelper;
    Context con;
    @BindView(R.id.SearchView)SearchView SW;

    public ENGFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity())
                .setActionBarTitle("ENG-IND");
        View view = inflater.inflate(R.layout.fragment_eng, container, false);
        ButterKnife.bind(this, view);
        con = getActivity();
        engHelper = new ENGhelper(con);
        engAdapter = new ENGadapter(con);


        recyclerView.setLayoutManager(new LinearLayoutManager(con));

        recyclerView.setAdapter(engAdapter);

        engHelper.open();
        final ArrayList<ENGmodel> engModels = engHelper.getAllData();
        Log.d(">>>", String.valueOf(engModels));
        engHelper.close();
        engAdapter.addItem(engModels);

        showRecyclerList(engModels);
        // Search View
        SW.setQueryHint(getResources().getString(R.string.insert_word));
        SW.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                engHelper.open();
                ArrayList<ENGmodel> engModels = engHelper.getDataByName(query);
                engHelper.close();
                engAdapter.addItem(engModels);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                engHelper.open();
                ArrayList<ENGmodel> engModels = engHelper.getDataByName(newText);
                engHelper.close();
                engAdapter.addItem(engModels);
                showRecyclerList(engModels);
                return false;
            }
        });
        return view;
    }
    private void showRecyclerList(final ArrayList<ENGmodel> engModels) {
        recyclerView.setLayoutManager(new LinearLayoutManager(con));

        engAdapter.addItem(engModels);
        recyclerView.setAdapter(engAdapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                Intent moveWithDataIntent = new Intent(getActivity(), DetailActivity.class);
                moveWithDataIntent.putExtra(DetailActivity.EXTRA_WORD, engModels.get(position).getWord_eng());
                moveWithDataIntent.putExtra(DetailActivity.EXTRA_MEAN, engModels.get(position).getMean_eng());
                Toast.makeText(getActivity(), engModels.get(position).getWord_eng(), Toast.LENGTH_SHORT).show();
                getActivity().startActivity(moveWithDataIntent);
            }
        });
    }
}
