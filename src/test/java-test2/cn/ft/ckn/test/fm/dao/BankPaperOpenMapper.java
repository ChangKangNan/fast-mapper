package cn.ft.ckn.test.fm.dao;
import cn.ft.ckn.fastmapper.component.FastMapperParam;
import cn.ft.ckn.test.fm.action.BankPaperOpenAction;

public class BankPaperOpenMapper {
    private BankPaperOpenMapper() {}

    public static BankPaperOpenAction.InsertMapper INSERT() {
    return new BankPaperOpenAction.InsertMapper(new FastMapperParam());
  }
    public static BankPaperOpenAction.BaseSelectMapper SELECT() {
        return new BankPaperOpenAction.BaseSelectMapper(new FastMapperParam());
  }
    public static BankPaperOpenAction.BaseUpdateMapper UPDATE() {
        return new BankPaperOpenAction.BaseUpdateMapper(new FastMapperParam());
  }
    public static BankPaperOpenAction.BaseDeletedMapper DELETE() {
        return new BankPaperOpenAction.BaseDeletedMapper(new FastMapperParam());
  }
}
