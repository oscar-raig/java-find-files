package raig.org.services;


import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;


import org.junit.Test;

public class FindFilesSerivceShould {
  @Test
  public void findFilesInFolder() throws Exception {

    FindFilesService findFilesService = new FindFilesService();

    assertThat(findFilesService, not(nullValue()));
  }

}