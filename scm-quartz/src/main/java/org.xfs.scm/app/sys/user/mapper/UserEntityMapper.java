package org.xfs.scm.app.sys.user.mapper;

import org.xfs.scm.app.sys.user.entity.UserEntity;

public interface UserEntityMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);
}