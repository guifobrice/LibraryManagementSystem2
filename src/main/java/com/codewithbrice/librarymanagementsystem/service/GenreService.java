package com.codewithbrice.librarymanagementsystem.service;

import com.codewithbrice.librarymanagementsystem.modal.Genre;
import com.codewithbrice.librarymanagementsystem.payload.dto.GenreDTO;

import java.util.List;

public interface GenreService {
    public GenreDTO createGenre(GenreDTO genre);

    List<GenreDTO> getAllGenres();
}
