package tn.esprit.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.backend.entity.ProjetDetaille;
import tn.esprit.backend.service.IProjetDetailleService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import tools.jackson.databind.ObjectMapper;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
@WebMvcTest(ProjetDetailleController.class)
class ProjetDetailleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IProjetDetailleService projetDetailleService;

    @Test
    void testAddProjetDetaille() throws Exception {
        ProjetDetaille projetDetaille = new ProjetDetaille();

        when(projetDetailleService.addProjetDetaille(any(ProjetDetaille.class)))
                .thenReturn(projetDetaille);

        mockMvc.perform(post("/projet-detaille/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projetDetaille)))
                .andExpect(status().isOk());

        verify(projetDetailleService).addProjetDetaille(any(ProjetDetaille.class));
    }

    @Test
    void testUpdateProjetDetaille() throws Exception {
        ProjetDetaille projetDetaille = new ProjetDetaille();

        when(projetDetailleService.updateProjetDetaille(any(ProjetDetaille.class)))
                .thenReturn(projetDetaille);

        mockMvc.perform(put("/projet-detaille/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projetDetaille)))
                .andExpect(status().isOk());

        verify(projetDetailleService).updateProjetDetaille(any(ProjetDetaille.class));
    }

    @Test
    void testDeleteProjetDetaille() throws Exception {
        doNothing().when(projetDetailleService).deleteProjetDetaille(1L);

        mockMvc.perform(delete("/projet-detaille/delete/1"))
                .andExpect(status().isOk());

        verify(projetDetailleService).deleteProjetDetaille(1L);
    }

    @Test
    void testGetProjetDetailleById() throws Exception {
        ProjetDetaille projetDetaille = new ProjetDetaille();

        when(projetDetailleService.getProjetDetailleById(1L))
                .thenReturn(projetDetaille);

        mockMvc.perform(get("/projet-detaille/get/1"))
                .andExpect(status().isOk());

        verify(projetDetailleService).getProjetDetailleById(1L);
    }

    @Test
    void testGetAllProjetsDetailles() throws Exception {
        when(projetDetailleService.getAllProjetsDetailles())
                .thenReturn(Arrays.asList(
                        new ProjetDetaille(),
                        new ProjetDetaille()
                ));

        mockMvc.perform(get("/projet-detaille/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(projetDetailleService).getAllProjetsDetailles();
    }

    @Test
    void testGetProjetDetaillesByProjet() throws Exception {
        when(projetDetailleService.getProjetDetaillesByProjet(1L))
                .thenReturn(Arrays.asList(new ProjetDetaille()));

        mockMvc.perform(get("/projet-detaille/by-projet/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(projetDetailleService).getProjetDetaillesByProjet(1L);
    }

    @Test
    void testAssignProjetDetailleToProjet() throws Exception {
        ProjetDetaille projetDetaille = new ProjetDetaille();

        when(projetDetailleService.assignProjetDetailleToProjet(1L, 2L))
                .thenReturn(projetDetaille);

        mockMvc.perform(put("/projet-detaille/assign-projet/1/2"))
                .andExpect(status().isOk());

        verify(projetDetailleService)
                .assignProjetDetailleToProjet(1L, 2L);
    }
}
