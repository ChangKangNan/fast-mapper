package cn.ft.ckn.fastmapper.fm.bean;
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
@Table(name = "bz_open_bill")
@Entity
@Data
@ApiModel(value="开立票据申请(包括票据,信用证)", description="开立票据申请(包括票据,信用证)")
public class OpenBill {

    @Id
    @ApiModelProperty(value = "")
    @Column(name = "id")
    private Long id;


    @ApiModelProperty(value = "组织机构id,哪个机构开的传哪个机构id")
    @Column(name = "org_id")
    private Long orgId;


    @ApiModelProperty(value = "申请单号,银、商票：申请编号信用证：申请编号")
    @Column(name = "apply_no")
    private String applyNo;


    @ApiModelProperty(value = "开立单号:KLZX（开立执行缩写）+日期+编号")
    @Column(name = "bill_no")
    private String billNo;


    @ApiModelProperty(value = "开立类型,1020,银票、1021,商票、1022,信用证")
    @Column(name = "bill_type")
    private Integer billType;


    @ApiModelProperty(value = "开立进度:1030待开立、1031部分开立、1032已撤销、1033已作废、1034已完成")
    @Column(name = "process")
    private Integer process;


    @ApiModelProperty(value = "关联合同:业务系统提交申请信息中关联的合同编号")
    @Column(name = "contract_no")
    private String contractNo;


    @ApiModelProperty(value = "我方账户,id")
    @Column(name = "our_account_id")
    private Long ourAccountId;


    @ApiModelProperty(value = "我方账户,展示银行账户【账号备注】筛选读取当前组织机构下的银行账户")
    @Column(name = "our_account_remark")
    private String ourAccountRemark;


    @ApiModelProperty(value = "对方账户,id")
    @Column(name = "kyc_id")
    private Long kycId;


    @ApiModelProperty(value = "对方户名;全部KYC类型数据（IMS获取+组织机构）")
    @Column(name = "kyc_account_name")
    private String kycAccountName;


    @ApiModelProperty(value = "结算币种")
    @Column(name = "currency")
    private String currency;


    @ApiModelProperty(value = "申请金额,票据金额")
    @Column(name = "apply_amount")
    private BigDecimal applyAmount;


    @ApiModelProperty(value = "待办金额,申请金额-已开票据/信用证金额（过滤已作废状态）")
    @Column(name = "leave_amount")
    private BigDecimal leaveAmount;


    @ApiModelProperty(value = "申请人,员工账号,业务系统接口给出")
    @Column(name = "staff_account")
    private String staffAccount;


    @ApiModelProperty(value = "申请人,员工姓名,业务系统接口给出")
    @Column(name = "staff_name")
    private String staffName;


    @ApiModelProperty(value = "申请日期,精确到日,业务系统给出")
    @Column(name = "apply_date")
    private Date applyDate;


    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;


    @ApiModelProperty(value = "手续费扣款单编号")
    @Column(name = "fee_deduction_no")
    private String feeDeductionNo;


    @ApiModelProperty(value = "手续费扣款单id")
    @Column(name = "fee_deduction_id")
    private Long feeDeductionId;


    @ApiModelProperty(value = "质押信息,本次迭代暂不涉及（仅做表格展示）")
    @Column(name = "impawn")
    private String impawn;


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


}
