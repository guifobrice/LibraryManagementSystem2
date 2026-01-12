package com.codewithbrice.librarymanagementsystem.service.Impl;

import com.codewithbrice.librarymanagementsystem.modal.Genre;
import com.codewithbrice.librarymanagementsystem.payload.dto.GenreDTO;
import com.codewithbrice.librarymanagementsystem.repository.GenreRepository;
import com.codewithbrice.librarymanagementsystem.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {
        // return genreRepository.save(genre);

        Genre genre= Genre.builder()
                .code(genreDTO.getCode())
                .name(genreDTO.getName())
                .description(genreDTO.getDescription())
                .displayOrder(genreDTO.getDisplayOrder())
                .active(true)
                .build();

        if (genreDTO.getParentGenreId() != null) {
            Genre parentGenre = genreRepository.findById(genreDTO.getParentGenreId()).get();
            genre.setParentGenre(parentGenre);
        }

        Genre savedGenre = genreRepository.save(genre);

        GenreDTO dto = GenreDTO.builder()
                .id(savedGenre.getId())
                .code(savedGenre.getCode())
                .name(savedGenre.getName())
                .description(savedGenre.getDescription())
                .displayOrder(savedGenre.getDisplayOrder())
                .active(savedGenre.getActive())
                .createdAt(savedGenre.getCreatedAt())
                .updateAt(savedGenre.getUpdatedAt())
                .build();

        if (savedGenre.getParentGenre() != null)
        {
            dto.setParentGenreId(savedGenre.getParentGenre().getId());
            dto.setParentGenreName(savedGenre.getParentGenre().getName());
        }
        /*
        dto.setSubGenre(savedGenre.getSubGenres().stream()
                .filter(subGenre->));

         */

        // dto.setBookCount((long) (savedGenre.getB));

        return dto;
    }
}
