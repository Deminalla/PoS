package com.pos.demo.service;

import com.pos.demo.mapper.UserMapper;
import com.pos.demo.model.dto.user.CreateUserDto;
import com.pos.demo.model.dto.user.UserDto;
import com.pos.demo.model.dto.user.UserInfoDto;
import com.pos.demo.model.entity.UserEntity;
import com.pos.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public UserInfoDto getUserByID(UUID userId) {
        log.info("Searching for user with ID {}", userId);
        Optional<UserEntity> userEntity = userRepository.findById(userId);

        if (userEntity.isEmpty()) {
            log.warn("User was not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User was not found");
        }

        UserDto userDto = userMapper.entityToDto(userEntity.get());

        return userMapper.userToInfo(userDto);
    }

    @Transactional
    public UserInfoDto createUser(CreateUserDto creteUserDto) {
        log.info("Creating new user");

        //Convert to userDto, add randomId and convert to user entity
        UserDto newUserDto = userMapper.createToDto(creteUserDto);
        UUID randomId = UUID.randomUUID();
        newUserDto.setUserId(randomId);
        UserEntity userEntity = userMapper.dtoToEntity(newUserDto);

        //Check if user already exists
        if(userRepository.findByEmail(userEntity.getEmail()).isPresent()) {
            log.warn("User with provided email already exists");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with provided email already exists");
        }

        //Password hashing
        String password = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(password);

        //User creation
        if (userRepository.createUser(userEntity) == 0) {
            log.warn("User creation unsuccessful");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while adding new user to database");
        }

        //Retrieving the newly added user
        Optional<UserEntity> foundUserEntity = userRepository.findById(userEntity.getUserId());
        UserDto userDto = userMapper.entityToDto(foundUserEntity.get());

        return userMapper.userToInfo(userDto);
    }

    @Transactional
    public void deleteUser(UUID userId) {
        log.info("Deleting user with ID {}", userId);

        //Check if user exists
        if(userRepository.findById(userId).isPresent()) {
            if(userRepository.deleteUser(userId) == 0) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error occurred while trying to delete the user from the database");
            }
            else {
                return;
            }
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }

    }

    @Transactional
    public UserInfoDto updateUser(UUID userId, CreateUserDto updatedUser)
    {
        log.info("Updating user with ID {}", userId);

        //Create userDto and add the id
        UserDto userDto = userMapper.createToDto(updatedUser);
        userDto.setUserId(userId);

        //Update user info
        if(userRepository.updateUser(userMapper.dtoToEntity(userDto)) == 0) {
            log.warn("User update unsuccessful");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while updating user in the database.");
        }

        //Get newly updated user
        Optional<UserEntity> foundUserEntity = userRepository.findById(userId);
        UserDto foundUserDto = userMapper.entityToDto(foundUserEntity.get());

        return userMapper.userToInfo(foundUserDto);
    }
}
