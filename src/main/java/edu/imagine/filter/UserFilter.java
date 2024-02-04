package edu.imagine.filter;

import edu.imagine.entity.user.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserFilter {
    String userName;
    String firstName;
    String companyName;
    String chatName;
    Role role;
}
