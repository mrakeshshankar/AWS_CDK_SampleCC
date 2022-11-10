package com.myorg;

import java.util.Map;

import com.amazonaws.services.connect.model.QueueSearchCriteria;
import com.amazonaws.services.connect.model.StringCondition;
import com.amazonaws.services.gamelift.model.ComparisonOperatorType;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.CustomResource;
import software.amazon.awscdk.CustomResourceProviderProps;
import software.amazon.awscdk.customresources.AwsCustomResource;
import software.amazon.awscdk.customresources.AwsCustomResourcePolicy;
import software.amazon.awscdk.customresources.AwsSdkCall;
import software.amazon.awscdk.customresources.PhysicalResourceId;
import software.amazon.awscdk.customresources.Provider;
import software.amazon.awscdk.customresources.SdkCallsPolicyOptions;
import software.amazon.awscdk.services.connect.CfnHoursOfOperation;
import software.amazon.awscdk.services.connect.CfnInstance;
import software.constructs.Construct;

public class ListQueueCustomResource {

    private String queueID = null;
    
    public String getQueueID() {
        return queueID;
    }

    public ListQueueCustomResource (Construct scope, String id, CfnInstance cfnInstance, CfnHoursOfOperation hoursOfOperations) {
        build(scope, id, cfnInstance, hoursOfOperations);
    }

    private void build (Construct scope, String id, CfnInstance cfnInstance, CfnHoursOfOperation hoursOfOperations) {
        // AwsCustomResource awsCustom = AwsCustomResource.Builder.create(scope, id)
        // .onCreate(AwsSdkCall.builder()
        //         .service("Connect")
        //         .action("createQueue")
        //         .parameters(Map.of(
        //            "HoursOfOperationId",hoursOfOperations.getRef(),
        //            "InstanceId", cfnInstance.getAtt("Id"),
        //            "Name", "ReservationQueue"))
        //         .physicalResourceId(PhysicalResourceId.of(id))
        //         .build())
        // .policy(AwsCustomResourcePolicy.fromSdkCalls(SdkCallsPolicyOptions.builder()
        //         .resources(AwsCustomResourcePolicy.ANY_RESOURCE)
        //         .build()))
        // .installLatestAwsSdk(false)
        // .build();

        // String status = awsCustom.getResponseField("Status");
        // if(status.equalsIgnoreCase("SUCCESS")){
        //     queueID = awsCustom.getResponseFieldReference("QueueId").toString();

        //     CfnOutput.Builder.create(scope, "queueId")
        //                         .value(queueID).build();
        // }else {
        //     // get the details of the queue.
        //     listQueueDetails(scope, id, cfnInstance);
        // }
        
        //listQueueDetails(scope, id, cfnInstance);
        searchQueue(scope, id, cfnInstance);
    }

    private void listQueueDetails(Construct scope, String id, CfnInstance cfnInstance) {

        AwsCustomResource awsCustom = AwsCustomResource.Builder.create(scope, id+"list")
        .onCreate(AwsSdkCall.builder()
                .service("Connect")
                .action("listQueues")
                .parameters(Map.of(
                   "InstanceId", (null==cfnInstance? "eed0b07a-134b-43f1-9e66-f4e231bb40ff":cfnInstance.getAtt("Id"))
                   ))
                .physicalResourceId(PhysicalResourceId.of(id+"list"))
                .build())
        .policy(AwsCustomResourcePolicy.fromSdkCalls(SdkCallsPolicyOptions.builder()
                .resources(AwsCustomResourcePolicy.ANY_RESOURCE)
                .build()))
        .installLatestAwsSdk(false)
        .build();

        // String res = awsCustom.getResponseField("Queues.0.QueueArn");
        // CfnOutput.Builder.create(scope, "Queues.0.QueueArn")
        //                         .value(res).build();
    }

    private void searchQueue(Construct scope, String id, CfnInstance cfnInstance) {

        //CustomResource
        //Provider

        QueueSearchCriteria qsc = new QueueSearchCriteria();
        StringCondition sc = new StringCondition();
        sc.setComparisonType("EXACT");
        sc.setFieldName("Name");
        sc.setValue("ReservationQueue");
        qsc.setStringCondition(sc);


        AwsCustomResource awsCustom = AwsCustomResource.Builder.create(scope, id+"search")
        .onCreate(AwsSdkCall.builder()
                .service("Connect")
                .action("searchQueues")
                .parameters(Map.of(
                   "InstanceId", (null==cfnInstance? "eed0b07a-134b-43f1-9e66-f4e231bb40ff":cfnInstance.getAtt("Id")),
                   "SearchCriteria", qsc
                   ))
                .physicalResourceId(PhysicalResourceId.of(id+"search"))
                .build())
        .policy(AwsCustomResourcePolicy.fromSdkCalls(SdkCallsPolicyOptions.builder()
                .resources(AwsCustomResourcePolicy.ANY_RESOURCE)
                .build()))
        .installLatestAwsSdk(false)
        .build();

        // String res = awsCustom.getResponseField("Queues.0.QueueArn");
        // CfnOutput.Builder.create(scope, "Queues.0.QueueArn")
        //                         .value(res).build();
    }


}
