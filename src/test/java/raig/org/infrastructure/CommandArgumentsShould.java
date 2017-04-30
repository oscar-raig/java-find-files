package raig.org.infrastructure;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.beust.jcommander.JCommander;
import org.junit.Test;
import raig.org.services.Command;

public class CommandArgumentsShould {

  @Test
  public void returnNoCommandWhenParametersAreIncorrect() {
    CommandArguments commandArguments = new CommandArguments();
    String[] argv = { "--dummy", "2", "--dummy2","value_dummy2" };
    JCommander.newBuilder()
            .addObject(commandArguments)
            .build()
            .parse(argv);

    assertThat(commandArguments.getCommand(), is(Command.NO_COMMAND));

  }

  @Test
  public void returnListOfFilesInFoldersAndTheFolder() {

    CommandArguments commandArguments = new CommandArguments();
    String[] argv = { "--list", "myFolder" };
    JCommander.newBuilder()
            .addObject(commandArguments)
            .build()
            .parse(argv);

    assertThat(commandArguments.getCommand(), is(Command.LIST_OF_FILES_IN_FOLDER));
    assertThat(commandArguments.getFolderForList(), is("myFolder"));
  }

  @Test
  public void returnFindEqulFilesAndTheFolder() {

    CommandArguments commandArguments = new CommandArguments();
    String[] argv = { "--firstFolder", "firstFolder", "--secondFolder", "secondFolder"  };
    JCommander.newBuilder()
            .addObject(commandArguments)
            .build()
            .parse(argv);

    assertThat(commandArguments.getCommand(), is(Command.FIND_EQUAL_FILES));
    assertThat(commandArguments.getFirstFolder(), is("firstFolder"));
    assertThat(commandArguments.getSecondFolder(), is("secondFolder"));
  }


  @Test
  public void returnMoveByDate() {

    CommandArguments commandArguments = new CommandArguments();
    String[] argv = { "--moveByDate"};
    JCommander.newBuilder()
            .addObject(commandArguments)
            .build()
            .parse(argv);

    assertThat(commandArguments.getCommand(), is(Command.MOVE_TO_FOLDER_BY_DATE));
  }

  @Test
  public void returnHelp() {

    CommandArguments commandArguments = new CommandArguments();
    String[] argv = { "--help"};
    JCommander.newBuilder()
            .addObject(commandArguments)
            .build()
            .parse(argv);

    assertThat(commandArguments.getCommand(), is(Command.HELP));
  }
}