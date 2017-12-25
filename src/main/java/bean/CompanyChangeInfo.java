package bean;

import com.alibaba.fastjson.JSON;

/**
 * 公司变更信息
 */
public class CompanyChangeInfo {
    private Long id;

    /** 
     * 公司id
     */
    private Long companyId;

    /** 
     * 变更事项
     */
    private String changeItem;

    /**
     * 变更前内容
     */
    private String beforeChange;

    /**
     * 变更后内容
     */
    private String afterChange;

    /** 
     * 变更日期
     */
    private Long changeTime;

    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getChangeItem() {
        return changeItem;
    }

    public void setChangeItem(String changeItem) {
        this.changeItem = changeItem;
    }

    public String getBeforeChange() {
        return beforeChange;
    }

    public void setBeforeChange(String beforeChange) {
        this.beforeChange = beforeChange;
    }

    public String getAfterChange() {
        return afterChange;
    }

    public void setAfterChange(String afterChange) {
        this.afterChange = afterChange;
    }

    public Long getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Long changeTime) {
        this.changeTime = changeTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString(){return JSON.toJSONString(this);}
}