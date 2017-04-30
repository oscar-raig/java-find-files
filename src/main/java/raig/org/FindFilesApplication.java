package raig.org;


import raig.org.infrastructure.ApplicationConsole;
import raig.org.services.CommandNotFoundExcption;
import raig.org.services.FindFilesService;
import raig.org.services.SystemUtils;

public class FindFilesApplication {


  public static void main(String [] args) {

    ApplicationConsole applicationConsole =
            new ApplicationConsole(
                    new FindFilesService(new SystemUtils()));
    try {
      applicationConsole.controller(args);
    } catch (CommandNotFoundExcption commandNotFoundExcption) {
      System.out.println("Command Not Found: " + commandNotFoundExcption);
    } catch (Exception error) {
      System.out.println("Error: " +  error);
      error.printStackTrace();
    }
  }
}
