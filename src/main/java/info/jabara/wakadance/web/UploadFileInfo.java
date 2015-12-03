package info.jabara.wakadance.web;

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
}