package erfolgi.com.kamus.db;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static String TABLE_ENG = "ENG_TABLE";
    public static final class EngColumns implements BaseColumns {
        public static String WORD_ENG = "word";
        public static String MEAN_ENG = "mean";
    }
    public static String TABLE_IND = "IND_TABLE";
    public static final class IndColumns implements BaseColumns {
        public static String WORD_IND = "word";
        public static String MEAN_IND = "mean";
    }
}
