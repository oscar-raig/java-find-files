package raig.org.services;


import raig.org.domain.model.FileFound;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FindFilesService {


  public void findFiles(String folder) {
    List<FileFound> list = findFilesInFolder(folder);
    printFileList(list);
  }

  private List<FileFound> findFilesInFolder(String folder) {
    List<FileFound> results = new ArrayList<>();
    File[] list = new File(folder).listFiles();
    for (File file : list) {
      if (file.isFile()) {
        results.add(new FileFound(file.getName()));
      }
    }
    return results;
  }

  public void compareFolders(String firstFolder, String secondFolder) {
    List<FileFound> listFirstFolder =  findFilesInFolder(firstFolder);
    List<FileFound> listSecondFolder =  findFilesInFolder(secondFolder);

    List<FileFound> listOfequalFiles = findEqualFiles(listFirstFolder, listSecondFolder);
    printFileList(listOfequalFiles);
  }

  private List<FileFound> findEqualFiles(List<FileFound> listFirstFolder,
                                         List<FileFound> listSecondFolder) {
    List<FileFound> listOfEqualFiles = new ArrayList<>();

    for (FileFound fileFound : listFirstFolder) {
      if (existFileFound(fileFound, listSecondFolder))  {
        listOfEqualFiles.add(fileFound);
      }
    }
    return listOfEqualFiles;
  }

  private boolean existFileFound(FileFound fileFound, List<FileFound> listOfFiles) {
    return listOfFiles
            .stream()
            .anyMatch( file -> fileFound.getName().equals(file.getName()));
  }

  private void printFileList(List<FileFound> list) {
    for ( FileFound fileFound : list) {
      System.out.println(fileFound.getName());
    }
  }
}
