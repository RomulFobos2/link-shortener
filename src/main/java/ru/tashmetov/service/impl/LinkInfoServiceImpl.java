package ru.tashmetov.service.impl;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tashmetov.dto.CreateLinkInfoRequest;
import ru.tashmetov.exception.NotFoundException;
import ru.tashmetov.mapper.LinkInfoMapper;
import ru.tashmetov.model.LinkInfo;
import ru.tashmetov.model.LinkInfoResponse;
import ru.tashmetov.repository.LinkInfoRepository;
import ru.tashmetov.service.LinkInfoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LinkInfoServiceImpl implements LinkInfoService {

    private final int LINK_LENGTH;

    private final LinkInfoRepository linkInfoRepository;

    public LinkInfoServiceImpl(@Value("${link.length}") int LINK_LENGTH, LinkInfoRepository linkInfoRepository) {
        this.LINK_LENGTH = LINK_LENGTH;
        this.linkInfoRepository = linkInfoRepository;
    }

    @Override
    public LinkInfoResponse createLinkInfo(CreateLinkInfoRequest request) {
        LinkInfoResponse linkInfoResponse = new LinkInfoResponse();

        linkInfoResponse.setLink(request.getLink());
        linkInfoResponse.setEndTime(request.getEndTime());
        linkInfoResponse.setDescription(request.getDescription());
        linkInfoResponse.setActive(request.getActive());
        linkInfoResponse.setShortLink(createLink(LINK_LENGTH));

        return linkInfoResponse;
    }

    @Override
    public LinkInfoResponse getByShortLink(String shortLink) {
        LinkInfo linkInfo = linkInfoRepository.findByShortLink(shortLink)
                .orElseThrow(() -> new NotFoundException("Для короткой ссылки " + shortLink + " значение в репозитории не найдено."));
        return LinkInfoMapper.toResponse(linkInfo);
    }

    @Override
    public List<LinkInfoResponse> findByFilter() {
        return linkInfoRepository.findAll().stream()
                .map(LinkInfoMapper::toResponse)
                .collect(Collectors.toList());
    }

    private String createLink(int length) {
        RandomStringGenerator generator = RandomStringGenerator.builder()
                .withinRange('0', 'z')
                .filteredBy(Character::isLetterOrDigit)
                .get();
        return generator.generate(length);
    }

}