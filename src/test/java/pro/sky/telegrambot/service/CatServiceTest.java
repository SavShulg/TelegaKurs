package pro.sky.telegrambot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.model.Cats;
import pro.sky.telegrambot.repository.CatsRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatServiceTest {

    @Mock
    private CatsRepository catsRepository;

    @InjectMocks
    private CatService catService;

    @Test
    void addCatsTest() {
        Cats cat = new Cats();
        cat.setIdCat(1);
        cat.setNameCat("Bulka");
        cat.setAge(2);
        when(catsRepository.save(cat)).thenReturn(cat);
        Cats result = catService.addCats(cat);
        assertEquals(cat, result);
    }

    @Test
    void editCatsTest() {
        Cats cat = new Cats();
        cat.setIdCat(1);
        cat.setNameCat("Bulka");
        cat.setAge(2);
        when(catsRepository.save(cat)).thenReturn(cat);
        Cats savedCat = catService.addCats(cat);
        savedCat.setNameCat("Chubik");
        when(catsRepository.save(savedCat)).thenReturn(savedCat);
        Cats updatedCat = catService.editCats(savedCat);
        assertEquals(savedCat, updatedCat);
    }

    @Test
    void deleteCatsTest() {
        Cats cat = new Cats();
        cat.setIdCat(1);
        cat.setNameCat("Bulka");
        cat.setAge(2);
        when(catsRepository.save(cat)).thenReturn(cat);
        Cats savedCat = catService.addCats(cat);
        doNothing().when(catsRepository).deleteById(savedCat.getIdCat());
        catService.deleteCats(savedCat.getIdCat());
        verify(catsRepository, times(1)).deleteById(savedCat.getIdCat());
    }

    @Test
    void getAllCatsTest() {
        List<Cats> cats = new ArrayList<>();
        Cats cat = new Cats();
        cat.setIdCat(1);
        cat.setNameCat("Bulka");
        cat.setAge(2);

        Cats cat2 = new Cats();
        cat.setIdCat(2);
        cat.setNameCat("Chubaka");
        cat.setAge(3);

        when(catsRepository.findAll()).thenReturn(cats);
        Collection<Cats> result = catService.getAllCats();
        assertEquals(cats, result);
    }

    @Test
    void findCatsByIdTest() {
        Cats cat = new Cats();
        cat.setIdCat(1);
        cat.setNameCat("Bulka");
        cat.setAge(2);
        when(catsRepository.findById(cat.getIdCat())).thenReturn(Optional.of(cat));
        Cats result = catService.findCatsById(cat.getIdCat());
        assertEquals(cat, result);
    }

    @Test
    void findCatsByNameTest() {
        Cats cat = new Cats();
        cat.setIdCat(1);
        cat.setNameCat("Bulka");
        cat.setAge(2);
        when(catsRepository.findCatsByNameCat(cat.getNameCat())).thenReturn(cat);
        Cats result = catService.findCatsByName(cat.getNameCat());
        assertEquals(cat, result);
    }
}