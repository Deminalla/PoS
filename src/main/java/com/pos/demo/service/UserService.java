package com.pos.demo.service;

import com.pos.demo.mapper.UserMapper;
import com.pos.demo.model.dto.UserDto;
import com.pos.demo.model.entity.UserEntity;
import com.pos.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserDto getUserByID(BigInteger user_id) {
        log.info("Searching for user with ID {}", user_id);
        Optional<UserEntity> userEntity = userRepository.findById(user_id);

        if (userEntity.isEmpty()) {
            log.warn("User was not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User was not found");
        }

        return userMapper.entityToDto(userEntity.get());
    }

    public UserDto createUser(UserDto newUserDto) {
        log.info("Creating new user");

        UserEntity newUserEntity = userMapper.dtoToEntity(newUserDto);

        //Check if user already exists
        if(existsUser(newUserEntity)) {
            log.warn("User with provided email already exists");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with provided email already exists");
        }

        //Password hashing
        newUserEntity.setPassword(passwordEncoder.encode(newUserEntity.getPassword()));

        //User creation
        int response = userRepository.createUser(newUserEntity);

        if (response == 0) {
            log.warn("User creation unsuccessful");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while adding new user to database");
        }

        //Retrieving the newly added user
        Optional<UserEntity> foundUserEntity = userRepository.findById(newUserEntity.getUser_id());

        return userMapper.entityToDto(foundUserEntity.get());
    }

    public void deleteUser(BigInteger user_id)
    {
        log.info("Deleting user with ID {}", user_id);

        //Check if user exists
        //Optional<UserEntity> user = userRepository.findById(user_id);

        if(userRepository.findById(user_id).isPresent()) {
            int response = userRepository.deleteUser(user_id);
            if(response == 0) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error occurred while trying to delete the user");
            }
            else {
                return;
            }
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }

    }

    public boolean existsUser(UserEntity newUserEntity) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(newUserEntity.getEmail());

        return userEntity.isPresent();
    }
}
