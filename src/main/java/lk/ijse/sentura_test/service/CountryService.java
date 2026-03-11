package lk.ijse.sentura_test.service;

import lk.ijse.sentura_test.dto.CountryDTO;

import java.util.List;

public interface CountryService {
    public void fetchAndCacheCountries();
    public List<CountryDTO> getAllCountries();
    public List<CountryDTO> searchCountries(String query);

}
