package com.carlrue.rau.adapters.out.persistence;

import com.carlrue.rau.common.exception.ResourceNotFoundException;
import com.carlrue.rau.ports.out.LoadUserPort;
import com.carlrue.rau.ports.out.UpdateUserPort;
import com.carlrue.rau.common.PersistenceAdapter;
import com.carlrue.rau.domain.entities.User;

import java.util.ArrayList;
import java.util.List;

@PersistenceAdapter
public class UserPersistenceAdapter implements LoadUserPort, UpdateUserPort {

    private final SpringUserRepository userRepository;

    public UserPersistenceAdapter(SpringUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User load(Long id) {
        return userRepository
                .findById(id)
                .map(UserMapper::entityToDomain)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "Id", id)
                );
    }

    @Override
    public List<User> loadAll() {
        List<UserEntity> usersDB = userRepository.findAll();

        List<User> users = new ArrayList<>();
        for (UserEntity userDB : usersDB) {
            users.add(UserMapper.entityToDomain(userDB));
        }

        return users;
    }

    @Override
    public boolean update(User user) {
        User userToUpdate = userRepository
                .findById(user.getId())
                .map(UserMapper::entityToDomain)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "Id", user.getId())
                );

        userRepository.save(UserMapper.domainToEntity(userToUpdate));

        return true;
    }

    @Override
    public boolean save(User user) {

        userRepository.save(UserMapper.domainToEntity(user));

        return true;
    }

    @Override
    public boolean delete(Long id) {
        User userToDelete = userRepository
                .findById(id)
                .map(UserMapper::entityToDomain)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "Id", id)
                );
        userRepository.delete(UserMapper.domainToEntity(userToDelete));

        return true;
    }
}
