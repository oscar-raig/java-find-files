package raig.org.domain.model;

public class FileFound {

  private final String name;
  private final String absolutePath;

  public FileFound(String name, String absolutePath) {
    this.name = name;
    this.absolutePath = absolutePath;
  }

  public String getName() {
    return name;
  }

  public String getAbsolutePath() {
    return absolutePath;
  }
}
