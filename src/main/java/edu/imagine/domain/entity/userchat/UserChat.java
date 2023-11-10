package edu.imagine.domain.entity.userchat;


import edu.imagine.domain.entity.base.AuditableEntity;
import edu.imagine.domain.entity.chat.Chat;
import edu.imagine.domain.entity.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.PERSIST;


@Data
@NoArgsConstructor
@ToString(callSuper = true, exclude = "user")
@EqualsAndHashCode(callSuper = true, of = {"user", "chat"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users_chat", schema = "public")
public class UserChat extends AuditableEntity<Long> {

    @ManyToOne(optional = false, cascade = PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(optional = false, cascade = PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    @Fetch(FetchMode.JOIN)
    Chat chat;

    public UserChat(LocalDateTime createdAt, String createdBy, User user, Chat chat) {
        super(createdAt, createdBy);
        this.user = user;
        this.chat = chat;
    }
}
