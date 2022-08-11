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
@Table(name = "funding_bank_paper_open")
@Entity
@Data
@ApiModel(value="票据开立表", description="票据开立表")
public class BankPaperOpen {

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


    @ApiModelProperty(value = "申请表主键")
    @Column(name = "funding_bank_paper_apply_id")
    private Long fundingBankPaperApplyId;


    @ApiModelProperty(value = "状态reject:驳回back:撤回cancel:撤消cancel_wait:撤消审核中return:退回invalid:作废invalid_wait:作废审核中wait:审批中success:已审核init:创建")
    @Column(name = "open_status")
    private String openStatus;


    @ApiModelProperty(value = "开立金额")
    @Column(name = "open_amount")
    private BigDecimal openAmount;


    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;


    @ApiModelProperty(value = "金蝶同步状态")
    @Column(name = "kingdee_sync")
    private Boolean kingdeeSync;


    @ApiModelProperty(value = "金蝶错误备注")
    @Column(name = "kingdee_remark")
    private String kingdeeRemark;


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
