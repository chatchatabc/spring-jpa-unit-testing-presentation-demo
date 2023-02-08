package org.spring.jpa.user.application.web;

import org.junit.jupiter.api.Test;
import org.spring.jpa.user.SpringBaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@AutoConfigureMockMvc
class UserControllerTest extends SpringBaseTest {

    @Autowired
    private MockMvc mockMvc;


    /**
     * Test login page using Spring Test MockMVC
     *
     */
    @Test
    void testLoginPage() throws Exception {
        this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    void testDoLogin() throws Exception {
        this.mockMvc.perform(post("/login").param("email", "admin@email.com").param("password", "123"))
                .andExpect(view().name("homepage"));
        this.mockMvc.perform(post("/login").param("email", "admin@email.com").param("password", "1234"))
                .andExpect(view().name("login"));
    }

    @Test
    void testRegisterPage() throws Exception {
        this.mockMvc.perform(get("/register")).andDo(print()).andExpect(status().isOk());
    }
    @Test
    void testDoRegister() throws Exception {
        this.mockMvc.perform(post("/register").param("username", "admin").param("email", "admin@example.com").param("password", "123").param("matchingPassword", "123"))
                .andExpect(view().name("homepage"));
    }
}