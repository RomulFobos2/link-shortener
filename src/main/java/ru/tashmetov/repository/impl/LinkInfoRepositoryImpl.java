package ru.tashmetov.repository.impl;

import org.springframework.stereotype.Repository;
import ru.tashmetov.model.LinkInfo;
import ru.tashmetov.repository.LinkInfoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Repository
public class LinkInfoRepositoryImpl implements LinkInfoRepository {

    private ConcurrentMap<String, LinkInfo> linkInfoRequestMap = new ConcurrentHashMap<>();

    @Override
    public Optional<LinkInfo> findByShortLink(String shortLink) {
        return Optional.ofNullable(linkInfoRequestMap.get(shortLink));
    }

    @Override
    public LinkInfo save(LinkInfo linkInfo) {
        linkInfo.setId(UUID.randomUUID());
        linkInfoRequestMap.put(linkInfo.getShortLink(), linkInfo);
        return linkInfo;
    }

    @Override
    public List<LinkInfo> findAll() {
        return linkInfoRequestMap.values().stream().collect(Collectors.toList());
    }
}
