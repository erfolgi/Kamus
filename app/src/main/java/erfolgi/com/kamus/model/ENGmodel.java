package erfolgi.com.kamus.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ENGmodel implements Parcelable {
    private int id_eng;
    private String word_eng;
    private String mean_eng;

    public ENGmodel(){}
    protected ENGmodel(Parcel in) {
        id_eng = in.readInt();
        word_eng = in.readString();
        mean_eng = in.readString();
    }

    public ENGmodel(String word,String mean){
        this.word_eng = word;
        this.mean_eng = mean;
    }

    public int getId_eng() {
        return id_eng;
    }

    public void setId_eng(int id_eng) {
        this.id_eng = id_eng;
    }

    public String getWord_eng() {
        return word_eng;
    }

    public void setWord_eng(String word_eng) {
        this.word_eng = word_eng;
    }

    public String getMean_eng() {
        return mean_eng;
    }

    public void setMean_eng(String mean_eng) {
        this.mean_eng = mean_eng;
    }



    public static final Creator<ENGmodel> CREATOR = new Creator<ENGmodel>() {
        @Override
        public ENGmodel createFromParcel(Parcel in) {
            return new ENGmodel(in);
        }

        @Override
        public ENGmodel[] newArray(int size) {
            return new ENGmodel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_eng);
        dest.writeString(word_eng);
        dest.writeString(mean_eng);
    }
}
