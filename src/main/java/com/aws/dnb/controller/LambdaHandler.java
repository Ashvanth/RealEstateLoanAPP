package com.aws.dnb.controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.aws.dnb.model.Arithmetic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class LambdaHandler implements RequestStreamHandler {


    private  CalculatorController calculatorController = new CalculatorController();



    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        String requestBody = readInputStream(inputStream);

        Arithmetic arithmetic = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            arithmetic = objectMapper.readValue(requestBody, Arithmetic.class);
            log.debug("----------------Value1------------"+arithmetic.getFirst_number());
            log.debug("----------------Value2------------"+arithmetic.getSecond_number());
            log.debug("----------------Operator------------"+arithmetic.getOperation());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String result = calculatorController.arthimaticOperation(arithmetic);

        String responseBody = "Value for the Provided arithmeticOperation -> "+arithmetic.getOperation()+" is = "+result;
        outputStream.write(responseBody.getBytes());

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
