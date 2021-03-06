// Copyright 2017 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * OfflineDataUploadServiceInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Mar 02, 2009 (07:08:06 PST) WSDL2Java emitter.
 */

package com.google.api.ads.adwords.axis.v201708.rm;

public interface OfflineDataUploadServiceInterface extends java.rmi.Remote {

    /**
     * Returns a list of OfflineDataUpload objects that match the
     * criteria specified in the selector.
     *         
     *         <p><b>Note:</b> If an upload fails after processing, reason
     * will be reported in {@link
     *         OfflineDataUpload#failureReason}.
     *         
     *         
     * @throws {@link ApiException} if problems occurred while retrieving
     * results.
     */
    public com.google.api.ads.adwords.axis.v201708.rm.OfflineDataUploadPage get(com.google.api.ads.adwords.axis.v201708.cm.Selector serviceSelector) throws java.rmi.RemoteException, com.google.api.ads.adwords.axis.v201708.cm.ApiException;

    /**
     * Uploads offline data for each entry in operations. Some operations
     * can fail for upload level
     *         errors like invalid {@code UploadMetadata}. Check {@code OfflineDataUploadReturnValue}
     * for
     *         partial failure list.
     *         
     *         <p><b>Note:</b> Operation might possibly succeed even with
     * errors in {@code OfflineData}. Data
     *         errors are reported in {@link OfflineDataUpload#partialDataErrors}
     * 
     *         <p><b>Note:</b> Supports only the {@code ADD} operator. ({@code
     * SET} and {@code REMOVE} are not
     *         supported.
     *         
     *         
     * @param operations A list of Offline data upload operations.
     *         
     * @return The list of offline data upload results in the same order
     * as operations.
     *         
     * @throws {@link ApiException} if problems occur.
     */
    public com.google.api.ads.adwords.axis.v201708.rm.OfflineDataUploadReturnValue mutate(com.google.api.ads.adwords.axis.v201708.rm.OfflineDataUploadOperation[] operations) throws java.rmi.RemoteException, com.google.api.ads.adwords.axis.v201708.cm.ApiException;
}
