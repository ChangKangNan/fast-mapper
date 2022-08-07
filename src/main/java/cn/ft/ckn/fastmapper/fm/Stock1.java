package cn.ft.ckn.fastmapper.fm;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author ckn
 * @date 2022/7/28
 */
@Table(name = "stock1")
@Accessors(chain = true)
@Data
public class Stock1 {
    @Id
    @Column(name = "id")
    private Long id1;

    @Column(name = "stock_name")
    private String stockName1;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "deleted")
    private Boolean deleted;

}