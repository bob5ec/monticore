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

import de.se_rwth.commons.logging.Log;
import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.Writer;

/**
 * Handles exceptions occurring during the template processing.
 * 
 * @author Arne Haber
 * 
 */
public class MontiCoreTemplateExceptionHandler implements TemplateExceptionHandler {
  
  /**
   * Add an error, add a comment to the generated code and continue processing
   * the template skipping the erroneous expression.
   */
  public static final int LOG_AND_CONTINUE = 0;
  
  /**
   * Add an error, add a comment to the generated code and abort processing the
   * template .
   */
  public static final int LOG_AND_ABORT = 1;
  
  /**
   * Add an error and continue processing the template without adding a comment
   * to the generated code.
   */
  public static final int CONTINUE = 2;
  
  /**
   * Add an error and abort without adding a comment to the generated code.
   */
  public static final int ABORT = 3;
  
  /**
   * Throw the error without logging it.
   */
  public static final int THROW_ERROR = 4;
  
  private static final String COMMENT_START = "/* TODO: ";
  
  private static final String COMMENT_END = " */";
  
  /** The behavior from the handler. */
  protected final int behavior;
  
  /**
   * Creates a new {@link MontiCoreTemplateExceptionHandler} with the default
   * exception handling LOG_AND_CONTINUE.
   */
  public MontiCoreTemplateExceptionHandler() {
    behavior = LOG_AND_CONTINUE;
  }
  
  /**
   * Creates a new {@link MontiCoreTemplateExceptionHandler} with the given
   * exception handling behavior.
   * 
   * @param behavior use one of
   *          MontiCoreTemplateExceptionHandler.LOG_AND_CONTINUE,
   *          MontiCoreTemplateExceptionHandler.LOG_AND_ABORT,
   *          MontiCoreTemplateExceptionHandler.CONTINUE or
   *          MontiCoreTemplateExceptionHandler.ABORT
   */
  public MontiCoreTemplateExceptionHandler(int behavior) {
    if (behavior >= 0 && behavior <= 4) {
      this.behavior = behavior;
    }
    else {
      this.behavior = LOG_AND_CONTINUE;
    }
  }
  
  @Override
  public void handleTemplateException(TemplateException te, Environment env, Writer writer) throws TemplateException {
    switch (behavior) {
      case LOG_AND_ABORT:
        Log.error("0xA0354 " + te.getMessage());
        try {
          writer.append(COMMENT_START + te.getMessage() + COMMENT_END);
        }
        catch (IOException e) {
        }
        throw te;
      case CONTINUE:
        Log.error("0xA0355 " + te.getMessage());
        break;
      case ABORT:
        Log.error("0xA0356 " + te.getMessage());
        throw te;
      case THROW_ERROR:
        throw te;
      case LOG_AND_CONTINUE:

      default: // includes LOG_AND_CONTINUE
        Log.error(te.getMessage());
        try {
          writer.append(COMMENT_START + "0xA0357 " + te.getMessage() + COMMENT_END);
        }
        catch (IOException e) {
        }
        break;
    }
    
  }
  
}
