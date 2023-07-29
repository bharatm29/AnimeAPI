package com.bharat.AnimeAPI.userAnimeData.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("animeUser")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimeUser {
    @Id
    private String email;
    private List<String> animeIds;
}
