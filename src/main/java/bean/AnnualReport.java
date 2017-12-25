package bean;

import com.alibaba.fastjson.JSON;

/**
 * 企业年报
 */
public class AnnualReport {
    private Long id;

    /** 
     * 公司id
     */
    private Long companyId;

    /** 
     * 年报年度
     */
    private String nbnd;

    /** 
     * 企业名称
     */
    private String name;

    /** 
     * 统一社会信用代码
     */
    private String tyshxydm;

    /** 
     * 注册号
     */
    private String zch;

    /** 
     * 企业联系电话
     */
    private String phone;

    /** 
     * 邮政编码
     */
    private String postcode;

    /** 
     * 企业通信地址
     */
    private String addr;

    /** 
     * 电子邮箱
     */
    private String email;

    /** 
     * 企业经营状态
     */
    private String qyjyzt;

    /** 
     * 从业人数
     */
    private String cyrs;

    /** 
     * 经营者名称
     */
    private String jyzmc;

    /** 
     * 资产总额
     */
    private String zcze;

    /** 
     * 所有者权益合计
     */
    private String syzqyhj;

    /** 
     * 销售总额(营业总收入)
     */
    private String xsze;

    /** 
     * 利润总额
     */
    private String lrze;

    /** 
     * 主营业务收入
     */
    private String zyywsr;

    /** 
     * 净利润
     */
    private String jlr;

    /** 
     * 纳税总额
     */
    private String nsze;

    /** 
     * 负债总额
     */
    private String fzze;

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

    public String getNbnd() {
        return nbnd;
    }

    public void setNbnd(String nbnd) {
        this.nbnd = nbnd == null ? null : nbnd.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTyshxydm() {
        return tyshxydm;
    }

    public void setTyshxydm(String tyshxydm) {
        this.tyshxydm = tyshxydm == null ? null : tyshxydm.trim();
    }

    public String getZch() {
        return zch;
    }

    public void setZch(String zch) {
        this.zch = zch == null ? null : zch.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQyjyzt() {
        return qyjyzt;
    }

    public void setQyjyzt(String qyjyzt) {
        this.qyjyzt = qyjyzt == null ? null : qyjyzt.trim();
    }

    public String getCyrs() {
        return cyrs;
    }

    public void setCyrs(String cyrs) {
        this.cyrs = cyrs == null ? null : cyrs.trim();
    }

    public String getJyzmc() {
        return jyzmc;
    }

    public void setJyzmc(String jyzmc) {
        this.jyzmc = jyzmc == null ? null : jyzmc.trim();
    }

    public String getZcze() {
        return zcze;
    }

    public void setZcze(String zcze) {
        this.zcze = zcze == null ? null : zcze.trim();
    }

    public String getSyzqyhj() {
        return syzqyhj;
    }

    public void setSyzqyhj(String syzqyhj) {
        this.syzqyhj = syzqyhj == null ? null : syzqyhj.trim();
    }

    public String getXsze() {
        return xsze;
    }

    public void setXsze(String xsze) {
        this.xsze = xsze == null ? null : xsze.trim();
    }

    public String getLrze() {
        return lrze;
    }

    public void setLrze(String lrze) {
        this.lrze = lrze == null ? null : lrze.trim();
    }

    public String getZyywsr() {
        return zyywsr;
    }

    public void setZyywsr(String zyywsr) {
        this.zyywsr = zyywsr == null ? null : zyywsr.trim();
    }

    public String getJlr() {
        return jlr;
    }

    public void setJlr(String jlr) {
        this.jlr = jlr == null ? null : jlr.trim();
    }

    public String getNsze() {
        return nsze;
    }

    public void setNsze(String nsze) {
        this.nsze = nsze == null ? null : nsze.trim();
    }

    public String getFzze() {
        return fzze;
    }

    public void setFzze(String fzze) {
        this.fzze = fzze == null ? null : fzze.trim();
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