package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Cats;
import pro.sky.telegrambot.repository.CatsRepository;

import java.util.Collection;

@Service
public class CatService {
    private final Logger logger = LoggerFactory.getLogger(CatService.class);
    private final CatsRepository catsRepository;

    public CatService(CatsRepository catsRepository) {
        this.catsRepository = catsRepository;
    }

    public Cats addCats(Cats cats) {
        return catsRepository.save(cats);
    }

    public Cats editCats(Cats cats) {
        return catsRepository.save(cats);
    }

    public void deleteCats(long id) {
        catsRepository.deleteById(id);
    }

    public Collection<Cats> getAllCats() {
        return catsRepository.findAll();
    }

    public Cats findCatsById(long id) {
        return catsRepository.findById(id).orElseThrow();
    }

    public Cats findCatsByName(String name) {
        return catsRepository.findCatsByNameCat(name);
    }
}
