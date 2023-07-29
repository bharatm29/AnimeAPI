package com.bharat.AnimeAPI.animeInfo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Anime {
    private String animeId, animeTitle, animeUrl, animeImg, status;
}
