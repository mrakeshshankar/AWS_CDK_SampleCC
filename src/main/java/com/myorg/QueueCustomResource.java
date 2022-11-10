package com.myorg;

import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.customresources.AwsCustomResource;
import software.amazon.awscdk.customresources.AwsCustomResourcePolicy;
import software.amazon.awscdk.customresources.AwsSdkCall;
import software.amazon.awscdk.customresources.PhysicalResourceId;
import software.amazon.awscdk.customresources.SdkCallsPolicyOptions;
import software.amazon.awscdk.services.connect.CfnHoursOfOperation;
import software.amazon.awscdk.services.connect.CfnInstance;
import software.constructs.Construct;

public class QueueCustomResource {

    private String queueID = null;
    private String queueArn = null;
    
    public String getQueueArn() {
        return queueArn;
    }

    public String getQueueID() {
        return queueID;
    }

    public QueueCustomResource (Construct scope, String id, CfnInstance cfnInstance, CfnHoursOfOperation hoursOfOperations) {
        build(scope, id, cfnInstance, hoursOfOperations);
    }

    private void build (Construct scope, String id, CfnInstance cfnInstance, CfnHoursOfOperation hoursOfOperations) {
        AwsCustomResource awsCustom = AwsCustomResource.Builder.create(scope, id)
        .onCreate(AwsSdkCall.builder()
                .service("Connect")
                .action("createQueue")
                .parameters(Map.of(
                   "HoursOfOperationId",hoursOfOperations.getRef(),
                   "InstanceId", cfnInstance.getAtt("Id"),
                   "Name", "ReservationQueue"))
                .physicalResourceId(PhysicalResourceId.of(id))
                .build())
        .policy(AwsCustomResourcePolicy.fromSdkCalls(SdkCallsPolicyOptions.builder()
                .resources(AwsCustomResourcePolicy.ANY_RESOURCE)
                .build()))
        //.installLatestAwsSdk(false)
        .build();

        // String status = awsCustom.getResponseField("Status");

        queueID = awsCustom.getResponseFieldReference("QueueId").toString();
            queueArn = awsCustom.getResponseFieldReference("QueueArn").toString();

            CfnOutput.Builder.create(scope, "queueId")
                                .value(queueID).build();
            CfnOutput.Builder.create(scope, "queueArn")
            .value(queueArn).build();

            // CfnOutput.Builder.create(scope, "queueStatus")
            // .value(status).build();

        // if(status.equalsIgnoreCase("SUCCESS")){
        //     queueID = awsCustom.getResponseFieldReference("QueueId").toString();
        //     queueArn = awsCustom.getResponseFieldReference("QueueARN").toString();

        //     CfnOutput.Builder.create(scope, "queueId")
        //                         .value(queueID).build();
        //     CfnOutput.Builder.create(scope, "queueARN")
        //     .value(queueArn).build();
        // }else {
        //     // get the details of the queue.
        //     listQueueDetails(scope, id, cfnInstance);
        // }
        
    }

    // private void listQueueDetails(Construct scope, String id, CfnInstance cfnInstance) {
    //     AwsCustomResource awsCustom = AwsCustomResource.Builder.create(scope, id+"list")
    //     .onCreate(AwsSdkCall.builder()
    //             .service("Connect")
    //             .action("listQueues")
    //             .parameters(Map.of(
    //                "InstanceId", cfnInstance.getAtt("Id")
    //                ))
    //             .physicalResourceId(PhysicalResourceId.of(id+"list"))
    //             .build())
    //     .policy(AwsCustomResourcePolicy.fromSdkCalls(SdkCallsPolicyOptions.builder()
    //             .resources(AwsCustomResourcePolicy.ANY_RESOURCE)
    //             .build()))
    //     .installLatestAwsSdk(false)
    //     .build();

    //     // JsonArray res = (JsonArray) awsCustom.getResponseFieldReference("QueueSummaryList").toJSON();
    //     CfnOutput.Builder.create(scope, "ListqueueId")
    //                             .value(awsCustom.$jsii$toJson().toString()).build();
    // }


}
