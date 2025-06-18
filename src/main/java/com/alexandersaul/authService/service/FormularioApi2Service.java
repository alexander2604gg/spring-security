package com.alexandersaul.authService.service;

import com.alexandersaul.authService.model.FormularioApi2;

import java.util.List;
import java.util.Optional;

public interface FormularioApi2Service {

    public FormularioApi2 guardar(FormularioApi2 formulario);
    public Optional<FormularioApi2> buscarPorId(Long id);
    public List<FormularioApi2> listarTodos();

}
