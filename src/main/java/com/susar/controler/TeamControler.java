package com.susar.controler;



import com.susar.logging.LogEnum;
import com.susar.logging.LoggerSusar;
import com.susar.model.Specialist;
import com.susar.service.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TeamControler {

    LoggerSusar logger = LoggerSusar.getInstance();

    @Autowired
    SpecialistService specialistService;

    @GetMapping("/Team")
    public @ResponseBody
    Iterable<Specialist> getTeam(){
        logger.log(LogEnum.INFO,"GET /Team -> http://localhost:8080");
        return specialistService.getAllSpecialists();
    }

    @CrossOrigin(origins = "http://localhost:4200" )
    @RequestMapping(value = "/Team/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void deleteSusar(@PathVariable(value="id") Long id) {
        logger.log(LogEnum.INFO,"DELETE /Team/" + id + " -> http://localhost:8080");
        specialistService.delete(id);
    }

    @CrossOrigin(origins = "http://localhost:4200" )
    @PostMapping("/Team2")
    public @ResponseBody Specialist createSpecialist(@RequestBody Specialist specialist) {
        logger.log(LogEnum.INFO,"POST /Team2 -> http://localhost:8080");
        return specialistService.create(specialist);
    }

    @CrossOrigin(origins = "http://localhost:4200" )
    @RequestMapping(value = "/Team/{id}", method = RequestMethod.PUT)
    public @ResponseBody void updateSpecialist(@RequestBody  Specialist specialist, @PathVariable(value="id") Long id) {
        logger.log(LogEnum.INFO,"PUT /Team/" + id + " -> http://localhost:8080");
        specialistService.update(id,specialist);
    }
}
