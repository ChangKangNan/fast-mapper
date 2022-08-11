package cn.ft.ckn.test.fm.bean;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;


@Accessors(chain=true)
@Table(name = "funding_pay_interest_detail")
@Entity
@Data
@ApiModel(value="付息申请明细表", description="付息申请明细表")
public class PayInterestDetail {

    @Id
    @ApiModelProperty(value = "主键")
    @Column(name = "id")
    private Long id;


    @ApiModelProperty(value = "全局ID")
    @Column(name = "global_id")
    private Long globalId;


    @ApiModelProperty(value = "编号")
    @Column(name = "no")
    private String no;


    @ApiModelProperty(value = "付息申请单主键")
    @Column(name = "funding_pay_interest_id")
    private Long fundingPayInterestId;


    @ApiModelProperty(value = "票据快照")
    @Column(name = "paper_snapshot")
    private String paperSnapshot;


    @ApiModelProperty(value = "票据类型silver_paper:银票,credit_prove:信用证,business_paper:商票")
    @Column(name = "paper_type")
    private String paperType;


    @ApiModelProperty(value = "票据编号")
    @Column(name = "paper_no")
    private String paperNo;


    @ApiModelProperty(value = "票据ID")
    @Column(name = "paper_id")
    private Long paperId;


    @ApiModelProperty(value = "调整天数")
    @Column(name = "adjust_days")
    private Integer adjustDays;


    @ApiModelProperty(value = "付息利率")
    @Column(name = "interest_rate")
    private BigDecimal interestRate;


    @ApiModelProperty(value = "付息金额")
    @Column(name = "pay_interest_amount")
    private BigDecimal payInterestAmount;


    @ApiModelProperty(value = "付款状态")
    @Column(name = "pay_status")
    private Integer payStatus;


    @ApiModelProperty(value = "付款时间")
    @Column(name = "pay_time")
    private Date payTime;


    @ApiModelProperty(value = "创建人ID")
    @Column(name = "create_user_id")
    private Long createUserId;


    @ApiModelProperty(value = "创建人姓名")
    @Column(name = "create_user_name")
    private String createUserName;


    @ApiModelProperty(value = "创建人账号")
    @Column(name = "create_user_account")
    private String createUserAccount;


    @ApiModelProperty(value = "更新人ID")
    @Column(name = "update_user_id")
    private Long updateUserId;


    @ApiModelProperty(value = "更新人姓名")
    @Column(name = "update_user_name")
    private String updateUserName;


    @ApiModelProperty(value = "更新人账号")
    @Column(name = "update_user_account")
    private String updateUserAccount;


    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Date createTime;


    @ApiModelProperty(value = "")
    @Column(name = "use_orgs")
    private String useOrgs;


    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    private Date updateTime;


    @ApiModelProperty(value = "是否删除")
    @Column(name = "deleted")
    private Boolean deleted;


}
