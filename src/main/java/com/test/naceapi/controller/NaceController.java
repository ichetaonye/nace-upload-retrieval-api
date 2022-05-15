package com.test.naceapi.controller;

import com.test.naceapi.domain.NaceService;
import com.test.naceapi.domain.model.ResponseMessage;
import com.test.naceapi.domain.util.CSVHelper;
import com.test.naceapi.repository.NaceRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/v1.0/nace")
public class NaceController {

    @Autowired
    private NaceRepository naceRepository;

    @Autowired
    private CSVHelper csvHelper;

    @Autowired
    private NaceService naceService;


    @PostMapping("/upload")
    @ApiOperation(value = "uploadNaceFile", notes = "This API uploads a Nace details from a csv file")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful",
                    response = ResponseMessage.class, responseContainer = "List")})
    public ResponseEntity<ResponseMessage> uploadNaceFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.status(HttpStatus.OK).body(naceService.save(file));
    }




    @GetMapping(produces = "application/json")
    @ApiOperation(value = "getNaceDetails", notes = "This API retrieves a Nace record based on the order id provided")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful",
                    examples = @Example(value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value =
                    "{\"Order\" :  \"string\", \"Description\": \"string\", \"Rulings\": \"string\", \"Parent\": \"string\", \"Reference to ISIC Rev. 4\": \"string\", " +
                            "\"This item excludes\": \"string\", \"This item also excludes\": \"string\", \"This item includes\": \"string\",\"Level\": \"string\", \"Code\": \"string\"}"))),
            @ApiResponse(code = 204, message = "", responseContainer = "Map"),
    })
    public ResponseEntity<Object> getNaceDetails(@RequestParam("orderId") String orderId) {
        return new ResponseEntity<>(naceService.getNaceDetails(orderId).toString(), HttpStatus.OK);
    }
}
