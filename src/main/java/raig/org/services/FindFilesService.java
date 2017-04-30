package raig.org.services;


import raig.org.domain.model.FileFormatType;
import raig.org.domain.model.FileFound;
import raig.org.domain.model.NoFolderFoundException;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FindFilesService {

  private SystemUtils systemUtils;

  public FindFilesService( SystemUtils systemUtils) {
    this.systemUtils = systemUtils;
  }


  public void findFiles(String folder) {
    List<FileFound> list = findFilesInFolder(folder);
    printFileList(list);
  }

  public void compareFolders(String firstFolder, String secondFolder) {
    List<FileFound> listFirstFolder =  findFilesInFolder(firstFolder);
    List<FileFound> listSecondFolder =  findFilesInFolder(secondFolder);

    List<FileFound> listOfequalFiles = findEqualFiles(listFirstFolder, listSecondFolder);
    printFileList(listOfequalFiles);
  }


  public void moveByDate(String sourceFolder, String targetFolder) {
    List<FileFound> list = findFilesInFolder(sourceFolder);
    for( FileFound file : list ) {
      String folderToMove;
      try {
        folderToMove = getFolderToMove(file.getName());
      } catch ( NoFolderFoundException error ) {
        System.out.println(error.getMessage());
        continue;
      }
      moveFile(file.getName(), getFolderToMove(targetFolder ,folderToMove));
    }
  }

  private String getFolderToMove(String targetFolder, String folderToMove) {
    String endOfTheFolder = "/";
    if( targetFolder.substring(targetFolder.length() - 1).equals("/")) {
      endOfTheFolder = "";
    }
    return targetFolder + endOfTheFolder +folderToMove;
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


  private String getFolderToMove(String name) {
    return FileFormatType.getFolderFrom(name);
  }

  private void moveFile(String name, String folderToMove) {
      System.out.println("Moving: " + name + "To: " + folderToMove);
    File afile =new File(name);

    File folder = new File(folderToMove);
    if (!folder.exists()) {
        systemUtils.createDir(folderToMove);

    }

    if(systemUtils.moveFile(name  , folderToMove + afile.getName())){
      System.out.println("File is moved successful!");
    }else{
      System.out.println("File is failed to move!");
    }
  }


  private List<FileFound> findFilesInFolder(String folder) {
    List<FileFound> results = new ArrayList<>();
    File[] list = new File(folder).listFiles();
    if ( list == null || list.length == 0 ) {
      throw new NoFilesFoundException("Files Not found in folder: " + folder);
    }
    for (File file : list) {
      if (file.isFile()) {
        results.add(new FileFound(file.getName()));
      }
    }
    return results;
  }
}
