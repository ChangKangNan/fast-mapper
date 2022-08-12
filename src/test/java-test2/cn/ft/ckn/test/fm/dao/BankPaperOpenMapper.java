package cn.ft.ckn.test.fm.dao;
import cn.ft.ckn.fastmapper.component.SplicingParam;
import cn.ft.ckn.test.fm.action.BankPaperOpenAction;

public class BankPaperOpenMapper {
    private BankPaperOpenMapper() {}

    public static BankPaperOpenAction.InsertMapper INSERT() {
    return new BankPaperOpenAction.InsertMapper(new SplicingParam());
  }
    public static BankPaperOpenAction.BaseSelectMapper SELECT() {
        return new BankPaperOpenAction.BaseSelectMapper(new SplicingParam());
  }
    public static BankPaperOpenAction.BaseUpdateMapper UPDATE() {
        return new BankPaperOpenAction.BaseUpdateMapper(new SplicingParam());
  }
    public static BankPaperOpenAction.BaseDeletedMapper DELETE() {
        return new BankPaperOpenAction.BaseDeletedMapper(new SplicingParam());
  }
}
