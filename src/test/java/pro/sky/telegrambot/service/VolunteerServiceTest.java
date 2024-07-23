package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VolunteerServiceTest {
    private final VolunteerRepository volunteerRepository = mock(VolunteerRepository.class);
    private final TelegramBot bot = mock(TelegramBot.class);
    private VolunteerService out;

    @BeforeEach
    public void initOut() {
        out = new VolunteerService(volunteerRepository);
    }

    @Test
    void addVolunteer() {
        Volunteer volunteer = new Volunteer();
        when(volunteerRepository.save(volunteer)).thenReturn(volunteer);
        assertEquals(volunteer, out.addVolunteer(volunteer));
    }

    @Test
    void editVolunteer() {
        Volunteer volunteer = new Volunteer();
        when(volunteerRepository.save(volunteer)).thenReturn(volunteer);
        assertEquals(volunteer, out.addVolunteer(volunteer));
    }

    @Test
    void getAllVolunteer() {
        Volunteer volunteer1 = new Volunteer();
        Volunteer volunteer2 = new Volunteer();
        Volunteer volunteer3 = new Volunteer();
        List<Volunteer> exp = new ArrayList<>();
        exp.add(volunteer1);
        exp.add(volunteer2);
        exp.add(volunteer3);
        when(volunteerRepository.findAll()).thenReturn(exp);
        assertEquals(exp, out.getAllVolunteer());
    }

    @Test
    void findVolunteerById() {
        Volunteer volunteer1 = new Volunteer();
        Volunteer volunteer2 = new Volunteer();
        Volunteer volunteer3 = new Volunteer();
        when(volunteerRepository.findById(1L)).thenReturn(Optional.of(volunteer2));
        assertEquals(volunteer2, out.findVolunteerById(1));

    }

    @Test
    public void testDeleteVolunteer() {
        long id = 1L;
        doNothing().when(volunteerRepository).deleteById(id);
        out.delete(id);
        verify(volunteerRepository, times(1)).deleteById(id);
    }



}