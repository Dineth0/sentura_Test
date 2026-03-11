package lk.ijse.sentura_test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryDTO {
    private String name;
    private String capital;
    private String region;
    private long population;
    private String flag;
}
