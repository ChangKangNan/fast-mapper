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
@Table(name = "funding_bank_paper_apply")
@Entity
@Data
@ApiModel(value="票据申请表", description="票据申请表")
public class BankPaperApply {

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


    @ApiModelProperty(value = "票据类型silver_paper:银票,credit_prove:信用证,business_paper:商票")
    @Column(name = "paper_type")
    private String paperType;


    @ApiModelProperty(value = "联行号")
    @Column(name = "lian_hang_no")
    private String lianHangNo;


    @ApiModelProperty(value = "承兑人")
    @Column(name = "acceptor")
    private String acceptor;


    @ApiModelProperty(value = "通知行账户")
    @Column(name = "notice_account")
    private String noticeAccount;


    @ApiModelProperty(value = "通知行银行名称")
    @Column(name = "notice_bank")
    private String noticeBank;


    @ApiModelProperty(value = "付款人/受益人")
    @Column(name = "drawer_company")
    private String drawerCompany;


    @ApiModelProperty(value = "付款人银行账户/受益人银行账户")
    @Column(name = "drawer_account")
    private String drawerAccount;


    @ApiModelProperty(value = "付款人银行名称/受益人银行账户名称")
    @Column(name = "drawer_bank")
    private String drawerBank;


    @ApiModelProperty(value = "收款人主键/申请人主键")
    @Column(name = "payee_rcms_kyc_id")
    private Long payeeRcmsKycId;


    @ApiModelProperty(value = "收款人/申请人")
    @Column(name = "payee_rcms_kyc_company")
    private String payeeRcmsKycCompany;


    @ApiModelProperty(value = "收款人银行账户/申请人银行账户")
    @Column(name = "payee_account")
    private String payeeAccount;


    @ApiModelProperty(value = "收款人银行名称/申请人银行名称")
    @Column(name = "payee_bank")
    private String payeeBank;


    @ApiModelProperty(value = "开立日期")
    @Column(name = "open_time")
    private Date openTime;


    @ApiModelProperty(value = "到期日")
    @Column(name = "bill_due_date")
    private Date billDueDate;


    @ApiModelProperty(value = "天数")
    @Column(name = "limit_day")
    private Integer limitDay;


    @ApiModelProperty(value = "调整天数")
    @Column(name = "adjust_day")
    private Integer adjustDay;


    @ApiModelProperty(value = "最晚交单日")
    @Column(name = "last_time")
    private Date lastTime;


    @ApiModelProperty(value = "资金确认交单")
    @Column(name = "money_submit_order")
    private Integer moneySubmitOrder;


    @ApiModelProperty(value = "资金交单日期")
    @Column(name = "money_submit_order_time")
    private Date moneySubmitOrderTime;


    @ApiModelProperty(value = "执行确认交单")
    @Column(name = "perform_submit_order")
    private Integer performSubmitOrder;


    @ApiModelProperty(value = "执行交单日期")
    @Column(name = "perform_submit_order_time")
    private Date performSubmitOrderTime;


    @ApiModelProperty(value = "承兑日期")
    @Column(name = "acceptance_time")
    private Date acceptanceTime;


    @ApiModelProperty(value = "确认承兑0未承兑1已承兑2确认承兑中3撤消承兑中")
    @Column(name = "confirm_acceptance")
    private Integer confirmAcceptance;


    @ApiModelProperty(value = "币种")
    @Column(name = "currency")
    private String currency;


    @ApiModelProperty(value = "可开金额/开证金额")
    @Column(name = "can_open_amount")
    private BigDecimal canOpenAmount;


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


    @ApiModelProperty(value = "合同金额")
    @Column(name = "contract_amount")
    private BigDecimal contractAmount;


    @ApiModelProperty(value = "已开金额")
    @Column(name = "already_open_amount")
    private BigDecimal alreadyOpenAmount;


    @ApiModelProperty(value = "申请金额")
    @Column(name = "apply_amount")
    private BigDecimal applyAmount;


    @ApiModelProperty(value = "敞口授信")
    @Column(name = "exposure_pledge")
    private Boolean exposurePledge;


    @ApiModelProperty(value = "票据质押")
    @Column(name = "bill_pledge")
    private Boolean billPledge;


    @ApiModelProperty(value = "存款质押")
    @Column(name = "deposit_pledge")
    private Boolean depositPledge;


    @ApiModelProperty(value = "信用证号")
    @Column(name = "credit_no")
    private String creditNo;


    @ApiModelProperty(value = "收票日期")
    @Column(name = "receive_paper_time")
    private Date receivePaperTime;


    @ApiModelProperty(value = "确认议付行/受益人议付行")
    @Column(name = "confirm_negotiation_bank")
    private String confirmNegotiationBank;


    @ApiModelProperty(value = "确认议付行账号/受益人议付行帐号")
    @Column(name = "confirm_negotiation_account")
    private String confirmNegotiationAccount;


    @ApiModelProperty(value = "收款银行")
    @Column(name = "credit_receive_bank")
    private String creditReceiveBank;


    @ApiModelProperty(value = "收款银行帐号")
    @Column(name = "credit_receive_bank_account")
    private String creditReceiveBankAccount;


    @ApiModelProperty(value = "金蝶同步状态")
    @Column(name = "kingdee_sync")
    private Boolean kingdeeSync;


    @ApiModelProperty(value = "金蝶收款/付款单编号")
    @Column(name = "kingdee_no")
    private String kingdeeNo;


    @ApiModelProperty(value = "金蝶同步备注")
    @Column(name = "kingdee_remark")
    private String kingdeeRemark;


    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;


    @ApiModelProperty(value = "状态reject:驳回back:撤回cancel:撤消cancel_wait:撤消审核中return:退回invalid:作废invalid_wait:作废审核中wait:审批中success:已审核init:创建")
    @Column(name = "apply_status")
    private String applyStatus;


    @ApiModelProperty(value = "开立状态")
    @Column(name = "open_status")
    private String openStatus;


    @ApiModelProperty(value = "开立类型:this:自开,other:他开")
    @Column(name = "open_type")
    private String openType;


    @ApiModelProperty(value = "业务员")
    @Column(name = "salesman")
    private String salesman;


    @ApiModelProperty(value = "部门ID")
    @Column(name = "department_id")
    private Long departmentId;


    @ApiModelProperty(value = "部门")
    @Column(name = "department")
    private String department;


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


    @ApiModelProperty(value = "是否删除")
    @Column(name = "deleted")
    private Boolean deleted;


}
