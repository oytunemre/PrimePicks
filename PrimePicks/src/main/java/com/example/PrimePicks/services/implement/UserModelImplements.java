package com.example.PrimePicks.services.implement;

import com.example.PrimePicks.dto.UserModelDTO;
import com.example.PrimePicks.models.UserModel;
import com.example.PrimePicks.repository.UserModelRepository;
import com.example.PrimePicks.services.UserModelService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserModelImplements implements UserModelService {


    private UserModelRepository userModelRepository;

    @Autowired
    public UserModelImplements(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    @Override
    public List<UserModelDTO> listAllUser() {
        List<UserModel> userModels = userModelRepository.findAll();
        return userModels.stream().map(userModel -> mapToUserModelDTO(userModel)).collect(Collectors.toList());
    }


    private UserModelDTO mapToUserModelDTO(UserModel userModel) {

        UserModelDTO userModelDTO = UserModelDTO.builder()
                .UserId(userModel.getUserId())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .birthDate(userModel.getBirthDate())
                .email(userModel.getEmail())
                .password(userModel.getPassword())
                .userCreated(userModel.getUserCreated())
                .userUpdated(userModel.getUserUpdated())
                .build();

        return userModelDTO;

    }


}
