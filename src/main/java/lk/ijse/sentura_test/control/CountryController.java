package lk.ijse.sentura_test.control;

import lk.ijse.sentura_test.dto.CountryDTO;
import lk.ijse.sentura_test.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/countries")
@CrossOrigin
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public List<CountryDTO> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/search")
    public List<CountryDTO> searchCountries(@RequestParam String q) {
        return countryService.searchCountries(q);
    }
}
