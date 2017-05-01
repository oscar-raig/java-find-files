package raig.org.services;


import raig.org.domain.model.FileFormatType;
import raig.org.domain.model.FileFound;
import raig.org.domain.model.NoFolderFoundException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FindFilesService {

  private SystemUtils systemUtils;

  public FindFilesService( SystemUtils systemUtils) {
    this.systemUtils = systemUtils;
  }


  public void findFiles(String folder) {
    List<FileFound> list = systemUtils.findFiles(folder);
    printFileList(list);
  }

  public void compareFolders(String firstFolder, String secondFolder) {
    List<FileFound> listFirstFolder =  systemUtils.findFiles(firstFolder);
    List<FileFound> listSecondFolder =  systemUtils.findFiles(secondFolder);

    List<FileFound> listOfequalFiles = findEqualFiles(listFirstFolder, listSecondFolder);
    printFileList(listOfequalFiles);
  }


  public void moveByDate(String sourceFolder, String targetFolder) {

    String folderToMove;
    List<FileFound> list = systemUtils.findFiles(sourceFolder);
    for( FileFound file : list ) {
      try {
        folderToMove = getFolderToMove(file.getName());
      } catch ( NoFolderFoundException error ) {
        System.out.println(error.getMessage());
        continue;
      }
      moveFile(file.getAbsolutePath(), getFolderToMove(targetFolder ,folderToMove));
    }
  }

  private String getFolderToMove(String targetFolder, String folderToMove) {
    String endOfTheFolder = "/";
    if( targetFolder.substring(targetFolder.length() - 1).equals("/")) {
      endOfTheFolder = "";
    }
    return targetFolder + endOfTheFolder +folderToMove +  "/";
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

}
