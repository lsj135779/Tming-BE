package com.spring.tming.domain.chat.service;

import com.spring.tming.domain.chat.dto.response.RoomLastChatRes;
import com.spring.tming.domain.chat.dto.response.RoomMessageRes;
import com.spring.tming.domain.chat.dto.response.RoomSaveRes;
import com.spring.tming.domain.chat.entity.Chat;
import com.spring.tming.domain.chat.entity.ChatRoom;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChatRoomServiceMapper {
    ChatRoomServiceMapper INSTANCE = Mappers.getMapper(ChatRoomServiceMapper.class);

    @Mapping(source = "createTimestamp", target = "createTimestamp")
    default String toCreateTimeStamp(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        LocalDateTime localDateTime =
                timestamp.toInstant().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Mapping(source = "chatRoomId", target = "roomId")
    RoomSaveRes toRoomSaveRes(ChatRoom chatRoom);

    @Mapping(source = "chat.userId.userId", target = "userId")
    @Mapping(source = "content", target = "content")
    RoomMessageRes toRoomMessageRes(Chat chat);

    @Mapping(source = "chat.userId.userId", target = "userId")
    @Mapping(source = "content", target = "content")
    List<RoomMessageRes> toRoomMessageResList(List<Chat> chat);

    @Mapping(source = "createTimestamp", target = "createTimestamp")
    RoomLastChatRes toRoomLastChatRes(Chat chat);
}