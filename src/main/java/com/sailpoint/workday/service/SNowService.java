package com.sailpoint.workday.service;

import java.util.Optional;
import java.util.Random;

import com.sailpoint.workday.dto.SNowResponse;
import com.sailpoint.workday.repository.SNowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sailpoint.workday.entity.ServiceNow;

@Service
public class SNowService {
    private final String serviceNowPreId="RITM";
    private String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    @Autowired
    private SNowRepository serviceNowRepository;

    public SNowResponse createServiceNowInstance(ServiceNow serviceNow) {
        String serviceNowId;
        Optional<ServiceNow> checkExistingServiceNowRequest = serviceNowRepository.findById(serviceNow.getEmployeeId());
        if(checkExistingServiceNowRequest.isPresent()) {
            serviceNowId= checkExistingServiceNowRequest.get().getServicenowId();
            return new SNowResponse("Already exist",409,serviceNowId);
        }else {
            serviceNow.setServicenowId(GenerateServiceNowTicketNumber());
            serviceNowRepository.save(serviceNow);
            serviceNowId= serviceNow.getServicenowId();
            return new SNowResponse("Success",200,serviceNowId);
        }

    }
    private String GenerateServiceNowTicketNumber() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 7;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return serviceNowPreId+sb.toString();
    }
    public SNowResponse checkServiceNowInstance(String employeeId) {
        String serviceNowId=null;
        Optional<ServiceNow> checkExistingServiceNowRequest = serviceNowRepository.findById(employeeId);
        if(checkExistingServiceNowRequest.isPresent()) {
            serviceNowId=checkExistingServiceNowRequest.get().getServicenowId();
            return new SNowResponse("ServiceNow requestId exist",200,serviceNowId);
        }else {
            return new SNowResponse("ServiceNow requestId Not Found",404,serviceNowId);
        }

    }


}