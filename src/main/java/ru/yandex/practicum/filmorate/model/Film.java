package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Film {
    //целочисленный идентификатор
    private int id;
    //название
    private String name;
    //описание
    private String description;
    //дата релиза
    private LocalDate releaseDate;
    //продолжительность фильма
    private int duration;

    private Set<Integer> likes = new HashSet<>();

    public Film(int id, String name, String description, LocalDate releaseDate, int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    public Film(String name, String description, LocalDate releaseDate, int duration) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

//    public Film(int id ,String name, String description, LocalDate releaseDate, int duration, Set<Integer> likes) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.releaseDate = releaseDate;
//        this.duration = duration;
//        this.likes = likes;
//    }

    public Film() {
    }

}
