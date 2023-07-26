/*
 * Copyright 2023 EPAM Systems.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.digital.data.platform.el.juel.it.digsignature;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

import java.io.IOException;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests;
import org.junit.jupiter.api.Test;

public class SignatureContentJuelFunctionIT extends AbstractSignatureJuelFunctionIT {

  @Test
  @Deployment(resources = "bpmn/signature_content.bpmn")
  void shouldReturnContentFromSignature() throws IOException {
    mockConnectToKeycloak();
    digitalSignatureService.addStubMapping(
        stubFor(post(urlPathMatching("/api/esignature/content"))
            .withRequestBody(equalTo("{\"data\":\"dGVzdERhdGE=\",\"container\":\"ASIC\"}"))
            .willReturn(aResponse().withStatus(200)
                .withHeader("Content-type", "application/json")
                .withBody("{\"content\":["
                    + "{\"data\":\"dGVzdC50eHQ=\",\"name\":\"test.txt\"},"
                    + "{\"data\":\"dGVzdDIudHh0\",\"name\":\"test2.txt\"}]}"))));

    var processInstance = runtimeService().startProcessInstanceByKey("signature_content");

    BpmnAwareTests.assertThat(processInstance).isEnded();
  }
}
