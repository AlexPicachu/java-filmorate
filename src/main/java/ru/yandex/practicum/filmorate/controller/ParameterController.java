package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.MPA;
import ru.yandex.practicum.filmorate.services.ParameterService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class ParameterController {
    ParameterService parameterService;
@Autowired
    public ParameterController(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @GetMapping("/genres")
    public List<Genre> getListGenre(){
        return new ArrayList<Genre>(parameterService.getListGenre());
    }

    @GetMapping("/genres/{id}")
   public Genre getGenreById(@PathVariable int id){
        return parameterService.getGenreById(id);
   }
   @GetMapping("/mpa")
   public List<MPA> getListMpa(){
        return new ArrayList<MPA>(parameterService.getListMpa());
   }
    @GetMapping("/mpa/{id}")
    public MPA getMpaById(@PathVariable int id){
        return parameterService.getMpaById(id);
    }
}
