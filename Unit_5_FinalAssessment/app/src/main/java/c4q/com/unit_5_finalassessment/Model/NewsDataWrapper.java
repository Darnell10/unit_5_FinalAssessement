package c4q.com.unit_5_finalassessment.Model;


import java.util.List;

public class NewsDataWrapper {
    private String status;
    private int totalResults;
    private List<Articles> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }
}
