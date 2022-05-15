package com.test.naceapi.domain.util;

import com.test.naceapi.domain.Transaction.NaceEntity;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class CSVHelperTest {

    private CSVHelper csvHelper = new CSVHelper();

    @Test
    public void when_uploading_csv_file_return_true(){
        MultipartFile file = new MockMultipartFile("foo", "foo.csv", "text/csv",
                "Assuming this is a valid csv".getBytes());
       assertTrue(csvHelper.hasCSVFormat(file));
    }

    @Test
    public void when_uploading_non_csv_file_return_false(){
        MultipartFile file = new MockMultipartFile("foo", "foo.txt", "text",
                "Assuming this is a text fine".getBytes());
        assertFalse(csvHelper.hasCSVFormat(file));
    }

    @Test
    public void when_uploading_csv_file_fetch_Content() throws Exception{
            String test = "\"Order\",\"Level\",\"Code\",\"Parent\",\"Description\",\"This item includes\",\"This item also includes\",\"Rulings\",\"This item excludes\",\"Reference to ISIC Rev. 4\"\n" +
                    "\"398481\",\"1\",\"A\",,\"AGRICULTURE, FORESTRY AND FISHING\",\"This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats.\",,,,\"A\"";
        MultipartFile file = new MockMultipartFile("foo", "foo.txt", "text",
                test.getBytes());
      List<NaceEntity> retrievedNaceDetails =  csvHelper.csvToNaceObject(file.getInputStream());
        assertNotNull(retrievedNaceDetails);
        assertEquals(1, retrievedNaceDetails.size());
        assertEquals("398481", retrievedNaceDetails.get(0).getNaceOrder());
    }
}