package com.test.naceapi.domain.util;

import com.test.naceapi.domain.transaction.NaceEntity;
import com.test.naceapi.exceptions.TechnicalException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVHelper {

    public CSVHelper(){

    }

    public  String TYPE = "text/csv";

    public boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public List<NaceEntity> csvToNaceObject(InputStream is){
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            List<NaceEntity> naceEntities = new ArrayList<NaceEntity>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                NaceEntity naceEntity = new NaceEntity(
                        csvRecord.get("Order"),
                        csvRecord.get("Level"),
                        csvRecord.get("Code"),
                        csvRecord.get("Parent"),
                        csvRecord.get("Description"),
                        csvRecord.get("This item includes"),
                        csvRecord.get("This item also includes"),
                        csvRecord.get("Rulings"),
                        csvRecord.get("This item excludes"),
                        csvRecord.get("Reference to ISIC Rev. 4")
                );
                naceEntities.add(naceEntity);
            }
            return naceEntities;
        } catch (IOException e) {
            throw new TechnicalException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
