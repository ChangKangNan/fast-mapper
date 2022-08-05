package cn.ft.ckn.fastmapper.mapper;

import cn.ft.ckn.fastmapper.component.SplicingParam;
import cn.ft.ckn.fastmapper.fm.action.StockAction;

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
    public static StockAction.SelectMapper SELECT() {
        return new StockAction.SelectMapper(new SplicingParam());
    }
   public static StockAction.UpdateMapper UPDATE() {
        return new StockAction.UpdateMapper(new SplicingParam());
    }
    public static StockAction.DeletedMapper DELETE() {
        return new StockAction.DeletedMapper(new SplicingParam());
    }
}
