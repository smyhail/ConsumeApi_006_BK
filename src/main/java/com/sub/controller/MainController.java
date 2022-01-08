package com.sub.controller;

import com.sub.DemoApplication;
import com.sub.entity.reguest.Reguest;
import com.sub.entity.respone.Response;
import com.sub.service.ResponseService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
    @Autowired
    private RestTemplate restTemplate;
    private final ResponseService responseService;
    private String date;

    @GetMapping("/responses")
    public List<Response> getResponses(){
        return responseService.getAllResponse();
    }
    @GetMapping("/responses/{id}")
    public Response getSingleResponse(Long id){
        return responseService.getSingleResponse(id);
    }

    @PostMapping("/responses/single2")
    public void addSingleResponse2(@RequestParam String account , @RequestParam(required = false) String localDate  ){

        if( localDate != null ){
            date = localDate;
        }else {
            date =  LocalDate.now().toString();
            log.info(date);
        }
        try {

            Reguest reguest = restTemplate.getForObject(
                    "https://wl-api.mf.gov.pl/api/search/bank-account/"+ account +"?date="+ date, Reguest.class);
            Response response = new Response();
            response.setNumberChecked(account);
            response.setName(reguest.getResult().getSubjects().get(0).getName());
            responseService.addResponse(response);

        }catch (Exception e){
            log.error(String.valueOf(e));
        }

    }

    @PostMapping("/responses/single")
    public Response addSingleResponse(@RequestParam String account , @RequestParam(required = false) String localDate  ){

        if( localDate != null ){
            date = localDate;
        }else {
            date =  LocalDate.now().toString();
            log.info(date);
        }

        Reguest reguest = restTemplate.getForObject(
                "https://wl-api.mf.gov.pl/api/search/bank-account/"+ account +"?date="+ date, Reguest.class);
        Response response = new Response();
        response.setNumberChecked(account);
        response.setName(reguest.getResult().getSubjects().get(0).getName());
        return responseService.addResponse(response);
    }

    @PostMapping("/responses/Multiple2")
    public void addMultipleResponse2(@RequestParam String[] accounts, @RequestParam(required = false) String localDate ){

        if( localDate != null ){
            date = localDate;
        }else {
            date =  LocalDate.now().toString();
            log.info(date);
        }

        try {

            for (String account : accounts) {
                Reguest reguest = restTemplate.getForObject(
                        "https://wl-api.mf.gov.pl/api/search/bank-account/"+ accounts+"?date="+ date, Reguest.class);
                Response response = new Response();
                response.setNumberChecked(account);
                response.setName(reguest.getResult().getSubjects().get(0).getName());
                responseService.addResponse(response);
                log.info("added: " + account);
            }


        }catch (Exception e){
            log.error(String.valueOf(e));
        }
    }

        @PostMapping("/responses/multiple")
    public Response addMultipleResponse(@RequestParam String[] accounts, @RequestParam(required = false) String localDate ){
        if( localDate != null ){
            date = localDate;
        }else {
            date =  LocalDate.now().toString();
            log.info(date);
        }

        for (String account : accounts) {
            Reguest reguest = restTemplate.getForObject(
                    "https://wl-api.mf.gov.pl/api/search/bank-account/"+ accounts+"?date="+ date, Reguest.class);
            Response response = new Response();
            response.setNumberChecked(account);
            response.setName(reguest.getResult().getSubjects().get(0).getName());
            responseService.addResponse(response);
        }

        for (int i = 0; i < accounts.length; i++) {

        }
        return null;
    }

    @DeleteMapping("/responses")
    public void deleteAll(){
        responseService.deleteAllResponse();
    }
}
