package com.goodsoft.internship.gsservletjsp.converter;

import com.goodsoft.internship.gsservletjsp.dto.UserDTO;
import com.goodsoft.internship.gsservletjsp.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDTO, User> {
    @Override
    public User convert(UserDTO source) {
        return User.builder()
                .id(source.getId())
                .login(source.getLogin())
                .password(source.getPassword())
                .name(source.getName())
                .birthdate(source.getBirthdate())
                .age(source.getAge())
                .salary(source.getSalary())
                .roles(source.getRoles())
                .build();
    }
}
