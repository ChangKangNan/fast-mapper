package cn.ft.ckn.fastmapper.fm;

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
@Table(name = "bz_other_open")
@Entity
@Data
@ApiModel(value="他开票据", description="他开票据")
public class OtherOpen {

    @Id
    @ApiModelProperty(value = "主键")
    @Column(name = "id")
    private Integer id;


    @ApiModelProperty(value = "我方户名(当前组织机构id)")
    @Column(name = "institution_id")
    private Integer institutionId;


    @ApiModelProperty(value = "我方户名,当前组织机构名称")
    @Column(name = "org_name")
    private String orgName;


    @ApiModelProperty(value = "收票单号:TKPJ（他开票据缩写）+日期+编号,系统自动生成")
    @Column(name = "sp_no")
    private String spNo;


    @ApiModelProperty(value = "票据类型,1020,银票、1021,商票")
    @Column(name = "bill_type")
    private BigDecimal billType;


    @ApiModelProperty(value = "录入状态:1060,已保存;1061,已提交;1062,作废;")
    @Column(name = "logging_state")
    private Integer loggingState;


    @ApiModelProperty(value = "结算币种")
    @Column(name = "currency")
    private String currency;


    @ApiModelProperty(value = "交易对手,id")
    @Column(name = "kyc_id")
    private Integer kycId;


    @ApiModelProperty(value = "交易对手户名;全部KYC类型数据（IMS获取+组织机构）")
    @Column(name = "kyc_account_name")
    private String kycAccountName;


    @ApiModelProperty(value = "收票账户id")
    @Column(name = "sp_account_id")
    private Integer spAccountId;


    @ApiModelProperty(value = "收票账户备注,展示银行账户【账号备注】筛选读取当前组织机构下的银行账户")
    @Column(name = "sp_account_remark")
    private String spAccountRemark;


    @ApiModelProperty(value = "票据总额")
    @Column(name = "sp_amount")
    private BigDecimal spAmount;


    @ApiModelProperty(value = "票据数量")
    @Column(name = "sp_quantity")
    private Integer spQuantity;


    @ApiModelProperty(value = "承兑人")
    @Column(name = "acceptor")
    private String acceptor;


    @ApiModelProperty(value = "出票日期")
    @Column(name = "draft_date")
    private Date draftDate;


    @ApiModelProperty(value = "收票到期")
    @Column(name = "receive_date")
    private Date receiveDate;


    @ApiModelProperty(value = "汇票到期")
    @Column( name = "bill_due_date")
    private Date billDueDate;


    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;


    @ApiModelProperty(value = "逻辑删除标识")
    @Column(name = "delete_flag")
    private Integer deleteFlag;


    @ApiModelProperty(value = "创建人账号")
    @Column(name = "create_user_account")
    private String createUserAccount;


    @ApiModelProperty(value = "创建人姓名")
    @Column(name = "create_user_name")
    private String createUserName;


    @ApiModelProperty(value = "创建人")
    @Column(name = "create_user_id")
    private Integer createUserId;


    @ApiModelProperty(value = "修改人")
    @Column(name = "update_user_id")
    private Integer updateUserId;


    @ApiModelProperty(value = "逻辑锁")
    @Column(name = "version")
    private Integer version;


    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Date createTime;


    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    private Date updateTime;


}
