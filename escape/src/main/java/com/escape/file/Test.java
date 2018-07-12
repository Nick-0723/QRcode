package com.escape.file;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        String[] f = {"sudtype",
        "acctype",
        "claimno",
        "payFlag",
        "etldate",
        "classname",
        "realamt",
        "classcode",
        "delcode",
        "typeno",
        "policyno","claim_rowkey","person_tele","role","person_tele","pname","ben_name","pid"};
        Arrays.sort(f);
//        System.out.println(Arrays.toString(f));
        test();
    }


    private static void test(){
        String[] a = {"aperson_id",
                "apid",
                "aidtype",
                "empno",
                "gpolicyno",
                "saleattr",
                "policyno",
                "appno",
                "appf",
                "tmount",
                "oper_id",
                "operno",
                "person_id",
                "classcode",
                "pid",
                "id ",
                "prelname",
                "payseq",
                "branch",
                "src_sys",
                "name "};

        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
    }

    @org.junit.Test
    public void test2(){
        String s = "0324830AE141CHC";
        StringBuffer sb = new StringBuffer(s);
        System.out.println(sb.reverse());
    }
}
