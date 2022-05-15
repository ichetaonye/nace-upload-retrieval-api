package com.test.naceapi.controller;

import com.test.naceapi.domain.NaceService;
import com.test.naceapi.domain.model.ResponseMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import java.util.LinkedHashMap;
import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NaceControllerTest {


    @Autowired
    private NaceService naceService;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    @Test
    public void shouldUploadFile() {

        ClassPathResource resource = new ClassPathResource("Nacetest.csv");

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("file", resource);
        ResponseEntity<String> response = this.restTemplate.postForEntity("/v1.0/nace/upload", map,
                String.class);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody().compareToIgnoreCase("Uploaded the file successfully: Nacetest.csv"));
    }

    @Test
    public void should_Get_An_Uploaded_File_By_Id() throws Exception{
        ClassPathResource resource = new ClassPathResource("Nacetest.csv");

        MultipartFile multipartFileToSend = new MockMultipartFile("file", resource.getFile().getName(), "text/csv", resource.getInputStream());

        ResponseMessage responseFromUpload = naceService.save(multipartFileToSend);
        assertThat(responseFromUpload).isNotNull();
        assertThat(responseFromUpload.getMessage()).isEqualTo("Uploaded the file successfully: Nacetest.csv");
        ResponseEntity<Object> response = this.restTemplate.getForEntity("/v1.0/nace?order=398481",
                Object.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        LinkedHashMap<String, Object> retrievedNaceDetails = (LinkedHashMap)response.getBody();
        assertThat(retrievedNaceDetails.get("Order")).isEqualTo("398481");
    }

    @Test
    public void should_Return_No_Content_If_Order_Doesnt_Exist() {

        ResponseEntity<Object> response = this.restTemplate.getForEntity("/v1.0/nace?order=38333",
                Object.class);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.NO_CONTENT);
    }


}
