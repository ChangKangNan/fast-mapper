package cn.ft.ckn.test.fm.dao;
import cn.ft.ckn.fastmapper.component.FastMapperParam;
import cn.ft.ckn.test.fm.action.ContractAction;

public class ContractMapper {
    private ContractMapper() {}

    public static ContractAction.InsertMapper INSERT() {
    return new ContractAction.InsertMapper(new FastMapperParam());
  }
    public static ContractAction.BaseSelectMapper SELECT() {
        return new ContractAction.BaseSelectMapper(new FastMapperParam());
  }
    public static ContractAction.BaseUpdateMapper UPDATE() {
        return new ContractAction.BaseUpdateMapper(new FastMapperParam());
  }
    public static ContractAction.BaseDeletedMapper DELETE() {
        return new ContractAction.BaseDeletedMapper(new FastMapperParam());
  }
}
