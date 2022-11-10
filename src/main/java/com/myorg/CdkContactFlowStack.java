package com.myorg;

import java.io.IOException;

import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.connect.CfnContactFlow;
import software.amazon.awscdk.services.connect.CfnInstance;
import software.amazon.awscdk.services.connect.CfnPhoneNumber;
import software.constructs.Construct;;

public class CdkContactFlowStack extends Stack {

        private CfnInstance connect = null;

        public CdkContactFlowStack(final Construct parent, final String id) throws IOException {
                this(parent, id, null);
        }

        public CdkContactFlowStack(final Construct parent, final String id, final StackProps props) throws IOException {
                super(parent, id, props);

                // connect = ((CdkContactFlowStackProps)props).getConnect();
                // Add Connect flow2 to the intance
                //CfnContactFlow cfnContactFlow1 = new SampleConnectFlow(this, "SampleConnectFlowLatest1", connect, "src/resources/flows/Test.json").getInstance();

                // Claim a phone number2
                // CfnPhoneNumber phoneNumber1 = new SampleContactCenterPhoneNumber(this, "SampleContactCenterPhoneNumber1",
                //                  connect).getInstance();


                // Associate Phone number with the Sample flow 2
                // new PhoneAssociationCustomResource(this, "PhoneAssociationCustomResource1",
                // connect, cfnContactFlow1, phoneNumber1);

                //CfnOutput.Builder.create(this,
                // "ConnectInstanceUrl").value(connect.getAtt("Url").toString()).build();


                
                new ListQueueCustomResource(this, "ListQueueCustomResource", null, null);
                

                // CfnOutput.Builder.create(this, "phoneNumberOutput")
                //                 .value(phoneNumber1.getAtt("Address").toString()).build();
                
        }

}
