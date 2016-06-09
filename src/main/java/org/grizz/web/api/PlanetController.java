package org.grizz.web.api;

import org.grizz.model.Building;
import org.grizz.model.Planet;
import org.grizz.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @PreAuthorize("authenticated")
    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Planet getPlanet(@PathVariable String id) {
        return planetService.get(id);
    }

    @PreAuthorize("authenticated")
    @RequestMapping(value = "/{id}/buildings", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<Building> getBuildings(@PathVariable String id) {
        return planetService.get(id).getBuildings();
    }

}
