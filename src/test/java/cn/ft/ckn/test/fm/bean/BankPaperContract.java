package cn.ft.ckn.test.fm.bean;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;


@Accessors(chain=true)
@Table(name = "funding_bank_paper_contract")
@Entity
@Data
@ApiModel(value="票据申请关联合同表", description="票据申请关联合同表")
public class BankPaperContract {

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


    @ApiModelProperty(value = "合同号")
    @Column(name = "contract_v2_no")
    private String contractV2No;


    @ApiModelProperty(value = "lot_id")
    @Column(name = "contract_v2_lot_id")
    private Long contractV2LotId;


    @ApiModelProperty(value = "票据申请表主键")
    @Column(name = "funding_bank_paper_apply_id")
    private Long fundingBankPaperApplyId;


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
