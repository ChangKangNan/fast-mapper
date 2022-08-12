package cn.ft.ckn.test.fm.dao;
import cn.ft.ckn.fastmapper.component.SplicingParam;
import cn.ft.ckn.test.fm.action.BankPaperAction;

public class BankPaperMapper {
    private BankPaperMapper() {}

    public static BankPaperAction.InsertMapper INSERT() {
    return new BankPaperAction.InsertMapper(new SplicingParam());
  }
    public static BankPaperAction.BaseSelectMapper SELECT() {
        return new BankPaperAction.BaseSelectMapper(new SplicingParam());
  }
    public static BankPaperAction.BaseUpdateMapper UPDATE() {
        return new BankPaperAction.BaseUpdateMapper(new SplicingParam());
  }
    public static BankPaperAction.BaseDeletedMapper DELETE() {
        return new BankPaperAction.BaseDeletedMapper(new SplicingParam());
  }
}
