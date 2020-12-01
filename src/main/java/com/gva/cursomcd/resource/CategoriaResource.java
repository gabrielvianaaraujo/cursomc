package com.gva.cursomcd.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.gva.cursomcd.domain.Categoria;
import com.gva.cursomcd.dto.CategoriaDTO;
import com.gva.cursomcd.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
    
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        List<Categoria> categorias = categoriaService.findAll();
        List<CategoriaDTO> categoriasDTO = categorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(categoriasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id){

        Categoria categoria = categoriaService.findById(id);

        if(categoria != null){
            return ResponseEntity.ok().body(categoria);
        }
        else return ResponseEntity.notFound().build();
    }

    //Chama o service para inserção de uma nova categoria passando a categoria vinda no body da requisição
    //Para devolver a resposta correta que no caso é 201 CREATED temos que passar a URI da nova categoria criada por iSsso o encadeamento de métodos
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Categoria obj){
        obj = categoriaService.insert(obj);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(obj.getId())
            .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
        obj.setId(id);
        obj = categoriaService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> deleteById(@PathVariable Integer id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}