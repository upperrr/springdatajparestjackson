package th.com.proj.springdatajparestjackson.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.com.proj.springdatajparestjackson.entity.Singers;
import th.com.proj.springdatajparestjackson.service.SingersService;

import java.util.List;

@RestController
@RequestMapping("/rest/api/singers")
@RequiredArgsConstructor
public class SingersController {

    private final SingersService singersService;
    //create
    @PostMapping(value="/save")
    public ResponseEntity<String> save(@RequestBody Singers singers) {
        ResponseEntity<String> responseEntity = null;
        try {
            Integer integer = singersService.saveSingers(singers); // singer '1' created
            responseEntity = new ResponseEntity<String>("Singer '" + integer + "' created", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return responseEntity;
    }


    //update
    @PutMapping(value="/update")
    public ResponseEntity<String> update(@RequestBody Singers singers) {
        ResponseEntity<String> responseEntity = null;
        boolean available = singersService.isAvailable(singers.getSingersPosition());
        if(available) {
            singersService.update(singers);
            responseEntity = new ResponseEntity<String>("updated successfully", HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<String>("Record '"+singers.getSingersPosition()+"' not found", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }


    //delete
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) { //TODO PathVariable
        ResponseEntity responseEntity = null;
        boolean availableSinger = singersService.isAvailable(id);
        if(availableSinger) {
            singersService.delete(id);
            responseEntity = new ResponseEntity<String>("Deleted"+id+"successfully", HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<String>(id+"Not Exist", HttpStatus.OK);
        }
        return responseEntity;
    }


    // Retrieve/Fetch
    @GetMapping(value = "/getSingleSinger/{id}")
    public ResponseEntity getSingleSingerById(@PathVariable Integer id) { //single obj return --> <String> 뺌
        ResponseEntity responseEntity = null;
        if(singersService.isAvailable(id)) {
            Singers oneSinger =  singersService.getOneSinger(id);
            return responseEntity = new ResponseEntity<Singers>(oneSinger, HttpStatus.OK);
        } else {
            return new ResponseEntity("Record Not Found", HttpStatus.OK);
        }
//        return responseEntity;
    }


    //Fetch operations
    //produding both values
    @GetMapping(value = "/getAllSingers", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getAllSingers() {
        ResponseEntity responseEntity = null;
        List<Singers> allSingers = singersService.getAllSingers();
        if(allSingers == null || allSingers.isEmpty()) {
            String message = "No Data Found";
            responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
        } else{
            responseEntity = new ResponseEntity<List<Singers>>(allSingers, HttpStatus.OK); //List of Singers
        }
        return responseEntity;
    }

}
