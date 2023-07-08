package cl.ufro.showplace_api.controller;

import cl.ufro.showplace_api.helper.map.MapRequest;
import cl.ufro.showplace_api.helper.map.MapResponse;
import cl.ufro.showplace_api.model.Location;
import cl.ufro.showplace_api.service.GoogleMapsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/map")
public class MapController {
    private final GoogleMapsService service;

    /**
     * Obtains the latitude and longitude from an address.
     *
     * @param request MapRequest
     * @return MapResponse
     */
    @GetMapping("/address")
    public ResponseEntity<MapResponse> getLatLngFromAddress(@RequestBody MapRequest request) {
        return ResponseEntity.ok(service.getLatLngFromAddress(request));
    }

    /**
     * Creates a Location entity from an address
     *
     * @param request MapRequest
     * @return Location created from address
     */
    @GetMapping("/location")
    public ResponseEntity<Location> getLocationFromAddress(@RequestBody MapRequest request) {
        return ResponseEntity.ok(service.getLocationFromAddress(request.getAddress()));
    }

}
