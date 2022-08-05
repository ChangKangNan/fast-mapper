package cn.ft.ckn.fastmapper.fm;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ckn
 * @date 2022/7/28
 */
@Table(name = "stock")
@Accessors(chain = true)
@Data
public class Stock {
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="stock_name")
    private String stockName;
}