package com.controller;

import com.model.Weight;
import com.service.MorphiaService;
import com.service.WeightService;
import org.mongodb.morphia.Key;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jigar on 8/12/2017.
 */
@RestController
public class WeightController {

    static WeightService weightService;

    static{
        weightService = new WeightService(Weight.class, new MorphiaService().getMetricStore());
    }

    @RequestMapping(value="/weight", method= RequestMethod.POST)
    public ResponseEntity<Void> addWeight(@RequestBody Weight weight){
        Weight weight2 = weightService.create(weight);
        return new ResponseEntity<Void>( HttpStatus.OK);
    }

    @RequestMapping(value="/weight", method = RequestMethod.GET)
    public ResponseEntity<List<Weight>> listAllWeight( @RequestParam(value = "from", required = false) String from ,  @RequestParam(value="to", required = false) String to){
        List<Weight> weights = weightService.getWeight(from, to);
        if(weights.isEmpty()){
            return new ResponseEntity<List<Weight>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Weight>>(weights, HttpStatus.OK);
    }


}
