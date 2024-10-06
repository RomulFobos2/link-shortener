package ru.tashmetov.service;

import ru.tashmetov.dto.CreateLinkInfoRequest;

/**
 * Интерфейс для сервиса управления информацией о ссылках.
 *
 * Предоставляет метод для генерации случайных ссылок, основанных на данных,
 * переданных в запросе {@link CreateLinkInfoRequest}.
 */
public interface LinkInfoService {

    /**
     * Генерирует случайную ссылку на основе переданного запроса.
     *
     * @param request объект {@link CreateLinkInfoRequest}, содержащий информацию для создания ссылки.
     * @return сгенерированная ссылка.
     */
    public String generateLink(CreateLinkInfoRequest request);

}
