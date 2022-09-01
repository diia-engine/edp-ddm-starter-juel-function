/*
 * Copyright 2021 EPAM Systems.
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

package com.epam.digital.data.platform.el.juel.plugin;

import com.epam.digital.data.platform.el.juel.mapper.CompositeApplicationContextAwareJuelFunctionMapper;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JuelJavaFunctionsProcessEnginePlugin extends AbstractProcessEnginePlugin {

  private final CompositeApplicationContextAwareJuelFunctionMapper compositeApplicationContextAwareJuelFunctionMapper;

  @Override
  public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    processEngineConfiguration.getExpressionManager()
        .addFunctionMapper(compositeApplicationContextAwareJuelFunctionMapper);
  }
}
