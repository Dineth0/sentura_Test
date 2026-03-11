package lk.ijse.sentura_test.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import lk.ijse.sentura_test.dto.CountryDTO;
import lk.ijse.sentura_test.service.CountryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {
    private List<CountryDTO> cachedCountries = new ArrayList<>();
    private final RestTemplate restTemplate = new RestTemplate();
    @Override
    @Scheduled(fixedRate = 5000)
    @PostConstruct
    public void fetchAndCacheCountries() {
        String url = "https://restcountries.com/v3.1/all";
        try {
            JsonNode[] nodes = restTemplate.getForObject(url, JsonNode[].class);
            List<CountryDTO> tempCountries = new ArrayList<>();

            if (nodes != null) {
                for (JsonNode node : nodes) {
                    String name = node.at("/name/common").asText();
                    String capital = node.has("capital") && node.get("capital").isArray() ? node.at("/capital/0").asText() : "N/A";
                    String region = node.has("region") ? node.get("region").asText() : "N/A";
                    long population = node.has("population") ? node.get("population").asLong() : 0;
                    String flag = node.at("/flags/png").asText();

                    tempCountries.add(new CountryDTO(name, capital, region, population, flag));
                }
            }
            this.cachedCountries = tempCountries;
            System.out.println("updated successfully!");
        } catch (Exception e) {
            System.err.println("Failed to fetch countries: " + e.getMessage());
        }
    }

    public List<CountryDTO> getAllCountries() {
        return cachedCountries;
    }

    public List<CountryDTO> searchCountries(String query) {
        if (query == null || query.trim().isEmpty()) {
            return cachedCountries;
        }
        String lowerCaseQuery = query.toLowerCase();
        return cachedCountries.stream()
                .filter(c -> c.getName().toLowerCase().contains(lowerCaseQuery) ||
                        c.getCapital().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }

}
