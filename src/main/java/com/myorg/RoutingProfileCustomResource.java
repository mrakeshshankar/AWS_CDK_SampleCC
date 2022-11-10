package com.myorg;

import java.util.List;
import java.util.Map;

import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.customresources.AwsCustomResource;
import software.amazon.awscdk.customresources.AwsCustomResourcePolicy;
import software.amazon.awscdk.customresources.AwsSdkCall;
import software.amazon.awscdk.customresources.PhysicalResourceId;
import software.amazon.awscdk.customresources.SdkCallsPolicyOptions;
import software.amazon.awscdk.services.connect.CfnInstance;
import software.constructs.Construct;

public class RoutingProfileCustomResource {
    
    public RoutingProfileCustomResource (Construct scope, String id, CfnInstance cfnInstance, String  queueId) {
        build(scope, id, cfnInstance, queueId);
    }

    private void build (Construct scope, String id, CfnInstance cfnInstance, String  queueId) {
        AwsCustomResource awsCustom = AwsCustomResource.Builder.create(scope, id)
        .onCreate(AwsSdkCall.builder()
                .service("Connect")
                .action("createRoutingProfile")
                //.outputPaths(List.of("RoutingProfileId"))
                .parameters(Map.of(
                   "DefaultOutboundQueueId",queueId,
                   "InstanceId", cfnInstance.getAtt("Id"),
                   "Description", "CustomRoutingProfile",
                   "MediaConcurrencies", List.of(new MediaConcurrency("VOICE", 1), new MediaConcurrency("CHAT", 1)),
                   "Name", "CustomRoutingProfile"))
                .physicalResourceId(PhysicalResourceId.of(id))
                .build())
        .policy(AwsCustomResourcePolicy.fromSdkCalls(SdkCallsPolicyOptions.builder()
                .resources(AwsCustomResourcePolicy.ANY_RESOURCE)
                .build()))
        //.installLatestAwsSdk(false)
        .build();

        String routeId = awsCustom.getResponseField("RoutingProfileId");

        CfnOutput.Builder.create(scope, "routeId")
                                .value(routeId).build();

    }

}
