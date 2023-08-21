import com.polarbookshop.catalogservice.controllers.BookController
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

@AutoConfigureMockMvc
@SpringBootTest(classes = [BookController::class]) // Specify the configuration class
class BookControllerMvcTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `test getBook endpoint`() {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk)
            .andExpect(content().string("Welcome to the book catalog!"))
    }
}
