package tn.esprit.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.backend.entity.Entreprise;
import tn.esprit.backend.service.IEntrepriseService;

import java.util.Arrays;
import tools.jackson.databind.ObjectMapper;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EntrepriseController.class)
class EntrepriseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IEntrepriseService entrepriseService;

    @Test
    void testAddEntreprise() throws Exception {
        Entreprise entreprise = new Entreprise();

        when(entrepriseService.addEntreprise(any(Entreprise.class)))
                .thenReturn(entreprise);

        mockMvc.perform(post("/entreprise/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entreprise)))
                .andExpect(status().isOk());

        verify(entrepriseService).addEntreprise(any(Entreprise.class));
    }

    @Test
    void testUpdateEntreprise() throws Exception {
        Entreprise entreprise = new Entreprise();

        when(entrepriseService.updateEntreprise(any(Entreprise.class)))
                .thenReturn(entreprise);

        mockMvc.perform(put("/entreprise/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entreprise)))
                .andExpect(status().isOk());

        verify(entrepriseService).updateEntreprise(any(Entreprise.class));
    }

    @Test
    void testDeleteEntreprise() throws Exception {
        doNothing().when(entrepriseService).deleteEntreprise(1L);

        mockMvc.perform(delete("/entreprise/delete/1"))
                .andExpect(status().isOk());

        verify(entrepriseService).deleteEntreprise(1L);
    }

    @Test
    void testGetEntrepriseById() throws Exception {
        Entreprise entreprise = new Entreprise();

        when(entrepriseService.getEntrepriseById(1L))
                .thenReturn(entreprise);

        mockMvc.perform(get("/entreprise/get/1"))
                .andExpect(status().isOk());

        verify(entrepriseService).getEntrepriseById(1L);
    }

    @Test
    void testGetAllEntreprises() throws Exception {
        when(entrepriseService.getAllEntreprises())
                .thenReturn(Arrays.asList(new Entreprise(), new Entreprise()));

        mockMvc.perform(get("/entreprise/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(entrepriseService).getAllEntreprises();
    }
}
