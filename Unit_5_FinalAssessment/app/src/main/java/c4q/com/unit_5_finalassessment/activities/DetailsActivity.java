package c4q.com.unit_5_finalassessment.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import c4q.com.unit_5_finalassessment.R;
import c4q.com.unit_5_finalassessment.model.Article;

public class DetailsActivity extends AppCompatActivity {
    Article articles;
    TextView articleTitle, articleTimestamp, articleSource, articleDescription;
    ImageView articlePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        gettingArticleData();
        setViews();
        bindingViews();

        Log.d("Author", articles.getAuthor());
        Log.d("Published At", articles.getPublishedAt());

        //TODO: Missing button on activity_details.xml & its corresponding method to open actual article page

    }

    public void gettingArticleData(){
        String data = getIntent().getStringExtra("Articles");
        Gson gson = new Gson();
        articles = gson.fromJson(data, Article.class);
    }

    public void setViews(){
        articleTitle = findViewById(R.id.article_title);
        articleTimestamp = findViewById(R.id.timestamp);
        articleSource = findViewById(R.id.source);
        articlePic = findViewById(R.id.article_image);
        articleDescription = findViewById(R.id.description);
    }

    public void bindingViews(){

        Picasso.with(getApplicationContext())
                .load(articles.getUrlToImage())
                .into(articlePic);

        articleTitle.setText(articles.getTitle());
        articleTimestamp.setText(articles.getPublishedAt());
        articleSource.setText(articles.getAuthor());
        articleDescription.setText(articles.getDescription());

    }
}
