package raig.org.infrastructure;

import com.beust.jcommander.Parameter;
import raig.org.services.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandArguments {


  @Parameter
  private List<String> parameters = new ArrayList<>();

  @Parameter(names = "-list", description = "one of the posible commands, list")
  private String folderForList;

  @Parameter(names = "-firstFolder", description = "First folder for compare")
  private String firstFolder;
  @Parameter(names = "-secondFolder", description = "Second folder for compare")
  private String secondFolder;

  Command getCommand() {

    if (folderForList !=  null && !folderForList.isEmpty()) {
      return Command.LIST_OF_FILES_IN_FOLDER;
    }

    if (firstFolder != null && secondFolder != null) {
      return Command.FIND_EQUAL_FILES;
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