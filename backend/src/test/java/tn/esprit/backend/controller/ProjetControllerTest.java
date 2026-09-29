
package tn.esprit.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.backend.entity.Projet;
import tn.esprit.backend.service.IProjetService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjetController.class)
class ProjetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IProjetService projetService;

    @Test
    void testAddProjet() throws Exception {
        Projet projet = new Projet();

        when(projetService.addProjet(any(Projet.class)))
                .thenReturn(projet);

        mockMvc.perform(post("/projet/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projet)))
                .andExpect(status().isOk());

        verify(projetService).addProjet(any(Projet.class));
    }

    @Test
    void testUpdateProjet() throws Exception {
        Projet projet = new Projet();

        when(projetService.updateProjet(any(Projet.class)))
                .thenReturn(projet);

        mockMvc.perform(put("/projet/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projet)))
                .andExpect(status().isOk());

        verify(projetService).updateProjet(any(Projet.class));
    }

    @Test
    void testDeleteProjet() throws Exception {
        doNothing().when(projetService).deleteProjet(1L);

        mockMvc.perform(delete("/projet/delete/1"))
                .andExpect(status().isOk());

        verify(projetService).deleteProjet(1L);
    }

    @Test
    void testGetProjetById() throws Exception {
        Projet projet = new Projet();

        when(projetService.getProjetById(1L))
                .thenReturn(projet);

        mockMvc.perform(get("/projet/get/1"))
                .andExpect(status().isOk());

        verify(projetService).getProjetById(1L);
    }

    @Test
    void testGetAllProjets() throws Exception {
        when(projetService.getAllProjets())
                .thenReturn(Arrays.asList(new Projet(), new Projet()));

        mockMvc.perform(get("/projet/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(projetService).getAllProjets();
    }
}
