/*
 * ******************************************************************************
 * MontiCore Language Workbench
 * Copyright (c) 2015, MontiCore, All rights reserved.
 *
 * This project is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this project. If not, see <http://www.gnu.org/licenses/>.
 * ******************************************************************************
 */

package de.monticore.generating.templateengine.freemarker;

import java.io.File;
import java.io.IOException;

import de.se_rwth.commons.logging.Log;
import freemarker.cache.FileTemplateLoader;

public class MontiCoreFileTemplateLoader extends FileTemplateLoader {
  
  /**
   * A template loader that uses files in a specified directory as the source of templates.
   * 
   * @author Galina Volkova
   */
  public MontiCoreFileTemplateLoader(File baseDir) throws IOException {
    super(baseDir);
  }
  
  @Override
  public java.lang.Object findTemplateSource(String templateName) throws java.io.IOException {
    Log.debug("Looking for template " + templateName, MontiCoreFileTemplateLoader.class.getName());
    
    Object template = super.findTemplateSource(templateName.replace('.', '/').concat(
        FreeMarkerTemplateEngine.FM_FILE_EXTENSION));
    if (template == null) {
      if (templateName.endsWith(FreeMarkerTemplateEngine.FM_FILE_EXTENSION)) {
        template = super.findTemplateSource(templateName);
      }
      else {
        template = super.findTemplateSource(templateName
            .concat(FreeMarkerTemplateEngine.FM_FILE_EXTENSION));
      }
    }
    return template;
  }
  
}
