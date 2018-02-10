package c4q.com.unit_5_finalassessment.model;


public class Articles {

  private String author;
  private String title;
  private String description;
  private String url;
  private String urlToImage;
  private String publishedAt;

  public Articles(String author, String title, String description, String url, String urlToImage,
      String published) {
  }

  public String getAuthor() {
    return author;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getUrl() {
    return url;
  }

  public String getUrlToImage() {
    return urlToImage;
  }

  public String getPublishedAt() {
    return publishedAt;
  }
}
