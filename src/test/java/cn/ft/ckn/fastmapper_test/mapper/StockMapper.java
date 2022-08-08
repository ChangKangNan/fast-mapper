package cn.ft.ckn.fastmapper_test.mapper;

import cn.ft.ckn.fastmapper.component.SplicingParam;
import cn.ft.ckn.fastmapper_test.fm.action.StockAction;

/**
 * @author ckn
 * @date 2022/7/28
 */
@SuppressWarnings("unused")
public class StockMapper {
    private StockMapper() {}
    public static StockAction.InsertMapper INSERT() {
        return new StockAction.InsertMapper(new SplicingParam());
    }
    public static StockAction.BaseSelectMapper SELECT() {
        return new StockAction.BaseSelectMapper(new SplicingParam());
    }
   public static StockAction.BaseUpdateMapper UPDATE() {
        return new StockAction.BaseUpdateMapper(new SplicingParam());
    }
    public static StockAction.BaseDeletedMapper DELETE() {
        return new StockAction.BaseDeletedMapper(new SplicingParam());
    }
}
