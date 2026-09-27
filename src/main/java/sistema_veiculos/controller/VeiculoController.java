package sistema_veiculos.controller;

import org.springframework.web.bind.annotation.*;
import sistema_veiculos.model.Veiculo;
import sistema_veiculos.service.VeiculoService;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Veiculo> listar() {
        return service.listar();
    }

    @PostMapping
    public Veiculo criar(@RequestBody Veiculo veiculo) {
        return service.criar(veiculo);
    }

    @PutMapping("/{id}")
    public Veiculo atualizar(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        return service.atualizar(id, veiculo);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}