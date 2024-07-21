package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.model.Dogs;
import pro.sky.telegrambot.repository.DogsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogServiceTest {
    private final DogsRepository dogsRepository = mock(DogsRepository.class);
    private DogService out;

    @BeforeEach
    public void initOut() {
        out = new DogService(dogsRepository);
    }
    @Test
    void addDogs() {
        Dogs dogs = new Dogs();
        when(dogsRepository.save(dogs)).thenReturn(dogs);
        assertEquals(dogs, out.addDogs(dogs));
    }


    @Test
    void editDogs() {
        Dogs dogs = new Dogs();
        when(dogsRepository.save(dogs)).thenReturn(dogs);
        assertEquals(dogs, out.editDogs(dogs));
    }

    @Test
    void getAllDogs() {
        Dogs dogs1 = new Dogs();
        Dogs dogs2 = new Dogs();
        Dogs dogs3 = new Dogs();
        List<Dogs> exp = new ArrayList<>();
        exp.add(dogs1);
        exp.add(dogs2);
        exp.add(dogs3);
        when(dogsRepository.findAll()).thenReturn(exp);
        out.addDogs(dogs1);
        out.addDogs(dogs2);
        out.addDogs(dogs3);
        assertEquals(exp, out.getAllDogs());
    }

    @Test
    void findDogsById() {
        Dogs dogs1 = new Dogs();
        Dogs dogs2 = new Dogs();
        Dogs dogs3 = new Dogs();
        when(dogsRepository.save(dogs1)).thenReturn(dogs1);
        when(dogsRepository.findById(1L)).thenReturn(Optional.of(dogs1));
        Dogs exp = dogs1;
        assertEquals(exp, out.findDogsById(1L));
    }

    @Test
    void findDogsByName() {
        Dogs dogs1 = new Dogs();
        Dogs dogs2 = new Dogs();
        Dogs dogs3 = new Dogs();
        dogs1.setNameDog("Bulka");
        dogs2.setNameDog("Chubik");
        dogs3.setNameDog("Chubaka");
        Dogs exp = dogs2;
        when(dogsRepository.findByNameDog("Chubik")).thenReturn(dogs2);
        assertEquals(exp, out.findDogsByName("Chubik"));
    }
}