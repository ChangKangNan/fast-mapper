package cn.ft.ckn.test.fm.dao;
import cn.ft.ckn.fastmapper.component.FastMapperParam;
import cn.ft.ckn.test.fm.action.BankPaperContractAction;

public class BankPaperContractMapper {
    private BankPaperContractMapper() {}

    public static BankPaperContractAction.InsertMapper INSERT() {
    return new BankPaperContractAction.InsertMapper(new FastMapperParam());
  }
    public static BankPaperContractAction.BaseSelectMapper SELECT() {
        return new BankPaperContractAction.BaseSelectMapper(new FastMapperParam());
  }
    public static BankPaperContractAction.BaseUpdateMapper UPDATE() {
        return new BankPaperContractAction.BaseUpdateMapper(new FastMapperParam());
  }
    public static BankPaperContractAction.BaseDeletedMapper DELETE() {
        return new BankPaperContractAction.BaseDeletedMapper(new FastMapperParam());
  }
}
