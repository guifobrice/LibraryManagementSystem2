package com.codewithbrice.librarymanagementsystem.repository;

import com.codewithbrice.librarymanagementsystem.modal.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
