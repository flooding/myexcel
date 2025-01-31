/*
 * Copyright 2019 liaochong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.liaochong.myexcel.core.parser;

/**
 * 内容类型枚举
 *
 * @author liaochong
 * @version 1.0
 */
public enum ContentTypeEnum {

    STRING,

    BOOLEAN,

    DOUBLE,

    DATE,

    DROP_DOWN_LIST,

    NUMBER_DROP_DOWN_LIST,

    BOOLEAN_DROP_DOWN_LIST;

    public static boolean isString(ContentTypeEnum contentTypeEnum) {
        return STRING.equals(contentTypeEnum);
    }

    public static boolean isBool(ContentTypeEnum contentTypeEnum) {
        return BOOLEAN.equals(contentTypeEnum);
    }

    public static boolean isDouble(ContentTypeEnum contentTypeEnum) {
        return DOUBLE.equals(contentTypeEnum);
    }

}
