package com.alexandersaul.authService.service.Impl;

import com.alexandersaul.authService.client.Api1Client;
import com.alexandersaul.authService.dto.Api1RequestDTO;
import com.alexandersaul.authService.dto.Api1ResponseDTO;
import com.alexandersaul.authService.model.FormularioApi1;
import com.alexandersaul.authService.model.UserSec;
import com.alexandersaul.authService.repository.FormularioApi1Repository;
import com.alexandersaul.authService.repository.IUserRepository;
import com.alexandersaul.authService.service.FormularioApi1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormularioApi1ServiceImpl implements FormularioApi1Service {

    @Autowired
    private FormularioApi1Repository formularioApi1Repository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private Api1Client api1Client;


    @Override
    public Api1ResponseDTO guardar(FormularioApi1 formulario) {
        UserSec userSec = userRepository.findById(formulario.getUser().getId())
                .orElseThrow(()-> new NotFoundException("User dont exist"));
        formulario.setUser(userSec);
        formularioApi1Repository.save(formulario);
        Api1RequestDTO api1RequestDTO = buildRequest(formulario);
        return api1Client.predict(api1RequestDTO);
    }

    public Api1RequestDTO buildRequest(FormularioApi1 formularioApi1) {
        Api1RequestDTO dto = new Api1RequestDTO();

        dto.setAge(formularioApi1.getEdad());

        // Suponiendo que "M" = 1 y "F" = 0
        dto.setGender("M".equalsIgnoreCase(formularioApi1.getGenero()) ? 1 : 0);

        dto.setEducationyears(formularioApi1.getAniosEducacion() != null ? formularioApi1.getAniosEducacion() : 0);
        dto.setGlobal(formularioApi1.getGlobal() != null ? formularioApi1.getGlobal() : 0.0);
        dto.setEf(formularioApi1.getEf() != null ? formularioApi1.getEf() : 0.0);
        dto.setPs(formularioApi1.getPs() != null ? formularioApi1.getPs() : 0.0);

        // Como no tienes glucosa, colesterol ni presión en el modelo, los seteamos con valores por defecto
        dto.setGlucoseMin(85.0);               // puedes cambiarlo por una constante o eliminarlo si no es requerido
        dto.setCholesterolTotal(185.0);        // idem
        dto.setHypertensionSys(145.0);         // idem

        dto.setSmoking(Boolean.TRUE.equals(formularioApi1.getFumador()) ? 1 : 0);
        dto.setFazekas(convertFazekasToInt(formularioApi1.getCategoriaFazekas()));
        dto.setLacunesNum(formularioApi1.getNumeroLacunes() != null ? formularioApi1.getNumeroLacunes() : 0);
        dto.setSvdSimpleScore(formularioApi1.getPuntajeSvdSimple() != null ? formularioApi1.getPuntajeSvdSimple() : 0);
        dto.setCmbCount(formularioApi1.getConteoCmb() != null ? formularioApi1.getConteoCmb() : 0);

        return dto;
    }

    private int convertFazekasToInt(String categoriaFazekas) {
        if (categoriaFazekas == null) return 0;
        switch (categoriaFazekas.toLowerCase()) {
            case "leve": return 1;
            case "moderado":
            case "moderate": return 2;
            case "severo":
            case "severe": return 3;
            default: return 0;
        }
    }

    @Override
    public Optional<FormularioApi1> buscarPorId(Long id) {
        return formularioApi1Repository.findById(id);
    }

    @Override
    public List<FormularioApi1> listarTodos() {
        return formularioApi1Repository.findAll();
    }
}
