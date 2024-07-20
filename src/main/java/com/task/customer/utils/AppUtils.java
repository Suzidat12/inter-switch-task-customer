package com.task.customer.utils;

import com.task.customer.dto.response.BvnVerificationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class AppUtils {


    public String generateAccountNumber(){
        Random random = new Random();
        int digit = 1000000000 + random.nextInt(900000000);
        return String.valueOf(digit);
    }

    public BvnVerificationResponse verifyBvn1(String bvn) {
        // Your existing implementation to verify the BVN
        String BVN_VERIFICATION_URL = "https://api.credodemo.com/core/bvn/validate/{bvn}";

//        // Replace {bvn} with the actual BVN number to be verified
//        String url = BVN_VERIFICATION_URL.replace("{bvn}", bvn);
//
//        // Get access token
//        String accessToken = getAccessToken();
//        if (Objects.isNull(accessToken)) {
//            log.info("Authorization Failed");
//            // Return null or throw an exception depending on your error handling strategy
//            return null;
//        }
//
//        // Create headers with Bearer token and content type
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(accessToken);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        log.info(accessToken + " accessToken");
//        log.info(url + " url");
//
//        try {
//            ResponseEntity<String> responseEntity = restTemplate.exchange(
//                    url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
//
//            // Check if the response status is successful
//            if (responseEntity.getStatusCode() == HttpStatus.OK) {
//                String responseBody = responseEntity.getBody();
//                log.info("Response: " + responseBody);
//                log.info("Bvn Verified");
//
//                // Parse the response body and extract the necessary information
//                ObjectMapper mapper = new ObjectMapper();
//                JsonNode responseJson = mapper.readTree(responseBody);
//
//                // Check if the response contains the expected fields
//                if (responseJson.has("status") && responseJson.has("data")) {
//                    // Extract the verification status and NIN from the response
//                    boolean verificationStatus = responseJson.get("status").asInt() == 200;
//                    String nin = responseJson.get("data").get("nin").asText();
//                    String dateOfBirth = responseJson.get("data").get("dateOfBirth").asText();
//                    String gender = responseJson.get("data").get("gender").asText();
//                    String firstName = responseJson.get("data").get("firstName").asText();
//                    String lastName = responseJson.get("data").get("lastName").asText();
//                    String middleName = responseJson.get("data").get("middleName").asText();
//
//                    // Create and return the BvnVerificationResponse object
//                    return new BvnVerificationResponse(verificationStatus, nin,dateOfBirth,gender,firstName,lastName,middleName);
//                } else {
//                    log.error("Unexpected response format: " + responseBody);
//                    // Return null or throw an exception depending on your error handling strategy
//                    return null;
//                }
//            } else {
//                log.info("Failed to verify BVN. Status code: "
//                        + responseEntity.getStatusCode() + " " + responseEntity.getBody());
//                // Return null or throw an exception depending on your error handling strategy
//                return null;
//            }
//        } catch (RestClientException | JsonProcessingException ex) {
//            log.error(ex.getLocalizedMessage(), ex);
//            // Return null or throw an exception depending on your error handling strategy
//            return null;
//        }
        return null;
    }


}
