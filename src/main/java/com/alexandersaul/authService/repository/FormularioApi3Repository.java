package com.alexandersaul.authService.repository;

import com.alexandersaul.authService.model.FormularioApi3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularioApi3Repository extends JpaRepository<FormularioApi3, Long> {
}
