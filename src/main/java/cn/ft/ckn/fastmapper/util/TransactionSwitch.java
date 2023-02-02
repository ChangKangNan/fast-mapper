package cn.ft.ckn.fastmapper.util;

import io.netty.util.concurrent.FastThreadLocal;

public class TransactionSwitch {
    public static FastThreadLocal<Boolean> GLOBAL_TRANSACTION_SWITCH_STATUS=new FastThreadLocal<>();
    public static FastThreadLocal<Integer> GLOBAL_TRANSACTION_ISOLATION=new FastThreadLocal<>();

}
