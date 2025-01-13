package ma.formations.ioc.commande_service.service;

import ma.formations.ioc.commande_service.dto.CommandeRequestDto;
import ma.formations.ioc.commande_service.dto.CommandeResponseDto;

import java.util.List;

public interface CommandeService {
    CommandeResponseDto createCommande(CommandeRequestDto requestDto);
    List<CommandeResponseDto> getRecentCommandes();
    List<CommandeResponseDto> getAllCommandes();
    void deleteCommande(Long id); // New method
    CommandeResponseDto updateCommande(Long id, CommandeRequestDto requestDto); // New method
    CommandeResponseDto getCommandeById(Long id); // New method
}
