package ru.itis.context.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileInfo {
    private Long id;
    private String storageName;
    private String originalName;
    private Long size;
    private String url;
    private String type;
}
