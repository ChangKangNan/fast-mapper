package cn.ft.ckn.test.fm.dao;
import cn.ft.ckn.fastmapper.component.FastMapperParam;
import cn.ft.ckn.test.fm.action.OtherOpenAction;

public class OtherOpenMapper {
    private OtherOpenMapper() {}

    public static OtherOpenAction.InsertMapper INSERT() {
    return new OtherOpenAction.InsertMapper(new FastMapperParam());
  }
    public static OtherOpenAction.BaseSelectMapper SELECT() {
        return new OtherOpenAction.BaseSelectMapper(new FastMapperParam());
  }
    public static OtherOpenAction.BaseUpdateMapper UPDATE() {
        return new OtherOpenAction.BaseUpdateMapper(new FastMapperParam());
  }
    public static OtherOpenAction.BaseDeletedMapper DELETE() {
        return new OtherOpenAction.BaseDeletedMapper(new FastMapperParam());
  }
}
