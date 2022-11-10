package com.myorg;

import software.amazon.awscdk.services.connect.CfnInstance;
import software.amazon.awscdk.services.connect.CfnInstance.AttributesProperty;
import software.constructs.Construct;

public class SampleConnectInstance {

    public CfnInstance connect = null;

    public SampleConnectInstance(Construct scope, String id) {
        build(scope, id);
    }

    private void build(Construct scope, String id) {
        connect = CfnInstance.Builder.create(scope, id)
                // contact instance attribute.
                .attributes(AttributesProperty.builder()
                        .inboundCalls(true)
                        .outboundCalls(true)
                        // the properties below are optional
                        .autoResolveBestVoices(true)
                        .contactflowLogs(true)
                        .contactLens(true)
                        .earlyMedia(true)
                        .useCustomTtsVoices(false)
                        .build())
                .identityManagementType("CONNECT_MANAGED")
                .instanceAlias(id + "Alias")
                .build();
    }

    public CfnInstance getConnectInstance() {
        return connect;
    }
}
