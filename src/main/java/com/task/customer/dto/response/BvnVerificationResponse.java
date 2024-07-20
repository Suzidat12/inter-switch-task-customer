package com.task.customer.dto.response;import com.fasterxml.jackson.annotation.JsonIgnoreProperties;import lombok.AllArgsConstructor;import lombok.Builder;import lombok.Data;import lombok.NoArgsConstructor;/** * @author Shalom Olomolaiye * @philosophy It's not "CODE" It's how you think... Think and solve the problem * Growth is when you keep finding efficient ways to solve the problem * <p> * ------ * Tip: Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live. * 'Cause he is violent,and he knows where you live. * ------ * Anyway, welcome to the BvnValidationResponse class... HAPPY THINKING * @since 07/09/2022 5:48 PM */@Data@Builder@AllArgsConstructor@NoArgsConstructor@JsonIgnoreProperties(ignoreUnknown = true)public class BvnVerificationResponse {    private boolean verificationStatus;    private String nin;    private String dateOfBirth;    private String gender;    private String firstName;    private String lastName;    private String middleName;    private String mobileNumber;}