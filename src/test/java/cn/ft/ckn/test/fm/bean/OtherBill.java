package cn.ft.ckn.test.fm.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;


@Accessors(chain=true)
@Table(name = "bz_other_bill")
@Entity
@Data
@ApiModel(value="他开票据明细(他开票据池)", description="他开票据明细(他开票据池)")
public class OtherBill {

    @Id
    @ApiModelProperty(value = "")
    @Column(name = "id")
    private Long id;


    @ApiModelProperty(value = "当前组织机构id(他开收票信息)")
    @Column(name = "institution_id")
    private Long institutionId;


    @ApiModelProperty(value = "收票id(他开收票信息)")
    @Column(name = "other_open_id")
    private Long otherOpenId;


    @ApiModelProperty(value = "收票单号")
    @Column(name = "sp_no")
    private String spNo;


    @ApiModelProperty(value = "票据编号")
    @Column(name = "bno")
    private String bno;


    @ApiModelProperty(value = "票据金额")
    @Column(name = "amount")
    private BigDecimal amount;


    @ApiModelProperty(value = "随机数,用于控制不重复;数据作废或删除random值=主键")
    @Column(name = "uq_flag")
    private Long uqFlag;


    @ApiModelProperty(value = "票据状态:1050,保存;1051:已收票;1052:作废-已收票：已提交的票据信息-已作废：已作废的票据信息")
    @Column(name = "bill_state")
    private Integer billState;


    @ApiModelProperty(value = "认领状态:1,已认领;0,未认领")
    @Column(name = "claim_state")
    private Integer claimState;


    @ApiModelProperty(value = "贴现状态:1070,已贴现,1071,未贴现")
    @Column(name = "discount_cash_state")
    private Integer discountCashState;


    @ApiModelProperty(value = "质押状态:1140,已质押;1141,未质押")
    @Column(name = "pledge_state")
    private Integer pledgeState;


    @ApiModelProperty(value = "合同编号")
    @Column(name = "contract_no")
    private String contractNo;


    @ApiModelProperty(value = "逻辑删除标识")
    @Column(name = "delete_flag")
    private Boolean deleteFlag;


    @ApiModelProperty(value = "创建人账号")
    @Column(name = "create_user_account")
    private String createUserAccount;


    @ApiModelProperty(value = "创建人姓名")
    @Column(name = "create_user_name")
    private String createUserName;


    @ApiModelProperty(value = "创建人")
    @Column(name = "create_user_id")
    private Long createUserId;


    @ApiModelProperty(value = "修改人")
    @Column(name = "update_user_id")
    private Long updateUserId;


    @ApiModelProperty(value = "逻辑锁")
    @Column(name = "version")
    private Long version;


    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Date createTime;


    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    private Date updateTime;


    @ApiModelProperty(value = "票据付款状态:1190,已付款;1191,未付款")
    @Column(name = "pay_state")
    private Integer payState;


    @ApiModelProperty(value = "出票日期")
    @Column(name = "draft_date")
    private Date draftDate;


    @ApiModelProperty(value = "到期日期")
    @Column(name = "expiration_date")
    private Date expirationDate;


}
