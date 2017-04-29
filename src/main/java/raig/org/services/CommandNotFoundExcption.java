package raig.org.services;

public class CommandNotFoundExcption extends RuntimeException {

  public CommandNotFoundExcption(String message) {
    super(message);
  }
}
