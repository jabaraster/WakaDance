package info.jabara.wakadance.model;

import java.nio.file.Path;

import lombok.Value;

/**
 * @author jabaraster
 */
@Value
public class UploadFileInfo {
    private Path   saveFilePath;
    private String fileName;
    private String contentType;
    private long   size;
    private Long   dbKeyValue;
}