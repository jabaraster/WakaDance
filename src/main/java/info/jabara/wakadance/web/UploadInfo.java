package info.jabara.wakadance.web;

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