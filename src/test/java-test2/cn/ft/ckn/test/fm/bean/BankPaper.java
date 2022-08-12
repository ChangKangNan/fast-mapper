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
@Table(name = "funding_bank_paper")
@Entity
@Data
@ApiModel(value="票据池表", description="票据池表")
public class BankPaper {

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


    @ApiModelProperty(value = "票据申请单ID")
    @Column(name = "funding_bank_paper_apply_id")
    private Long fundingBankPaperApplyId;


    @ApiModelProperty(value = "票据开立单ID")
    @Column(name = "funding_bank_paper_open_id")
    private Long fundingBankPaperOpenId;


    @ApiModelProperty(value = "票据开立明细中的票据ID")
    @Column(name = "funding_bank_paper_id")
    private Long fundingBankPaperId;


    @ApiModelProperty(value = "票据类型silver_paper:银票,credit_prove:信用证,business_paper:商票")
    @Column(name = "paper_type")
    private String paperType;


    @ApiModelProperty(value = "票据号")
    @Column(name = "paper_no")
    private String paperNo;


    @ApiModelProperty(value = "票据金额")
    @Column(name = "paper_amount")
    private BigDecimal paperAmount;


    @ApiModelProperty(value = "承兑人")
    @Column(name = "acceptor")
    private String acceptor;


    @ApiModelProperty(value = "通知行账户")
    @Column(name = "notice_account")
    private String noticeAccount;


    @ApiModelProperty(value = "通知行银行名称")
    @Column(name = "notice_bank")
    private String noticeBank;


    @ApiModelProperty(value = "付款人")
    @Column(name = "drawer_company")
    private String drawerCompany;


    @ApiModelProperty(value = "付款人银行账户")
    @Column(name = "drawer_account")
    private String drawerAccount;


    @ApiModelProperty(value = "付款人银行名称")
    @Column(name = "drawer_bank")
    private String drawerBank;


    @ApiModelProperty(value = "收款人主键")
    @Column(name = "payee_rcms_kyc_id")
    private Long payeeRcmsKycId;


    @ApiModelProperty(value = "收款人")
    @Column(name = "payee_rcms_kyc_company")
    private String payeeRcmsKycCompany;


    @ApiModelProperty(value = "收款人银行账户")
    @Column(name = "payee_account")
    private String payeeAccount;


    @ApiModelProperty(value = "收款人银行名称")
    @Column(name = "payee_bank")
    private String payeeBank;


    @ApiModelProperty(value = "开立日期")
    @Column(name = "open_time")
    private Date openTime;


    @ApiModelProperty(value = "到期日")
    @Column(name = "bill_due_date")
    private Date billDueDate;


    @ApiModelProperty(value = "期限")
    @Column(name = "limit_day")
    private Integer limitDay;


    @ApiModelProperty(value = "最晚交单日")
    @Column(name = "last_time")
    private Date lastTime;


    @ApiModelProperty(value = "币种")
    @Column(name = "currency")
    private String currency;


    @ApiModelProperty(value = "开票手续费率")
    @Column(name = "poundage_proportion")
    private BigDecimal poundageProportion;


    @ApiModelProperty(value = "手续费")
    @Column(name = "poundage")
    private BigDecimal poundage;


    @ApiModelProperty(value = "收票行行号")
    @Column(name = "receive_bank_no")
    private String receiveBankNo;


    @ApiModelProperty(value = "出票日期")
    @Column(name = "draft_date")
    private Date draftDate;


    @ApiModelProperty(value = "确认交单")
    @Column(name = "submit_order")
    private Integer submitOrder;


    @ApiModelProperty(value = "交单日期")
    @Column(name = "submit_order_time")
    private Date submitOrderTime;


    @ApiModelProperty(value = "承兑日期")
    @Column(name = "acceptance_time")
    private Date acceptanceTime;


    @ApiModelProperty(value = "确认承兑(0未承兑1已承兑2确认承兑中3撤消承兑中)")
    @Column(name = "confirm_acceptance")
    private Integer confirmAcceptance;


    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;


    @ApiModelProperty(value = "")
    @Column(name = "kingdee_sync")
    private Boolean kingdeeSync;


    @ApiModelProperty(value = "")
    @Column(name = "kingdee_remark")
    private String kingdeeRemark;


    @ApiModelProperty(value = "开立类型:this:自开,other:他开")
    @Column(name = "open_type")
    private String openType;


    @ApiModelProperty(value = "是否含有质押物")
    @Column(name = "is_have_pledges")
    private Integer isHavePledges;


    @ApiModelProperty(value = "相差日")
    @Column(name = "differ_day")
    private Long differDay;


    @ApiModelProperty(value = "提交人ID")
    @Column(name = "submit_user_id")
    private Long submitUserId;


    @ApiModelProperty(value = "提交人姓名")
    @Column(name = "submit_user_name")
    private String submitUserName;


    @ApiModelProperty(value = "提交人账号")
    @Column(name = "submit_user_account")
    private String submitUserAccount;


    @ApiModelProperty(value = "提交时间")
    @Column(name = "submit_time")
    private Date submitTime;


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


    @ApiModelProperty(value = "是否真实存在(1真实存在0暂存)")
    @Column(name = "is_exist")
    private Integer isExist;


    @ApiModelProperty(value = "付息状态")
    @Column(name = "pay_interest_status")
    private String payInterestStatus;


    @ApiModelProperty(value = "兑付状态")
    @Column(name = "honour_status")
    private String honourStatus;


    @ApiModelProperty(value = "付息金额")
    @Column(name = "pay_interest_amount")
    private BigDecimal payInterestAmount;


    @ApiModelProperty(value = "付息日期")
    @Column(name = "pay_interest_time")
    private Date payInterestTime;


    @ApiModelProperty(value = "兑付日期")
    @Column(name = "honour_time")
    private Date honourTime;


    @ApiModelProperty(value = "贴现状态")
    @Column(name = "discount_status")
    private String discountStatus;


    @ApiModelProperty(value = "质押状态")
    @Column(name = "pledge_status")
    private String pledgeStatus;


    @ApiModelProperty(value = "收票日期")
    @Column(name = "receive_paper_time")
    private Date receivePaperTime;


    @ApiModelProperty(value = "已使用金额")
    @Column(name = "have_been_used_amount")
    private BigDecimal haveBeenUsedAmount;


    @ApiModelProperty(value = "未使用金额")
    @Column(name = "remaining_amount")
    private BigDecimal remainingAmount;


    @ApiModelProperty(value = "安排单的合同号")
    @Column(name = "claim_lot_id")
    private String claimLotId;


    @ApiModelProperty(value = "业务员")
    @Column(name = "salesman")
    private String salesman;


    @ApiModelProperty(value = "部门ID")
    @Column(name = "department_id")
    private Long departmentId;


    @ApiModelProperty(value = "部门")
    @Column(name = "department")
    private String department;


    @ApiModelProperty(value = "是否删除")
    @Column(name = "deleted")
    private Boolean deleted;


}
