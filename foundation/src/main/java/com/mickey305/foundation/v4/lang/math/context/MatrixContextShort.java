/*
 * Copyright (c) 2019. K.Misaki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mickey305.foundation.v4.lang.math.context;

import com.mickey305.foundation.v4.lang.math.factory.ElementInitializerFactory;
import com.mickey305.foundation.v4.lang.math.factory.OperationShortFactory;

public class MatrixContextShort extends MatrixContext<Short> {
  public MatrixContextShort() {
    super(
        OperationShortFactory.getInstance(),
        ElementInitializerFactory.shortIni());
  }
}