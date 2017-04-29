package raig.org.infrastructure;


import com.beust.jcommander.JCommander;
import raig.org.services.Command;
import raig.org.services.CommandNotFoundExcption;
import raig.org.services.FindFilesService;

public class ApplicationConsole {
  private FindFilesService findFilesService;


  public ApplicationConsole( FindFilesService findFilesService) {
    this.findFilesService = findFilesService;
  }

  public void controller(String[] args ) {
    CommandArguments commandArguments = new CommandArguments();
    JCommander.newBuilder()
            .addObject(commandArguments)
            .build()
            .parse(args);

    executeCommand(commandArguments);
  }

  private void executeCommand(CommandArguments  commandArguments) {

    if (commandArguments.getCommand().equals(Command.LIST_OF_FILES_IN_FOLDER)) {
      findFilesService.findFiles(commandArguments.getFolderForList());
      return;
    }

    if (commandArguments.getCommand().equals(Command.FIND_EQUAL_FILES)) {
      findFilesService.compareFolders(commandArguments.getFirstFolder(),
              commandArguments.getSecondFolder());
      return;
    }

    throw new CommandNotFoundExcption("Command Not found");
  }
}
