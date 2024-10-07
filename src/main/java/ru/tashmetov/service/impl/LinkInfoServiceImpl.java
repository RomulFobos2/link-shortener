package ru.tashmetov.service.impl;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tashmetov.dto.CreateLinkInfoRequest;
import ru.tashmetov.service.LinkInfoService;

import java.util.HashMap;
import java.util.Map;

@Service
public class LinkInfoServiceImpl implements LinkInfoService {

    private final int LINK_LENGTH;

    private Map<String, CreateLinkInfoRequest> linkInfoRequestMap = new HashMap<>();

    public LinkInfoServiceImpl(@Value("${link.length}") int LINK_LENGTH) {
        this.LINK_LENGTH = LINK_LENGTH;
    }

    @Override
    public String generateLink(CreateLinkInfoRequest request) {
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
