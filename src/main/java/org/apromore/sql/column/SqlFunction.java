/*-
 * #%L
 * This file is part of "Apromore Enterprise Edition".
 * %%
 * Copyright (C) 2019 - 2022 Apromore Pty Ltd. All Rights Reserved.
 * %%
 * NOTICE:  All information contained herein is, and remains the
 * property of Apromore Pty Ltd and its suppliers, if any.
 * The intellectual and technical concepts contained herein are
 * proprietary to Apromore Pty Ltd and its suppliers and may
 * be covered by U.S. and Foreign Patents, patents in process,
 * and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this
 * material is strictly forbidden unless prior written permission
 * is obtained from Apromore Pty Ltd.
 * #L%
 */

package org.apromore.sql.column;

public enum SqlFunction {
    // Standard functions

    ABS("ABS"),
    APPROX_MOST_FREQUENT("approx_most_frequent"),
    APPROX_PERCENTILE("approx_percentile"),
    ARRAY_AGG("array_agg"),
    AVG("AVG"),
    CARDINALITY("cardinality"),
    CAST("CAST"),
    CEILING("CEILING"),
    COALESCE("coalesce"),
    CONCAT("CONCAT"),
    CONTAINS("CONTAINS"),
    COUNT("COUNT"),
    DATE_DIFF("date_diff"),
    DENSE_RANK("DENSE_RANK"),
    DISTINCT("DISTINCT"),
    ELEMENT_AT("element_at"),
    FIRST_VALUE("first_value"),
    FLOOR("floor"),
    FROM_BIG_ENDIAN_64("FROM_BIG_ENDIAN_64"),
    FROM_ISO8601_TIMESTAMP("from_iso8601_timestamp"),
    FROM_UNIX("from_unixtime"),
    GREATEST("GREATEST"),
    LAG("lag"),
    LEAD("lead"),
    LEAST("LEAST"),
    LENGTH("LENGTH"),
    LOG("LOG"),
    MAP("map"),
    MAP_KEYS("map_keys"),
    MAX("MAX"),
    MIN("MIN"),
    NTILE("NTILE"),
    NULLIF("NULLIF"),
    POWER("POWER"),
    QDIGEST_AGG("qdigest_agg"),
    RAND("RAND"),
    ROUND("ROUND"),
    ROW_NUMBER("ROW_NUMBER"),
    SEQUENCE("sequence"),
    STD_DEV("stddev"),
    STD_DEV_POP("stddev_pop"),
    SUM("SUM"),
    TO_UNIX("to_unixtime"),
    TRY("try"),
    TRY_CAST("try_cast"),
    UNNEST("unnest"),
    VALUES("VALUES"),
    VALUE_AT_QUANTILE("value_at_quantile"),
    VARIANCE("variance"),
    VAR_POP("var_pop"),
    WIDTH_BUCKET("width_bucket"),
    XXHASH64("XXHASH64"),

    // Custom functions

    GET_DURATION("getDuration");

    private final String functionName;
    private CastType castType;

    SqlFunction(String functionName) {
        this.functionName = functionName;
    }

    public SqlFunction withType(CastType castType) {
        this.castType = castType;
        return this;
    }

    public CastType getCastType() {
        return this.castType;
    }

    @Override
    public String toString() {
        return this.functionName;
    }
}