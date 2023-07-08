package cl.ufro.showplace_api.service;

import cl.ufro.showplace_api.helper.map.MapRequest;
import cl.ufro.showplace_api.helper.map.MapResponse;
import cl.ufro.showplace_api.model.Location;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapsService {
    private GeoApiContext context;

    public GoogleMapsService(@Value("${google.maps.key}") String apiKey) {
        this.context = new GeoApiContext.Builder().apiKey(apiKey).build();
    }

    /**
     * Get LatLng from address
     * @param request MapRequest
     * @return MapResponse
     */
    public MapResponse getLatLngFromAddress(MapRequest request) {
        String address = request.getAddress();
        try {
            GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
            if (results != null && results.length > 0 && results[0].geometry != null && results[0].geometry.location != null) {
                LatLng latLng = results[0].geometry.location;
                return MapResponse.builder().message("Dirección encontrada con éxito").lat(latLng.lat).lng(latLng.lng).build();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return MapResponse.builder().message("Dirección no encontrada").build();
    }

    /**
     * Creates a Location entity from an address
     * @param address address to get location
     * @return Location
     */
    public Location getLocationFromAddress(String address) {
        try {
            GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
            if (results != null && results.length > 0 && results[0].geometry != null && results[0].geometry.location != null) {
                LatLng latLng = results[0].geometry.location;
                Location location = new Location();
                location.setLatitude(latLng.lat);
                location.setLongitude(latLng.lng);
                location.setStreet(results[0].formattedAddress);
                location.setCity(results[0].addressComponents[2].longName);
                location.setCommune(results[0].addressComponents[3].longName);
                location.setProvince(results[0].addressComponents[4].longName);
                location.setRegion(results[0].addressComponents[5].longName);
                location.setCountry(results[0].addressComponents[6].longName);
                return location;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
