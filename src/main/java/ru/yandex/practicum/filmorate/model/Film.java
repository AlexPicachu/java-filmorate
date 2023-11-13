package ru.yandex.practicum.filmorate.model;

import lombok.Data;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Film {

    /**
     * целочисленный идентификатор
     */
    private int id;

    /**
     * название
     */
    private String name;

    /**
     * описание
     */
    private String description;

    /**
     * дата релиза
     */
    private LocalDate releaseDate;

    /**
     * продолжительность фильма
     */
    private int duration;

    /**
     * рейтинг фильмов
     */
    private MPA mpa;

    /**
     * список жанров
     */
    private Set<Genre> genres = new HashSet<>();


    /**
     * Set для хранения лайков поставленных пользователем
     */
   // private Set<Integer> likes = new HashSet<>();

    private Integer like;
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

    public Film(int id, String name, String description, LocalDate releaseDate, int duration, MPA mpa) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.mpa = mpa;
    }

    public Film(String name, String description, LocalDate releaseDate, int duration, Integer likes, MPA mpa, Set<Genre> genres) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.like = likes;
        this.mpa = mpa;
        this.genres = genres;

    }

    public Film() {
    }

}
