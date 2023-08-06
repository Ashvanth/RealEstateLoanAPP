package com.aws.dnb.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws.dnb.controller.ApplicationController;
import com.aws.dnb.model.ApplicantInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Slf4j
public class LambdaHandler2 implements RequestHandler<Map<String, Object>, String> {

    private static final String BASE_PACKAGE = "com.aws.dnb";

    private final ApplicationController applicationController;

    public LambdaHandler2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan(BASE_PACKAGE);
        context.refresh();
        applicationController = context.getBean(ApplicationController.class);
    }

    @Override
    public String handleRequest(Map<String, Object> input, Context context) {
        log.info("Input Map values: {}", input);

        if (!input.isEmpty()) {
            log.info("Into POST");

            Integer SSN = (Integer) input.get("customerSSN");
            String fullName = (String) input.get("fullName");
            Integer loanAmount = (Integer) input.get("loanAmount");
            Integer salaryAmount = (Integer) input.get("salaryAmount");
            Integer equityAmount = (Integer) input.get("equityAmount");

            if (SSN != null && fullName != null && loanAmount != null && salaryAmount != null && equityAmount != null) {
                try {
                    ResponseEntity responseEntity = applicationController.submitApplicationInformation(
                            ApplicantInformation.builder()
                                    .fullName(fullName)
                                    .customerSSN(Long.valueOf(SSN))
                                    .salaryAmount(Long.valueOf(salaryAmount))
                                    .equityAmount(Long.valueOf(equityAmount))
                                    .loanAmount(Long.valueOf(loanAmount))
                                    .build()
                    );
                    return responseEntity.getBody().toString();
                } catch (Exception e) {
                    throw new RuntimeException("Error processing POST request", e);
                }
            } else {
                throw new IllegalArgumentException("One or more required input fields are missing.");
            }
        } else {
            log.info("Into GET");
            ResponseEntity responseEntity = applicationController.fetchApplications();
            return responseEntity.getBody().toString();
        }
    }
}
