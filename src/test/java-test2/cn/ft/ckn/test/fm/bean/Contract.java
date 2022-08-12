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
@Table(name = "contract")
@Entity
@Data
@ApiModel(value="合同", description="合同")
public class Contract {

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


    @ApiModelProperty(value = "外部合同号")
    @Column(name = "external_no")
    private String externalNo;


    @ApiModelProperty(value = "合同类型贸易合同,服务合同,形式合同")
    @Column(name = "contract_type")
    private String contractType;


    @ApiModelProperty(value = "交易对手")
    @Column(name = "kyc_ids")
    private String kycIds;


    @ApiModelProperty(value = "合同年份")
    @Column(name = "contract_year")
    private Date contractYear;


    @ApiModelProperty(value = "签定日期")
    @Column(name = "sign_date")
    private Date signDate;


    @ApiModelProperty(value = "签署地点")
    @Column(name = "sign_place")
    private String signPlace;


    @ApiModelProperty(value = "合同模板提供方对方提供,我方提供")
    @Column(name = "deal_template_provider")
    private String dealTemplateProvider;


    @ApiModelProperty(value = "所属长单合同")
    @Column(name = "pid")
    private Long pid;


    @ApiModelProperty(value = "完成状态")
    @Column(name = "complete_status")
    private String completeStatus;


    @ApiModelProperty(value = "合约是否存在差异")
    @Column(name = "contract_difference")
    private Boolean contractDifference;


    @ApiModelProperty(value = "合同状态")
    @Column(name = "status")
    private String status;


    @ApiModelProperty(value = "旧状态")
    @Column(name = "old_status")
    private String oldStatus;


    @ApiModelProperty(value = "合同双签状态")
    @Column(name = "double_sign_status")
    private String doubleSignStatus;


    @ApiModelProperty(value = "终止状态")
    @Column(name = "stop_status")
    private String stopStatus;


    @ApiModelProperty(value = "关闭状态")
    @Column(name = "close_status")
    private String closeStatus;


    @ApiModelProperty(value = "取消状态")
    @Column(name = "cancel_status")
    private String cancelStatus;


    @ApiModelProperty(value = "合同取消双签状态")
    @Column(name = "cancel_double_sign_status")
    private String cancelDoubleSignStatus;


    @ApiModelProperty(value = "结算状态")
    @Column(name = "settled")
    private Boolean settled;


    @ApiModelProperty(value = "记录原合同ID：当合同类型为内部合同时，采购内部合同记录原销售内部合同ID。")
    @Column(name = "parent_id")
    private Long parentId;


    @ApiModelProperty(value = "记录内容")
    @Column(name = "remark")
    private String remark;


    @ApiModelProperty(value = "是否为合同变更[1：否；2：是]")
    @Column(name = "change_distinguish")
    private Integer changeDistinguish;


    @ApiModelProperty(value = "使用组织")
    @Column(name = "use_orgs")
    private String useOrgs;


    @ApiModelProperty(value = "创建者所在组织")
    @Column(name = "create_org")
    private Long createOrg;


    @ApiModelProperty(value = "创建人")
    @Column(name = "create_by")
    private String createBy;


    @ApiModelProperty(value = "更新人")
    @Column(name = "update_by")
    private String updateBy;


    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Date createTime;


    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    private Date updateTime;


    @ApiModelProperty(value = "是否删除")
    @Column(name = "deleted")
    private Boolean deleted;


}
