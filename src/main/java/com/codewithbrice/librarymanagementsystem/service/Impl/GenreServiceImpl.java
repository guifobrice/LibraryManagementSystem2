package com.codewithbrice.librarymanagementsystem.service.Impl;

import com.codewithbrice.librarymanagementsystem.modal.Genre;
import com.codewithbrice.librarymanagementsystem.repository.GenreRepository;
import com.codewithbrice.librarymanagementsystem.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }
}
