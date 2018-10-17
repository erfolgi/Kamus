package erfolgi.com.kamus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    public static String EXTRA_WORD="The word";
    public static String EXTRA_MEAN="it means";
    TextView word,mean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        EXTRA_WORD=getIntent().getStringExtra(EXTRA_WORD);
        EXTRA_MEAN=getIntent().getStringExtra(EXTRA_MEAN);
        word=findViewById(R.id.detail_word);
        mean=findViewById(R.id.detail_mean);
        word.setText(EXTRA_WORD);
        mean.setText(EXTRA_MEAN);
        Objects.requireNonNull(getSupportActionBar()).setTitle("The Word");
    }
}
