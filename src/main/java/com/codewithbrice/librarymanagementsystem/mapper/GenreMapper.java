package com.codewithbrice.librarymanagementsystem.mapper;

import com.codewithbrice.librarymanagementsystem.modal.Genre;
import com.codewithbrice.librarymanagementsystem.payload.dto.GenreDTO;

import java.util.stream.Collectors;

public class GenreMapper {
    public static GenreDTO toDTO(Genre genre) {
        if(genre == null) {
            return null;
        }

        GenreDTO dto = GenreDTO.builder()
                .id(genre.getId())
                .code(genre.getCode())
                .name(genre.getName())
                .description(genre.getDescription())
                .displayOrder(genre.getDisplayOrder())
                .active(genre.getActive())
                .createdAt(genre.getCreatedAt())
                .updateAt(genre.getUpdatedAt())
                .build();

        if(genre.getParentGenre() != null) {
            dto.setParentGenreId(genre.getParentGenre().getId());
            dto.setParentGenreName(genre.getParentGenre().getName());
        }
        if (genre.getSubGenres() != null && !genre.getSubGenres().isEmpty()) {
            dto.setSubGenre(genre.getSubGenres().stream()
                    .filter(subGenre ->subGenre.getActive())
                    .map(subGenre -> toDTO(subGenre)).collect(Collectors.toList()));
        }


        return dto;
    }
}
