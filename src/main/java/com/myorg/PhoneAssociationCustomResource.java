package com.myorg;

import java.util.Map;

import software.amazon.awscdk.customresources.AwsCustomResource;
import software.amazon.awscdk.customresources.AwsCustomResourcePolicy;
import software.amazon.awscdk.customresources.AwsSdkCall;
import software.amazon.awscdk.customresources.PhysicalResourceId;
import software.amazon.awscdk.customresources.PhysicalResourceIdReference;
import software.amazon.awscdk.customresources.SdkCallsPolicyOptions;
import software.amazon.awscdk.services.connect.CfnContactFlow;
import software.amazon.awscdk.services.connect.CfnInstance;
import software.amazon.awscdk.services.connect.CfnPhoneNumber;
import software.constructs.Construct;

public class PhoneAssociationCustomResource {
    
    public PhoneAssociationCustomResource(Construct scope, String id, CfnInstance cfnInstance, CfnContactFlow cfnContactFlow, CfnPhoneNumber cfnPhoneNumber){
        build(scope, id, cfnInstance,cfnContactFlow,cfnPhoneNumber);
    }

    private void build(Construct scope, String id, CfnInstance cfnInstance,CfnContactFlow cfnContactFlow, CfnPhoneNumber cfnPhoneNumber){
        AwsCustomResource awsCustom = AwsCustomResource.Builder.create(scope, id)
         .onCreate(AwsSdkCall.builder()
                 .service("Connect")
                 .action("associatePhoneNumberContactFlow")
                 .parameters(Map.of(
                    "ContactFlowId",cfnContactFlow.getRef(),
                    "InstanceId", cfnInstance.getAtt("Id"),
                    "PhoneNumberId", cfnPhoneNumber.getRef()))
                 .physicalResourceId(PhysicalResourceId.of(id))
                 .build())
         .policy(AwsCustomResourcePolicy.fromSdkCalls(SdkCallsPolicyOptions.builder()
                 .resources(AwsCustomResourcePolicy.ANY_RESOURCE)
                 .build()))
         //.installLatestAwsSdk(false)
         .build();
    }
}
