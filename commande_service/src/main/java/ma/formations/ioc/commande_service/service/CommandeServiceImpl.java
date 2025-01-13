package ma.formations.ioc.commande_service.service;

import ma.formations.ioc.commande_service.dto.CommandeRequestDto;
import ma.formations.ioc.commande_service.dto.CommandeResponseDto;
import ma.formations.ioc.commande_service.model.Commande;
import ma.formations.ioc.commande_service.repository.CommandeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository repository;

    public CommandeServiceImpl(CommandeRepository repository) {
        this.repository = repository;
    }

    @Override
    public CommandeResponseDto createCommande(CommandeRequestDto requestDto) {
        Commande commande = new Commande();
        commande.setDescription(requestDto.getDescription());
        commande.setQuantite(requestDto.getQuantite());
        commande.setDate(requestDto.getDate());
        commande.setMontant(requestDto.getMontant());

        Commande savedCommande = repository.save(commande);

        return mapToResponseDto(savedCommande);
    }

    @Override
    public List<CommandeResponseDto> getRecentCommandes() {
        List<Commande> commandes = repository.findByDateAfter(LocalDate.now().minusDays(20));
        return commandes.stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    public List<CommandeResponseDto> getAllCommandes() {
        return repository.findAll().stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    public void deleteCommande(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CommandeResponseDto updateCommande(Long id, CommandeRequestDto requestDto) {
        Optional<Commande> optionalCommande = repository.findById(id);
        if (optionalCommande.isPresent()) {
            Commande existingCommande = optionalCommande.get();
            existingCommande.setDescription(requestDto.getDescription());
            existingCommande.setQuantite(requestDto.getQuantite());
            existingCommande.setDate(requestDto.getDate());
            existingCommande.setMontant(requestDto.getMontant());

            Commande updatedCommande = repository.save(existingCommande);
            return mapToResponseDto(updatedCommande);
        } else {
            throw new IllegalArgumentException("Commande with ID " + id + " not found.");
        }
    }

    private CommandeResponseDto mapToResponseDto(Commande commande) {
        CommandeResponseDto dto = new CommandeResponseDto();
        dto.setId(commande.getId());
        dto.setDescription(commande.getDescription());
        dto.setQuantite(commande.getQuantite());
        dto.setDate(commande.getDate());
        dto.setMontant(commande.getMontant());
        return dto;
    }
    @Override
    public CommandeResponseDto getCommandeById(Long id) {
        Commande commande = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Commande with ID " + id + " not found."));
        return mapToResponseDto(commande);
    }

}
