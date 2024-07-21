package pro.sky.telegrambot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.Dogs;
import pro.sky.telegrambot.service.DogService;

@RestController
@RequestMapping("dogs")
public class DogsController {
    private final Logger logger = LoggerFactory.getLogger(DogsController.class);

    private final DogService dogService;

    public DogsController(DogService dogService) {
        this.dogService = dogService;
    }

    @PostMapping
    public Dogs addDogs(@RequestBody Dogs dogs) {
        return dogService.addDogs(dogs);
    }

    @PutMapping
    public ResponseEntity<Dogs> editDogs(@RequestBody Dogs dogs) {
        Dogs foundDog = dogService.editDogs(dogs);
        if (foundDog == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundDog);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Dogs> deleteDogs(@PathVariable Long id) {
        dogService.deleteDogs(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity findDogs(@RequestParam(required = false) Long id,
                                   @RequestParam(required = false) String name) {
        if (id != null) {
            return ResponseEntity.ok(dogService.findDogsById(id));
        }
        if (name != null) {
            return ResponseEntity.ok(dogService.findDogsByName(name));
        }
        return ResponseEntity.ok(dogService.getAllDogs());
    }

}
