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
import erfolgi.com.kamus.model.ENGmodel;

public class ENGadapter extends RecyclerView.Adapter<ENGadapter.ENGHolder> {
    private ArrayList<ENGmodel> mData = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;

    public ENGadapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ENGHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_dictionary, parent, false);
        return new ENGHolder(view);
    }
    public void addItem(ArrayList<ENGmodel> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(ENGHolder holder, int position) {
        holder.txtWord.setText(mData.get(position).getWord_eng());
        holder.txtMean.setText(mData.get(position).getMean_eng());
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
    public static class ENGHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_word) TextView txtWord;
        @BindView(R.id.txt_mean) TextView txtMean;
        public ENGHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}