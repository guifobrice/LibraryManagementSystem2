package com.codewithbrice.librarymanagementsystem.controller;

import com.codewithbrice.librarymanagementsystem.exception.GenreException;
import com.codewithbrice.librarymanagementsystem.payload.dto.GenreDTO;
import com.codewithbrice.librarymanagementsystem.payload.response.ApiResponse;
import com.codewithbrice.librarymanagementsystem.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    @PostMapping("/create")
    public ResponseEntity<GenreDTO> addGenre(@RequestBody GenreDTO genre) {
         GenreDTO createdGenre=genreService.createGenre(genre);
         return ResponseEntity.ok(createdGenre);
    }

    @GetMapping
    public ResponseEntity<?> getAllGenres() {
        List<GenreDTO> genres = genreService.getAllGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{genreId}")
    public ResponseEntity<?> getGenreById(
            @PathVariable("genreId") Long genreId
    ) throws GenreException {
        GenreDTO genre = genreService.getGenreById(genreId);
        return ResponseEntity.ok(genre);
    }

    @PutMapping("/{genreId}")
    public ResponseEntity<?> updateGenre(
            @PathVariable("genreId") Long genreId,
            @RequestBody GenreDTO genreDTO

    ) throws GenreException {
        GenreDTO genre = genreService.updateGenre(genreId, genreDTO);
        return ResponseEntity.ok(genre);
    }

    @DeleteMapping("/{genreId}")
    public ResponseEntity<?> deleteGenre(@PathVariable("genreId") Long genreId) throws GenreException {
        genreService.deleteGenre(genreId);
        ApiResponse response = new ApiResponse("Genre deleted - soft delete", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{genreId}/hard")
    public ResponseEntity<?> hardDeleteGenre(@PathVariable("genreId") Long genreId) throws GenreException {
        genreService.hardDeleteGenre(genreId);
        ApiResponse response = new ApiResponse("Genre deleted - hard delete", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/top-level")
    public ResponseEntity<?> getTopLevelGenres() {
        List<GenreDTO> genres = genreService.getTopLevelGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/count")
    public ResponseEntity<?> getTotalActiveGenres() {
        long total = genreService.getTotalActiveGenres();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/{id}/book-count")
    public ResponseEntity<?> getBookCountByGenre(
            @PathVariable Long id
    ) {
        long total = genreService.getBookCountByGenre(id);
        return ResponseEntity.ok(total);
    }
}
