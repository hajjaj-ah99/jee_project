package ma.formations.ioc.commande_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeResponseDto {
    private Long id;
    private String description;
    private int quantite;
    private LocalDate date;
    private double montant;
}

