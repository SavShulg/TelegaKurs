package pro.sky.telegrambot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.Cats;
import pro.sky.telegrambot.service.CatService;

@RestController
@RequestMapping("/cats")
public class CatsController {

    private final Logger logger = LoggerFactory.getLogger(CatsController.class);

    private final CatService catService;

    public CatsController(CatService catService) {
        this.catService = catService;
    }

    @PostMapping
    public Cats addCats(@RequestBody Cats cats) {
        return catService.addCats(cats);
    }

    @PutMapping
    public ResponseEntity<Cats> editCats(@RequestBody Cats cats) {
        Cats foundCat = catService.editCats(cats);
        if (foundCat == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundCat);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Cats> deleteCats(@PathVariable Long id) {
        catService.deleteCats(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity findCats(@RequestParam(required = false) Long id,
                                   @RequestParam(required = false) String name) {
        if (id != null) {
            return ResponseEntity.ok(catService.findCatsById(id));
        }
        if (name != null) {
            return ResponseEntity.ok(catService.findCatsByName(name));
        }
        return ResponseEntity.ok(catService.getAllCats());
    }
}
