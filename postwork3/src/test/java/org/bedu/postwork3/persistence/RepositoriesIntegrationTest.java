package org.bedu.postwork3.persistence;


import org.bedu.postwork3.model.Curso;
import org.bedu.postwork3.model.Estudiante;
import org.bedu.postwork3.model.Materia;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan(basePackages = "org.bedu.postwork.javase2project")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RepositoriesIntegrationTest {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @BeforeAll
    void cleanDatabases(){
        cursoRepository.deleteAll();
        materiaRepository.deleteAll();
        estudianteRepository.deleteAll();
    }

    @Test
    @DisplayName("Test de inserciones")
    void smokeTest(){
        Estudiante estudiante = new Estudiante();
        estudiante.setNombreCompleto("Estudiante 2023");
        estudianteRepository.save(estudiante);

        Materia materia = new Materia();
        materia.setNombre("Programacion funcional");
        materiaRepository.save(materia);

        Curso curso = new Curso();
        curso.setCiclo("2030");
        curso.setMateria(materia);
        Map<Estudiante, Integer> calificaciones = new HashMap<>();
        calificaciones.put(estudiante, 90);
        curso.setCalificaciones(calificaciones);
        cursoRepository.save(curso);

        assertNotNull(estudiante.getId());
        assertNotNull(materia.getId());
        assertNotNull(curso.getId());
    }

}