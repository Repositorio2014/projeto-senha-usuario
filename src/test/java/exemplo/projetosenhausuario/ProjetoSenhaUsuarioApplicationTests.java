package exemplo.projetosenhausuario;

import exemplo.projetosenhausuario.controller.UsuarioController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProjetoSenhaUsuarioApplicationTests {

	@Autowired
	private MockMvc userMockMvc;

	@InjectMocks
	private UsuarioController usuarioController;

	@Test
	public void contextLoads() {
	}

	@Test
	public void listarTodos() throws Exception {
		userMockMvc.perform(MockMvcRequestBuilders.get("/api/usuario/listarTodos/").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
