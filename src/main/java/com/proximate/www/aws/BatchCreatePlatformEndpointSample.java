/*
 * Copyright 2013 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

/*
 * OpenCSV is licensed under Apache 2.0. Please see more details at 
 * http://opencsv.sourceforge.net/#using-commercially
 */

package com.proximate.www.aws;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.proximate.www.pushmate.model.Token;

public class BatchCreatePlatformEndpointSample {

    static final int MALFORMED_PROPERTIES_ERROR_CODE = 1;
    static final int CREDENTIAL_RETRIEVAL_FAILURE_ERROR_CODE = 2;
    static final int FILE_ACCESS_FAILURE_ERROR_CODE = 3;
    static final int NOT_FOUND_ERROR_CODE = 4;

    /*
     * The properties files
     */
    static final String AWSCREDENTIALSPROPERTIES_FILE     = "AwsCredentials.properties";

    static final List<String> listOfRegions = Collections
            .unmodifiableList(new ArrayList<String>() {
                private static final long serialVersionUID = 1L;

                {
                    add("us-east-1");
                    add("us-west-1");
                    add("us-west-2");
                    add("sa-east-1");
                    add("eu-west-1");
                    add("ap-southeast-1");
                    add("ap-southeast-2");
                    add("ap-northeast-1");
                    add("us-gov-west-1");
                }
            });

    
    public BatchCreatePlatformEndpointSample() {
    } 
    
    public String desinscribir(String suscriptionArn, String endpointArn, String appArn, String access, String secret) {
    	String salida = "";
        if (endpointArn != null && !endpointArn.equals("") &&
        		suscriptionArn != null && !suscriptionArn.equals("")) {
            CreateEndpointJob worker = new CreateEndpointJob(access, secret);             
            salida = worker.unsuscribe(suscriptionArn, endpointArn, appArn);
        } else {
            System.err
                    .println("Token nulo");
        }
        return salida;   	
    }
    
    public Token suscribir(String token, String topicARN, String appARN, String access, String secret) {
        Token salida = null;        
        try {
    		System.out.println("read: " + token);
            if (token != null && !token.equals("")) {
                CreateEndpointJob worker = new CreateEndpointJob(access, secret);                    
                salida = worker.suscribe(token, "", appARN, topicARN);
            } else {
                System.err
                        .println("Token nulo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salida;
    }
  
}