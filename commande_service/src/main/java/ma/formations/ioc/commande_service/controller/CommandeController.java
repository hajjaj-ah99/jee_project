package ma.formations.ioc.commande_service.controller;

import ma.formations.ioc.commande_service.dto.CommandeRequestDto;
import ma.formations.ioc.commande_service.dto.CommandeResponseDto;
import ma.formations.ioc.commande_service.service.CommandeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commandes")
public class CommandeController {
    private final CommandeService service;

    public CommandeController(CommandeService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public CommandeResponseDto createCommande(@RequestBody CommandeRequestDto requestDto) {
        return service.createCommande(requestDto);
    }

    @GetMapping("/recent")
    public List<CommandeResponseDto> getRecentCommandes() {
        return service.getRecentCommandes();
    }

    @GetMapping
    public List<CommandeResponseDto> getAllCommandes() {
        return service.getAllCommandes();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCommande(@PathVariable Long id) {
        service.deleteCommande(id);
    }

    @PutMapping("/modifie/{id}")
    public CommandeResponseDto updateCommande(@PathVariable Long id, @RequestBody CommandeRequestDto requestDto) {
        return service.updateCommande(id, requestDto);
    }
    @GetMapping("/{id}")
    public CommandeResponseDto getCommandeById(@PathVariable Long id) {
        return service.getCommandeById(id);
    }

}
