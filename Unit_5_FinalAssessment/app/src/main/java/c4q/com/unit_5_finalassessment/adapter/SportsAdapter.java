package c4q.com.unit_5_finalassessment.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import c4q.com.unit_5_finalassessment.Model.Articles;
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
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.articles_itemview, parent, false);
        return new SportsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SportsViewHolder holder, int position) {
        Articles articles = articlesList.get(position);
        holder.onBind(articles);

    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public class SportsViewHolder extends RecyclerView.ViewHolder{
        ImageView articleImage;
        TextView articleTitle;

        public SportsViewHolder(View itemView) {
            super(itemView);

            articleImage = itemView.findViewById(R.id.article_image);
            articleTitle = itemView.findViewById(R.id.article_title);
        }

        public void onBind(Articles articles) {
            Picasso.with(itemView.getContext())
                    .load(articles.getUrlToImage())
                    .into(articleImage);

            articleTitle.setText(articles.getTitle());

        }
    }
}
