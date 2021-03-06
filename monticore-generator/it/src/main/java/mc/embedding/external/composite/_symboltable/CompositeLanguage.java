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

package mc.embedding.external.composite._symboltable;

import java.util.Optional;

import de.monticore.EmbeddingModelingLanguage;
import de.monticore.ast.ASTNode;
import de.monticore.modelloader.ModelingLanguageModelLoader;
import de.monticore.symboltable.MutableScope;
import de.monticore.symboltable.ResolverConfiguration;
import mc.embedding.external.composite._parser.CompositeParser;
import mc.embedding.external.embedded._symboltable.EmbeddedLanguage;
import mc.embedding.external.host._symboltable.HostLanguage;

public class CompositeLanguage extends EmbeddingModelingLanguage {

  public static final String FILE_ENDING = HostLanguage.FILE_ENDING;

  public CompositeLanguage() {
    super("Composite Language", FILE_ENDING, new HostLanguage(), new EmbeddedLanguage());

    modelLoader =  provideModelLoader();
    addResolver(new Text2ContentResolvingFilter());
  }

  @Override public CompositeParser getParser() {
    return new CompositeParser();
  }

  @Override public Optional<CompositeSymbolTableCreator> getSymbolTableCreator(
      ResolverConfiguration resolverConfiguration, MutableScope enclosingScope) {
    return Optional.of(new CompositeSymbolTableCreator(resolverConfiguration, enclosingScope));
  }

  @Override protected ModelingLanguageModelLoader<? extends ASTNode> provideModelLoader() {
    return new CompositeModelLoader(this);
  }
}
