package com.mail.demo.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.stereotype.Component;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "email",
        "emailHeader",
        "emailBody"
})
@Component
public class EmailVo {

    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("emailHeader")
    private String emailHeader;
    @JsonProperty("emailBody")
    private String emailBody;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("emailHeader")
    public String getEmailHeader() {
        return emailHeader;
    }

    @JsonProperty("emailHeader")
    public void setEmailHeader(String emailHeader) {
        this.emailHeader = emailHeader;
    }

    @JsonProperty("emailBody")
    public String getEmailBody() {
        return emailBody;
    }

    @JsonProperty("emailBody")
    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "name = " + name + '\n' +
                "email = " + email + '\n' +
                "emailHeader = " + emailHeader + '\n' +
                "emailBody = " + emailBody + '\n' ;
    }
}

