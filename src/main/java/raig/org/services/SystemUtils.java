package raig.org.services;

import java.io.File;

public class SystemUtils {

  public void createDir(String folderName) {
    File folder = new File(folderName);
    if (!folder.exists()) {
      folder.mkdir();
    }
  }

  public boolean moveFile(String source, String target) {
    File afile =new File(source);

    return afile.renameTo(new File(target + afile.getName()));
  }
}
