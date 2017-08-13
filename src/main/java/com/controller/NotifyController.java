package com.controller;

import com.model.Notify;
import com.service.NotifyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Jigar on 8/13/2017.
 */
@RestController
public class NotifyController {
    static NotifyService notifyService;

    static {
        notifyService = NotifyService.getInstance();
    }

    @RequestMapping(value = "/notify", method = RequestMethod.GET)
    public ResponseEntity<List<Notify>> listAllNotification(@RequestParam(value = "from", required = false) String from, @RequestParam(value = "to", required = false) String to) {
        List<Notify> notifications = notifyService.getNotifications(from, to);
        if (notifications.isEmpty()) {
            return new ResponseEntity<List<Notify>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Notify>>(notifications, HttpStatus.OK);
    }
}
