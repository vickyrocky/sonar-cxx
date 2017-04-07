/*
 * Sonar C++ Plugin (Community)
 * Copyright (C) 2010-2017 SonarOpenCommunity
 * http://github.com/SonarOpenCommunity/sonar-cxx
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.cxx.sensors.compiler;

import org.sonar.cxx.sensors.compiler.CxxCompilerVcRuleRepository;
import org.sonar.cxx.sensors.compiler.CxxCompilerGccRuleRepository;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.sonar.api.platform.ServerFileSystem;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;
import org.sonar.cxx.CxxLanguage;
import org.sonar.cxx.sensors.utils.TestUtils;

public class CxxCompilerRuleRepositoryTest {

  @Test
  public void createVcRulesTest() {
    CxxLanguage language = TestUtils.mockCxxLanguage();
    
    CxxCompilerVcRuleRepository def = new CxxCompilerVcRuleRepository(
      mock(ServerFileSystem.class),
      new RulesDefinitionXmlLoader(), language);

    RulesDefinition.Context context = new RulesDefinition.Context();
    def.define(context);

    RulesDefinition.Repository repo = context.repository(CxxCompilerVcRuleRepository.KEY);
    assertThat(repo.rules()).hasSize(732);
  }

  @Test
  public void createGccRulesTest() {
    CxxLanguage language = TestUtils.mockCxxLanguage();
    
    CxxCompilerGccRuleRepository def = new CxxCompilerGccRuleRepository(
      mock(ServerFileSystem.class),
      new RulesDefinitionXmlLoader(), language);

    RulesDefinition.Context context = new RulesDefinition.Context();
    def.define(context);

    RulesDefinition.Repository repo = context.repository(CxxCompilerGccRuleRepository.KEY);
    assertThat(repo.rules()).hasSize(160);
  }
}