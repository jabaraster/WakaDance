package info.jabara.wakadance.model;

import java.util.List;

import lombok.Value;

/**
 * @author jabaraster
 */
@Value
public class UploadInfo {
    private String               personName;
    private List<UploadFileInfo> uploadFileInfos;
}