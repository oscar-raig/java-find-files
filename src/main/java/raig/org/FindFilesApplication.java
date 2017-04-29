package raig.org;


import raig.org.infrastructure.ApplicationConsole;
import raig.org.services.FindFilesService;

public class FindFilesApplication {


  public static void main(String [] args) {

    ApplicationConsole applicationConsole = new ApplicationConsole(new FindFilesService());
    try {
      applicationConsole.controller(args);
    } catch (Exception error) {
      System.out.println("Error" +  error);
    }
  }
}