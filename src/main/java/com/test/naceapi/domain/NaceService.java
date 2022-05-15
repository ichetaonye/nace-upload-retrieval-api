package com.test.naceapi.domain;

import com.test.naceapi.domain.Transaction.NaceEntity;
import com.test.naceapi.domain.model.ResponseMessage;
import com.test.naceapi.domain.util.CSVHelper;
import com.test.naceapi.exceptions.InvalidFileFormatException;
import com.test.naceapi.exceptions.OrderNotFoundException;
import com.test.naceapi.exceptions.TechnicalException;
import com.test.naceapi.repository.NaceRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service("naceService")
public class NaceService {

    private final NaceRepository naceRepository;

    private final CSVHelper csvHelper;

    public NaceService(NaceRepository naceRepository, CSVHelper csvHelper) {
        this.naceRepository = naceRepository;
        this.csvHelper = csvHelper;
    }


    public ResponseMessage save(MultipartFile file) {
        try {
            if (csvHelper.hasCSVFormat(file)) {
                List<NaceEntity> tutorials = csvHelper.csvToNaceObject(file.getInputStream());
                naceRepository.saveAll(tutorials);
                return new ResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename());
            }
            throw new InvalidFileFormatException("Could not upload the file: " + file.getOriginalFilename() + "!");
        } catch (IOException ex) {
            throw new TechnicalException("fail to parse CSV file: " + ex.getMessage());
        }
    }

    public JSONObject   getNaceDetails(String orderId) {
        NaceEntity naceEntity = naceRepository.findByNaceOrder(orderId);
        if (naceEntity == null) {
            throw new OrderNotFoundException();
        }
        return naceEntity.translate();
    }
}
