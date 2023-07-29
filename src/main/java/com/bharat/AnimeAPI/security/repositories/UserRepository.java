package com.bharat.AnimeAPI.security.repositories;

import com.bharat.AnimeAPI.security.models.UserSaveWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserSaveWrapper, String> {
}
