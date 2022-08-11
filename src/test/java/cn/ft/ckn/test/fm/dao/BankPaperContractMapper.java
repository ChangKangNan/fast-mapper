package cn.ft.ckn.test.fm.dao;
import cn.ft.ckn.fastmapper.component.SplicingParam;
import cn.ft.ckn.test.fm.action.BankPaperContractAction;

public class BankPaperContractMapper {
    private BankPaperContractMapper() {}

    public static BankPaperContractAction.InsertMapper INSERT() {
    return new BankPaperContractAction.InsertMapper(new SplicingParam());
  }
    public static BankPaperContractAction.BaseSelectMapper SELECT() {
        return new BankPaperContractAction.BaseSelectMapper(new SplicingParam());
  }
    public static BankPaperContractAction.BaseUpdateMapper UPDATE() {
        return new BankPaperContractAction.BaseUpdateMapper(new SplicingParam());
  }
    public static BankPaperContractAction.BaseDeletedMapper DELETE() {
        return new BankPaperContractAction.BaseDeletedMapper(new SplicingParam());
  }
}
