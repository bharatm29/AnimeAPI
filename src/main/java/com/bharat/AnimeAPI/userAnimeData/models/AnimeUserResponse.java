package com.bharat.AnimeAPI.userAnimeData.models;

import com.bharat.AnimeAPI.animeInfo.models.AnimeDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimeUserResponse {
    private String email;
    private List<AnimeDetails> animes;
}
