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
package com.github.liaochong.myexcel.core.converter.reader;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 时间戳读取转换器
 *
 * @author liaochong
 * @version 1.0
 */
public class TimestampReadConverter extends AbstractReadConverter<Timestamp> {

    @Override
    protected Timestamp doConvert(String v, Field field) {
        if (isNumber(v)) {
            final long time = Long.parseLong(v);
            return new Timestamp(time);
        }
        String dateFormatPattern = getDateFormatPattern(field);
        SimpleDateFormat sdf = SIMPLE_DATE_FORMAT_WEAK_CACHE.get(dateFormatPattern);
        if (sdf == null) {
            sdf = new SimpleDateFormat(dateFormatPattern);
            SIMPLE_DATE_FORMAT_WEAK_CACHE.cache(dateFormatPattern, sdf);
        }
        try {
            return new Timestamp(sdf.parse(v).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
