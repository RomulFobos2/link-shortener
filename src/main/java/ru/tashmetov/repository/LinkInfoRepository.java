package ru.tashmetov.repository;

import ru.tashmetov.model.LinkInfo;

import java.util.List;
import java.util.Optional;

public interface LinkInfoRepository {

    Optional<LinkInfo> findByShortLink(String shortLink);

    LinkInfo save(LinkInfo linkInfo);

    List<LinkInfo> findAll();

}
