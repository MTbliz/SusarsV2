package com.susar.controler;

import com.susar.logging.LogEnum;
import com.susar.logging.LoggerSusar;
import com.susar.model.Susar;
import com.susar.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SusarController {

    private final LoggerSusar logger = LoggerSusar.getInstance();

    private SusarService susarService;

    @Autowired
    public SusarController(SusarService susarService) {
        this.susarService = susarService;
    }

    @PostMapping("/Susars2")
    public Susar createSusar(@RequestBody Susar susar) {
        logger.log(LogEnum.INFO, "POST /Susars2 -> http://localhost:8080");
        return susarService.create(susar);
    }

    @RequestMapping(value = "/Susars/{id}", method = RequestMethod.PUT)
    public void updateSusar(@RequestBody Susar susar, @PathVariable(value = "id") Long id) {
        logger.log(LogEnum.INFO, "PUT /Susars/" + id + " -> http://localhost:8080");
        susarService.update(id, susar);
    }

    @GetMapping("/Susars")
    public Iterable<Susar> getSusars() {
        logger.log(LogEnum.INFO, "GET /Susars -> http://localhost:8080");
        return susarService.getAllSusars();
    }

    @GetMapping("/Types")
    public String getAllTypes() {
        logger.log(LogEnum.INFO, "GET /Types -> http://localhost:8080");
        List<String> types = susarService.getTypes();
        TypesView typesView = new TypesView();
        return typesView.typesToJson(types);
    }

    @GetMapping("/Studies")
    public String getAllStudies() {
        logger.log(LogEnum.INFO, "GET /Studies -> http://localhost:8080");
        List<String> stuides = susarService.getStudies();
        StudiesView studiesView = new StudiesView();
        return studiesView.stuidesToJson(stuides);
    }

    @GetMapping("/Countries")
    public String getAllCountries() {
        logger.log(LogEnum.INFO, "GET /Countries -> http://localhost:8080");
        List<String> countries = susarService.getCountries();
        CountriesView countriesView = new CountriesView();
        return countriesView.countriesToJson(countries);
    }

    @GetMapping("/Sites")
    public String getAllSites() {
        logger.log(LogEnum.INFO, "GET /Sites -> http://localhost:8080");
        List<String> sites = susarService.getSites();
        SitesView sitesView = new SitesView();
        return sitesView.sitesToJson(sites);
    }

    @RequestMapping(value = "/Susars/{id}", method = RequestMethod.DELETE)
    public void deleteSusar(@PathVariable(value = "id") Long id) {
        logger.log(LogEnum.INFO, "DELETE /Susars/" + id + "  -> http://localhost:8080");
        susarService.delete(id);
    }
}
