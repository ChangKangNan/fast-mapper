package cn.ft.ckn.test.fm.dao;
import cn.ft.ckn.fastmapper.component.FastMapperParam;
import cn.ft.ckn.test.fm.action.PayInterestDetailAction;

public class PayInterestDetailMapper {
    private PayInterestDetailMapper() {}

    public static PayInterestDetailAction.InsertMapper INSERT() {
    return new PayInterestDetailAction.InsertMapper(new FastMapperParam());
  }
    public static PayInterestDetailAction.BaseSelectMapper SELECT() {
        return new PayInterestDetailAction.BaseSelectMapper(new FastMapperParam());
  }
    public static PayInterestDetailAction.BaseUpdateMapper UPDATE() {
        return new PayInterestDetailAction.BaseUpdateMapper(new FastMapperParam());
  }
    public static PayInterestDetailAction.BaseDeletedMapper DELETE() {
        return new PayInterestDetailAction.BaseDeletedMapper(new FastMapperParam());
  }
}
