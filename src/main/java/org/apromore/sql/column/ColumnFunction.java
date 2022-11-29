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

import com.healthmarketscience.common.util.AppendeeObject;
import com.healthmarketscience.sqlbuilder.ComboExpression;
import com.healthmarketscience.sqlbuilder.CustomSql;
import com.healthmarketscience.sqlbuilder.OrderObject.Dir;
import com.healthmarketscience.sqlbuilder.SqlObject;
import com.healthmarketscience.sqlbuilder.WindowDefinitionClause;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ColumnFunction {

    public ColumnFunction(SqlFunction function) {
        this.function = function;
        this.columns = new LinkedList<>();
        this.params = new LinkedList<>();
        this.orderings = new LinkedHashMap<>();
        this.partitions = new LinkedList<>();
        this.window = null;
    }

    private final SqlFunction function;
    private final LinkedList<CustomSql> params;
    private final LinkedList<CustomSql> columns;
    private SqlObject window;
    private final Map<CustomSql, Dir> orderings;
    private final LinkedList<CustomSql> partitions;
    private boolean ignoreNulls;

    public ColumnFunction addAllColumns() {
        this.columns.add(new CustomSql("*"));
        return this;
    }

    public ColumnFunction addColumns(String... columns) {
        this.columns.addAll(Arrays.stream(columns).map(CustomSql::new).collect(Collectors.toList()));
        return this;
    }

    public ColumnFunction addColumns(ColumnFunction... columnFunctions) {
        return addColumns(Arrays.stream(columnFunctions).map(ColumnFunction::toString).toArray(String[]::new));
    }

    public ColumnFunction addColumns(ComboExpression... comboExpressions) {
        return addColumns(Arrays.stream(comboExpressions).map(ComboExpression::toString).toArray(String[]::new));
    }

    public ColumnFunction addIgnoreNulls() {
        this.ignoreNulls = true;
        return this;
    }

    public ColumnFunction addOrderings(String... columns) {
        for (String column : columns) {
            this.orderings.put(new CustomSql(column), Dir.ASCENDING);
        }
        return this;
    }

    public ColumnFunction addOrdering(String column, Dir orderType) {
        this.orderings.put(new CustomSql(column), orderType);
        return this;
    }

    public ColumnFunction addPartitions(String... columns) {
        this.partitions.addAll(Arrays.stream(columns).map(CustomSql::new).collect(Collectors.toList()));
        return this;
    }

    public ColumnFunction addParams(Object... params) {
        this.params.addAll(Arrays.stream(params).map(param ->
                new CustomSql(param.toString())).collect(Collectors.toList()));
        return this;
    }

    public ColumnFunction addParams(String... params) {
        this.params.addAll(Arrays.stream(params).map(CustomSql::new).collect(Collectors.toList()));
        return this;
    }

    public ColumnFunction addWindow(String window) {
        this.window = new CustomSql(window);
        return this;
    }

    public ColumnFunction addWindow(WindowDefinitionClause window) {
        this.window = window;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(function.toString());

        s.append("(").append(columns.stream().map(AppendeeObject::toString).collect(Collectors.joining(",")));

        if (partitions.size() > 0) {
            s.append(" PARTITION BY ")
                .append(partitions.stream().map(AppendeeObject::toString).collect(Collectors.joining(",")));
        }

        if (orderings.size() > 0) {
            List<String> ordering = new ArrayList<>();
            for (Entry<CustomSql, Dir> entry : orderings.entrySet()) {
                ordering.add(entry.getValue() == Dir.ASCENDING ? entry.getKey().toString()
                    : entry.getKey() + " " + entry.getValue());
            }

            s.append(" ORDER BY ").append(String.join(",", ordering));
        }

        if (params.size() > 0) {
            if (columns.size() > 0) {
                s.append(",");
            }
            s.append(params.stream().map(AppendeeObject::toString).collect(Collectors.joining(",")));
        }

        s.append(")");
        if (ignoreNulls) {
            s.append(" IGNORE NULLS");
        }

        return window != null ? s.append(" OVER ").append(window).toString() : s.toString();
    }
}
