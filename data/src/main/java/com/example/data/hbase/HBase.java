package com.example.data.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.*;

/**
 * @author jleo
 * @date 2021/3/8
 */
public class HBase {

    public final static String SHELL_TABLE_NAME = "shell";
    //public final static String HTTP_TABLE_NAME = "http";
    public final static String VALUE_CELL_NAME = "value";

    public static Connection getConn() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2182");
        configuration.set("hbase.zookeeper.quorum", "127.0.0.1");
        //configuration.set("hbase.master", "127.0.0.1:16010");
        return ConnectionFactory.createConnection(configuration);
    }

    public static Admin getAdmin(){
        Admin admin=null;
        try {
            admin=getConn().getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return admin;
    }


    public static void createTable(String tableName) {
        Admin admin=HBase.getAdmin();
        HTableDescriptor htd=new HTableDescriptor(TableName.valueOf(tableName));
        HColumnDescriptor family=new HColumnDescriptor(HBase.VALUE_CELL_NAME);
        htd.addFamily(family);
        try {
            admin.createTable(htd);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                admin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insert(String tableName, String id, String time, String value) {
        try {
            Table table = HBase.getConn().getTable(TableName.valueOf(tableName));
            Put put = new Put(Bytes.toBytes(id + time));
            //参数：1.列族名  2.列名  3.值
            put.addColumn(HBase.VALUE_CELL_NAME.getBytes(), HBase.VALUE_CELL_NAME.getBytes(), value.getBytes());
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List[] select(String tableName, String taskId, String begin, String end) {
        try {
            assert taskId != null;
            assert begin != null;
            assert end != null;
            Table table = HBase.getConn().getTable(TableName.valueOf(tableName));
            Scan scan = new Scan();
            scan.setStartRow((taskId + begin).trim().getBytes());
            scan.setStopRow((taskId + end).trim().getBytes());
            ResultScanner scanner = table.getScanner(scan);
            List<Long> timeList = new ArrayList<>();
            List<Integer> valueList = new ArrayList<>();
            for (Result result : scanner) {
                for (Cell cell : result.listCells()) {
                    valueList.add(Integer.parseInt(Bytes.toString(CellUtil.cloneValue(cell))));
                    timeList.add(cell.getTimestamp());
                }
            }
            return new List[]{timeList, valueList};
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
