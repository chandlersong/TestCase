package me.chandlersong.webflux;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(WebFluxController.class)
public class MockMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WordService wordService;

   @Test
    public void testHelloWorld() throws Exception {

            doReturn("mock").when(wordService).printHello();

            this.mockMvc.perform(get("/normal/123")).andExpect(status().isOk())
                    .andExpect(content().string(containsString("mock")));

    }


    @Test
    public void testWebFlux() throws Exception {

        doReturn("mock").when(wordService).printHello();

        MvcResult mvcResult = this.mockMvc.perform(get("/mono/123"))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("mock")));

    }
}
