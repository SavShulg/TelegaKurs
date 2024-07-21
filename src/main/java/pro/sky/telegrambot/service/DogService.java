package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Dogs;
import pro.sky.telegrambot.repository.DogsRepository;

import java.util.Collection;

@Service
public class DogService {
    private final Logger logger = LoggerFactory.getLogger(DogService.class);
    private final DogsRepository dogsRepository;

    public DogService(DogsRepository dogsRepository) {
        this.dogsRepository = dogsRepository;
    }

    public Dogs addDogs(Dogs dogs) {
        return dogsRepository.save(dogs);
    }

    public Dogs editDogs(Dogs dogs) {
        return dogsRepository.save(dogs);
    }

    public void deleteDogs(Long id) {
        dogsRepository.findAll();
    }

    public Collection<Dogs> getAllDogs() {
        return dogsRepository.findAll();
    }
    public Dogs findDogsById(Long id) {
        return dogsRepository.findById(id).orElseThrow();
    }

    public Dogs findDogsByName(String name) {
        return dogsRepository.findByNameDog(name);
    }
}
