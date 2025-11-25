package in.project.moneymanager;

// Add these imports
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.security.test.context.support.WithMockUser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc // <-- 1. Add this annotation
class MoneymanagerApplicationTests {

	@Autowired
	private MockMvc mockMvc; // <-- 2. Inject MockMvc

	@Test
	void contextLoads() {
	}

	// 3. Add this new test method
//	@Test
//	void testEndpointShouldReturnSuccessMessage() throws Exception {
//		this.mockMvc.perform(get("/test")) // Perform a GET request to /test
//				.andExpect(status().isOk()) // Expect HTTP 200 OK
//				.andExpect(content().string("Test successful")); // Expect the body to be "Test successful"
//	}

	@Test
	@WithMockUser // <--- Add this annotation
	void testEndpointShouldReturnSuccessMessage() throws Exception {
		this.mockMvc.perform(get("/test"))
				.andExpect(status().isOk())
				.andExpect(content().string("Test successful"));
	}

}