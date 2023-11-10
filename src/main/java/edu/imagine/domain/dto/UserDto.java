package edu.imagine.domain.dto;


import edu.imagine.domain.entity.company.Company;
import edu.imagine.domain.entity.user.Role;

public record UserDto(int id, Role role, String info, String username, Company company) {
}
