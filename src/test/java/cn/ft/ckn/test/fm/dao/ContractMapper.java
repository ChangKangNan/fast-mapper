package cn.ft.ckn.test.fm.dao;
import cn.ft.ckn.fastmapper.component.SplicingParam;
import cn.ft.ckn.test.fm.action.ContractAction;

public class ContractMapper {
    private ContractMapper() {}

    public static ContractAction.InsertMapper INSERT() {
    return new ContractAction.InsertMapper(new SplicingParam());
  }
    public static ContractAction.BaseSelectMapper SELECT() {
        return new ContractAction.BaseSelectMapper(new SplicingParam());
  }
    public static ContractAction.BaseUpdateMapper UPDATE() {
        return new ContractAction.BaseUpdateMapper(new SplicingParam());
  }
    public static ContractAction.BaseDeletedMapper DELETE() {
        return new ContractAction.BaseDeletedMapper(new SplicingParam());
  }
}
