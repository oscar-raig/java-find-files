package raig.org.infrastructure;

import com.beust.jcommander.Parameter;
import raig.org.services.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandArguments {


  @Parameter
  private List<String> parameters = new ArrayList<>();

  @Parameter(names = "--help", help = true, description ="Show how to use" )
  private boolean help = false;

  @Parameter(names = "--moveByDate", description = "Move files by date")
  private boolean moveByDate = false;

  @Parameter(names = "--list", description = "one of the posible commands, list")
  private String folderForList;

  @Parameter(names = "--firstFolder", description = "First folder for compare, should be use wiht --secondFolder option")
  private String firstFolder;
  @Parameter(names = "--secondFolder", description = "Second folder for compare, should be use wiht --firstFolder option")
  private String secondFolder;

  Command getCommand() {

    if (folderForList !=  null && !folderForList.isEmpty()) {
      return Command.LIST_OF_FILES_IN_FOLDER;
    }

    if (firstFolder != null && secondFolder != null) {
      return Command.FIND_EQUAL_FILES;
    }

    if ( moveByDate) {
      return Command.MOVE_TO_FOLDER_BY_DATE;
    }

    if ( help) {
      return Command.HELP;
    }

    return Command.NO_COMMAND;
  }

  public String getFolderForList() {
    return folderForList;
  }

  public String getFirstFolder() {
    return firstFolder;
  }

  public String getSecondFolder() {
    return secondFolder;
  }
}
