package com.sub.service;

import com.sub.entity.respone.Response;

import java.util.List;

public interface ResponseService {


    List<Response> getAllResponse ();

    Response getSingleResponse(Long id);

    Response addResponse (Response response);

    void deleteAllResponse();

    void addresponse(Response response);


}
