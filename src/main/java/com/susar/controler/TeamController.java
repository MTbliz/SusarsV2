package com.susar.controler;


import com.susar.logging.LogEnum;
import com.susar.logging.LoggerSusar;
import com.susar.model.Specialist;
import com.susar.service.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeamController {

    private final LoggerSusar logger = LoggerSusar.getInstance();

    private SpecialistService specialistService;

    @Autowired
    public TeamController(SpecialistService specialistService) {
        this.specialistService = specialistService;
    }

    @GetMapping("/Team")
    public @ResponseBody
    Iterable<Specialist> getTeam() {
        logger.log(LogEnum.INFO, "GET /Team -> http://localhost:8080");
        return specialistService.getAllSpecialists();
    }

    @RequestMapping(value = "/Team/{id}", method = RequestMethod.DELETE)
    public void deleteSusar(@PathVariable(value = "id") Long id) {
        logger.log(LogEnum.INFO, "DELETE /Team/" + id + " -> http://localhost:8080");
        specialistService.delete(id);
    }

    @PostMapping("/Team2")
    public Specialist createSpecialist(@RequestBody Specialist specialist) {
        logger.log(LogEnum.INFO, "POST /Team2 -> http://localhost:8080");
        return specialistService.create(specialist);
    }

    @RequestMapping(value = "/Team/{id}", method = RequestMethod.PUT)
    public void updateSpecialist(@RequestBody Specialist specialist, @PathVariable(value = "id") Long id) {
        logger.log(LogEnum.INFO, "PUT /Team/" + id + " -> http://localhost:8080");
        specialistService.update(id, specialist);
    }
}
