package bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zoufq on 2017/10/30.
 */
public class BaseInfo {
    /**
     * 店铺id
     */
    private String id;

    /**
     * 店铺链接
     */
    private String url;

    /**
     * 店铺名称
     */
    private String sname;

    /**
     * 一级分类
     */
    private String firclass;

    /**
     * 二级分类
     */
    private String secclass;

    /**
     * 城市名称
     */
    private String city;

    /**
     * 行政区
     */
    private String region;

    /**
     * 评论数
     */
    private Integer comment;

    /**
     * 人均消费
     */
    private BigDecimal expense;

    /**
     * 口味评分
     */
    private BigDecimal kouwei;

    /**
     * 环境评分
     */
    private BigDecimal env;

    /**
     * 服务评分
     */
    private BigDecimal server;

    /**
     * 地址
     */
    private String addr;

    /**
     * 电话
     */
    private String phone;

    /**
     * 商户星级
     */
    private Integer star;

    /**
     * 特色
     */
    private String tese;

    /**
     * 抓取时间
     */
    private Date crawltime;

    /**
     * 其他:停车信息、营业时间等
     */
    private String other;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getFirclass() {
        return firclass;
    }

    public void setFirclass(String firclass) {
        this.firclass = firclass == null ? null : firclass.trim();
    }

    public String getSecclass() {
        return secclass;
    }

    public void setSecclass(String secclass) {
        this.secclass = secclass == null ? null : secclass.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    public BigDecimal getKouwei() {
        return kouwei;
    }

    public void setKouwei(BigDecimal kouwei) {
        this.kouwei = kouwei;
    }

    public BigDecimal getEnv() {
        return env;
    }

    public void setEnv(BigDecimal env) {
        this.env = env;
    }

    public BigDecimal getServer() {
        return server;
    }

    public void setServer(BigDecimal server) {
        this.server = server;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getTese() {
        return tese;
    }

    public void setTese(String tese) {
        this.tese = tese == null ? null : tese.trim();
    }

    public Date getCrawltime() {
        return crawltime;
    }

    public void setCrawltime(Date crawltime) {
        this.crawltime = crawltime;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }
}
