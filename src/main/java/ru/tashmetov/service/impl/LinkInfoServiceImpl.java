package ru.tashmetov.service.impl;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tashmetov.dto.CreateLinkInfoRequest;
import ru.tashmetov.service.LinkInfoService;

import java.util.HashMap;
import java.util.Map;

/**
 * Реализация интерфейса {@link LinkInfoService}.
 *
 * Сервис предоставляет метод для генерации случайной ссылки, основываясь на
 * переданных данных о ссылке, с использованием случайных букв и цифр.
 */
@Service
public class LinkInfoServiceImpl implements LinkInfoService {

    /**
     * Длина генерируемой ссылки.
     * Значение инициализируется из конфигурационного файла.
     */
    private final int LINK_LENGTH;

    public LinkInfoServiceImpl(@Value("${link.length}") int LINK_LENGTH) {
        this.LINK_LENGTH = LINK_LENGTH;
    }

    /**
     * Генерирует случайную ссылку и сохраняет связанную с ней информацию.
     *
     * @param request объект запроса {@link CreateLinkInfoRequest}, содержащий информацию о ссылке.
     * @return сгенерированная случайная ссылка.
     */
    @Override
    public String generateLink(CreateLinkInfoRequest request) {
        Map<String, CreateLinkInfoRequest> linkInfoRequestMap = new HashMap<>();
        String link = createLink(LINK_LENGTH);
        linkInfoRequestMap.put(link, request);
        return link;
    }

    private String createLink(int length){
        RandomStringGenerator generator = RandomStringGenerator.builder()
                .withinRange('0', 'z')
                .filteredBy(Character::isLetterOrDigit)
                .get();

        return generator.generate(length);
    }
}
