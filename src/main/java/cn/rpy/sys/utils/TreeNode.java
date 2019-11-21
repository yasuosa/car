package cn.rpy.sys.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    private Integer id;
    @JsonProperty("parentId")
    private Integer pid;



    private String title;
    private String icon;
    private String href;
    private Boolean spread;
    private String target;


    public TreeNode(){}




    //复选树的必要属性
    private String checkArr="0"; //选中1 ;

    public String  getCheckArr() {
        return checkArr;
    }

    public void setCheckArr(String  checkArr) {
        this.checkArr = checkArr;
    }

    /**
     * 给dtree的复选树用的
     * @param id
     * @param pid
     * @param title
     * @param spread
     * @param checkArr
     */
    public TreeNode(Integer id, Integer pid, String title, Boolean spread, String  checkArr) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.spread = spread;
        this.checkArr = checkArr;
    }

    /**
     * 左边导航树的 节点构造器
     * @param id
     * @param pid
     * @param title
     * @param icon
     * @param href
     * @param spread
     * @param target
     */
    public TreeNode(Integer id, Integer pid, String title, String icon, String href, Boolean spread, String target) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
        this.target = target;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    private List<TreeNode> children=new ArrayList<>();

}
