package com.aws.dnb.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.aws.dnb.controller.LoanController;
import com.aws.dnb.model.ApplicantInformation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
@Service
public class LambdaHandler implements RequestStreamHandler {


    private static LoanController loanController ;


    static {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.aws.dnb"); // Replace "com.aws.dnb" with the base package of your Spring components
        context.refresh();
        loanController = context.getBean(LoanController.class);
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        String requestBody = readInputStream(inputStream);

        ApplicantInformation applicantInformation = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            applicantInformation = objectMapper.readValue(requestBody, ApplicantInformation.class);
            log.debug("----------------Value1------------"+applicantInformation.getFullName());
            log.debug("----------------Value2------------"+applicantInformation.getCustomerSSN());
            log.debug("----------------Value3------------"+applicantInformation.getEquityAmount());
            log.debug("----------------Value3------------"+applicantInformation.getLoanAmount());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity responseEntity = loanController.storeApplicationInformation(applicantInformation);

       // String responseBody = "Value for the Provided arithmeticOperation -> "+arithmetic.getOperation()+" is = "+result;
        outputStream.write(responseEntity.getBody().toString().getBytes());

    }

    private String readInputStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            stringBuilder.append(new String(buffer, 0, bytesRead));
        }
        return stringBuilder.toString();
    }
}
