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

package de.monticore.generating;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;

import de.monticore.generating.templateengine.GlobalExtensionManagement;
import de.monticore.io.paths.IterablePath;

/**
 * Setup for generator (see {@link GeneratorEngine}).
 *
 * @author (last commit) $Author$
 * @version $Revision$, $Date$
 */
public class GeneratorSetup {
  
  private final File outputDirectory;
  
  private ClassLoader classLoader;
  
  private GlobalExtensionManagement glex;
  
  /**
   * The path for the handwritten code 
   */
  private IterablePath handcodedPath;
  
  /**
   * Additional path as the source of templates
   */
  private List<File> additionalTemplatePaths = new ArrayList<>();
  
  /**
   * Defines if tracing infos are added to the result as comments
   */
  private boolean tracing = true;
  
  /**
   * The characters for the start of a comment. Usually same as the target
   * language.
   */
  private Optional<String> commentStart = Optional.empty();
  
  /**
   * The characters for the end of a comment. Usually same as the target
   * language.
   */
  private Optional<String> commentEnd = Optional.empty();
  
  public GeneratorSetup(File outputDirectory) {
    this.outputDirectory = outputDirectory;
    this.classLoader = getClass().getClassLoader();
  }
  
  public File getOutputDirectory() {
    return outputDirectory;
  }
  
  public void setClassLoader(ClassLoader classLoader) {
    this.classLoader = classLoader;
  }
  
  public ClassLoader getClassLoader() {
    return classLoader;
  }
  
  public void setGlex(GlobalExtensionManagement glex) {
    this.glex = glex;
  }
  
  public Optional<GlobalExtensionManagement> getGlex() {
    return Optional.ofNullable(glex);
  }
  
  public void setAdditionalTemplatePaths(List<File> additionalTemplatePaths) {
    this.additionalTemplatePaths = new ArrayList<>(additionalTemplatePaths);
  }
  
  public List<File> getAdditionalTemplatePaths() {
    return ImmutableList.copyOf(additionalTemplatePaths);
  }
  
  /**
   * @return targetPath
   */
  public IterablePath getHandcodedPath() {
    return this.handcodedPath;
  }

  /**
   * @param hwcpath the handcoded path to set
   */
  public void setHandcodedPath(IterablePath hwcPath) {
    this.handcodedPath = hwcPath;
  }
  
  /**
   * @param tracing defines if tracing infos are added to the result as
   * comments.
   */
  public void setTracing(boolean tracing) {
    this.tracing = tracing;
  }
  
  /**
   * @return true, if tracing infos are added to the result as comments.
   */
  public boolean isTracing() {
    return tracing;
  }
  
  /**
   * @return the characters for the start of a comment. Usually same as the
   * target language.
   */
  public Optional<String> getCommentStart() {
    return commentStart;
  }
  
  /**
   * @param commentStart the characters for the start of a comment. Usually same
   * as the target language.
   */
  public void setCommentStart(Optional<String> commentStart) {
    this.commentStart = commentStart;
  }
  
  /**
   * @return the characters for the end of a comment. Usually same as the target
   * language.
   */
  public Optional<String> getCommentEnd() {
    return commentEnd;
  }
  
  /**
   * @param commentEnd the characters for the end of a comment. Usually same as
   * the target language.
   */
  public void setCommentEnd(Optional<String> commentEnd) {
    this.commentEnd = commentEnd;
  }
}
