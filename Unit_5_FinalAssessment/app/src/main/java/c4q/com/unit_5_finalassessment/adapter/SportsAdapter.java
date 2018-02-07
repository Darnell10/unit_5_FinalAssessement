package c4q.com.unit_5_finalassessment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import c4q.com.unit_5_finalassessment.utils.NotificationUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import c4q.com.unit_5_finalassessment.model.Articles;
import c4q.com.unit_5_finalassessment.R;

/**
 * Created by c4q on 2/4/18.
 */

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.SportsViewHolder> {

  List<Articles> articlesList;

  public SportsAdapter(List<Articles> articlesList) {
    this.articlesList = articlesList;
  }

  @Override
  public SportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.articles_itemview, parent, false);
    return new SportsViewHolder(v);
  }

  @Override
  public void onBindViewHolder(final SportsViewHolder holder, final int position) {
    Articles articles = articlesList.get(position);
    holder.onBind(articles);
    holder.itemView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        articlesList.get(position).getUrl();
        NotificationUtils.breakingNews(holder.itemView.getContext(), articlesList.get(position));
      }
    });
  }

  @Override
  public int getItemCount() {
    return articlesList.size();
  }

  public class SportsViewHolder extends RecyclerView.ViewHolder {

    ImageView articleImage;
    TextView articleTitle, articleDescription;

    public SportsViewHolder(View itemView) {
      super(itemView);

      articleImage = itemView.findViewById(R.id.article_image);
      articleTitle = itemView.findViewById(R.id.article_title);
      articleDescription = itemView.findViewById(R.id.article_description);

    }

    public void onBind(Articles articles) {
      Picasso.with(itemView.getContext())
          .load(articles.getUrlToImage())
          .into(articleImage);

      articleTitle.setText(articles.getTitle());
      articleDescription.setText(articles.getDescription());

    }
  }
}
