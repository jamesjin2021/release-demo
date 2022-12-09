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

public enum CastType {
    TANGO("TANGO"),
    BOOLEAN("boolean"),
    TINYINT("tinyInt"),
    SMALLINT("smallInt"),
    INTEGER("integer"),
    BIGINT("bigInt"),
    REAL("real"),
    DOUBLE("double"),
    VARCHAR("varchar(255)"),
    VARCHAR_UNBOUND("varchar"),
    VARBINARY("varbinary"),
    DATE("date"),
    TIME("time"),
    TIMESTAMP("timestamp");

    private final String castType;

    CastType(String castType) {
        this.castType = castType;
    }

    @Override
    public String toString() {
        return this.castType;
    }
}