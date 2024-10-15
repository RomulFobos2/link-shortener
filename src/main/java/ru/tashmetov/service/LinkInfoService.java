package ru.tashmetov.service;

import ru.tashmetov.dto.CreateLinkInfoRequest;
import ru.tashmetov.exception.NotFoundException;
import ru.tashmetov.model.LinkInfoResponse;

import java.util.List;

public interface LinkInfoService {

    LinkInfoResponse createLinkInfo(CreateLinkInfoRequest request);

    LinkInfoResponse getByShortLink(String shortLink) throws NotFoundException;

    List<LinkInfoResponse> findByFilter();

}
