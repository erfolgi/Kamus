package erfolgi.com.kamus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import erfolgi.com.kamus.R;
import erfolgi.com.kamus.model.INDmodel;

public class INDadapter extends RecyclerView.Adapter<INDadapter.INDHolder> {
    private ArrayList<INDmodel> mData = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;

    public INDadapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public INDHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_dictionary, parent, false);
        return new INDHolder(view);
    }
    public void addItem(ArrayList<INDmodel> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(INDHolder holder, int position) {
        holder.txtWord.setText(mData.get(position).getWord_ind());
        holder.txtMean.setText(mData.get(position).getMean_ind());
    }
    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class INDHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_word)TextView txtWord;
        @BindView(R.id.txt_mean)TextView txtMean;
        public INDHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}