package edu.imagine.entity.chat;

import edu.imagine.entity.base.BaseEntity;
import edu.imagine.entity.userchat.UserChat;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "name")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@BatchSize(size = 32)
public class Chat extends BaseEntity<Long> {

    String name;

    @OneToMany(mappedBy = "chat", orphanRemoval = true, cascade = ALL)
    List<UserChat> userChats = new ArrayList<>();

    public void addUserChat(UserChat userChat) {
        this.userChats.add(userChat);
        userChat.setChat(this);
    }

    @Builder
    public Chat(String name) {
        this.name = name;
    }
}
