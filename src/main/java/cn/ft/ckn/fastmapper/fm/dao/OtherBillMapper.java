package cn.ft.ckn.fastmapper.fm.dao;
import cn.ft.ckn.fastmapper.component.SplicingParam;
import cn.ft.ckn.fastmapper.fm.action.OtherBillAction;

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
