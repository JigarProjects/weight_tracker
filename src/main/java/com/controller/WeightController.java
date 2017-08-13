package com.controller;

import com.model.Weight;
import com.service.WeightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jigar on 8/12/2017.
 */
@RestController
public class WeightController {

    static WeightService weightService;
    static{
        weightService = WeightService.getInstance();
    }

    @RequestMapping(value="/weight", method= RequestMethod.POST)
    public ResponseEntity<Void> addWeight(@RequestBody Weight weight, HttpServletRequest request) {
        Weight weight2 = weightService.create(weight, request.getRemoteAddr());

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
