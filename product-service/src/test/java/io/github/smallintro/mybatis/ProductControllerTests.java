package io.github.smallintro.mybatis;

import io.github.smallintro.mybatis.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
class ProductControllerTests extends ProductServiceApplicationTests {

    @Test
    public void testController_addProduct() throws Exception {
        productService.removeProducts(Arrays.asList(new Integer[]{101}));
        String jsonRequest = objectMapper.writeValueAsString(buildProduct(101));
        RequestBuilder requestBuilder = post("/save")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andDo(print()).andReturn();
        String responseText = response.getResponse().getContentAsString();
        int status = response.getResponse().getStatus();
        assertThat(responseText).isEqualTo(String.format("%d product added with productId: %d", 1, 101));
        assertThat(status).isEqualTo(201);
    }

    @Test
    public void testController_updateProduct() throws Exception {
        productService.addProduct(buildProduct(102));
        String jsonRequest = objectMapper.writeValueAsString(buildProduct(102));
        RequestBuilder requestBuilder = put("/update/{id}", 102)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        String responseText = response.getResponse().getContentAsString();
        int status = response.getResponse().getStatus();
        assertThat(responseText).isEqualTo(String.format("%d product updated with productId: %s", 1, 102));
        assertThat(status).isEqualTo(200);
    }

    @Test
    public void testController_removeProducts() throws Exception {
        productService.addProduct(buildProduct(104));
        List<Integer> productIds = Arrays.asList(new Integer[]{104});
        RequestBuilder requestBuilder = delete("/delete")
                .content(String.valueOf(productIds))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        String responseText = response.getResponse().getContentAsString();
        int status = response.getResponse().getStatus();
        assertThat(responseText).isEqualTo(String.format("%d product deleted with productId: [%d]", 1, 104));
        assertThat(status).isEqualTo(200);
    }

    @Test
    public void testController_getProductById() throws Exception {
        productService.addProduct(buildProduct(105));
        RequestBuilder requestBuilder = get("/get/{id}", 105)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

        String responseText = response.getResponse().getContentAsString();
        int status = response.getResponse().getStatus();
        String jsonResponse = objectMapper.writeValueAsString(buildProduct(105));
        assertThat(responseText).isEqualTo(jsonResponse);
        assertThat(status).isEqualTo(200);
    }

    @Test
    public void testController_getProductByName() throws Exception {
        productService.addProduct(buildProduct(106));
        List<Product> products = Arrays.asList(new Product[]{buildProduct(106)});
        RequestBuilder requestBuilder = post("/get/{name}", "Test106")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        String responseText = response.getResponse().getContentAsString();
        int status = response.getResponse().getStatus();

        String jsonResponse = objectMapper.writeValueAsString(products);
        assertThat(responseText).isEqualTo(jsonResponse);
        assertThat(status).isEqualTo(200);
    }

}
