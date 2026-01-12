package com.codewithbrice.librarymanagementsystem.modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Genre Code is Mandatory")
    private String code;

    @NotBlank(message = "Genre Name is Mandatory")
    private String name;

    @Size(max = 500, message = "description must not exceed 500")
    private String description;

    @Min(value=0, message = "display order connot be negative")
    private Integer displayOrder = 0;

    @Column(nullable = false)
    private Boolean active=true;

    @ManyToOne(fetch = FetchType.EAGER)  // par défaut c’est EAGER, mais parfois il est LAZY
    private Genre parentGenre;

    @OneToMany(mappedBy = "parentGenre", fetch = FetchType.LAZY)
    private List<Genre> subGenres = new ArrayList<>();

/*   @OneToMany(mappedBy = "genre", cascade = CascadeType.PERSIST)
    private List<Book> books=new ArrayList<Book>(); */

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
