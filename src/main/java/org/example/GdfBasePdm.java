package org.example;


import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class GdfBasePdm {

    public static final DbColumn GE_CN_ENTITY_NAME_ID = new DbColumn("ENTITY_DEF_ID");
    public static final DbColumn GE_CN_KEYS = new DbColumn("ENTITY_PK_ATTRS");
    public static final DbColumn GE_CN_CHANGE_COUNT = new DbColumn("CHANGE_COUNT");
    public static final DbColumn GE_CN_TOUCH_REMINDER_DATE = new DbColumn("TOUCH_DATE");
    public static final DbColumn GE_CN_BILL_CYCLE_ID1 = new DbColumn("BILL_CYCLE_ID1");
    public static final DbColumn GE_CN_BILL_CYCLE_ID2 = new DbColumn("BILL_CYCLE_ID2");
    public static final DbColumn GE_CN_BILL_CYCLE_SWITCH = new DbColumn("BILL_CYCLE_SWITCH");
    public static final DbColumn GE_CN_SERVICE_PROVIDER_ID = new DbColumn("SERVICE_PROVIDER_ID");
    public static final DbColumn SU_CN_CONTAINER = new DbColumn("SU_CONTAINER");
    public static final DbColumn FU_CN_CONTAINER = new DbColumn("FU_CONTAINER");
    public static final DbColumn GE_CN_AFFINITY_KEY = new DbColumn("AFFINITY_KEY");

    public static final DbColumn[] GE_CN_COLUMNS = {GE_CN_ENTITY_NAME_ID, GE_CN_KEYS, GE_CN_CHANGE_COUNT,
                    GE_CN_TOUCH_REMINDER_DATE, GE_CN_BILL_CYCLE_ID1, GE_CN_BILL_CYCLE_ID2, GE_CN_BILL_CYCLE_SWITCH,
                    GE_CN_SERVICE_PROVIDER_ID, SU_CN_CONTAINER, FU_CN_CONTAINER, GE_CN_AFFINITY_KEY
    };

    /**
     * Column name constants for Generic Reference (AT_REF_ADN)
     */
    public static final DbColumn GR_CN_RK_NAME_ID = new DbColumn("RK_ID");
    public static final DbColumn GR_CN_SOURCE_NAME_ID = new DbColumn("SOURCE_ENTITY_DEF_ID");
    public static final DbColumn GR_CN_SOURCE_KEYS = new DbColumn("SOURCE_ENTITY_PK_ATTRS");
    public static final DbColumn GR_CN_FK_VVALID_FROM = new DbColumn("FK_VVALID_FROM");
    public static final DbColumn GR_CN_FK_VINVALID_FROM = new DbColumn("FK_VINVALID_FROM");
    public static final DbColumn GR_CN_TARGET_NAME_ID = new DbColumn("TARGET_ENTITY_DEF_ID");
    public static final DbColumn GR_CN_TARGET_KEYS = new DbColumn("TARGET_ENTITY_PK_ATTRS");
    public static final DbColumn GR_CN_LINEAGE = new DbColumn("LINEAGE");
    public static final DbColumn GR_CN_LINEAGE_0_ENTITY_PK_ATTRS = new DbColumn("");

    public static final DbColumn GR_CN_LINEAGE_0_ENTITY_DEF_ID = new DbColumn("");

    public static final List<DbColumn> GR_CN_COLUMNS = Collections.unmodifiableList(Arrays.asList(
            GR_CN_RK_NAME_ID, GR_CN_SOURCE_NAME_ID, GR_CN_SOURCE_KEYS, GR_CN_FK_VVALID_FROM, GR_CN_FK_VINVALID_FROM,
            GR_CN_TARGET_NAME_ID, GR_CN_TARGET_KEYS, GR_CN_LINEAGE,
            GR_CN_LINEAGE_0_ENTITY_PK_ATTRS, GR_CN_LINEAGE_0_ENTITY_DEF_ID
    ));

    /**
     * Column name constants for Generic Access (AT_ACC_ADN)
     */
    public static final DbColumn GA_CN_EK_NAME_ID = new DbColumn("ID");
    public static final DbColumn GA_CN_ENTRY_KEYS = new DbColumn("KEY_ATTRS");
    public static final DbColumn GA_CN_ENTITY_NAME_ID = new DbColumn("ENTITY_DEF_ID");
    public static final DbColumn GA_CN_ENTITY_KEYS = new DbColumn("ENTITY_PK_ATTRS");
    public static final DbColumn GA_CN_VVALID_FROM = new DbColumn("VVALID_FROM");
    public static final DbColumn GA_CN_VINVALID_FROM = new DbColumn("VINVALID_FROM");
    public static final DbColumn GA_CN_CHANGE_COUNT = new DbColumn("CHANGE_COUNT");
    public static final DbColumn GA_CN_AFFINITY_KEY = new DbColumn("AFFINITY_KEY");

    public static final DbColumn[] GA_CN_COLUMNS = {
            GA_CN_EK_NAME_ID, GA_CN_ENTRY_KEYS, GA_CN_ENTITY_NAME_ID, GA_CN_ENTITY_KEYS, GA_CN_VVALID_FROM, GA_CN_VINVALID_FROM,
            GA_CN_CHANGE_COUNT, GA_CN_AFFINITY_KEY
    };

    private static final String ALL_GA_COLUMNS = Arrays.stream(GA_CN_COLUMNS).map(DbColumn::getName).collect(joining(","));

    private static final String ALL_GE_COLUMNS = Arrays.stream(GE_CN_COLUMNS).map(DbColumn::getName).collect(joining(","));
    private static final String ALL_GE_COLUMNS_PREFIX = Arrays.stream(GE_CN_COLUMNS).map(DbColumn::getName)
                                                        .map(n -> "ge." + n).collect(joining(","));

    private static final Map<String, Integer> GA_ALL_INDEXES = getIndexMap(GA_CN_COLUMNS);
    private static final Map<String, Integer> GE_ALL_INDEXES = getIndexMap(GE_CN_COLUMNS);


    public static final String DEFAULT_NAMESPACE = "OCE";

    private String applicationDomainName;

    public GdfBasePdm() {

    }

    private static Map<String, Integer> getIndexMap (DbColumn[] columns) {
        Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < columns.length; i++) {
            result.put(columns[i].getName(), i);
        }
        return result;
    }

    public void setAdnIdentifier(String msApplicationDomainName) {
        this.applicationDomainName = msApplicationDomainName;
    }

    public void onRemove() {

    }

    public String getAcc() {
        return "AT_ACC";
    }

    public String getAccTable() {
        return DEFAULT_NAMESPACE + ".AT_ACC";
    }

    public String getAccAllColumns() {
        return ALL_GA_COLUMNS;
    }

    public String getEntAllColumns() {
        return ALL_GE_COLUMNS;
    }

    public String getEntAllColumnsWithPrefix() {
        return ALL_GE_COLUMNS_PREFIX;
    }

    public int getAccColumnIndex(DbColumn column) {
        return GA_ALL_INDEXES.get(column.getName());
    }

    public int getEntColumnIndex(DbColumn column) {
        return GE_ALL_INDEXES.get(column.getName());
    }

    public String getEnt() {
        return "AT_ENT";
    }

    public String getEntTable() {
        return DEFAULT_NAMESPACE + ".AT_ENT";
    }

    public String getRef() {
        return "AT_REF";
    }

    public String getRefTable() {
        return DEFAULT_NAMESPACE + ".AT_REF";
    }

    public String substEscapeChars(String pattern) {
        StringBuilder buf = new StringBuilder();

        if (pattern == null) {
            return (buf.toString());
        }

        int i = 0;

        while (i < pattern.length()) {
            if (i < pattern.length() - 1) {
                /* @todo // 1.) '\\' --> '\\\\' ???? */
                if (pattern.charAt(i) == '\\' && pattern.charAt(i + 1) == '\\') {
                    // 1.) '\\' --> '\\\\'
                    // buf.append("\\\\\\\\");
                    buf.append("\\\\");
                    i += 2;
                } else if (pattern.charAt(i) == '\\' && pattern.charAt(i + 1) == '_') {
                    // 3.) '\_' --> '\_' no change !
                    buf.append("\\_");
                    i += 2;
                } else if (pattern.charAt(i) == '\\' && pattern.charAt(i + 1) == '%') {
                    // 4.) '\%' --> '\%' no change !
                    buf.append("\\%");
                    i += 2;
                } else if (pattern.charAt(i) == '\\' && pattern.charAt(i + 1) == '?') {
                    // 2.) '\?' --> '?'
                    buf.append("?");
                    i += 2;
                } else if (pattern.charAt(i) == '_') {
                    // 5.) '_' --> '\_'
                    buf.append("\\_");
                    i++;
                } else if (pattern.charAt(i) == '?') {
                    // 6.) '?' --> '_'
                    buf.append("_");
                    i++;
                } else {
                    buf.append(pattern.charAt(i));
                    i++;
                }
            } else {
                if (pattern.charAt(i) == '_') {
                    // 5.) '_' --> '\_'
                    buf.append("\\_");
                    i++;
                } else if (pattern.charAt(i) == '?') {
                    // 6.) '?' --> '_'
                    buf.append("_");
                    i++;
                } else {
                    buf.append(pattern.charAt(i));
                    i++;
                }
            }
        }

        return (buf.toString());
    }

    /**
     * When query string contains %s placeholder and we want to keep those, but replace certain piece of string.
     * e.g. SELECT %s FROM X WHERE {0}, replacing just the {0}
     *
     * @param userWhereClause
     * @return
     */
    public static String constructCustomQuery(final String userWhereClause, final Object... arguments) {
        Objects.requireNonNull(userWhereClause);
        return MessageFormat.format(userWhereClause, arguments);
    }

    public static String getObjectsCommaSeperated(Collection<Integer> array) {
        return array.stream().map(Object::toString)
            .collect(Collectors.joining(","));
    }

}
