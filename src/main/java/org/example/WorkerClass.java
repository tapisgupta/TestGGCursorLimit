package org.example;

import de.siemens.advantage.in.gdm1.gdf.base.impl.gg.generated.AtEnt;
import de.siemens.advantage.in.gdm1.gdf.base.impl.gg.generated.AtEntKey;
import org.apache.ignite.cache.query.FieldsQueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.client.IgniteClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.List;

import static org.example.GdfBasePdm.*;
import static org.example.GdfBasePdm.GE_CN_KEYS;

public class WorkerClass implements Runnable{

    private IgniteClient client;
    private QueryParams param;

    public WorkerClass(IgniteClient client, QueryParams param){
        this.client = client;
        this.param = param;

    }

    @Override
    public void run() {

        while (true){
                try {
                    testError(client,param.getId(),param.getKeyAttr());
                    Thread.sleep(100);
                } catch (Exception e) {
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    printError("Some error :  \n" + sw.toString());
                    throw new RuntimeException(e);
                }
        }

    }

    public void testError(IgniteClient client, Object...args) throws Exception {
        GdfBasePdm pdm = new GdfBasePdm();
        String query = "SELECT " + pdm.getEntAllColumnsWithPrefix() + " FROM OCE.AT_ENT ge " +
                " INNER JOIN OCE.AT_ACC ga ON " +
                " ga.ENTITY_DEF_ID=ge.ENTITY_DEF_ID AND ga.ENTITY_PK_ATTRS=ge.ENTITY_PK_ATTRS " +
                " WHERE  ga.ID=? AND ga.KEY_ATTRS=?" ;
        printError(query);
        for (Object arg:args){
            printError(arg.toString());
        }
        SqlFieldsQuery q = new SqlFieldsQuery(query);
        q.setArgs(args);
        try(final FieldsQueryCursor<List<?>> cursor = client.query(q)) {
            Iterator<List<?>> itr = cursor.iterator();
            while (itr.hasNext()) {
                EntQueryResult result = convertToQueryResult(pdm, itr.next());
                printError(result.toString());
            }
        }catch (Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            printError("Some error :  \n" + sw.toString());
            throw new RuntimeException(e);
        }
    }

    void printError(String message){

        System.out.println(message);
        try {

            Files.write(Paths.get("trace.log"), (message + "\n").getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    EntQueryResult convertToQueryResult(GdfBasePdm pdm, List<?> fields) {
        AtEnt entity = new AtEnt();
        entity.setChangeCount((Integer) fields.get(pdm.getEntColumnIndex(GE_CN_CHANGE_COUNT)));
        entity.setTouchDate((Long) fields.get(pdm.getEntColumnIndex(GE_CN_TOUCH_REMINDER_DATE)));
        entity.setServiceProviderId((String) fields.get(pdm.getEntColumnIndex(GE_CN_SERVICE_PROVIDER_ID)));
        if(fields.get(pdm.getEntColumnIndex(GE_CN_BILL_CYCLE_SWITCH)) != null) {
            entity.setBillCycleSwitch((Long) fields.get(pdm.getEntColumnIndex(GE_CN_BILL_CYCLE_SWITCH)));
        }
        if(fields.get(pdm.getEntColumnIndex(GE_CN_BILL_CYCLE_ID1)) != null) {
            entity.setBillCycleId1((Short) fields.get(pdm.getEntColumnIndex(GE_CN_BILL_CYCLE_ID1)));
        }
        if(fields.get(pdm.getEntColumnIndex(GE_CN_BILL_CYCLE_ID2)) != null) {
            entity.setBillCycleId2((Short) fields.get(pdm.getEntColumnIndex(GE_CN_BILL_CYCLE_ID2)));
        }
        entity.setSuContainer((byte[]) fields.get(pdm.getEntColumnIndex(SU_CN_CONTAINER)));
        entity.setFuContainer((byte[]) fields.get(pdm.getEntColumnIndex(FU_CN_CONTAINER)));

        AtEntKey key = AtEntKey.of((Integer) fields.get(pdm.getEntColumnIndex(GE_CN_ENTITY_NAME_ID)),
                (String) fields.get(pdm.getEntColumnIndex(GE_CN_KEYS)));

        return new EntQueryResult(key, entity);
    }
}
