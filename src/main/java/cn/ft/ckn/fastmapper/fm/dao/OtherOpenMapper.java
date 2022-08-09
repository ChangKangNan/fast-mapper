package cn.ft.ckn.fastmapper.fm.dao;
import cn.ft.ckn.fastmapper.component.SplicingParam;
import cn.ft.ckn.fastmapper.fm.action.OtherOpenAction;

public class OtherOpenMapper {
    private OtherOpenMapper() {}

    public static OtherOpenAction.InsertMapper INSERT() {
    return new OtherOpenAction.InsertMapper(new SplicingParam());
  }
    public static OtherOpenAction.BaseSelectMapper SELECT() {
        return new OtherOpenAction.BaseSelectMapper(new SplicingParam());
  }
    public static OtherOpenAction.BaseUpdateMapper UPDATE() {
        return new OtherOpenAction.BaseUpdateMapper(new SplicingParam());
  }
    public static OtherOpenAction.BaseDeletedMapper DELETE() {
        return new OtherOpenAction.BaseDeletedMapper(new SplicingParam());
  }
}
