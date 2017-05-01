package raig.org.domain.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class FileFormatTypeShould {

  private static final String EXPECTED_FOLDER_FOR_VIDEO = "2016_09";
  private static final String EXPECTED_FOLDER_FOR_JPG_IMG = "2016_05";

  @Test
  public void shouldReturnFolderWithVideooFile() {

    String fileName = "VID_20160901_190614.mp4";
    String folder = FileFormatType.getFolderFrom(fileName);
    assertThat(folder, is(EXPECTED_FOLDER_FOR_VIDEO));
  }

  @Test
  public void shouldReturnFolderWithJpgImgFile() {

    String fileName = "IMG_20160516_153816_951.JPG";
    String folder = FileFormatType.getFolderFrom(fileName);
    assertThat(folder, is(EXPECTED_FOLDER_FOR_JPG_IMG));
  }


}