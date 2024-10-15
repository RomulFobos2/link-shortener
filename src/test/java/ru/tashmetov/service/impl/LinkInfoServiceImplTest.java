package ru.tashmetov.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import ru.tashmetov.dto.CreateLinkInfoRequest;
import ru.tashmetov.exception.NotFoundException;
import ru.tashmetov.model.LinkInfo;
import ru.tashmetov.model.LinkInfoResponse;
import ru.tashmetov.repository.LinkInfoRepository;
import ru.tashmetov.repository.impl.LinkInfoRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkInfoServiceImplTest {
    private static Integer LINK_LENGTH = 10;

    private LinkInfoServiceImpl linkInfoService;
    private LinkInfoRepository linkInfoRepository;

    @BeforeEach
    void setUp() {
        linkInfoRepository = new LinkInfoRepositoryImpl();
        linkInfoService = new LinkInfoServiceImpl(LINK_LENGTH, linkInfoRepository);
    }

    @Test
    void createLinkInfo() {
        String fullLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS5x2IbW0FgAFfxwb_DxmdDaUVbPxdEkrZE8g&s";
        LocalDateTime endTime = LocalDateTime.now().plusDays(7);
        String description = "Ссылка на Логотип Google";
        Boolean active = true;

        CreateLinkInfoRequest request =
                new CreateLinkInfoRequest(fullLink, endTime, description, active);

        LinkInfoResponse response = linkInfoService.createLinkInfo(request);

        assertNotNull(response);
        assertEquals(fullLink, response.getLink());
        assertEquals(description, response.getDescription());
        assertTrue(response.getActive());
        assertNotNull(response.getShortLink());
        assertEquals(LINK_LENGTH, response.getShortLink().length());
    }

    @Test
    void getByShortLink() throws NotFoundException {
        String fullLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS5x2IbW0FgAFfxwb_DxmdDaUVbPxdEkrZE8g&s";
        LocalDateTime endTime = LocalDateTime.now().plusDays(7);
        String description = "Ссылка на Логотип Google";
        Boolean active = true;

        String shortLink = "qwertyui";

        LinkInfo linkInfo = new LinkInfo();
        linkInfo.setLink(fullLink);
        linkInfo.setEndTime(endTime);
        linkInfo.setDescription(description);
        linkInfo.setActive(active);
        linkInfo.setShortLink(shortLink);
        linkInfoRepository.save(linkInfo);

        LinkInfoResponse response = linkInfoService.getByShortLink(shortLink);

        assertNotNull(response);
        assertEquals(fullLink, response.getLink());
        assertEquals(description, response.getDescription());
        assertTrue(response.getActive());
        assertEquals(shortLink, response.getShortLink());

    }

    @Test
    void getByShortLink_NotFoundException() {
        assertThrows(NotFoundException.class, () -> {
            linkInfoService.getByShortLink("qwertyui");
        });
    }

    @Test
    void findByFilter() {
        LinkInfo linkInfo1 = new LinkInfo();
        linkInfo1.setLink("https://example1.com");
        linkInfo1.setEndTime(LocalDateTime.now().plusDays(1));
        linkInfo1.setDescription("Test link 1");
        linkInfo1.setActive(true);
        linkInfo1.setShortLink("qwertyui");
        linkInfoRepository.save(linkInfo1);

        LinkInfo linkInfo2 = new LinkInfo();
        linkInfo2.setLink("https://example2.com");
        linkInfo2.setEndTime(LocalDateTime.now().plusDays(1));
        linkInfo2.setDescription("Test link 2");
        linkInfo2.setActive(false);
        linkInfo2.setShortLink("asdfghjk");
        linkInfoRepository.save(linkInfo2);

        List<LinkInfoResponse> responses = linkInfoService.findByFilter();

        assertNotNull(responses);
        assertEquals(2, responses.size());
        assertTrue(responses.stream().anyMatch(response -> "https://example1.com".equals(response.getLink())));
        assertTrue(responses.stream().anyMatch(response -> "https://example2.com".equals(response.getLink())));
    }
}