package ru.tashmetov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkInfoResponse {
    private UUID id;

    private String link;
    private LocalDateTime endTime;
    private String description;
    private Boolean active;
    private String shortLink;
    private Long openingCount;
}
