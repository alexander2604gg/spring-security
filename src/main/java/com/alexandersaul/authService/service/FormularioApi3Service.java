package com.alexandersaul.authService.service;

import com.alexandersaul.authService.model.FormularioApi2;
import com.alexandersaul.authService.model.FormularioApi3;

import java.util.List;
import java.util.Optional;

public interface FormularioApi3Service {
    public FormularioApi3 guardar(FormularioApi3 formulario);
    public Optional<FormularioApi3> buscarPorId(Long id);
    public List<FormularioApi3> listarTodos();
}
