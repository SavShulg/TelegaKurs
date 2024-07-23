package pro.sky.telegrambot.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.service.VolunteerService;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {

    private final VolunteerService volunteerService;


    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }


    @PostMapping("/add")
    public Volunteer add(@RequestBody Volunteer volunteer) {
        return volunteerService.addVolunteer(volunteer);
    }

    @PutMapping("/edit")
    public ResponseEntity<Volunteer> editVolunteer(@RequestBody Volunteer volunteer) {
        Volunteer foundVolunteer = volunteerService.editVolunteer(volunteer);
        if (foundVolunteer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundVolunteer);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam Long idVolunteer) {
        return volunteerService.delete(idVolunteer);
    }


    @GetMapping("/find")
    public ResponseEntity findVolunteer(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(VolunteerService.findVolunteerById(id));
        }
        return ResponseEntity.ok(VolunteerService.getAllVolunteer());
    }

}
