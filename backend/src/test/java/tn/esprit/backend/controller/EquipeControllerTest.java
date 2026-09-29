package tn.esprit.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.backend.entity.Equipe;
import tn.esprit.backend.service.IEquipeService;
import java.util.Arrays;
import tools.jackson.databind.ObjectMapper;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EquipeController.class)
class EquipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IEquipeService equipeService;

    @Test
    void testAddEquipe() throws Exception {
        Equipe equipe = new Equipe();

        when(equipeService.addEquipe(any(Equipe.class)))
                .thenReturn(equipe);

        mockMvc.perform(post("/equipe/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(equipe)))
                .andExpect(status().isOk());

        verify(equipeService).addEquipe(any(Equipe.class));
    }

    @Test
    void testUpdateEquipe() throws Exception {
        Equipe equipe = new Equipe();

        when(equipeService.updateEquipe(any(Equipe.class)))
                .thenReturn(equipe);

        mockMvc.perform(put("/equipe/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(equipe)))
                .andExpect(status().isOk());

        verify(equipeService).updateEquipe(any(Equipe.class));
    }

    @Test
    void testDeleteEquipe() throws Exception {
        doNothing().when(equipeService).deleteEquipe(1L);

        mockMvc.perform(delete("/equipe/delete/1"))
                .andExpect(status().isOk());

        verify(equipeService).deleteEquipe(1L);
    }

    @Test
    void testGetEquipeById() throws Exception {
        Equipe equipe = new Equipe();

        when(equipeService.getEquipeById(1L))
                .thenReturn(equipe);

        mockMvc.perform(get("/equipe/get/1"))
                .andExpect(status().isOk());

        verify(equipeService).getEquipeById(1L);
    }

    @Test
    void testGetAllEquipes() throws Exception {
        when(equipeService.getAllEquipes())
                .thenReturn(Arrays.asList(new Equipe(), new Equipe()));

        mockMvc.perform(get("/equipe/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(equipeService).getAllEquipes();
    }

    @Test
    void testGetEquipesByEntreprise() throws Exception {
        when(equipeService.getEquipesByEntreprise(1L))
                .thenReturn(Arrays.asList(new Equipe()));

        mockMvc.perform(get("/equipe/by-entreprise/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(equipeService).getEquipesByEntreprise(1L);
    }

    @Test
    void testAssignEquipeToEntreprise() throws Exception {
        Equipe equipe = new Equipe();

        when(equipeService.assignEquipeToEntreprise(1L, 2L))
                .thenReturn(equipe);

        mockMvc.perform(put("/equipe/assign-entreprise/1/2"))
                .andExpect(status().isOk());

        verify(equipeService).assignEquipeToEntreprise(1L, 2L);
    }

    @Test
    void testAssignEquipeToProjet() throws Exception {
        Equipe equipe = new Equipe();

        when(equipeService.assignEquipeToProjet(1L, 2L))
                .thenReturn(equipe);

        mockMvc.perform(put("/equipe/assign-projet/1/2"))
                .andExpect(status().isOk());

        verify(equipeService).assignEquipeToProjet(1L, 2L);
    }
}
