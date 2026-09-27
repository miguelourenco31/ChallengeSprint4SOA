package sistema_veiculos;

import tools.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class VeiculoControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;
    private String token;

    @BeforeEach
    void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

        String username = "usuario_" + UUID.randomUUID();
        String password = "123456";

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of(
                                "username", username,
                                "password", password
                        ))))
                .andExpect(status().isOk());

        String response = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of(
                                "username", username,
                                "password", password
                        ))))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        token = objectMapper.readTree(response).get("token").asString();
    }

    @Test
    void deveListarVeiculosComTokenValido() throws Exception {
        mockMvc.perform(get("/veiculos")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void deveRecusarAcessoSemToken() throws Exception {
        mockMvc.perform(get("/veiculos"))
                .andExpect(status().isForbidden());
    }

    @Test
    void deveCriarVeiculoComSucesso() throws Exception {
        mockMvc.perform(post("/veiculos")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of(
                                "modelo", "Civic",
                                "marca", "Honda",
                                "placa", "ABC1234"
                        ))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelo").value("Civic"));
    }

    @Test
    void deveRetornar404AoAtualizarVeiculoInexistente() throws Exception {
        mockMvc.perform(put("/veiculos/999999")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of(
                                "modelo", "Civic",
                                "marca", "Honda",
                                "placa", "ABC1234"
                        ))))
                .andExpect(status().isNotFound());
    }
}