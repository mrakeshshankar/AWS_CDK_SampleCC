package com.myorg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.amazonaws.services.alexaforbusiness.model.Content;

import software.amazon.awscdk.CfnTag;
import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.services.connect.CfnContactFlow;
import software.amazon.awscdk.services.connect.CfnInstance;
import software.constructs.Construct;

public class SampleConnectFlow {

    public CfnContactFlow cfnContactFlow = null;

    public SampleConnectFlow(Construct scope, String id, CfnInstance cfnInstance, String filePath, String queueArn) throws IOException {
        build(scope, id, cfnInstance, filePath, queueArn);
    }

    private void build(Construct scope, String id, CfnInstance cfnInstance, String filePath, String queueArn) throws IOException {

        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        content = content.replace("{QueueARN}",queueArn);
        cfnContactFlow = CfnContactFlow.Builder.create(scope, id)
                .content(content)
                //.content({"Version":"2019-10-30","StartAction":"d3954c2f-a5f3-479d-9d70-cb3b99d6321f","Metadata":{"entryPointPosition":{"x":20,"y":20},"snapToGrid":false,"ActionMetadata":{"d3954c2f-a5f3-479d-9d70-cb3b99d6321f":{"position":{"x":237,"y":139},"useDynamic":false},"373ccdf1-10d1-4a2f-804c-a8fc8cf26135":{"position":{"x":540,"y":283}}}},"Actions":[{"Identifier":"d3954c2f-a5f3-479d-9d70-cb3b99d6321f","Parameters":{"Text":"Hi Welcome"},"Transitions":{"NextAction":"373ccdf1-10d1-4a2f-804c-a8fc8cf26135","Errors":[{"NextAction":"373ccdf1-10d1-4a2f-804c-a8fc8cf26135","ErrorType":"NoMatchingError"}],"Conditions":[]},"Type":"MessageParticipant"},{"Identifier":"373ccdf1-10d1-4a2f-804c-a8fc8cf26135","Type":"DisconnectParticipant","Parameters":{},"Transitions":{}}]}')
                .instanceArn(cfnInstance.getAtt("Arn").toString())
                .name(id)
                //.type("CUSTOMER_QUEUE")
                .type("CONTACT_FLOW")
                // the properties below are optional
                .description("Sample Contact Center flow")
                // .state("state")
                .tags(List.of(CfnTag.builder()
                        .key("ContactCenterName")
                        .value("Sample")
                        .build()))
                .build();

                cfnContactFlow.applyRemovalPolicy(RemovalPolicy.DESTROY, null);
    }

    public CfnContactFlow getInstance() {
        return cfnContactFlow;
    }
}
