package edu.imagine.dto;


import edu.imagine.entity.company.Company;
import edu.imagine.entity.user.Role;

public record UserDto(int id,
                      Role role,
                      String info,
                      String username,
                      Company company) {}
