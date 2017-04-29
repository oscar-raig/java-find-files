package raig.org.infrastructure;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import raig.org.services.CommandNotFoundExcption;
import raig.org.services.FindFilesService;



public class ApplicationConsoleShould {
  private FindFilesService findFilesService;
  private ApplicationConsole applicationConsole;

  @Before
  public void setUp() {
    findFilesService = mock(FindFilesService.class);
    applicationConsole = new ApplicationConsole(findFilesService);
  }

  @Test
  public void shouldCallFindFilesWhenArgumentsIsList() {

    String[] argv = { "-list", "folder" };
    applicationConsole.controller(argv);
    verify(findFilesService,times(1))
            .findFiles(anyString());
  }

  @Test
  public void shouldCallCompareFoldersWhenArgumentsIsList() {

    String[] argv = { "-firstFolder", "firstFolder", "-secondFolder", "secondFolder"  };
    applicationConsole.controller(argv);

    verify(findFilesService,times(1))
            .compareFolders(anyString(), anyString());
  }

  @Test(expected = CommandNotFoundExcption.class)
  public void shouldThrowExceptionIfFirstFolderArgumentIsMissing() {

    String[] argv = { "-dummy", "dummy", "-secondFolder", "secondFolder"  };
    applicationConsole.controller(argv);

    verify(findFilesService,times(1))
            .compareFolders(anyString(), anyString());
  }

  @Test(expected = CommandNotFoundExcption.class)
  public void shouldThrowExceptionIfSecondFolderArgumentIsMissing() {

    String[] argv = { "-firstFolder", "dummy", "-dummy", "secondFolder"  };
    applicationConsole.controller(argv);

    verify(findFilesService,times(1))
            .compareFolders(anyString(), anyString());
  }
}