package com.alexandersaul.authService.controller;

import com.alexandersaul.authService.model.FormularioApi3;
import com.alexandersaul.authService.service.FormularioApi3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formulario-3")
public class FormularioApi3Controller {

    private final FormularioApi3Service formularioApi3Service;

    public FormularioApi3Controller(FormularioApi3Service formularioApi3Service) {
        this.formularioApi3Service = formularioApi3Service;
    }

    @PostMapping
    public ResponseEntity<FormularioApi3> guardar(@RequestBody FormularioApi3 formulario) {
        FormularioApi3 guardado = formularioApi3Service.guardar(formulario);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormularioApi3> buscarPorId(@PathVariable Long id) {
        return formularioApi3Service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<FormularioApi3>> listarTodos() {
        List<FormularioApi3> lista = formularioApi3Service.listarTodos();
        return ResponseEntity.ok(lista);
    }
}
