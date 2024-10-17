package ru.tashmetov.mapper;

import ru.tashmetov.model.LinkInfo;
import ru.tashmetov.model.LinkInfoResponse;

public class LinkInfoMapper {

    private LinkInfoMapper() {}

    public static LinkInfoResponse toResponse(LinkInfo linkInfo) {
        return new LinkInfoResponse(
                linkInfo.getId(),
                linkInfo.getLink(),
                linkInfo.getEndTime(),
                linkInfo.getDescription(),
                linkInfo.getActive(),
                linkInfo.getShortLink(),
                linkInfo.getOpeningCount()
        );
    }
}
