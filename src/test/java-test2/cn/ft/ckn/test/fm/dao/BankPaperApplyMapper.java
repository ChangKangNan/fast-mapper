package cn.ft.ckn.test.fm.dao;
import cn.ft.ckn.fastmapper.component.FastMapperParam;
import cn.ft.ckn.test.fm.action.BankPaperApplyAction;

public class BankPaperApplyMapper {
    private BankPaperApplyMapper() {}

    public static BankPaperApplyAction.InsertMapper INSERT() {
    return new BankPaperApplyAction.InsertMapper(new FastMapperParam());
  }
    public static BankPaperApplyAction.BaseSelectMapper SELECT() {
        return new BankPaperApplyAction.BaseSelectMapper(new FastMapperParam());
  }
    public static BankPaperApplyAction.BaseUpdateMapper UPDATE() {
        return new BankPaperApplyAction.BaseUpdateMapper(new FastMapperParam());
  }
    public static BankPaperApplyAction.BaseDeletedMapper DELETE() {
        return new BankPaperApplyAction.BaseDeletedMapper(new FastMapperParam());
  }
}
