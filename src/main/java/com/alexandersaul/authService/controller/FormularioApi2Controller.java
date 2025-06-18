package com.alexandersaul.authService.controller;

import com.alexandersaul.authService.model.FormularioApi2;
import com.alexandersaul.authService.service.FormularioApi2Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formulario-2")
public class FormularioApi2Controller {

    private final FormularioApi2Service formularioApi2Service;

    public FormularioApi2Controller(FormularioApi2Service formularioApi2Service) {
        this.formularioApi2Service = formularioApi2Service;
    }

    @PostMapping
    public ResponseEntity<FormularioApi2> guardar(@RequestBody FormularioApi2 formulario) {
        FormularioApi2 guardado = formularioApi2Service.guardar(formulario);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormularioApi2> buscarPorId(@PathVariable Long id) {
        return formularioApi2Service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<FormularioApi2>> listarTodos() {
        List<FormularioApi2> lista = formularioApi2Service.listarTodos();
        return ResponseEntity.ok(lista);
    }
}
