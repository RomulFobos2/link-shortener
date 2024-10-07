package ru.tashmetov.service;

import ru.tashmetov.dto.CreateLinkInfoRequest;

public interface LinkInfoService {

    public String generateLink(CreateLinkInfoRequest request);

}
