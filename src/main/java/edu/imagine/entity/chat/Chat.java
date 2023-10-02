package edu.imagine.entity.chat;

import edu.imagine.entity.base.BaseEntity;
import edu.imagine.entity.userchat.UserChat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = "userChats")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "chat", schema = "public")
public class Chat extends BaseEntity<Long> {

    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "chat", orphanRemoval = true, cascade = ALL)
    List<UserChat> userChats;

    public void addUserChat(UserChat userChat) {
        this.userChats.add(userChat);
        userChat.setChat(this);
    }

    public Chat(String name, List<UserChat> userChats) {
        this.name = name;
        this.userChats = (userChats != null) ? userChats : new ArrayList<>();
    }
}
