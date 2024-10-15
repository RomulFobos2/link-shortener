package ru.tashmetov.repository;

import ru.tashmetov.model.LinkInfo;

import java.util.List;

public interface LinkInfoRepository {

    LinkInfo findByShortLink(String shortLink);

    LinkInfo save(LinkInfo linkInfo);

    List<LinkInfo> findAll();

}
