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

package de.monticore.symboltable.types;/*
 * Copyright (c) 2015 RWTH Aachen. All rights reserved.
 *
 * http://www.se-rwth.de/
 */

import de.monticore.symboltable.Symbol;
import de.monticore.symboltable.types.references.JTypeReference;

/**
 * @author Pedram Mir Seyed Nazari
 *
 */
// TODO PN rename to JFieldSymbol
public interface JAttributeSymbol extends Symbol {

  JAttributeSymbolKind KIND = new JAttributeSymbolKind();

  JTypeReference<? extends JTypeSymbol> getType();

  boolean isStatic();

  boolean isFinal();

  boolean isParameter();

  boolean isPrivate();

  boolean isProtected();

  boolean isPublic();

}
