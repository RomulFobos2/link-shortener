package ru.tashmetov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO класс для передачи информации при создании ссылки.
 *
 * Этот класс используется для передачи данных о создаваемой ссылке,
 * таких как сама ссылка, время окончания действия ссылки, описание и статус активности.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateLinkInfoRequest {
    private String link;
    private LocalDateTime endTime;
    private String description;
    private Boolean active;
}
