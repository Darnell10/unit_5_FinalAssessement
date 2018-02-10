package c4q.com.unit_5_finalassessment.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import c4q.com.unit_5_finalassessment.activities.DetailsActivity;
import c4q.com.unit_5_finalassessment.model.Article;
import c4q.com.unit_5_finalassessment.utils.NotificationUtils;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import c4q.com.unit_5_finalassessment.R;

/**
 * Created by c4q on 2/4/18.
 */

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.SportsViewHolder> {

  private List<Article> articleList;

  public SportsAdapter(List<Article> articleList) {
    this.articleList = articleList;
  }

  @Override
  public SportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.articles_itemview, parent, false);
    return new SportsViewHolder(v);
  }

  @Override
  public void onBindViewHolder(final SportsViewHolder holder, final int position) {
    Article article = articleList.get(position);
    holder.onBind(article);
  }

  @Override
  public int getItemCount() {
    return articleList.size();
  }

  public class SportsViewHolder extends RecyclerView.ViewHolder {

    private ImageView articleImage;
    private TextView articleTitle, articleDescription;

    public SportsViewHolder(View itemView) {
      super(itemView);

      articleImage = itemView.findViewById(R.id.article_image);
      articleTitle = itemView.findViewById(R.id.article_title);
      articleDescription = itemView.findViewById(R.id.article_description);

    }

    public void onBind(Article article) {
      Picasso.with(itemView.getContext())
          .load(article.getUrlToImage())
          .into(articleImage);

      articleTitle.setText(article.getTitle());
      articleDescription.setText(article.getDescription());

      /**
       * Intent that opens article details page when clicking on the article's itemview
       * Using Gson to pass data instead of bundle...
       */

//      itemView.setOnClickListener(new OnClickListener() {
//        @Override
//        public void onClick(View view) {
//          Intent intent = new Intent(itemView.getContext(), DetailsActivity.class);
//          Gson gson = new Gson();
//          String articlesToString = gson.toJson(articles);
//          intent.putExtra("Articles", articlesToString);
//          itemView.getContext().startActivity(intent);
//
//        }
//      });

    }
  }
}
