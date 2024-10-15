package ru.tashmetov.service.impl;

import org.junit.jupiter.api.Test;
import ru.tashmetov.dto.CreateLinkInfoRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LinkInfoServiceImplTest {

    @Test
    public void testGenerateLink() {
        int linkLength = 10;
        LinkInfoServiceImpl linkInfoService = new LinkInfoServiceImpl(linkLength);

        // Создаем объект DTO с информацией о ссылке
        String fullLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS5x2IbW0FgAFfxwb_DxmdDaUVbPxdEkrZE8g&s";
        LocalDateTime endTime = LocalDateTime.now().plusDays(7);
        String description = "Ссылка на Логотип Google";
        Boolean active = true;

        CreateLinkInfoRequest request =
                new CreateLinkInfoRequest(fullLink, endTime, description, active);

        // Генерируем случайную ссылку
        String generatedLink = linkInfoService.generateLink(request);

        System.out.println("Сгенерированная ссылка: " + generatedLink);

        // Проверяем, что сгенерированная ссылка не пуста и соответствует ожидаемой длине
        assertNotNull(generatedLink, "Сгенерированная ссылка не должна быть null");
        assertEquals(linkLength, generatedLink.length(), "Длина сгенерированной ссылки должна быть " + linkLength);
    }

}