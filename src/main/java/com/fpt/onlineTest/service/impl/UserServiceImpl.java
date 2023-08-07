package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.dto.CourseDto;
import com.fpt.onlineTest.dto.UserDto;
import com.fpt.onlineTest.model.Course;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.reponsitory.UserReponsitory;
import com.fpt.onlineTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserReponsitory userReponsitory;

    @Override
    public List<User> findAll() {
        return userReponsitory.findAll();
    }

    @Override
    public List<User> findAll(Sort sort) {
        return userReponsitory.findAll(sort);
    }

    @Override
    public List<User> findAllById(Iterable<Integer> integers) {
        return userReponsitory.findAllById(integers);
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        return userReponsitory.saveAll(entities);
    }

    @Override
    public void flush() {
        userReponsitory.flush();
    }

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        return userReponsitory.saveAndFlush(entity);
    }

    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        return userReponsitory.saveAllAndFlush(entities);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<User> entities) {
        userReponsitory.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {
        userReponsitory.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        userReponsitory.deleteAllByIdInBatch(integers);
    }

    @Override
    public void deleteAllInBatch() {
        userReponsitory.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public User getOne(Integer integer) {
        return userReponsitory.getOne(integer);
    }

    @Override
    @Deprecated
    public User getById(Integer integer) {
        return userReponsitory.getById(integer);
    }

    @Override
    public User getReferenceById(Integer integer) {
        return userReponsitory.getReferenceById(integer);
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return userReponsitory.findAll(example);
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return userReponsitory.findAll(example, sort);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userReponsitory.findAll(pageable);
    }

    @Override
    public <S extends User> S save(S entity) {
        return userReponsitory.save(entity);
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return userReponsitory.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return userReponsitory.existsById(integer);
    }

    @Override
    public long count() {
        return userReponsitory.count();
    }

    @Override
    public boolean deleteById(Integer integer) {
        userReponsitory.deleteById(integer);
        return false;
    }

    @Override
    public void delete(User entity) {
        userReponsitory.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        userReponsitory.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        userReponsitory.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        userReponsitory.deleteAll();
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return userReponsitory.findOne(example);
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return userReponsitory.findAll(example, pageable);
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return userReponsitory.count(example);
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return userReponsitory.exists(example);
    }

    @Override
    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return userReponsitory.findBy(example, queryFunction);
    }

    @Override
    public Optional<UserDto> getUserDtoById(Integer userId) {
        return userReponsitory.findById(userId).map(this::mapToDTO);
    }
    private UserDto mapToDTO(User user) {
        UserDto userDto= new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(userDto.getEmail());
        userDto.setPhone(userDto.getPhone());
        userDto.setImageUser(user.getImageUser());
        return userDto;
    }
}
