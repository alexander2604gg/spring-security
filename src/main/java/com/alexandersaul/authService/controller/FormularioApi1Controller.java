package com.alexandersaul.authService.controller;

import com.alexandersaul.authService.client.Api1Client;
import com.alexandersaul.authService.dto.Api1ResponseDTO;
import com.alexandersaul.authService.model.FormularioApi1;
import com.alexandersaul.authService.service.FormularioApi1Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formulario-1")
public class FormularioApi1Controller {

    private final FormularioApi1Service formularioApi1Service;

    public FormularioApi1Controller(FormularioApi1Service formularioApi1Service) {
        this.formularioApi1Service = formularioApi1Service;
    }

    @PostMapping
    public ResponseEntity<Api1ResponseDTO> guardar(@RequestBody FormularioApi1 formulario) {
        Api1ResponseDTO guardado = formularioApi1Service.guardar(formulario);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormularioApi1> buscarPorId(@PathVariable Long id) {
        return formularioApi1Service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<FormularioApi1>> listarTodos() {
        List<FormularioApi1> lista = formularioApi1Service.listarTodos();
        return ResponseEntity.ok(lista);
    }
}