package edu.imagine.entity.userchat;


import edu.imagine.entity.base.AuditableEntity;
import edu.imagine.entity.chat.Chat;
import edu.imagine.entity.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.PERSIST;


@Data
@NoArgsConstructor
@ToString(callSuper = true, exclude = "user")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users_chat", schema = "public")
public class UserChat extends AuditableEntity<Long> {

    @ManyToOne(optional = false, cascade = PERSIST)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(optional = false, cascade = PERSIST)
    @JoinColumn(name = "chat_id")
    Chat chat;

    public UserChat(LocalDateTime createdAt, String createdBy, User user, Chat chat) {
        super(createdAt, createdBy);
        this.user = user;
        this.chat = chat;
    }

}
