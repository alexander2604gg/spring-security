package com.alexandersaul.authService.repository;

import com.alexandersaul.authService.model.FormularioApi1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularioApi1Repository extends JpaRepository<FormularioApi1, Long> {
}
