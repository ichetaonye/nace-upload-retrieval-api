package com.test.naceapi.domain;

import com.test.naceapi.domain.Transaction.NaceEntity;
import com.test.naceapi.domain.model.ResponseMessage;
import com.test.naceapi.domain.util.CSVHelper;
import com.test.naceapi.exceptions.InvalidFileFormatException;
import com.test.naceapi.repository.NaceRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class NaceServiceTest {

    @Mock
    private NaceRepository naceRepository;

    @Mock
    private CSVHelper csvHelper;

    @InjectMocks
    private NaceService naceService;


    @Test
    public void when_a_csv_file_is_uploaded_then_return_success() {
        MultipartFile file = new MockMultipartFile("foo", "foo.csv", "text/csv",
                "Assuming this is a valid csv".getBytes());

        Mockito.when(csvHelper.hasCSVFormat(file)).thenReturn(true);
        Mockito.when(naceRepository.saveAll(Mockito.any(List.class))).thenReturn(List.of(fetchAStubNaceEntity()));

        ResponseMessage message = naceService.save(file);

        assertThat(message.getMessage()).isEqualTo("Uploaded the file successfully: foo.csv");
    }

    @Test
    public void when_not_a_csv_file_throw_invalid_file_format() {
        MultipartFile file = new MockMultipartFile("foo", "foo.txt", "text",
                "Some text".getBytes());

        Mockito.when(csvHelper.hasCSVFormat(file)).thenReturn(false);
        assertThrows(InvalidFileFormatException.class, () -> {
            naceService.save(file);
        });
    }

    @Test
    public void when_get_nace_details_that_exist_return_the_object() {
        String order = "test";
        NaceEntity naceStub = fetchAStubNaceEntity();
        Mockito.when(naceRepository.findByNaceOrder(order)).thenReturn(naceStub);

        JSONObject message = naceService.getNaceDetails(order);

        assertThat(message).isNotNull();
        assertThat(message.optString("Order")).isEqualTo(naceStub.getNaceOrder());
        assertThat(message.optString("Level")).isEqualTo(naceStub.getLevel());
        assertThat(message.optString("Code")).isEqualTo(naceStub.getCode());
    }


    private NaceEntity fetchAStubNaceEntity() {
        return new NaceEntity("2345", "level 1", "code 1", "some description", "parent", "includes", "also includes", "ruling", "excludes", "reference");
    }

}
