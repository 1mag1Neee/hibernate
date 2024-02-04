package edu.imagine.entity.userchat;


import edu.imagine.entity.base.AuditableEntity;
import edu.imagine.entity.chat.Chat;
import edu.imagine.entity.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.PERSIST;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = {"user", "chat"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users_chat", schema = "public")
public class UserChat extends AuditableEntity<Long> {

    @ManyToOne(optional = false, cascade = PERSIST, fetch = FetchType.LAZY)
    User user;

    @ManyToOne(optional = false, cascade = PERSIST, fetch = FetchType.LAZY)
    Chat chat;

    @Builder
    public UserChat(LocalDateTime createdAt, String createdBy, User user, Chat chat) {
        super(createdAt, createdBy);
        this.user = user;
        this.chat = chat;
    }
}
