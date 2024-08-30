package com.elite.repository.user;

import com.elite.entity.user.UserSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSettingRepository extends JpaRepository<UserSetting, Long> {

    Optional<UserSetting> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
