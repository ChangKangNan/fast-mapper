package cn.ft.ckn.fastmapper.util;

/**
 * @author ckn
 * @date 2023/2/3
 */
public class TransactionStatus {
    private TransactionStatus() {
    }

    public static TransactionStatus build() {
        return new TransactionStatus();
    }

    private boolean error = false;

    public void setIsError() {
        error = true;
    }

    public boolean getIsError() {
        return error;
    }

}
