package com.dmadev.rest.controller;

/*
 *для того чтобы спринг воспринимал этот класс как класс обработчик htpp запросов,мы должны его пометить аннотацией rest контроллер
 */

import com.dmadev.rest.DTO.CatDTO;
import com.dmadev.rest.entity.Cat;
import com.dmadev.rest.exception.CatNotFoundException;
import com.dmadev.rest.repository.CatRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="main_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    //    @Autowired
//@RequiredArgsConstructor + final - автоматическое внедрение
    private final CatRepo catRepo;
    private final ObjectMapper objectMapper;

    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDTO catDTO) {
        //при конкатенации автоматически работает метод toString
        log.info(
                "New row: " + catRepo.save(
                        Cat.builder()
                                .age(catDTO.getAge())
                                .name(catDTO.getName())
                                .weight(catDTO.getWeight())
                                .build()));
    }

// @SneakyThrows -заставляет исключение  пробрасыватся- без его обработки

    @SneakyThrows
    @GetMapping("/api/all")
    public String getAll() throws CatNotFoundException {
        List<Cat> cats = catRepo.findAll();
        if (cats == null) {
            throw new CatNotFoundException("Cats not found");
        }

        return objectMapper.writeValueAsString(cats);
    }

    @SneakyThrows
    @GetMapping("/api/allCats")
    public List<Cat> getAllCats() throws CatNotFoundException {
        return catRepo.findAll();
    }

    @GetMapping("/api")
    public Cat getCat(@RequestParam long id) {
        return catRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("/api")
    public void deleteCat(@RequestParam long id) throws CatNotFoundException {
        if (!catRepo.existsById(id)) {
            throw new CatNotFoundException("Cat not found");
        }
        catRepo.deleteById(id);
    }

    @SneakyThrows
    @PutMapping("/api/put")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            throw new CatNotFoundException("Cat not found");
        }

        return catRepo.save(cat).toString();


    }


}
