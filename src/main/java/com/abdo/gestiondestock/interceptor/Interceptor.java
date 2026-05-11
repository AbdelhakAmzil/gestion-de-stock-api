package com.abdo.gestiondestock.interceptor;

import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

public class Interceptor implements StatementInspector {

    @Override
    public String inspect(String sql) {
        if (StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")) {
            final String entityName = sql.substring(7, sql.indexOf("."));
            final String idEntreprise = MDC.get("idEntreprise");

            if (StringUtils.hasLength(entityName)
                    && !entityName.toLowerCase().contains("entreprise")
                    && !entityName.toLowerCase().contains("roles")
                    && StringUtils.hasLength(idEntreprise)
                    && StringUtils.hasLength(idEntreprise)) {  // ✅ seulement si idEntreprise présent) {

                if (sql.contains("where")) {
                    sql = sql + " and " + entityName + ".identreprise = " + idEntreprise;
                } else {
                    sql = sql + " where " + entityName + ".identreprise = " + idEntreprise;
                }
            }
        }
        //System.out.println("SQL intercepté, idEntreprise MDC: " + MDC.get("idEntreprise"));
        return sql;
    }
}