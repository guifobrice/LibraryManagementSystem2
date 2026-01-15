package com.codewithbrice.librarymanagementsystem.mapper;

import com.codewithbrice.librarymanagementsystem.modal.Genre;
import com.codewithbrice.librarymanagementsystem.payload.dto.GenreDTO;
import com.codewithbrice.librarymanagementsystem.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GenreMapper {

    private final GenreRepository genreRepository;

    public GenreDTO toDTO(Genre genre) {
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

    public Genre toEntity(GenreDTO genreDTO) {
        if (genreDTO == null) {
            return null;
        }

        Genre genre= Genre.builder()
                .code(genreDTO.getCode())
                .name(genreDTO.getName())
                .description(genreDTO.getDescription())
                .displayOrder(genreDTO.getDisplayOrder())
                .active(genreDTO.getActive())
                .build();

        if (genreDTO.getParentGenreId() != null) {
            genreRepository.findById(genreDTO.getParentGenreId())
                    .ifPresent(genre::setParentGenre);
        }

        return genre;
    }

    public void updateEntityFromDTO(GenreDTO dto, Genre genre) {
        if(dto == null)
        {
            return ;
        }

        genre.setCode(dto.getCode());
        genre.setName(dto.getName());
        genre.setDescription(dto.getDescription());
        genre.setDisplayOrder(dto.getDisplayOrder() != null ? dto.getDisplayOrder() : 0);
        if (dto.getActive() != null)
        {
            genre.setActive(dto.getActive());
        }
        if(dto.getParentGenreId() != null) {
            genreRepository.findById(dto.getParentGenreId())
                    .ifPresent(genre::setParentGenre);
        }
    }

    public List<GenreDTO> toDTOList(List<Genre> genreList) {

        return genreList.stream()
                .map(genre -> toDTO(genre))
                .collect(Collectors.toList());
    }
}
