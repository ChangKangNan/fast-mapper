package cn.ft.ckn.test.fm.dao;
import cn.ft.ckn.fastmapper.component.SplicingParam;
import cn.ft.ckn.test.fm.action.OpenBillAction;

public class OpenBillMapper {
    private OpenBillMapper() {}

    public static OpenBillAction.InsertMapper INSERT() {
    return new OpenBillAction.InsertMapper(new SplicingParam());
  }
    public static OpenBillAction.BaseSelectMapper SELECT() {
        return new OpenBillAction.BaseSelectMapper(new SplicingParam());
  }
    public static OpenBillAction.BaseUpdateMapper UPDATE() {
        return new OpenBillAction.BaseUpdateMapper(new SplicingParam());
  }
    public static OpenBillAction.BaseDeletedMapper DELETE() {
        return new OpenBillAction.BaseDeletedMapper(new SplicingParam());
  }
}
