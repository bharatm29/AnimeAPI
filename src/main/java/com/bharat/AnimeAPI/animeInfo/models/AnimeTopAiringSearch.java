package com.bharat.AnimeAPI.animeInfo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AnimeTopAiringSearch {
    private List<AnimeTopAiring> animes;
}
