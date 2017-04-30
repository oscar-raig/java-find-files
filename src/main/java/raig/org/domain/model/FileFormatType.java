package raig.org.domain.model;

import org.apache.commons.io.FilenameUtils;

public enum FileFormatType {
  VIDEO_MP4_DATE_HOUR,
  IMAGE_JPG_DATE_HOUR_MILISECONDS,
  UNKNOWN_EXTENSION;

  private  static FileFormatType from( String fileName) {
    String extension = FilenameUtils.getExtension(fileName);
    String fileNameWithOutExt = FilenameUtils.removeExtension(fileName);

    if ("mp4".equals(extension)
            && fileNameWithOutExt.contains("VID")
            ) {
      return VIDEO_MP4_DATE_HOUR;
    }

    if ("JPG".equals(extension)
            && fileNameWithOutExt.contains("IMG")
            ) {
      return IMAGE_JPG_DATE_HOUR_MILISECONDS;
    }

    return UNKNOWN_EXTENSION;
  }

  public static String getFolderFrom(String fileName) {

    FileFormatType fileFormatType = from(fileName);
    if( fileFormatType.equals(VIDEO_MP4_DATE_HOUR)) {
      return fileName.substring(4,8) + "_" + fileName.substring(8,10);
    }

    if( fileFormatType.equals(IMAGE_JPG_DATE_HOUR_MILISECONDS)) {
      return fileName.substring(4,8) + "_" + fileName.substring(8,10);
    }

    throw new NoFolderFoundException("No folder found for file:"  + fileName);
  }
}
