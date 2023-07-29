package com.bharat.AnimeAPI.userAnimeData.repositories;

import com.bharat.AnimeAPI.userAnimeData.models.AnimeUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnimeUserRepository extends MongoRepository<AnimeUser, String> {
}
