package erfolgi.com.kamus.model;

import android.os.Parcel;
import android.os.Parcelable;

public class INDmodel implements Parcelable {
    private int id_ind;
    private String word_ind;
    private String mean_ind;

    public INDmodel(){}
    protected INDmodel(Parcel in) {
        id_ind = in.readInt();
        word_ind = in.readString();
        mean_ind = in.readString();
    }
    public INDmodel(String word,String mean){
        this.word_ind = word;
        this.mean_ind = mean;
    }

    public int getId_ind() {
        return id_ind;
    }

    public void setId_ind(int id_ind) {
        this.id_ind = id_ind;
    }

    public String getWord_ind() {
        return word_ind;
    }

    public void setWord_ind(String word_ind) {
        this.word_ind = word_ind;
    }

    public String getMean_ind() {
        return mean_ind;
    }

    public void setMean_ind(String mean_ind) {
        this.mean_ind = mean_ind;
    }



    public static final Parcelable.Creator<INDmodel> CREATOR = new Parcelable.Creator<INDmodel>() {
        @Override
        public INDmodel createFromParcel(Parcel in) {
            return new INDmodel(in);
        }

        @Override
        public INDmodel[] newArray(int size) {
            return new INDmodel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_ind);
        dest.writeString(word_ind);
        dest.writeString(mean_ind);
    }
}
