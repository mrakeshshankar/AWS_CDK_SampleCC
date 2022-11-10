package com.myorg;

import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.connect.CfnInstance;

public class CdkContactFlowStackProps implements StackProps {
    
    private CfnInstance connect = null;

        public CfnInstance getConnect() {
                return this.connect;
        } 
    public CdkContactFlowStackProps(CfnInstance connect){
        this.connect = connect;
    }
}
