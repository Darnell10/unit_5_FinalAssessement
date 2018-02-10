package c4q.com.unit_5_finalassessment.model;


public class Article {

  private String author;
  private String title;
  private String description;
  private String url;
  private String urlToImage;
  private String publishedAt;

  public Article(String author, String title, String description, String url, String urlToImage,
                 String published) {
    this.author = author;
    this.title = title;
    this.description = description;
    this.url = url;
    this.urlToImage = urlToImage;
    this.publishedAt = published;
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
