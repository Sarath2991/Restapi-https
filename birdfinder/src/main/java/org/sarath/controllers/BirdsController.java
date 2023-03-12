package org.sarath.controllers;

import org.sarath.models.Birds;
import org.sarath.repository.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(path="/api")
public class BirdsController {

    @Autowired
    BirdRepository birdrepo;

    @GetMapping("/birds")
    public ResponseEntity<List<Birds>> getAllBirds(@RequestParam(required=false) String name){
        try{
            List<Birds> birds = new ArrayList<Birds>();

            if (name == null)
                birdrepo.findAll().forEach(birds::add);
            else
                birdrepo.findByName(name).forEach(birds::add);

            if (birds.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(birds,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/birds/{id}")
    public ResponseEntity<Birds> getBirdbyId(@PathVariable("id") long id){
        Optional<Birds> bird = birdrepo.findById(id);

        if (bird.isPresent()){
            return new ResponseEntity<>(bird.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/birds")
    public ResponseEntity<Birds> createBird(@RequestBody Birds inpbird){
        try{
            Birds bird = birdrepo.save(new Birds(inpbird.getId(),inpbird.getName(), inpbird.getLocation(), inpbird.getType()));
            return new ResponseEntity<>(bird,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
