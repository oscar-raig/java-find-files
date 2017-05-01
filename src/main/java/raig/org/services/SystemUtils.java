package raig.org.services;

import raig.org.domain.model.FileFound;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SystemUtils {

  public void createDir(String folderName) {
    File folder = new File(folderName);
    if (!folder.exists()) {
      folder.mkdir();
    }
  }

  public boolean moveFile(String source, String target) {
    File afile =new File(source);
    if( !afile.exists()) {
      throw new NoFilesFoundException("File not found: " + source);
    }
    return afile.renameTo(new File(target));
  }

  public List<FileFound> findFiles(String folder) {
    List<FileFound> results = new ArrayList<>();
    File[] list = new File(folder).listFiles();
    if ( list == null || list.length == 0 ) {
      throw new NoFilesFoundException("Files Not found in folder: " + folder);
    }
    for (File file : list) {
      if (file.isFile()) {
        results.add(new FileFound(file.getName(), file.getAbsolutePath()));
      }
    }
    return results;
  }
}
