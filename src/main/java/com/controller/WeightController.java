package com.controller;

import com.model.Weight;
import com.service.MorphiaService;
import com.service.WeightService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;
import static org.springframework.format.annotation.DateTimeFormat.ISO.NONE;

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
        System.out.println("adding weight "+weight+" "+weight.getTimeStamp());
        weightService.save(weight);
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
