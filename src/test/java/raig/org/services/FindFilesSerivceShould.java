package raig.org.services;


import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import raig.org.domain.model.FileFound;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FindFilesSerivceShould {

  private SystemUtils systemUtils;

  @Before
  public void setUp(){
    systemUtils = mock(SystemUtils.class);
  }

  @Test
  public void findFilesInFolder() throws Exception {

    FindFilesService findFilesService =
            new FindFilesService(systemUtils);

    when(systemUtils.moveFile(anyString(),anyString())).thenReturn(true);
    when(systemUtils.findFiles(anyString())).thenReturn(createFiles());

    findFilesService.moveByDate("source", "target");

    verify(systemUtils,times(1))
            .moveFile(anyString(),anyString());
    assertThat(findFilesService, not(nullValue()));
  }

  private List<FileFound> createFiles() {
    List<FileFound> fileList = new ArrayList<>();
    fileList.add(new FileFound("VID_20160901_190614.mp4"));
    return fileList;
  }

}