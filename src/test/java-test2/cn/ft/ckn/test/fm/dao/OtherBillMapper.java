package cn.ft.ckn.test.fm.dao;
import cn.ft.ckn.fastmapper.component.SplicingParam;
import cn.ft.ckn.test.fm.action.OtherBillAction;

public class OtherBillMapper {
    private OtherBillMapper() {}

    public static OtherBillAction.InsertMapper INSERT() {
    return new OtherBillAction.InsertMapper(new SplicingParam());
  }
    public static OtherBillAction.BaseSelectMapper SELECT() {
        return new OtherBillAction.BaseSelectMapper(new SplicingParam());
  }
    public static OtherBillAction.BaseUpdateMapper UPDATE() {
        return new OtherBillAction.BaseUpdateMapper(new SplicingParam());
  }
    public static OtherBillAction.BaseDeletedMapper DELETE() {
        return new OtherBillAction.BaseDeletedMapper(new SplicingParam());
  }
}
