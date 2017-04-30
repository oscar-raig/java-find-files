package raig.org.infrastructure;


import com.beust.jcommander.JCommander;
import raig.org.services.Command;
import raig.org.services.CommandNotFoundExcption;
import raig.org.services.FindFilesService;

public class ApplicationConsole {
  private FindFilesService findFilesService;
  private CommandArguments commandArguments;
  private JCommander jommander;

  public ApplicationConsole( FindFilesService findFilesService) {
    this.findFilesService = findFilesService;
    this.commandArguments = new CommandArguments();
    this.jommander = new JCommander();
  }

  public void controller(String[] args ) {

    jommander.addObject(commandArguments);
    jommander.parse(args);
    jommander.setProgramName("findFilesApplication");

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

    if (commandArguments.getCommand().equals(Command.MOVE_TO_FOLDER_BY_DATE)) {
      findFilesService.moveByDate(commandArguments.getSourceFolderForMoving(),
              commandArguments.getTargetFolderForMoving());
      return;
    }

    if (commandArguments.getCommand().equals(Command.HELP)) {
      jommander.usage();
      return;
    }

    throw new CommandNotFoundExcption("Command Not found");
  }
}
