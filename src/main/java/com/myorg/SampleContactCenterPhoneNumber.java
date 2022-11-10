package com.myorg;

import java.util.List;

import software.amazon.awscdk.CfnTag;
import software.amazon.awscdk.services.connect.CfnInstance;
import software.amazon.awscdk.services.connect.CfnPhoneNumber;
import software.constructs.Construct;

public class SampleContactCenterPhoneNumber {

    public CfnPhoneNumber cfnPhoneNumber = null;
    
    public SampleContactCenterPhoneNumber (Construct scope, String id, CfnInstance cfnInstance){
        build(scope, id, cfnInstance);
    }

    private void build(Construct scope, String id, CfnInstance cfnInstance){
        cfnPhoneNumber = CfnPhoneNumber.Builder.create(scope, id)
         .countryCode("US")
         .targetArn(cfnInstance.getAtt("Arn").toString())
         .type("TOLL_FREE")
         // the properties below are optional
         .description("Sample Contact Center Phone number")
         //.prefix("prefix")
         .tags(List.of(CfnTag.builder()
                 .key("ContactCenterName")
                 .value("Sample")
                 .build()))
         .build();
    }

    public CfnPhoneNumber getInstance(){
        return cfnPhoneNumber;
    }
}
