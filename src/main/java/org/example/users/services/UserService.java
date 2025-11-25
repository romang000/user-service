package org.example.users.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.users.controllers.dto.UserDto;
import org.example.users.controllers.dto.UserToSave;
import org.example.users.exceptionsHandler.EntityAlreadyExists;
import org.example.users.repositories.UserRepository;
import org.example.users.repositories.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findAll() {
        var allUsers = userRepository.findAll();

        log.info("All users found");
        return allUsers.stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    public UserDto findById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " not found"));

        return mapEntityToDto(userEntity);
    }

    public UserDto save(UserToSave userDto) {
        var existUser = userRepository.findByEmail(userDto.email());

        if (existUser.isPresent()) {
            log.error("User with email {} already exists", userDto.email());
            throw new EntityAlreadyExists("User with email " + userDto.email() + " already exists");
        }

        var userEntity = new UserEntity(
                null,
                userDto.email(),
                userDto.password()
        );

        userRepository.save(userEntity);
        log.info("User saved with id {}", userEntity.getId());

        return mapEntityToDto(userEntity);
    }

    public UserDto mapEntityToDto(UserEntity userEntities) {
        return new UserDto(
                userEntities.getId(),
                userEntities.getEmail()
        );
    }
}
